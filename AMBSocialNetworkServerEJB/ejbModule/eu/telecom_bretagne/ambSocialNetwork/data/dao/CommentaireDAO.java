package eu.telecom_bretagne.ambSocialNetwork.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;

/**
 * Session Bean implementation class CommentaireDAO
 */
@Stateless
@LocalBean
public class CommentaireDAO
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
  public CommentaireDAO()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  /**
   * Rend persistante l'instance (benan entity) de l'utilisateur.
   * @param utilisateur bean entity représentant l'instance.
   * @return l'instance de l'utilisateur une fois persistée dans la base de données.
   */
  public Commentaire persist(Commentaire commentaire)
  {
    entityManager.persist(commentaire);
    return commentaire;
  }
  //----------------------------------------------------------------------------
  public Commentaire findById(Integer id)
  {
    return entityManager.find(Commentaire.class, id);
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Commentaire> findAllByUtilisateur(int idUtilisateur)
  {
    Query query = entityManager.createQuery("select c from Commentaire c join c.utilisateurBean u where u.id = :idUtilisateur order by c.datePublication desc");
    query.setParameter("idUtilisateur", idUtilisateur);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    
    return (List<Commentaire>)l;
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Commentaire> findAllByCentreInteret(int idCentreInteret)
  {
    Query query = entityManager.createQuery("select c from Commentaire c join c.centreInteretBean ci where ci.id = :idCentreInteret order by c.datePublication desc");
    query.setParameter("idCentreInteret", idCentreInteret);
    @SuppressWarnings("rawtypes")
    List l = query.getResultList();
    
    return (List<Commentaire>)l;
  }
  //----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
  public List<Commentaire> findAll()
  {
    Query query = entityManager.createQuery("select c from Commentaire c order by c.datePublication desc");
    @SuppressWarnings("rawtypes")
    List l = query.getResultList(); 
    
    return (List<Commentaire>)l;
  }
  //-----------------------------------------------------------------------------
  public Commentaire update(Commentaire commentaire)
  {
    entityManager.merge(commentaire);
    return findById(commentaire.getId());
  }
  //-----------------------------------------------------------------------------
  public void remove(Commentaire commentaire)
  {
    if(!entityManager.contains(commentaire))          // Si l'entité n'est pas dans un état "géré" (managed),
    {                                                 // il est impossible de la supprimer directement, erreur "Entity must be managed to call remove"
      commentaire = entityManager.merge(commentaire); // Il faut la "rattacher" au contexte de persistance par l'appel    
    }                                                 // de la méthode merge de l'ENtityManager.
    
    // L'entité était déjà attachée ou a été rattachée, on peut donc la supprimer...
    entityManager.remove(commentaire);
  }
  //-----------------------------------------------------------------------------
}
