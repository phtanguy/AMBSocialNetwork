package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Point;

/**
 * Session Bean implementation class CentreInteretDAO
 */
@Stateless
@LocalBean
public class PointDAO
{
  //-----------------------------------------------------------------------------
  /**
   * Référence vers le gestionnaire de persistance.
   */
  @PersistenceContext
  EntityManager entityManager;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public PointDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param poi bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public Point persist(Point point)
  {
    entityManager.persist(point);
    return point;
  }
  //----------------------------------------------------------------------------
  public Point findById(Integer id)
  {
    return entityManager.find(Point.class, id);
  }
  //----------------------------------------------------------------------------
  public Point findByPosition(String latitude, String longitude)
  {
    Query query = entityManager.createQuery("select p from Point p p.latitude = :latitude and p.longitude = :longitude");
    query.setParameter("latitude",  latitude);
    query.setParameter("longitude", longitude);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (Point) l.get(0);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Point> findAll()
  {
    Query query = entityManager.createQuery("select p from Point p order by p.id");
    @SuppressWarnings("rawtypes")
    List l = query.getResultList(); 
    
    return (List<Point>)l;
  }
  //-----------------------------------------------------------------------------
  public Point update(Point point)
  {
    entityManager.merge(point);
    return findById(point.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(Point point)
  {
    if(!entityManager.contains(point))    // Si l'entité n'est pas dans un état "géré" (managed),
    {                                     // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      point = entityManager.merge(point); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                     // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(point);
  }
  //-----------------------------------------------------------------------------
}
