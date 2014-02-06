package eu.telecom_bretagne.ambSocialNetwork.front.rest;

import java.util.ArrayList;
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
import eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteretDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.CommentaireDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.DTOUtils;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
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
  @Path("/text")
  public String getCentresInteret_TEXT()
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
  @Produces(MediaType.APPLICATION_JSON)
  public List<CentreInteretDTO> getUtilisateurs_JSON()
  {
    List<CentreInteret>    centresInteret = serviceCentreInteret.listeDesCentresInteret();
    List<CentreInteretDTO> centresInteretResultat = new ArrayList<CentreInteretDTO>();
    for(CentreInteret ci : centresInteret)
    {
      centresInteretResultat.add(DTOUtils.toDTO(ci));
    }
    return centresInteretResultat;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public CentreInteretDTO getCentreInteretById_JSON(@PathParam("id") int id)
  {
    CentreInteret centreInteret = serviceCentreInteret.getCentreInteret(id);
    return DTOUtils.toDTO(centreInteret);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getByPosition")
  public CentreInteretDTO getCentreInteretByPosition(@FormParam("latitude")  String latitude,
                                                     @FormParam("longitude") String longitude)
  {
    CentreInteret centreInteret = serviceCentreInteret.getCentreInteret(latitude, longitude);
    return DTOUtils.toDTO(centreInteret);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/new_comment")
  public CommentaireDTO nouveauCommentaire(@FormParam("id_utilisateur")    String idUtilisateur,
                                           @FormParam("id_centre_interet") String idCentreInteret,
                                           @FormParam("contenu")           String contenu,
                                           @FormParam("partage_public")    String partagePublic)
  {
    Commentaire commentaire =  serviceCentreInteret.nouveauCommentaire(Integer.parseInt(idUtilisateur),
                                                                       Integer.parseInt(idCentreInteret),
                                                                       contenu,
                                                                       Boolean.parseBoolean(partagePublic));
    return DTOUtils.toDTO(commentaire);
  }
  //-----------------------------------------------------------------------------
}
