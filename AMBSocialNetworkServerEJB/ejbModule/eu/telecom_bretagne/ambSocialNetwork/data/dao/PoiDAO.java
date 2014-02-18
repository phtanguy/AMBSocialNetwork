package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Poi;

/**
 * Session Bean implementation class CentreInteretDAO
 */
@Stateless
@LocalBean
public class PoiDAO
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
  public PoiDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param poi bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public Poi persist(Poi poi)
  {
    entityManager.persist(poi);
    return poi;
  }
  //----------------------------------------------------------------------------
  public Poi findById(Integer id)
  {
    return entityManager.find(Poi.class, id);
  }
  //----------------------------------------------------------------------------
  public Poi findByPosition(String latitude, String longitude)
  {
    Query query = entityManager.createQuery("select poi from Poi poi join poi.point p where p.latitude = :latitude and p.longitude = :longitude");
    query.setParameter("latitude",  latitude);
    query.setParameter("longitude", longitude);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (Poi) l.get(0);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Poi> findAll()
  {
    Query query = entityManager.createQuery("select poi from Poi poi order by poi.id");
    @SuppressWarnings("rawtypes")
    List l = query.getResultList(); 
    
    return (List<Poi>)l;
  }
  //-----------------------------------------------------------------------------
  public Poi update(Poi poi)
  {
    entityManager.merge(poi);
    return findById(poi.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(Poi poi)
  {
    if(!entityManager.contains(poi))  // Si l'entité n'est pas dans un état "géré" (managed),
    {                                 // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      poi = entityManager.merge(poi); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                 // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(poi);
  }
  //-----------------------------------------------------------------------------
}
