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

import eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Poi;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Service;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.CommentaireDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.DTOUtils;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.PoiDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.ServiceDTO;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.ambSocialNetwork.service.IServicePoint;

@Path("/centre_interet")
public class PointREST
{
  //-----------------------------------------------------------------------------
  private static IServicePoint servicePoint;
  static
  {
    try
    {
      servicePoint = (IServicePoint) ServicesLocator.getInstance().getRemoteInterface("ServicePoint");
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
  @Path("/points_text")
  public String getPoints()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(Poi poi : servicePoint.listeDesPois())
    {
      s += DTOUtils.toDTO(poi) + "\n";
    }
    s += separateur;
    for(Service service : servicePoint.listeDesServices())
    {
      s += DTOUtils.toDTO(service) + "\n";
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
    
    for(Commentaire c : servicePoint.listeDesCommentaires())
    {
      s += DTOUtils.toDTO(c) + "\n";
    }
    s += separateur;
    
    return s;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/poi")
  public List<PoiDTO> getPois()
  {
    return DTOUtils.toListePoisDTO(servicePoint.listeDesPois());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/service")
  public List<ServiceDTO> getServices()
  {
    return DTOUtils.toListeServicesDTO(servicePoint.listeDesServices());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm")
  public List<CommentaireDTO> getCommentaires()
  {
    return DTOUtils.toListeCommentaireDTO(servicePoint.listeDesCommentaires());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/service/{id}")
  public ServiceDTO getServiceById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(servicePoint.getService(id));
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/poi/{id}")
  public PoiDTO getPoiById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(servicePoint.getPoi(id));
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm/{id}")
  public CommentaireDTO getCommentaireById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(servicePoint.getCommentaire(id));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/new_poi")
  public PoiDTO nouveauPoi(@FormParam("latitude")  String latitude,
                           @FormParam("longitude") String longitude,
                           @FormParam("type")      String type)
  {
    Poi poi = servicePoint.nouveauPoi(latitude, longitude, type);
    return DTOUtils.toDTO(poi);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getPoiByPosition")
  public PoiDTO getPoiByPosition(@FormParam("latitude")  String latitude,
                                 @FormParam("longitude") String longitude)
  {
    return DTOUtils.toDTO(servicePoint.getPoi(latitude, longitude));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getServiceByPosition")
  public ServiceDTO getServiceByPosition(@FormParam("latitude")  String latitude,
                                         @FormParam("longitude") String longitude)
  {
    return DTOUtils.toDTO(servicePoint.getService(latitude, longitude));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/new_comment")
  public CommentaireDTO nouveauCommentaire(@FormParam("id_utilisateur") int     idUtilisateur,
                                           @FormParam("id_point")       int     idPoint,
                                           @FormParam("contenu")        String  contenu,
                                           @FormParam("partage_public") boolean partagePublic)
  {
    Commentaire commentaire =  servicePoint.nouveauCommentaire(idUtilisateur,
                                                               idPoint,
                                                               contenu,
                                                               partagePublic);
    return DTOUtils.toDTO(commentaire);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm_ci")
  public List<CommentaireDTO> listeDesCommentairesPourUnPoint(@FormParam("id_point") int idPoint)
  {
    return DTOUtils.toListeCommentaireDTO(servicePoint.listeDesCommentairesPourUnPoint(idPoint));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/comm_ut")
  public List<CommentaireDTO> listeDesCommentairesPourUnUtilisateur(@FormParam("id_utilisateur") int idUtilisateur)
  {
    return DTOUtils.toListeCommentaireDTO(servicePoint.listeDesCommentairesPourUnUtilisateur(idUtilisateur));
  }
  //-----------------------------------------------------------------------------
}
