package eu.telecom_bretagne.ambSocialNetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AfficheUtilisateurActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_affiche_utilisateur);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.affiche_utilisateur, menu);
    return true;
  }

}
