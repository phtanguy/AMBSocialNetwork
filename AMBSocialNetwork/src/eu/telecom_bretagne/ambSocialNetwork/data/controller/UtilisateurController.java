package eu.telecom_bretagne.ambSocialNetwork.data.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.telecom_bretagne.ambSocialNetwork.data.model.UtilisateursList;

public class UtilisateurController
{
  //-----------------------------------------------------------------------------
  private static UtilisateurController instance = null;
  
  private static final String hostname = "10.29.226.130";     // @IP Télécom Bretagne
  private static final String port     = "8080";
  /*
  private static final String hostname = "192.168.1.7";       // @IP Maison
  private static final String hostname = "192.108.117.199";   // @IP srv-labs-006
  private static final String port     = "80";
   */
  private static final String URL_UTILISATEUR_JSON = "http://" + hostname + ":" + port + "/AMBSocialNetworkServerWeb/RestWebServices/utilisateur";
  
  private static ObjectMapper oMapper;
  private static JsonFactory  jFactory;
  
  private static HttpClient client;

  //-----------------------------------------------------------------------------
  private UtilisateurController()
  {
    oMapper  = new ObjectMapper();
    jFactory = new JsonFactory();
    client   = new DefaultHttpClient();
  }
  //-----------------------------------------------------------------------------
  public static UtilisateurController getInstance()
  {
    if(instance == null)
      instance = new UtilisateurController();
    return instance;
  }
  //-----------------------------------------------------------------------------
  private String downloadContent(String url) throws ClientProtocolException, IOException
  {
    HttpGet      get = new HttpGet(url);
    HttpResponse response = client.execute(get);
    if(response != null)
    {
      return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
    return null;
  }
  //-----------------------------------------------------------------------------
  public String findAllText() throws IOException
  {
    String result = downloadContent(URL_UTILISATEUR_JSON + "/text");
    return result;
  }
  //-----------------------------------------------------------------------------
  public UtilisateursList findAllJson() throws IOException
  {
    String jsonData = downloadContent(URL_UTILISATEUR_JSON);
    JsonParser   jParser  = jFactory.createParser(jsonData);
    UtilisateursList ul = oMapper.readValue(jParser, UtilisateursList.class);
    return ul;
    //return oMapper.readValue(jParser, EntreprisesList.class);
  }
  //-----------------------------------------------------------------------------
  public String authentification(String email, String password) throws ClientProtocolException, IOException
  {
    HttpClient client = new DefaultHttpClient(); 
    HttpPost post = new HttpPost(URL_UTILISATEUR_JSON + "/authentification_form");
    post.setHeader("Content-Type",
                   "application/x-www-form-urlencoded;charset=UTF-8");
      
    NameValuePair emailPair    = new BasicNameValuePair("email",        email);
    NameValuePair passwordPair = new BasicNameValuePair("mot_de_passe", password);
    List<NameValuePair> values = new ArrayList<NameValuePair>();
    values.add(emailPair);
    values.add(passwordPair);
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values);
    post.setEntity(entity);
    HttpResponse response = client.execute(post);
    if(response != null)
    {
      String resultat = EntityUtils.toString(response.getEntity(), "UTF-8");
      Log.d("AMBSocialNetwork", "-----------------------------> authentification(" + email + ", " + password + ") = " + resultat);

      return resultat;
    }
    return null;
  }
  //-----------------------------------------------------------------------------
}
