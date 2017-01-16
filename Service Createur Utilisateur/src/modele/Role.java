package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrole;
	@Column(nullable = false)
	private String role;
	@Column(nullable = false)
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date derniere_mise_a_jour;
	@Column(nullable = false)
	private String modifie_par;
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Utilisateur> utilisateurs = new ArrayList<>();
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Action> actions = new ArrayList<>();

	public void ajoutAction(Action action) {
		actions.add(action);
		action.setRole(this);
	}
	
	public void ajoutUtilisateur(Utilisateur utilisateur) {
		utilisateurs.add(utilisateur);
		utilisateur.setRole(this);
	}
	
	public Role() {
		super();
	}

	public Role(String role, String description, Date derniere_mise_a_jour, String modifie_par) {
		super();
		this.role = role;
		this.description = description;
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifie_par = modifie_par;
	}

	public int getIdrole() {
		return idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

}
