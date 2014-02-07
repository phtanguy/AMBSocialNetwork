package eu.telecom_bretagne.ambSocialNetwork.data.model.dto;

import java.util.ArrayList;
import java.util.List;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;

public class DTOUtils
{
  //-----------------------------------------------------------------------------
  public static UtilisateurDTO toDTO(Utilisateur utilisateur)
  {
    UtilisateurDTO uDTO = new UtilisateurDTO();
    uDTO.setId(utilisateur.getId());
    uDTO.setNom(utilisateur.getNom());
    uDTO.setPrenom(utilisateur.getPrenom());
    uDTO.setEmail(utilisateur.getEmail());
    uDTO.setMotDePasse(utilisateur.getMotDePasse());
    uDTO.setUrlAvatar(utilisateur.getUrlAvatar());
    uDTO.setDescription(utilisateur.getDescription());
    uDTO.setPartagePosition(utilisateur.getPartagePosition());
    uDTO.setPartagePositionPublic(utilisateur.getPartagePositionPublic());
    uDTO.setLatitude(utilisateur.getLatitude());
    uDTO.setLongitude(utilisateur.getLongitude());
    uDTO.setCap(utilisateur.getCap());
    uDTO.setVitesse(utilisateur.getVitesse());
    List<Integer> commentairesId = new ArrayList<Integer>();
    for(Commentaire c : utilisateur.getCommentaires())
    {
      commentairesId.add(c.getId());
    }
    uDTO.setCommentaires(commentairesId);
    List<Integer> utilisateursId = new ArrayList<Integer>();
    for(Utilisateur u : utilisateur.getUtilisateurs1())
    {
      utilisateursId.add(u.getId());
    }
    uDTO.setDeclaresEtreMesAmis(utilisateursId);
    utilisateursId = new ArrayList<Integer>();
    for(Utilisateur u : utilisateur.getUtilisateurs2())
    {
      utilisateursId.add(u.getId());
    }
    uDTO.setMesAmis(utilisateursId);
    
    return uDTO;
  }
  //-----------------------------------------------------------------------------
  public static CommentaireDTO toDTO(Commentaire commentaire)
  {
    CommentaireDTO cDTO = new CommentaireDTO();
    cDTO.setId(commentaire.getId());
    cDTO.setUtilisateurId(commentaire.getUtilisateurBean().getId());
    cDTO.setCentreInteretId(commentaire.getCentreInteretBean().getId());
    cDTO.setContenu(commentaire.getContenu());
    cDTO.setUrlPhoto(commentaire.getUrlPhoto());
    cDTO.setDatePublication(commentaire.getDatePublication());
    cDTO.setPartageCommentairePublic(commentaire.getPartageCommentairePublic());
    
    return cDTO;
  }
  //-----------------------------------------------------------------------------
  public static CentreInteretDTO toDTO(CentreInteret centreInteret)
  {
    CentreInteretDTO ciDTO = new CentreInteretDTO();
    ciDTO.setId(centreInteret.getId());
    ciDTO.setNom(centreInteret.getNom());
    ciDTO.setDescription(centreInteret.getDescription());
    ciDTO.setLatitude(centreInteret.getLatitude());
    ciDTO.setLongitude(centreInteret.getLongitude());
    List<Integer> commentairesId = new ArrayList<Integer>();
    for(Commentaire c : centreInteret.getCommentaires())
    {
      commentairesId.add(c.getId());
    }
    ciDTO.setCommentaires(commentairesId);
    
    return ciDTO;
  }
  //-----------------------------------------------------------------------------
  public static List<UtilisateurDTO> toListeUtilisateurDTO(List<Utilisateur> utilisateurs)
  {
    List<UtilisateurDTO> resultat = new ArrayList<UtilisateurDTO>();
    for(Utilisateur u : utilisateurs)
    {
      resultat.add(DTOUtils.toDTO(u));
    }
    return resultat;
  }
  //-----------------------------------------------------------------------------
  public static List<CentreInteretDTO> toListeCentreInteretDTO(List<CentreInteret> centresInteret)
  {
    List<CentreInteretDTO> resultat = new ArrayList<CentreInteretDTO>();
    for(CentreInteret ci : centresInteret)
    {
      resultat.add(DTOUtils.toDTO(ci));
    }
    return resultat;
  }
  //-----------------------------------------------------------------------------
  public static List<CommentaireDTO> toListeCommentaireDTO(List<Commentaire> commentaires)
  {
    List<CommentaireDTO> resultat = new ArrayList<CommentaireDTO>();
    for(Commentaire c : commentaires)
    {
      resultat.add(DTOUtils.toDTO(c));
    }
    return resultat;
  }
  //-----------------------------------------------------------------------------
}
