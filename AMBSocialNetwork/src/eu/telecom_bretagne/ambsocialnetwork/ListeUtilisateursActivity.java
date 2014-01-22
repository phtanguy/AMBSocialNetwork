package eu.telecom_bretagne.ambsocialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListeUtilisateursActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste_utilisateurs);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.liste_utilisateurs, menu);
    return true;
  }

}
