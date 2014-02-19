package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the commentaire database table.
 * 
 */
@Entity
@NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c")
public class Commentaire implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "COMMENTAIRE_ID_GENERATOR", sequenceName = "COMMENTAIRE_ID_SEQ", allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENTAIRE_ID_GENERATOR")
  private Integer id;

  private String contenu;

  @Column(name = "date_publication")
  private Timestamp datePublication;

  @Column(name = "partage_commentaire_public")
  private Boolean partageCommentairePublic;

  @Column(name = "url_photo")
  private String urlPhoto;

  // bi-directional many-to-one association to Point
  @ManyToOne
  @JoinColumn(name = "point")
  private Point pointBean;

  // bi-directional many-to-one association to Utilisateur
  @ManyToOne
  @JoinColumn(name = "utilisateur")
  private Utilisateur utilisateurBean;

  public Commentaire()
  {}

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getContenu()
  {
    return this.contenu;
  }

  public void setContenu(String contenu)
  {
    this.contenu = contenu;
  }

  public Timestamp getDatePublication()
  {
    return this.datePublication;
  }

  public void setDatePublication(Timestamp datePublication)
  {
    this.datePublication = datePublication;
  }

  public Boolean getPartageCommentairePublic()
  {
    return this.partageCommentairePublic;
  }

  public void setPartageCommentairePublic(Boolean partageCommentairePublic)
  {
    this.partageCommentairePublic = partageCommentairePublic;
  }

  public String getUrlPhoto()
  {
    return this.urlPhoto;
  }

  public void setUrlPhoto(String urlPhoto)
  {
    this.urlPhoto = urlPhoto;
  }

  public Point getPointBean()
  {
    return this.pointBean;
  }

  public void setPointBean(Point pointBean)
  {
    this.pointBean = pointBean;
  }

  public Utilisateur getUtilisateurBean()
  {
    return this.utilisateurBean;
  }

  public void setUtilisateurBean(Utilisateur utilisateurBean)
  {
    this.utilisateurBean = utilisateurBean;
  }

}