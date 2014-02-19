package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Poi;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Service;

@Remote
public interface IServicePoint
{
  //-----------------------------------------------------------------------------
  public Poi                 getPoi(int id);
  public Service             getService(int id);
  public Commentaire         getCommentaire(int id);
  public Poi                 getPoi(String latitude, String longitude);
  public Service             getService(String latitude, String longitude);
  public List<Poi>           listeDesPois();
  public List<Service>       listeDesServices();
  public Poi                 nouveauPoi(String latitude, String longitude, String type);
  public Poi                 nouveauPoiAvecCommentaire(String  latitude,
                                                       String  longitude,
                                                       String  type,
                                                       int     idUtilisateur,
                                                       String  contenu,
                                                       boolean partagePublic);
  public Commentaire         nouveauCommentaire(int     idUtilisateur,
                                                int     idPoint,
                                                String  contenu,
                                                boolean partagePublic);
  public List<Commentaire>   listeDesCommentaires();
  public List<Commentaire>   listeDesCommentairesPourUnPoint(int idPoint);
  public List<Commentaire>   listeDesCommentairesPourUnUtilisateur(int idUtilisateur);
  //-----------------------------------------------------------------------------
}
