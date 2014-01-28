package eu.telecom_bretagne.ambSocialNetwork.front.android;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import eu.telecom_bretagne.ambSocialNetwork.R;
import eu.telecom_bretagne.ambSocialNetwork.data.controller.UtilisateurController;
import eu.telecom_bretagne.ambSocialNetwork.data.model.UtilisateursList;

public class Page2Activity extends Activity
{
  //-----------------------------------------------------------------------------
  private   ProgressDialog progressDialog = null;
  //protected Context        myContext;
  //-----------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_page2);
    //myContext = this;
    
    Button btbListeUtilisateurs = (Button) findViewById(R.id.buttonListeUtilisateurs);
    //btbListeUtilisateurs.setOnClickListener(this);
    btbListeUtilisateurs.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        new ChargementUtilisateursAsyncTask().execute();
      }
    });
    
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
        new LoginDialog(Page2Activity.this);
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

  //-----------------------------------------------------------------------------
  protected class ChargementUtilisateursAsyncTask extends AsyncTask<String, Integer, UtilisateursList>
  {
    @Override
    protected void onPreExecute()
    {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Page2Activity.this);
      progressDialog.setTitle("Chargement de la liste des utilisateurs");
      progressDialog.setMessage("En cours...");
      progressDialog.setCancelable(true);
      progressDialog.show();
    }
    @Override
    protected UtilisateursList doInBackground(String... params)
    {
      UtilisateurController utilisateurController = UtilisateurController.getInstance();
      try
      {
        Log.d("AMBSocialNetwork", "-----------------------------> " + utilisateurController.findAllJson().getClass().getName());
        return utilisateurController.findAllJson();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      return null;
    }
    @Override
    protected void onPostExecute(UtilisateursList response)
    {
      //Intent afficheListeUtilisateurs = new Intent(Page2Activity.this, ListeUtilisateursActivity.class);
      Intent afficheListeUtilisateurs = new Intent(Page2Activity.this, ListeUtilisateursActivity.class);
      afficheListeUtilisateurs.putExtra("listeDesUtilisateurs", response);

      progressDialog.dismiss();
      startActivity(afficheListeUtilisateurs);
    }
  }
  //-----------------------------------------------------------------------------

}
