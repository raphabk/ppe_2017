package composants;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@ManagedBean(name="bean")
@RequestScoped
public class ComposantUploadeur {

	private String fileContent;
	private Part file;
	private String resultat = " ";

	public ComposantUploadeur() {
		super();
	}
	
	public String upload() {
		if(file == null)
			return "index.xhtml";
		try {				
				file.write("/tmp/" + file.getSubmittedFileName());
				byte[] flux = Files.readAllBytes(Paths.get("/tmp/"+file.getSubmittedFileName()));
				boolean statut = transfert(new String(flux, Charset.defaultCharset()));
				if (statut) resultat = "Le chargement a réussi";
				else resultat = "échec du chargement";
				File lefichier = new File("/tmp/"+file.getSubmittedFileName());
				lefichier.delete();
		} catch (IOException e) { e.printStackTrace();
			resultat = "échec du chargement";
		}
		return "confirmation.xhtml";
	}
	
	private boolean transfert(String xml) {
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatcreateurutilisateur:8080/ppe-creation-utilisateurs-serveur"));
		WebTarget ciblefinale = cible.path("xml");
		ciblefinale.request(MediaType.TEXT_PLAIN_TYPE).post(Entity.entity(xml, MediaType.TEXT_PLAIN));
		return true;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}