package eu.telecom_bretagne.ambSocialNetwork.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;

@Remote
public interface IServiceUtilisateur
{
  //-----------------------------------------------------------------------------
  public Utilisateur       nouvelUtilisateur(String nom, String prenom,
                                             String email, String motDePasse,
                                             String  urlAvatar, String description,
                                             boolean partagePosition, boolean partagePositionPublic,
                                             float latitude, float longitude, int cap, int vitesse);
  public Utilisateur       nouvelUtilisateur(String nom, String prenom,
                                             String email, String motDePasse,
                                             String description);
  public Utilisateur       authentification(String email, String motDePasse);
  public Utilisateur       getUtilisateur(int id);
  public Utilisateur       getUtilisateur(String email);
  public void              effaceUtilisateur(int id);
  public List<Utilisateur> listeDesUtilisateurs();
  public List<Utilisateur> listeDeMesAmis(int id);
  public List<Utilisateur> listeDesUtilisateursDontJeSuisAmi(int id);
  public Utilisateur       miseAJour(int id,
                                     String nom, String prenom,
                                     String email, String motDePasse,
                                     String  urlAvatar, String description,
                                     boolean partagePosition, boolean partagePositionPublic);
                                     //float latitude, float longitude, int cap, int vitesse);
  //-----------------------------------------------------------------------------
}
