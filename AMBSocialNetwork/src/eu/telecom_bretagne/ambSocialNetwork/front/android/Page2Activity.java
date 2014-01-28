package eu.telecom_bretagne.ambSocialNetwork.front.android;

import java.io.IOException;

import eu.telecom_bretagne.ambSocialNetwork.R;
import eu.telecom_bretagne.ambSocialNetwork.R.id;
import eu.telecom_bretagne.ambSocialNetwork.R.layout;
import eu.telecom_bretagne.ambSocialNetwork.R.menu;
import eu.telecom_bretagne.ambSocialNetwork.data.controller.UtilisateurController;
import eu.telecom_bretagne.ambSocialNetwork.data.model.UtilisateursList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Page2Activity extends Activity implements OnClickListener
{
  // -----------------------------------------------------------------------------
  private   ProgressDialog progressDialog = null;
  protected Context        myContext;
  // -----------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_page2);
    myContext = this;
    
    Button btbListeUtilisateurs = (Button) findViewById(R.id.buttonListeUtilisateurs);
    btbListeUtilisateurs.setOnClickListener(this);
    
    Button btnRetourAccueil = (Button) findViewById(R.id.boutonAccueil);
    btnRetourAccueil.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        finish();
      }
    });
    
    Button btnConnexion = (Button) findViewById(R.id.boutonConnexion);
    btnConnexion.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        new LoginDialog(myContext);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.page2, menu);
    return true;
  }

  @Override
  public void onClick(View v)
  {
    //Intent intent = new Intent(this, ListeUtilisateursActivity.class);
    //startActivity(intent);
    
    progressDialog = ProgressDialog.show(this, "Chargement de la liste des utilisateurs", "En cours...");
    ChargementUtilisateursAsyncTask task = new ChargementUtilisateursAsyncTask();
    task.execute();
  }


  //-----------------------------------------------------------------------------
  protected class ChargementUtilisateursAsyncTask extends AsyncTask<String, Integer, UtilisateursList>
  //protected class ChargementUtilisateursAsyncTask extends AsyncTask<String, Integer, String>
  {
    //....................................................................
    @Override
    protected UtilisateursList doInBackground(String... params)
    //protected String doInBackground(String... params)
    {
      UtilisateurController utilisateurController = UtilisateurController.getInstance();
      try
      {
        // Pour tests...
        // Log.d("Cabinet de recrutement", entrepriseController.findAllText());
        // Log.d("Cabinet de recrutement", entrepriseController.findByIdJson(7).toString());
        // Log.d("Cabinet de recrutement", entrepriseController.findByIdText(7));
        
        Log.d("AMBSocialNetwork", "-----------------------------> " + utilisateurController.findAllJson().getClass().getName());
        
        return utilisateurController.findAllJson();
        //return utilisateurController.findAllText();
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return null;
    }
    //....................................................................
    @Override
    protected void onPostExecute(UtilisateursList response)
    //protected void onPostExecute(String response)
    {
      Intent afficheListeUtilisateurs = new Intent(myContext, ListeUtilisateursActivity.class);
      //Intent afficheListeUtilisateurs = new Intent(myContext, String.class);
      afficheListeUtilisateurs.putExtra("contenuAAfficher", response);

      progressDialog.dismiss();
      startActivity(afficheListeUtilisateurs);
    }
    //....................................................................
  }
  //-----------------------------------------------------------------------------

}
