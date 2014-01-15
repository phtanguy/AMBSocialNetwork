package eu.telecom_bretagne.ambsocialnetwork;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btnAccesPage2 = (Button) findViewById(R.id.buttonAccesPage2);
    btnAccesPage2.setOnClickListener(this);
    Button btnQuitter = (Button) findViewById(R.id.buttonQuitter);
    btnQuitter.setOnClickListener(new OnClickListener()
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
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public void onClick(View v)
  {
    Intent affichePage2 = new Intent(this,Page2Activity.class);
    startActivity(affichePage2);
  }

}
