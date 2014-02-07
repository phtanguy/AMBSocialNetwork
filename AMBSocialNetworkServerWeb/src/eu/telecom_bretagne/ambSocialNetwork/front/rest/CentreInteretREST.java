package eu.telecom_bretagne.ambSocialNetwork.front.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.CentreInteretDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.CommentaireDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.DTOUtils;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.ambSocialNetwork.service.IServiceCentreInteret;

@Path("/centre_interet")
public class CentreInteretREST
{
  //-----------------------------------------------------------------------------
  private static IServiceCentreInteret serviceCentreInteret;
  static
  {
    try
    {
      serviceCentreInteret = (IServiceCentreInteret) ServicesLocator.getInstance().getRemoteInterface("ServiceCentreInteret");
      System.out.println("####### Classe CentreInteretREST : composants EJB récupérés !");
      
    }
    catch (ServicesLocatorException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("/HelloHtml")
  public String sayHtmlHello()
  {
    return "<html>"
         + "  <head>"
         + "    <title>Hello from CentreInteretREST</title>"
         + "    <link rel=\"stylesheet\" href=\"/AMBSocialNetworkServerWeb/styles.css\" type=\"text/css\" />"
         + "  </head>"
         + "  <body>"
         + "    <h2>Hello from CentreInteretREST</h2>"
         + "  </body>"
         + "</html> ";
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/ci_text")
  public String getCentresInteretText()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(CentreInteret ci : serviceCentreInteret.listeDesCentresInteret())
    {
      s += DTOUtils.toDTO(ci) + "\n";
    }
    s += separateur;
    
    return s;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/comm_text")
  public String getCommentairesText()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(Commentaire c : serviceCentreInteret.listeDesCommentaires())
    {
      s += DTOUtils.toDTO(c) + "\n";
    }
    s += separateur;
    
    return s;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/ci")
  public List<CentreInteretDTO> getCentresInteret()
  {
    return DTOUtils.toListeCentreInteretDTO(serviceCentreInteret.listeDesCentresInteret());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm")
  public List<CommentaireDTO> getCommentaires()
  {
    return DTOUtils.toListeCommentaireDTO(serviceCentreInteret.listeDesCommentaires());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/ci/{id}")
  public CentreInteretDTO getCentreInteretById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(serviceCentreInteret.getCentreInteret(id));
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm/{id}")
  public CommentaireDTO getCommentaireById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(serviceCentreInteret.getCommentaire(id));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getByPosition")
  public CentreInteretDTO getCentreInteretByPosition(@FormParam("latitude")  String latitude,
                                                     @FormParam("longitude") String longitude)
  {
    return DTOUtils.toDTO(serviceCentreInteret.getCentreInteret(latitude, longitude));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/new_comment")
  public CommentaireDTO nouveauCommentaire(@FormParam("id_utilisateur")    int     idUtilisateur,
                                           @FormParam("id_centre_interet") int     idCentreInteret,
                                           @FormParam("contenu")           String  contenu,
                                           @FormParam("partage_public")    boolean partagePublic)
  {
    Commentaire commentaire =  serviceCentreInteret.nouveauCommentaire(idUtilisateur,
                                                                       idCentreInteret,
                                                                       contenu,
                                                                       partagePublic);
    return DTOUtils.toDTO(commentaire);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm_ci")
  public List<CommentaireDTO> listeDesCommentairesPourUnCentreInteret(@FormParam("id_centre_interet") int idCentreInteret)
  {
    return DTOUtils.toListeCommentaireDTO(serviceCentreInteret.listeDesCommentairesPourUnCentreInteret(idCentreInteret));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm_ut")
  public List<CommentaireDTO> listeDesCommentairesPourUnUtilisateur(@FormParam("id_utilisateur") int idUtilisateur)
  {
    return DTOUtils.toListeCommentaireDTO(serviceCentreInteret.listeDesCommentairesPourUnUtilisateur(idUtilisateur));
  }
  //-----------------------------------------------------------------------------
}
