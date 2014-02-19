package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the point database table.
 * 
 */
@Entity
@NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p")
public class Point implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "POINT_ID_GENERATOR", sequenceName = "POINT_ID_SEQ", allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINT_ID_GENERATOR")
  private Integer id;

  private String latitude;

  private String longitude;

  // bi-directional many-to-one association to Commentaire
  @OneToMany(mappedBy = "pointBean", fetch = FetchType.EAGER)
  private List<Commentaire> commentaires;

  // bi-directional one-to-one association to Poi
  @OneToOne(mappedBy = "point")
  private Poi poi;

  // bi-directional one-to-one association to Service
  @OneToOne(mappedBy = "point")
  private Service service;

  public Point() {}

  public Integer           getId()           { return this.id;           }
  public String            getLatitude()     { return this.latitude;     }
  public String            getLongitude()    { return this.longitude;    }
  public List<Commentaire> getCommentaires() { return this.commentaires; }
  public Poi               getPoi()          { return this.poi;          }
  public Service           getService()      { return this.service;      }

  public void setId(Integer id)                               { this.id           = id;           }
  public void setLatitude(String latitude)                    { this.latitude     = latitude;     }
  public void setLongitude(String longitude)                  { this.longitude    = longitude;    }
  public void setCommentaires(List<Commentaire> commentaires) { this.commentaires = commentaires; }
  public void setPoi(Poi poi)                                 { this.poi          = poi;          }
  public void setService(Service service)                     { this.service      = service;      }

  public Commentaire addCommentaire(Commentaire commentaire)
  {
    getCommentaires().add(commentaire);
    commentaire.setPointBean(this);

    return commentaire;
  }

  public Commentaire removeCommentaire(Commentaire commentaire)
  {
    getCommentaires().remove(commentaire);
    commentaire.setPointBean(null);

    return commentaire;
  }

}