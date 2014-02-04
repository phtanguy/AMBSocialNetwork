package eu.telecom_bretagne.ambSocialNetwork.data.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.data.model.UtilisateursList;

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
  public UtilisateursList findAllJson() throws IOException
  {
    String jsonData = downloadContent(URL_UTILISATEUR);
    JsonParser   jParser  = jFactory.createParser(jsonData);
    UtilisateursList ul = oMapper.readValue(jParser, UtilisateursList.class);
    return ul;
  }
  //-----------------------------------------------------------------------------
  public Utilisateur authentification(String email, String password) throws ClientProtocolException, IOException
  {
    /*
     * Plusieurs choses à faire :
     *   - Préparer une requête POST de type formulaire (la
     *     requête est l'équivalent d'un formulaire Web)
     *       . Définir l'URL
     *       . Encapsuler les paramètres 
     *   - Envoyer la requête sur le serveur
     *   - Récupérer le résultat (données au format JSON
     *     représentant une utilisateur)
     *   - Convertir les données dans un format objet (classe
     *     utilisateur).
     *   
     */
    
    // Réquête POST qui envrerra des données de type formulaire
    
    HttpPost post = new HttpPost(URL_UTILISATEUR + "/authentification");
    post.setHeader("Content-Type",
                   "application/x-www-form-urlencoded;charset=UTF-8");
    
    // Encapsulation des paramètres : le formulaire possède 2 paramètres
    // (email et mot_de_passe). Attention c'est ici passé en clair mais
    // c'est peu important dans notre contexte.
    // Chaque paramètre est représenté dans une instance de NameValuePair
    // (interface) qui contient un couple (nom du param, valeur). Chaque
    // couple est ajouté dans une liste (List<NameValuePair>) qui représente
    // l'ensemble des valeurs.
    // Ces valeur sont ensuite encodées avant d'être incorporées au POST. 
    
    List<NameValuePair> values = new ArrayList<NameValuePair>();
    values.add(new BasicNameValuePair("email",        email));
    values.add(new BasicNameValuePair("mot_de_passe", password));
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values);
    post.setEntity(entity);

    // Envoi de la requête et récupération de la réponse.
    
    HttpResponse response = client.execute(post);
    
    // Décodage de la réponse.
    Utilisateur utilisateur = null;
    if(response != null)
    {
      String jsonUtilisateurData =  EntityUtils.toString(response.getEntity(), "UTF-8");
      JsonParser   jParser  = jFactory.createParser(jsonUtilisateurData);
      try
      {
        utilisateur = oMapper.readValue(jParser, Utilisateur.class);
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
