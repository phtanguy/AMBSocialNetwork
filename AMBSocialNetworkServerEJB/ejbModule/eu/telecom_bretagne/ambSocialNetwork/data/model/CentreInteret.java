package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the centre_interet database table.
 * 
 */
@Entity
@Table(name="centre_interet")
@NamedQuery(name="CentreInteret.findAll", query="SELECT c FROM CentreInteret c")
public class CentreInteret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CENTRE_INTERET_ID_GENERATOR", sequenceName="CENTRE_INTERET_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CENTRE_INTERET_ID_GENERATOR")
	private Integer id;

	private String description;

	private String latitude;

	private String longitude;

	private String nom;

	//bi-directional many-to-one association to Commentaire
	@OneToMany(mappedBy="centreInteretBean")
	private List<Commentaire> commentaires;

	public CentreInteret() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Commentaire> getCommentaires() {
		return this.commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Commentaire addCommentaire(Commentaire commentaire) {
		getCommentaires().add(commentaire);
		commentaire.setCentreInteretBean(this);

		return commentaire;
	}

	public Commentaire removeCommentaire(Commentaire commentaire) {
		getCommentaires().remove(commentaire);
		commentaire.setCentreInteretBean(null);

		return commentaire;
	}

  @Override
  public String toString()
  {
    return "CentreInteret [id=" + id + ", description=" + description
        + ", latitude=" + latitude + ", longitude=" + longitude + ", nom="
        + nom + "]";
  }

}