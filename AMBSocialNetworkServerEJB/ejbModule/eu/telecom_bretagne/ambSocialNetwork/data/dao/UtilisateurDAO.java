package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;

/**
 * Session Bean implementation class UtilisateurDAO
 */
@Stateless
@LocalBean
public class UtilisateurDAO
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
  public UtilisateurDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param utilisateur bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public Utilisateur persist(Utilisateur utilisateur)
  {
    entityManager.persist(utilisateur);
    return utilisateur;
  }
  //----------------------------------------------------------------------------
  public Utilisateur findById(Integer id)
  {
    return entityManager.find(Utilisateur.class, id);
  }
  //----------------------------------------------------------------------------
  public Utilisateur findByEmail(String email)
  {
    Query query = entityManager.createQuery("select utilisateur from Utilisateur utilisateur where utilisateur.email = :email");
    query.setParameter("email",      email);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (Utilisateur) l.get(0);
  }
  //----------------------------------------------------------------------------
  public Utilisateur findByEmailAndPassword(String email, String motDePasse)
  {
    Query query = entityManager.createQuery("select utilisateur from Utilisateur utilisateur where utilisateur.email = :email and utilisateur.motDePasse = :motDePasse");
    query.setParameter("email",      email);
    query.setParameter("motDePasse", motDePasse);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    if(l.size() == 0)
      return null;
    return (Utilisateur) l.get(0);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public List<Utilisateur> findAll()
  {
    Query query = entityManager.createQuery("select utilisateur from Utilisateur utilisateur order by utilisateur.id");
    List l = query.getResultList(); 
    
    return (List<Utilisateur>)l;
  }
  //-----------------------------------------------------------------------------
  public Utilisateur update(Utilisateur utilisateur)
  {
    entityManager.merge(utilisateur);
    return findById(utilisateur.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(Utilisateur utilisateur)
  {
    if(!entityManager.contains(utilisateur))          // Si l'entité n'est pas dans un état "géré" (managed),
    {                                                 // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      utilisateur = entityManager.merge(utilisateur); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                                 // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(utilisateur);
  }
  //-----------------------------------------------------------------------------
}
