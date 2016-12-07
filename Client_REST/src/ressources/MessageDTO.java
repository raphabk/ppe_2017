package ressources;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageDTO {

	private String bienvenue;
	private String role;

	public MessageDTO() {
		super();
	}

	public MessageDTO(String bienvenue, String role) {
		super();
		this.bienvenue = bienvenue;
		this.role = role;
	}

	public String getBienvenue() {
		return bienvenue;
	}

	public void setBienvenue(String bienvenue) {
		this.bienvenue = bienvenue;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}