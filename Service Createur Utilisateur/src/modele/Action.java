package modele;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ACTION")
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idaction;
	@Column(nullable = false)
	private String action;
	@Column(nullable = false)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date derniere_mise_a_jour;
	@Column(nullable = false)
	private String modifie_par;
	@ManyToOne
	@JoinColumn(name = "ROLE_FK")
	private Role role;

	public Action() {
		super();
	}

	public Action(String action, String description, Date derniere_mise_a_jour, String modifie_par) {
		super();
		this.action = action;
		this.description = description;
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifie_par = modifie_par;
	}

	public int getIdaction() {
		return idaction;
	}

	public void setIdaction(int idaction) {
		this.idaction = idaction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDerniere_mise_a_jour() {
		return derniere_mise_a_jour;
	}

	public void setDerniere_mise_a_jour(Date derniere_mise_a_jour) {
		this.derniere_mise_a_jour = derniere_mise_a_jour;
	}

	public String getModifie_par() {
		return modifie_par;
	}

	public void setModifie_par(String modifie_par) {
		this.modifie_par = modifie_par;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
