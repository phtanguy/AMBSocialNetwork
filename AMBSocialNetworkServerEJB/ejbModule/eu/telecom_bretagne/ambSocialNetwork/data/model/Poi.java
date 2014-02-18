package eu.telecom_bretagne.ambSocialNetwork.data.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the poi database table.
 * 
 */
@Entity
@NamedQuery(name = "Poi.findAll", query = "SELECT p FROM Poi p")
public class Poi implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "POI_ID_GENERATOR", sequenceName = "POI_ID_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POI_ID_GENERATOR")
  private Integer id;

  //private Object type;
  private String type;

  // bi-directional one-to-one association to Point
  @OneToOne
  @JoinColumn(name = "id")
  private Point point;

  public Poi() {}

  public Integer getId()    { return this.id;    }
  //public Object  getType()  { return this.type;  }
  public String  getType()  { return this.type;  }
  public Point   getPoint() { return this.point; }

  public void setId(Integer id)     { this.id    = id;    }
  //public void setType(Object type)  { this.type  = type;  }
  public void setType(String type)  { this.type  = type;  }
  public void setPoint(Point point) { this.point = point; }

}