package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "UTILISATEUR_ID_GENERATOR", sequenceName = "UTILISATEUR_ID_SEQ", allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UTILISATEUR_ID_GENERATOR")
  private Integer id;

  private Integer cap;

  private String description;

  private String email;

  private float latitude;

  private float longitude;

  @Column(name = "mot_de_passe")
  private String motDePasse;

  private String nom;

  @Column(name = "partage_position")
  private Boolean partagePosition;

  @Column(name = "partage_position_public")
  private Boolean partagePositionPublic;

  private String prenom;

  @Column(name = "url_avatar")
  private String urlAvatar;

  private Integer vitesse;

  // bi-directional many-to-one association to Commentaire
  @OneToMany(mappedBy = "utilisateurBean", fetch = FetchType.EAGER)
  private List<Commentaire> commentaires;

  // bi-directional many-to-many association to Utilisateur
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "est_ami", joinColumns =
  { @JoinColumn(name = "utilisateur_cible") }, inverseJoinColumns =
  { @JoinColumn(name = "utilisateur_source") })
  private List<Utilisateur> utilisateurs1;

  // bi-directional many-to-many association to Utilisateur
  @ManyToMany(mappedBy = "utilisateurs1", fetch = FetchType.EAGER)
  private List<Utilisateur> utilisateurs2;

  public Utilisateur()
  {}

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Integer getCap()
  {
    return this.cap;
  }

  public void setCap(Integer cap)
  {
    this.cap = cap;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public float getLatitude()
  {
    return this.latitude;
  }

  public void setLatitude(float latitude)
  {
    this.latitude = latitude;
  }

  public float getLongitude()
  {
    return this.longitude;
  }

  public void setLongitude(float longitude)
  {
    this.longitude = longitude;
  }

  public String getMotDePasse()
  {
    return this.motDePasse;
  }

  public void setMotDePasse(String motDePasse)
  {
    this.motDePasse = motDePasse;
  }

  public String getNom()
  {
    return this.nom;
  }

  public void setNom(String nom)
  {
    this.nom = nom;
  }

  public Boolean getPartagePosition()
  {
    return this.partagePosition;
  }

  public void setPartagePosition(Boolean partagePosition)
  {
    this.partagePosition = partagePosition;
  }

  public Boolean getPartagePositionPublic()
  {
    return this.partagePositionPublic;
  }

  public void setPartagePositionPublic(Boolean partagePositionPublic)
  {
    this.partagePositionPublic = partagePositionPublic;
  }

  public String getPrenom()
  {
    return this.prenom;
  }

  public void setPrenom(String prenom)
  {
    this.prenom = prenom;
  }

  public String getUrlAvatar()
  {
    return this.urlAvatar;
  }

  public void setUrlAvatar(String urlAvatar)
  {
    this.urlAvatar = urlAvatar;
  }

  public Integer getVitesse()
  {
    return this.vitesse;
  }

  public void setVitesse(Integer vitesse)
  {
    this.vitesse = vitesse;
  }

  public List<Commentaire> getCommentaires()
  {
    return this.commentaires;
  }

  public void setCommentaires(List<Commentaire> commentaires)
  {
    this.commentaires = commentaires;
  }

  public Commentaire addCommentaire(Commentaire commentaire)
  {
    getCommentaires().add(commentaire);
    commentaire.setUtilisateurBean(this);

    return commentaire;
  }

  public Commentaire removeCommentaire(Commentaire commentaire)
  {
    getCommentaires().remove(commentaire);
    commentaire.setUtilisateurBean(null);

    return commentaire;
  }

  public List<Utilisateur> getUtilisateurs1()
  {
    return this.utilisateurs1;
  }

  public void setUtilisateurs1(List<Utilisateur> utilisateurs1)
  {
    this.utilisateurs1 = utilisateurs1;
  }

  public List<Utilisateur> getUtilisateurs2()
  {
    return this.utilisateurs2;
  }

  public void setUtilisateurs2(List<Utilisateur> utilisateurs2)
  {
    this.utilisateurs2 = utilisateurs2;
  }

}