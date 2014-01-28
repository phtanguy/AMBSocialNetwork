package eu.telecom_bretagne.ambSocialNetwork.front.android;

//import turpin.mathieu.almanachdumarinbreton.R;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import eu.telecom_bretagne.ambSocialNetwork.R;
import eu.telecom_bretagne.ambSocialNetwork.data.controller.UtilisateurController;

public class LoginDialog extends Dialog
{
  //-----------------------------------------------------------------------------
  public LoginDialog(Context context)
  {
    super(context);

    this.setContentView(R.layout.login);
    this.setCancelable(false);
    this.setTitle("Connexion au serveur");

    final EditText emailaddr = (EditText) findViewById(R.id.email);
    final EditText password = (EditText) findViewById(R.id.password);
    this.show();

    Button login = (Button) findViewById(R.id.boutonLogin);
    login.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        String emailText = emailaddr.getText().toString();
        String passwordText = password.getText().toString();
        new AuthentificationAsyncTask().execute();
//        UtilisateurController utilisateurController = UtilisateurController.getInstance();
//        try
//        {
//          utilisateurController.authentification(emailText, passwordText);
//        }
//        catch (ClientProtocolException e)
//        {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//        }
        dismiss();
      }
    });

    Button cancel = (Button) findViewById(R.id.boutonCancel);
    cancel.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        dismiss();
      }
    });
  }
  //-----------------------------------------------------------------------------
  protected class AuthentificationAsyncTask extends AsyncTask<Void, Void, Void>
  {
    @Override
    protected Void doInBackground(Void... arg0)
    {
      UtilisateurController utilisateurController = UtilisateurController.getInstance();
      try
      {
        utilisateurController.authentification("philippe.tanguy@telecom-bretagne.eu", "amb");
        utilisateurController.authentification("mathieu.simonnet@telecom-bretagne.eu", "amb");
        utilisateurController.authentification("personne@telecom-bretagne.eu", "zobi");
      }
      catch (ClientProtocolException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return null;
    }
    
  }
  //-----------------------------------------------------------------------------
}
