package modele;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

@Entity
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Journal implements Serializable {

	@Id
	@GeneratedValue
	private String id;
	@Basic
	private String email;
	@Basic
	private String utilisateur;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateacces;
	@Basic
	private String statut;

	public Journal() {
		super();
	}

	public Journal(String email, String utilisateur, Date dateacces, String statut) {
		super();
		this.email = email;
		this.utilisateur = utilisateur;
		this.dateacces = dateacces;
		this.statut = statut;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDateacces() {
		return dateacces;
	}

	public void setDateacces(Date dateacces) {
		this.dateacces = dateacces;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

}