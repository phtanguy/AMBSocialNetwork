package eu.telecom_bretagne.ambSocialNetwork.front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.ambSocialNetwork.data.dao.UtilisateurDAO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet
{
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlesDAOServlet()
	{
		super();
	}
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Flot de sortie pour écriture des résultats.
    PrintWriter out = response.getWriter();
    
    // Récupération de la réféence vers le(s) DAO(s)
    UtilisateurDAO utilisateurDAO = null;
    try
    {
      utilisateurDAO = (UtilisateurDAO) ServicesLocator.getInstance().getRemoteInterface("UtilisateurDAO");
    }
    catch (ServicesLocatorException e)
    {
    	e.printStackTrace();
    }
		out.println("Contrôles de fonctionnement du DAO UtilisateurDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		
		out.println("Liste des utilisateurs :");
		List<Utilisateur> utilisateurs = utilisateurDAO.findAll();
		
		for(Utilisateur utilisateur : utilisateurs)
		{
			out.println("  - " + utilisateur.getNom() + " " + utilisateur.getPrenom());
		}
		out.println();
		
		out.println("Obtention de l'utilisateur n° 1 :");
		Utilisateur u = utilisateurDAO.findById(1);
		out.println("  - " + u.getId() + " " + u.getNom() + " " + u.getPrenom());
    out.println("  - " + u.getEmail());
		out.println("  - " + u.getDescription());
		out.println();

    out.println("Obtention de l'utilisateur n° 2 :");
    u = utilisateurDAO.findById(2);
    out.println("  - " + u.getId() + " " + u.getNom() + " " + u.getPrenom());
    out.println("  - " + u.getEmail());
    out.println("  - " + u.getDescription());
    out.println();

    out.println("Obtention de l'utilisateur n° 3 :");
    u = utilisateurDAO.findById(3);
    out.println("  - " + u.getId() + " " + u.getNom() + " " + u.getPrenom());
    out.println("  - " + u.getEmail());
    out.println("  - " + u.getDescription());
    out.println();
	}
	//-----------------------------------------------------------------------------
}
