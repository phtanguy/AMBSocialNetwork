package eu.telecom_bretagne.ambSocialNetwork.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.CentreInteretDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.CommentaireDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;

/**
 * Session Bean implementation class ServiceCentreInteret
 */
@Stateless
@LocalBean
public class ServiceCentreInteret implements IServiceCentreInteret
{
  //-----------------------------------------------------------------------------
  @EJB CentreInteretDAO centreInteretDAO;
  @EJB CommentaireDAO   commentaireDAO;
  @EJB UtilisateurDAO   utilisateurDAO;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public ServiceCentreInteret()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  @Override
  public CentreInteret getCentreInteret(int id)
  {
    return centreInteretDAO.findById(id);
  }
  //-----------------------------------------------------------------------------
  @Override
  public CentreInteret getCentreInteret(String latitude, String longitude)
  {
    return centreInteretDAO.findByPosition(latitude, longitude);
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<CentreInteret> listeDesCentresInteret()
  {
    return centreInteretDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public Commentaire nouveauCommentaire(int idUtilisateur,
                                        int idCentreInteret,
                                        String contenu, 
                                        boolean partagePublic)
  {
    Utilisateur   utilisateur   = utilisateurDAO.findById(idUtilisateur);
    CentreInteret centreInteret = centreInteretDAO.findById(idCentreInteret);
    Commentaire commentaire = new Commentaire();
    commentaire.setUtilisateurBean(utilisateur);
    commentaire.setCentreInteretBean(centreInteret);
    commentaire.setContenu(contenu);
    commentaire.setPartageCommentairePublic(partagePublic);
    commentaire.setDatePublication(new Timestamp(System.currentTimeMillis()));
    commentaire.setUrlPhoto(null);
    commentaire = commentaireDAO.persist(commentaire);
    
    utilisateur.getCommentaires().add(commentaire);
    centreInteret.getCommentaires().add(commentaire);
    
    utilisateurDAO.update(utilisateur);
    centreInteretDAO.update(centreInteret);
    
    
    System.out.println("-----------> nouveau commentaire (dans service) = " + commentaire);
    
    return commentaire;
  }
}
