package outils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modele.Utilisateur;
import outils.ResultatCreationUtilisateur;

public class ExtracteurConstructeur extends DefaultHandler {

	private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static Utilisateur utilisateur = null;
    private static String chaine = null;
    private ResultatCreationUtilisateur resultat;    
    
    public ExtracteurConstructeur(ResultatCreationUtilisateur resultat) {
		super();
		this.resultat = resultat;		
	}

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    
    	switch (qName) {
                      		case "utilisateur": 	{
                      								utilisateur = new Utilisateur();
                      								break;
                      								}
                 		}
    }
        
   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
   
	   switch (qName) {
                      	case "utilisateur": 	{                       
                      							utilisateur.setDerniere_mise_a_jour(new Date());
                      							utilisateur.setModifie_par("default-admin");
                      							utilisateurs.add(utilisateur);
                      							break;
                      							}
                      	case "email": 			{
                      							utilisateur.setEmail(chaine);
                      							break;
                      							}
                      	case "nom": 			{
                      							utilisateur.setNom(chaine);
                      							break;
                      							}
                      	case "prenom": 			{
                      							utilisateur.setPrenom(chaine);
                      							break;
                      							}
                      	case "password": 		{
                      							utilisateur.setPassword(chaine);
                      							break;
                      							}
                 	}
             }
         
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
                  chaine = String.copyValueOf(ch, start, length).trim();
    }
    
    public void endDocument() throws SAXException {
		System.out.println("Fin de l'extraction");
		resultat.setUtilisateurs(utilisateurs);
	}	
}
