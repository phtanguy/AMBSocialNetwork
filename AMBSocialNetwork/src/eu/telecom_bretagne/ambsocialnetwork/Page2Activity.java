package eu.telecom_bretagne.ambsocialnetwork;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Page2Activity extends Activity implements OnClickListener
{
  private   ProgressDialog progressDialog = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_page2);
    
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
  protected class ChargementUtilisateursAsyncTask extends AsyncTask<String, Integer, EntreprisesList>
  {
    //....................................................................
    @Override
    protected EntreprisesList doInBackground(String... params)
    {
      EntrepriseController entrepriseController = EntrepriseController.getInstance();
      try
      {
        // Pour tests...
        // Log.d("Cabinet de recrutement", entrepriseController.findAllText());
        // Log.d("Cabinet de recrutement", entrepriseController.findByIdJson(7).toString());
        // Log.d("Cabinet de recrutement", entrepriseController.findByIdText(7));
        
        return entrepriseController.findAllJson();
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
    protected void onPostExecute(EntreprisesList response)
    {
      Intent afficheListeEntreprises = new Intent(myContext, AffichageActivity.class);
      afficheListeEntreprises.putExtra("contenuAAfficher", response);

      progressDialog.dismiss();
      startActivity(afficheListeEntreprises);
    }
    //....................................................................
  }
  //-----------------------------------------------------------------------------

}
