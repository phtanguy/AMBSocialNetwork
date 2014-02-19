package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Service;

/**
 * Session Bean implementation class CentreInteretDAO
 */
@Stateless
@LocalBean
public class ServiceDAO
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
  public ServiceDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param service bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public Service persist(Service service)
  {
    entityManager.persist(service);
    return service;
  }
  //----------------------------------------------------------------------------
  public Service findById(Integer id)
  {
    return entityManager.find(Service.class, id);
  }
  //----------------------------------------------------------------------------
  public Service findByPosition(String latitude, String longitude)
  {
    Query query = entityManager.createQuery("select s from Service s join s.point p where p.latitude = :latitude and p.longitude = :longitude");
    query.setParameter("latitude",  latitude);
    query.setParameter("longitude", longitude);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (Service) l.get(0);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Service> findAll()
  {
    Query query = entityManager.createQuery("select s from Service s order by s.id");
    @SuppressWarnings("rawtypes")
    List l = query.getResultList(); 
    
    return (List<Service>)l;
  }
  //-----------------------------------------------------------------------------
  public Service update(Service service)
  {
    entityManager.merge(service);
    return findById(service.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(Service service)
  {
    if(!entityManager.contains(service))      // Si l'entité n'est pas dans un état "géré" (managed),
    {                                         // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      service = entityManager.merge(service); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                         // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(service);
  }
  //-----------------------------------------------------------------------------
}
