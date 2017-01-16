package controleur;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import modele.Journal;


@WebServlet(urlPatterns="/*",loadOnStartup=1)
public class Journalisateur extends HttpServlet {

	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("journal");
	private final static String QUEUE_NAME = "journal-des-authentifications";
	private String message;
	private Connection connection = null;
	private Channel channel = null;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
				consommer();
		} catch (Exception e) {e.printStackTrace();
		}
	}
	
	public void service(HttpServletRequest req, HttpServletResponse rep) {		
	}
	
	public void consommer() throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbitmq");
	    connection = factory.newConnection();
	    channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);	
	    
	    boolean autoAck = false;
	    channel.basicConsume(QUEUE_NAME, autoAck, "journalisateur",
	         new DefaultConsumer(channel) {
	             @Override
	             public void handleDelivery(String consumerTag,
	                                        Envelope envelope,
	                                        AMQP.BasicProperties properties,
	                                        byte[] body)
	                 throws IOException
	             {
	                 String routingKey = envelope.getRoutingKey();
	                 String contentType = properties.getContentType();
	                 long deliveryTag = envelope.getDeliveryTag();
	                 
	                 message = new String(body, "UTF-8");
	                 System.out.println(message);
	   	          	 journaliser();
	                 channel.basicAck(deliveryTag, false);
	             }
	         });	    
	}
	
	private void journaliser() {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String donneesMembres[] = StringUtils.split(message, "|");
		Journal journal = new Journal();
		journal.setEmail(donneesMembres[0]);
		journal.setUtilisateur(donneesMembres[1]);
		journal.setStatut(donneesMembres[2]);
		journal.setDateacces(new Date());		
		em.persist(journal);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public Journalisateur() {
		super();
	}
	
	public void destroy() {
		try {
				channel.basicCancel("journalisateur");
				channel.close();
				connection.close();
		} catch (IOException e) {e.printStackTrace();
		} catch (TimeoutException e) {e.printStackTrace();
		}		
		emf.close();
	}

}