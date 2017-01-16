package serviceREST;



import java.io.IOException;
import java.io.StringReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import outils.ExtracteurConstructeur;
import outils.ResultatCreationUtilisateur;

@Path("xml")
public class ServiceREST {	
	
	@POST
	@Consumes("text/plain")
	public Response postXML(String xml) {
		if (creer(xml)) return Response.status(200).build();
		return Response.status(500).build();
	}
	
	private boolean creer(String xml) {
		boolean statut = false;
		ResultatCreationUtilisateur resultat = new ResultatCreationUtilisateur();
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser;		
		try {
				parser = parserFactor.newSAXParser();		
				ExtracteurConstructeur extracteur = new ExtracteurConstructeur(resultat);
				parser.parse(new InputSource(new StringReader(xml)), extracteur);
				resultat.enregistrer();
				resultat.getUtilisateurs().clear();
				statut= true;
		} catch (SAXException | IOException e) {e.printStackTrace();
		} catch (ParserConfigurationException e) {e.printStackTrace();
		}		
		return statut;
	}	
}
