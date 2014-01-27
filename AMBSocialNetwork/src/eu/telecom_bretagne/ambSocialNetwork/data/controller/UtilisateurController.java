package eu.telecom_bretagne.ambSocialNetwork.data.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.telecom_bretagne.ambSocialNetwork.data.model.UtilisateursList;

public class UtilisateurController
{
  //-----------------------------------------------------------------------------
  private static UtilisateurController instance = null;
  
  private static final String hostname = "10.29.226.130";
  //private static final String hostname = "192.168.1.7";
  private static final String URL_UTILISATEUR_JSON = "http://" + hostname + ":8080/AMBSocialNetworkServerWeb/RestWebServices/utilisateur";
  
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
    HttpResponse response = null;
    response = client.execute(get);
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
    JsonParser   jParser  = jFactory.createJsonParser(jsonData);
    UtilisateursList ul = oMapper.readValue(jParser, UtilisateursList.class);
    return ul;
    //return oMapper.readValue(jParser, EntreprisesList.class);
  }
  //-----------------------------------------------------------------------------
}
