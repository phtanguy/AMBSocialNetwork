package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the commentaire database table.
 * 
 */
public class Commentaire implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String contenu;

	private Timestamp datePublication;

	private Boolean partageCommentaire;

	private Boolean partageCommentairePublic;

	private String urlPhoto;

	private CentreInteret centreInteretBean;

	private Utilisateur utilisateurBean;

	public Commentaire() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Timestamp getDatePublication() {
		return this.datePublication;
	}

	public void setDatePublication(Timestamp datePublication) {
		this.datePublication = datePublication;
	}

	public Boolean getPartageCommentaire() {
		return this.partageCommentaire;
	}

	public void setPartageCommentaire(Boolean partageCommentaire) {
		this.partageCommentaire = partageCommentaire;
	}

	public Boolean getPartageCommentairePublic() {
		return this.partageCommentairePublic;
	}

	public void setPartageCommentairePublic(Boolean partageCommentairePublic) {
		this.partageCommentairePublic = partageCommentairePublic;
	}

	public String getUrlPhoto() {
		return this.urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public CentreInteret getCentreInteretBean() {
		return this.centreInteretBean;
	}

	public void setCentreInteretBean(CentreInteret centreInteretBean) {
		this.centreInteretBean = centreInteretBean;
	}

	public Utilisateur getUtilisateurBean() {
		return this.utilisateurBean;
	}

	public void setUtilisateurBean(Utilisateur utilisateurBean) {
		this.utilisateurBean = utilisateurBean;
	}

}