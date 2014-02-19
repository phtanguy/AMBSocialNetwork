package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
public class Service implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @Column(insertable = false, updatable = false, nullable = false)
  private Integer id;

  private String description;

  //private Object type;
  private String type;

  // bi-directional one-to-one association to Point
  @OneToOne
  @JoinColumn(name = "id")
  private Point point;

  public Service() {}

  public Integer getId()          { return this.id;          }
  //public Object  getType()        { return this.type;        }
  public String  getType()        { return this.type;        }
  public String  getDescription() { return this.description; }
  public Point   getPoint()       { return this.point;       }

  public void setId(Integer id)                  { this.id          = id;          }
  public void setType(String type)               { this.type        = type;        }
  public void setDescription(String description) { this.description = description; }
  public void setPoint(Point point)              { this.point       = point;       }

}