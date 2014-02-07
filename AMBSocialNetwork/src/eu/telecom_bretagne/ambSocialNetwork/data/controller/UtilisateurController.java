package eu.telecom_bretagne.ambSocialNetwork.data.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.UtilisateurDTO;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.UtilisateursDTOList;

public class UtilisateurController extends Controller
{
  //-----------------------------------------------------------------------------
  private static UtilisateurController instance = null;
  
  private static final String URL_UTILISATEUR = URL + "/utilisateur";
  //-----------------------------------------------------------------------------
  private UtilisateurController()
  {
    super();
  }
  //-----------------------------------------------------------------------------
  public static UtilisateurController getInstance()
  {
    if(instance == null)
      instance = new UtilisateurController();
    return instance;
  }
  //-----------------------------------------------------------------------------
  public String findAllText() throws IOException
  {
    String result = downloadContent(URL_UTILISATEUR + "/text");
    return result;
  }
  //-----------------------------------------------------------------------------
  public UtilisateursDTOList findAllJson() throws IOException
  {
    String jsonData = downloadContent(URL_UTILISATEUR);
    JsonParser   jParser  = jFactory.createParser(jsonData);
    UtilisateursDTOList ul = oMapper.readValue(jParser, UtilisateursDTOList.class);
    return ul;
  }
  //-----------------------------------------------------------------------------
  public UtilisateurDTO authentification(String email, String password) throws ClientProtocolException, IOException
  {
    Map<String,String> formValues = new HashMap<String, String>();
    formValues.put("email",        email);
    formValues.put("mot_de_passe", password);
    
    String jsonUtilisateurData = downloadContent(URL_UTILISATEUR + "/authentification", formValues);

    // Décodage de la réponse.
    UtilisateurDTO utilisateur = null;
    if(jsonUtilisateurData != null)
    {
      JsonParser   jParser  = jFactory.createParser(jsonUtilisateurData);
      try
      {
        utilisateur = oMapper.readValue(jParser, UtilisateurDTO.class);
        Log.d("AMBSocialNetwork", "-----------------------------> authentification(" + email + ", " + password + ") = " + utilisateur);
      }
      catch(JsonParseException jpe)
      {
        Log.e("AMBSocialNetwork", "-----------------------------> authentification(" + email + ", " + password + ") = erreur, l'utilisateur n'existe pas.");
      }
    }
    return utilisateur;
  }
  //-----------------------------------------------------------------------------
}
