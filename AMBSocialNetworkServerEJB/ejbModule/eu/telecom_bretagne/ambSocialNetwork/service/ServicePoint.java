package eu.telecom_bretagne.ambSocialNetwork.service;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.CommentaireDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.PoiDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.PointDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.ServiceDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Poi;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Point;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Service;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;

/**
 * Session Bean implementation class ServiceCentreInteret
 */
@Stateless
@LocalBean
public class ServicePoint implements IServicePoint
{
  //-----------------------------------------------------------------------------
  @EJB PointDAO         pointDAO;
  @EJB PoiDAO           poiDAO;
  @EJB ServiceDAO       serviceDAO;
  @EJB CommentaireDAO   commentaireDAO;
  @EJB UtilisateurDAO   utilisateurDAO;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public ServicePoint()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  @Override
  public Poi getPoi(int id)
  {
    return poiDAO.findById(id);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Service getService(int id)
  {
    return serviceDAO.findById(id);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Commentaire getCommentaire(int id)
  {
    return commentaireDAO.findById(id);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Poi getPoi(String latitude, String longitude)
  {
    return poiDAO.findByPosition(latitude, longitude);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Service getService(String latitude, String longitude)
  {
    return serviceDAO.findByPosition(latitude, longitude);
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Poi> listeDesPois()
  {
    return poiDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Service> listeDesServices()
  {
    return serviceDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public Poi nouveauPoi(String latitude, String longitude, String type)
  {
    Point point = new Point();
    point.setLatitude(latitude);
    point.setLongitude(longitude);
    pointDAO.persist(point);
    
    Poi poi = new Poi();
    poi.setId(point.getId());
    poi.setType(type);
    poiDAO.persist(poi, point);
    
    poi.setPoint(point);
    poiDAO.update(poi);
    point.setPoi(poi);
    pointDAO.update(point);
    
    return poi;
  }
  //-----------------------------------------------------------------------------
  @Override
  public Commentaire nouveauCommentaire(int     idUtilisateur,
                                        int     idPoint,
                                        String  contenu, 
                                        boolean partagePublic)
  {
    Utilisateur utilisateur = utilisateurDAO.findById(idUtilisateur);
    Point       point       = pointDAO.findById(idPoint);
    
    Commentaire commentaire = new Commentaire();
    commentaire.setUtilisateurBean(utilisateur);
    commentaire.setPointBean(point);
    commentaire.setContenu(contenu);
    commentaire.setPartageCommentairePublic(partagePublic);
    commentaire.setDatePublication(new Timestamp(System.currentTimeMillis()));
    commentaire.setUrlPhoto(null);
    commentaire = commentaireDAO.persist(commentaire);

    utilisateur.getCommentaires().add(commentaire);
    utilisateurDAO.update(utilisateur);

    point.getCommentaires().add(commentaire);
    pointDAO.update(point);
    
    return commentaire;
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Commentaire> listeDesCommentaires()
  {
    return commentaireDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Commentaire> listeDesCommentairesPourUnPoint(int idPoint)
  {
    return commentaireDAO.findAllByPoint(idPoint);
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Commentaire> listeDesCommentairesPourUnUtilisateur(int idUtilisateur)
  {
    return commentaireDAO.findAllByUtilisateur(idUtilisateur);
  }
  //-----------------------------------------------------------------------------
}
