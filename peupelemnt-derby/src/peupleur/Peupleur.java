package peupleur;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modele.Action;
import modele.Role;
import modele.Utilisateur;

public class Peupleur {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("peupleur");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Utilisateur u01 = new Utilisateur("durand.benoit@hotmail.fr", "durand", "Durand", "Benoit",  new Date(), "Operator: 15648");
		Utilisateur u02 = new Utilisateur("chapnat@free.fr","Chapuis","Chapuis", "Natalie", new Date(), "Operator: 15600 (professionel)");
		Utilisateur u03 = new Utilisateur("jamie.ghst@gmail.com", "ghost","St. Patrick", "James",  new Date(), "Operator: 15777");
		
		Action a01 = new Action("Modifier", "Modifier une anonce" , new Date(), "Operator: 15648");
		Action a02 = new Action("Supprimer", "Supprimer une annonce", new Date(), "Operator: 15648");
		Action a03 = new Action("Rechercher", "Rechercher une guitare", new Date(), "Operator: 15648");
		Action a04 = new Action("Accepter", "Accepter une guitare en depot-vente", new Date(), "Operator: 15648");
		
		Role r01 = new Role("Anonceur", "Client anonceur", new Date(), "Operator: 15648");
		Role r02 = new Role("Magasin", "Client magasin", new Date(), "Operator: 15648");
		Role r03 = new Role("Administrateur", "Client admin", new Date(), "Operator: 15648");
		
		r01.ajoutAction(a01);
		r01.ajoutAction(a02);
		r02.ajoutAction(a03);
		r03.ajoutAction(a04);
		
		r01.ajoutUtilisateur(u01);
		r02.ajoutUtilisateur(u02);
		r02.ajoutUtilisateur(u03);
		
		em.persist(r01);
		em.persist(r02);
		em.persist(r03);
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
