package eu.telecom_bretagne.ambSocialNetwork.front.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.weld.context.PassivatingContextWrapper;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur;

@Path("/utilisateur")
public class UtilisateurREST
{
  //-----------------------------------------------------------------------------
  //private static UtilisateurDAO utilisateurDAO;
  private static IServiceUtilisateur serviceUtilisateur;
  static
  {
    try
    {
      //utilisateurDAO     = (UtilisateurDAO)     ServicesLocator.getInstance().getRemoteInterface("UtilisateurDAO");
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
  public String getUtilisateurs_TEXT()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(Utilisateur u : serviceUtilisateur.listeDesUtilisateurs())
    {
      s += u + "\n";
    }
    s += separateur;
    
    return s;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Utilisateur> getUtilisateurs_JSON()
  {
    List<Utilisateur> utilisateurs = serviceUtilisateur.listeDesUtilisateurs();
    System.out.println("-----------------------------> utilisateurs.size() = " + utilisateurs.size());
    List<Utilisateur> utilisateursResultat = new ArrayList<Utilisateur>();
    for(Utilisateur u : utilisateurs)
    {
      // Si on ne met pas à jour les structures de données internes (elles sont renseignées
      // puisque le FetchType est positionné à EAGER), on a une erreur. 
      u.setCommentaires(null);
      u.setUtilisateurs1(null);
      u.setUtilisateurs2(null);
      utilisateursResultat.add(u);
    }
    return utilisateursResultat;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Utilisateur getUtilisateurById_JSON(@PathParam("id") int id)
  {
    Utilisateur utilisateur = serviceUtilisateur.getUtilisateur(id);
    // Si on ne met pas à jour les structures de données internes (elles sont renseignées
    // puisque le FetchType est positionné à EAGER), on a une erreur. 
    utilisateur.setCommentaires(null);
    utilisateur.setUtilisateurs1(null);
    utilisateur.setUtilisateurs2(null);
    return utilisateur;
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/new")
  public String nouvelUtilisateur(@FormParam("nom")          String nom,
                                  @FormParam("prenom")       String prenom,
                                  @FormParam("email")        String email,
                                  @FormParam("mot_de_passe") String motDePasse,
                                  @FormParam("description")  String description)
  {
    Utilisateur utilisateur = serviceUtilisateur.nouvelUtilisateur(nom, prenom, email, motDePasse, description);
    return utilisateur.toString();
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  //@Produces(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/authentification")
  public int authentification(@FormParam("email")        String email,
                              @FormParam("mot_de_passe") String motDePasse)
  {
    System.out.println("----------------------> " + email + "/" + motDePasse);
    Utilisateur u = serviceUtilisateur.authentification(email, motDePasse);
    System.out.println("----------------------> " + u);
    //return u.toString();
    return (u==null?-1:u.getId());
  }
  //-----------------------------------------------------------------------------
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/update")
  public Utilisateur updateUtilisateur(@FormParam("id")                      String id,
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
    System.out.println("---------------------------------> id                    = " + id);
    System.out.println("---------------------------------> nom                   = " + nom);
    System.out.println("---------------------------------> prenom                = " + prenom);
    System.out.println("---------------------------------> prenom                = " + prenom);
    System.out.println("---------------------------------> motDePasse            = " + motDePasse);
    System.out.println("---------------------------------> urlAvatar             = " + urlAvatar);
    System.out.println("---------------------------------> description           = " + description);
    System.out.println("---------------------------------> partagePosition       = " + partagePosition);
    System.out.println("---------------------------------> partagePositionPublic = " + partagePositionPublic);
    //System.out.println("---------------------------------> latitude              = " + latitude);
    //System.out.println("---------------------------------> longitude             = " + longitude);
    //System.out.println("---------------------------------> cap                   = " + cap);
    //System.out.println("---------------------------------> vitesse               = " + vitesse);
    
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
    u.setCommentaires(null);
    u.setUtilisateurs1(null);
    u.setUtilisateurs2(null);
    return u;
  }
  //-----------------------------------------------------------------------------
}
