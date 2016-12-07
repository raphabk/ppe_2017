package composants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import ressources.MessageDTO;

@ManagedBean(name="auth")
@RequestScoped
public class AuthentificationGUI {

	private String email;
	private String password;
	private String message;
	
	
	public AuthentificationGUI() {
		super();
	}
	
	public void controler() {
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatauthentificateur:8080/authentificateur"));
		WebTarget ciblefinale = cible.path("dto");
		MessageDTO dto = ciblefinale.queryParam("email", email).queryParam("password", password)
				.request(MediaType.APPLICATION_XML).get(MessageDTO.class);
		message = dto.getBienvenue()+dto.getRole();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}