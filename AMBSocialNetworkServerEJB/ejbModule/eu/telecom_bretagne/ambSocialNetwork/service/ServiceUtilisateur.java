package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;

/**
 * Session Bean implementation class ServiceUtilisateur
 */
@Stateless
@LocalBean
public class ServiceUtilisateur implements IServiceUtilisateur
{
  //-----------------------------------------------------------------------------
  @EJB UtilisateurDAO utilisateurDAO;
  //-----------------------------------------------------------------------------
  /**
   * Default constructor.
   */
  public ServiceUtilisateur()
  {
    // TODO Auto-generated constructor stub
  }
  //-----------------------------------------------------------------------------
  @Override
  public Utilisateur nouvelUtilisateur(String nom, String prenom,
                                       String email, String motDePasse,
                                       String urlAvatar, String description,
                                       boolean partagePosition, boolean partagePositionPublic,
                                       float latitude, float longitude, int cap, int vitesse)
  {
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNom(prenom);                                                   // non null
    utilisateur.setPrenom(prenom!=null?(prenom.equals("")?null:prenom):null);
    utilisateur.setEmail(email);                                                  // non null
    utilisateur.setMotDePasse(motDePasse);                                        // non null
    utilisateur.setUrlAvatar(urlAvatar!=null?(urlAvatar.equals("")?null:urlAvatar):null);
    utilisateur.setDescription(description!=null?(description.equals("")?null:description):null);
    utilisateur.setPartagePosition(partagePosition);
    utilisateur.setPartagePositionPublic(partagePositionPublic);
    utilisateur.setLatitude(latitude);
    utilisateur.setLongitude(longitude);
    utilisateur.setCap(cap);
    utilisateur.setVitesse(vitesse);
    
    return utilisateurDAO.persist(utilisateur);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Utilisateur nouvelUtilisateur(String nom, String prenom,
                                       String email, String motDePasse,
                                       String description)
  {
    // TODO Auto-generated method stub
    return nouvelUtilisateur(nom, prenom, email, motDePasse, null, description, false, false, -1, -1, -1, -1);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Utilisateur authentification(String email, String motDePasse)
  {
    return utilisateurDAO.findByEmailAndPassword(email, motDePasse);
  }
  //-----------------------------------------------------------------------------
  @Override
  public Utilisateur getUtilisateur(int id)
  {
    return utilisateurDAO.findById(id);
  }
  @Override
  //-----------------------------------------------------------------------------
  public Utilisateur getUtilisateur(String email)
  {
    return utilisateurDAO.findByEmail(email);
  }
  //-----------------------------------------------------------------------------
  @Override
  public void effaceUtilisateur(int id)
  {
    // TODO MessageDAO à ecrire afin d'effacer les messages (si implémenté) et les commentaires associés.
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Utilisateur> listeDesUtilisateurs()
  {
    return utilisateurDAO.findAll();
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Utilisateur> listeDeMesAmis(int id)
  {
    Utilisateur utilisateur = utilisateurDAO.findById(id);
    return utilisateur.getUtilisateurs2();
  }
  //-----------------------------------------------------------------------------
  @Override
  public List<Utilisateur> listeDeUtilisateursDontJeSuisAmi(int id)
  {
    Utilisateur utilisateur = utilisateurDAO.findById(id);
    return utilisateur.getUtilisateurs1();
  }
  //-----------------------------------------------------------------------------
  @Override
  public Utilisateur miseAJour(int id,
                               String nom, String prenom,
                               String email, String motDePasse,
                               String  urlAvatar, String description,
                               boolean partagePosition, boolean partagePositionPublic)
                               //float latitude, float longitude, int cap, int vitesse)
  {
    Utilisateur utilisateur = getUtilisateur(id);
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setEmail(email);
    utilisateur.setMotDePasse(motDePasse);
    utilisateur.setUrlAvatar(urlAvatar);
    utilisateur.setDescription(description);
    utilisateur.setPartagePosition(partagePosition);
    utilisateur.setPartagePositionPublic(partagePositionPublic);
    //utilisateur.setLatitude(latitude);
    //utilisateur.setLongitude(longitude);
    //utilisateur.setCap(cap);
    //utilisateur.setVitesse(vitesse);
    
    return utilisateurDAO.update(utilisateur);
  }
}
