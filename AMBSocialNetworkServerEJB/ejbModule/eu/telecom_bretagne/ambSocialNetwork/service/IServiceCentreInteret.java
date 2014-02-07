package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;

@Remote
public interface IServiceCentreInteret
{
  //-----------------------------------------------------------------------------
  public CentreInteret       getCentreInteret(int id);
  public Commentaire         getCommentaire(int id);
  public CentreInteret       getCentreInteret(String latitude, String longitude);
  public List<CentreInteret> listeDesCentresInteret();
  public Commentaire         nouveauCommentaire(int idUtilisateur,
                                                int idCentreInteret,
                                                String contenu,
                                                boolean partagePublic);
  public List<Commentaire>   listeDesCommentaires();
  public List<Commentaire>   listeDesCommentairesPourUnCentreInteret(int idCentreInteret);
  public List<Commentaire>   listeDesCommentairesPourUnUtilisateur(int idUtilisateur);
  //-----------------------------------------------------------------------------
}
