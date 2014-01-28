package eu.telecom_bretagne.ambSocialNetwork;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Liste2UtilisateursActivity extends Activity
{
  // -----------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste2_utilisateurs);
  }
  // -----------------------------------------------------------------------------
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.liste2_utilisateurs, menu);
    return true;
  }
  // -----------------------------------------------------------------------------
}
