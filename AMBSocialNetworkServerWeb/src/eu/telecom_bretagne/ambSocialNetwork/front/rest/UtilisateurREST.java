package eu.telecom_bretagne.ambSocialNetwork.front.rest;

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

import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;

//import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
//import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
//import eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi;
//import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
//import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
//import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
//import eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise;
//import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

@Path("/utilisateur")
public class UtilisateurREST
{
  //-----------------------------------------------------------------------------
  private static UtilisateurDAO utilisateurDAO;
  static
  {
    try
    {
      utilisateurDAO = (UtilisateurDAO) ServicesLocator.getInstance().getRemoteInterface("UtilisateurDAO");
      System.out.println("####### Classe UtilisateurREST : composant UtilisateurDAO récupéré !");
      
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
    return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>"
        + "Hello Jersey" + "</body></h1>" + "</html> ";
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/text")
  public String getUtilisateurs_TEXT()
  {
    String separateur = "---------------------------------------------------------\n";
    String s = separateur;
    
    for(Utilisateur u : utilisateurDAO.findAll())
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
    List<Utilisateur> utilisateurs1 = utilisateurDAO.findAll();
//    List<Entreprise> entreprisesResultat = new ArrayList<Entreprise>();
//    for(Entreprise e : entreprises1)
//    {
//      e.setOffresEmploi(null);
//      entreprisesResultat.add(e);
//    }
    
//    return entreprisesResultat;
    return utilisateurs1;
  }
  //-----------------------------------------------------------------------------
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Utilisateur getUtilisateurById_JSON(@PathParam("id") int id)
  {
    Utilisateur utilisateur = utilisateurDAO.findById(id);
    //utilisateur.setUtilisateurs1(new ArrayList<Utilisateur>());
    //utilisateur.setUtilisateurs2(new ArrayList<Utilisateur>());
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
    System.out.println("Tentative de référencement d'un nouvel utilisateur :");
    System.out.println("  - nom         = " + nom);
    System.out.println("  - prénom      = " + prenom);
    System.out.println("  - email       = " + email);
    System.out.println("  - motDePasse  = " + motDePasse);
    System.out.println("  - description = " + description);
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setEmail(email);
    utilisateur.setMotDePasse(motDePasse);
    utilisateur.setDescription(description);
    return (utilisateurDAO.persist(utilisateur)).toString();
  }
  //-----------------------------------------------------------------------------

  
  
  
  
  
  
//	//-----------------------------------------------------------------------------
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	@Path("/text/{id}")
//	public String getEntrepriseById_TEXT(@PathParam("id") int id)
//	{
//		Entreprise e = serviceEntreprise.getEntreprise(id);
//		return 
//				
//				"NOM DE L'ENTREPRISE :\n" +
//				"-------------------\n" +
//				"(" + e.getId() + ") " + e.getNom() + "\n\n" +
//				"DESCRIPTIF :\n" +
//				"----------\n" +
//				e.getDescriptif() + "\n\n" +
//				"ADRESSE POSTALE :\n" +
//				"---------------\n" +
//				e.getAdressePostale() + "\n\n" +
//				"NOMBRE D'OFFRES D'EMPLOI RÉFÉRENCÉES :\n" +
//				"------------------------------------\n" +
//				e.getOffresEmploi().size();
//	}
//	//-----------------------------------------------------------------------------
//  // This method is called if XML is request
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	@Path("/xml")
//	public String sayXMLHello()
//	{
//		String xml = "<?xml version=\"1.0\"?>" +
//				         "<entreprises>";
//		for(Entreprise e : serviceEntreprise.listeDesEntreprises())
//		{
//			xml += "<entreprise>" +
//					   "<id>"+e.getId()+"</id>" +
//					   "<nom>"+e.getNom()+"</nom>" +
//					   "<descriptif>"+e.getDescriptif().replace("&", "&amp;")+"</descriptif>" +
//					   	"<adresse>"+e.getAdressePostale()+"</adresse>" +
//					   	"</entreprise>";
//		}
//		xml += "</entreprises>";
//		return xml;
//	}
}
