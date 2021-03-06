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

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.DTOUtils;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.UtilisateurDTO;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur;

@Path("/utilisateur")
public class UtilisateurREST
{
  //-----------------------------------------------------------------------------
  private static IServiceUtilisateur serviceUtilisateur;
  static
  {
    try
    {
      serviceUtilisateur = (IServiceUtilisateur) ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
      System.out.println("####### Classe UtilisateurREST : composants EJB récupérés !");
      
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
         + "    <title>Hello from UtilisateurREST</title>"
         + "    <link rel=\"stylesheet\" href=\"/AMBSocialNetworkServerWeb/styles.css\" type=\"text/css\" />"
         + "  </head>"
         + "  <body>"
         + "    <h2>Hello from UtilisateurREST</h2>"
         + "  </body>"
         + "</html> ";
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/text")
  public String getUtilisateursText()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(Utilisateur u : serviceUtilisateur.listeDesUtilisateurs())
    {
      s += DTOUtils.toDTO(u) + "\n";
    }
    s += separateur;
    
    return s;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<UtilisateurDTO> getUtilisateurs()
  {
    return DTOUtils.toListeUtilisateurDTO(serviceUtilisateur.listeDesUtilisateurs());
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public UtilisateurDTO getUtilisateurById(@PathParam("id") int id)
  {
    return DTOUtils.toDTO(serviceUtilisateur.getUtilisateur(id));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/new")
  public UtilisateurDTO nouvelUtilisateur(@FormParam("nom")          String nom,
                                          @FormParam("prenom")       String prenom,
                                          @FormParam("email")        String email,
                                          @FormParam("mot_de_passe") String motDePasse,
                                          @FormParam("description")  String description)
  {
    Utilisateur utilisateur = serviceUtilisateur.nouvelUtilisateur(nom, prenom, email, motDePasse, description);
    return DTOUtils.toDTO(utilisateur);
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/authentification")
  public UtilisateurDTO authentification(@FormParam("email")        String email,
                                         @FormParam("mot_de_passe") String motDePasse)
  {
    return DTOUtils.toDTO(serviceUtilisateur.authentification(email, motDePasse));
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/update")
  public UtilisateurDTO updateUtilisateur(@FormParam("id")                      String id,
                                          @FormParam("nom")                     String nom,
                                          @FormParam("prenom")                  String prenom,
                                          @FormParam("email")                   String email,
                                          @FormParam("mot_de_passe")            String motDePasse,
                                          @FormParam("url_avatar")              String urlAvatar, 
                                          @FormParam("description")             String description,
                                          @FormParam("partage_position")        String partagePosition,
                                          @FormParam("partage_position_public") String partagePositionPublic)
                                        //@FormParam("latitude")                String latitude,
                                        //@FormParam("longitude")               String longitude,
                                        //@FormParam("cap")                     String cap,
                                        //@FormParam("vitesse")                 String vitesse)
  {
    Utilisateur u = serviceUtilisateur.miseAJour(Integer.parseInt(id),
                                        (nom.equals("")?null:nom),
                                        (prenom.equals("")?null:prenom),
                                        (email.equals("")?null:email),
                                        (motDePasse.equals("")?null:motDePasse),
                                        (urlAvatar.equals("")?null:urlAvatar),
                                        (description.equals("")?null:description),
                                        (partagePosition.equals("")?false:Boolean.parseBoolean(partagePosition)),
                                        (partagePositionPublic.equals("")?false:Boolean.parseBoolean(partagePositionPublic)));
                                        //(latitude.equals("")?-1:Float.parseFloat(latitude)),
                                        //(longitude.equals("")?-1:Float.parseFloat(longitude)),
                                        //(cap.equals("")?-1:Integer.parseInt(cap)),
                                        //(vitesse.equals("")?-1:Integer.parseInt(vitesse)));
    return DTOUtils.toDTO(u);
  }
  //-----------------------------------------------------------------------------
}
