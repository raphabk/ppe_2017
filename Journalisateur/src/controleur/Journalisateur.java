package controleur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@ManagedBean(name="journalisateur", eager=true)
@ApplicationScoped
public class Journalisateur {
	
	private final static String QUEUE_NAME = "journal";
	private String message;
	
	@PostConstruct
	public void initialisation() {
		try {
				consommer();
		} catch (Exception e) {	e.printStackTrace(); }
	}
	
	public void consommer() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbitmq");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);	    
	    Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          message = new String(body, "UTF-8");
	        }
	      };
	      channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	private void journaliser() {
		// todo : accès mongodb et persistance d'une instance de Journal
	}
	
	public Journalisateur() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}		
}