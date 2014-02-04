package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;

/**
 * Session Bean implementation class CentreInteretDAO
 */
@Stateless
@LocalBean
public class CentreInteretDAO
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
  public CentreInteretDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param utilisateur bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public CentreInteret persist(CentreInteret centreInteret)
  {
    entityManager.persist(centreInteret);
    return centreInteret;
  }
  //----------------------------------------------------------------------------
  public CentreInteret findById(Integer id)
  {
    return entityManager.find(CentreInteret.class, id);
  }
  //----------------------------------------------------------------------------
  public CentreInteret findByPosition(String latitude, String longitude)
  {
    Query query = entityManager.createQuery("select ci from CentreInteret ci where ci.latitude = :latitude and ci.longitude = :longitude");
    query.setParameter("latitude",  latitude);
    query.setParameter("longitude", longitude);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (CentreInteret) l.get(0);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<CentreInteret> findAll()
  {
    Query query = entityManager.createQuery("select ci from CentreInteret ci order by ci.id");
    @SuppressWarnings("rawtypes")
    List l = query.getResultList(); 
    
    return (List<CentreInteret>)l;
  }
  //-----------------------------------------------------------------------------
  public CentreInteret update(CentreInteret centreInteret)
  {
    entityManager.merge(centreInteret);
    return findById(centreInteret.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(CentreInteret centreInteret)
  {
    if(!entityManager.contains(centreInteret))            // Si l'entité n'est pas dans un état "géré" (managed),
    {                                                     // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      centreInteret = entityManager.merge(centreInteret); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                                     // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(centreInteret);
  }
  //-----------------------------------------------------------------------------
}
