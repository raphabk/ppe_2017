package outils;

import java.util.List;

import javax.persistence.EntityManager;

import modele.Role;
import modele.Utilisateur;
import ressources.FournisseurDePersistance;

public class ResultatCreationUtilisateur {

	private List<Utilisateur> utilisateurs;

	public ResultatCreationUtilisateur() {
		super();
	}	
	
	public boolean enregistrer() {
		EntityManager em = null;
		try {
				em = FournisseurDePersistance.getInstance().fournir();
				em.getTransaction().begin();
				Role rolemagasin = (Role) em.createNativeQuery("select * from role where role='magasin'", Role.class).getSingleResult();
				for(Utilisateur ref : utilisateurs) {
					rolemagasin.ajoutUtilisateur(ref);
				}
				em.getTransaction().commit();
		return true;		
		} catch (Exception e) {e.printStackTrace(); return false; }
		finally {
			em.close();
		}		
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	
}
