package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.CentreInteretDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.dao.CommentaireDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;

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
}
