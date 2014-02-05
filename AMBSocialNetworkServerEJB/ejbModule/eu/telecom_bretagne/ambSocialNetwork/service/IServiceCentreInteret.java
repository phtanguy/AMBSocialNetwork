package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;

@Remote
public interface IServiceCentreInteret
{
  //-----------------------------------------------------------------------------
  public CentreInteret getCentreInteret(int id);
  public CentreInteret getCentreInteret(String latitude, String longitude);
  public List<CentreInteret> listeDesCentresInteret();
  //-----------------------------------------------------------------------------
}
