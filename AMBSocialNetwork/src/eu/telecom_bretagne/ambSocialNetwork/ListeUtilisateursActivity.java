package eu.telecom_bretagne.ambSocialNetwork;

import java.util.ArrayList;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ListeUtilisateursActivity extends Activity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste_utilisateurs);
    
    this.setTitle("Liste des utilisateurs");
    
    // Récup l'intent
    Intent afficheListeUtilisateurs = this.getIntent();
    TextView affichageResultat = (TextView) findViewById(R.id.textViewListeUtilisateursJSON);
    
    ArrayList<Utilisateur> utilisateurs = (ArrayList<Utilisateur>) afficheListeUtilisateurs.getExtras().get("contenuAAfficher");
    //String utilisateurs = (String) afficheListeUtilisateurs.getExtras().get("contenuAAfficher");
    
    //String contenuAAfficher = afficheListeUtilisateurs.getExtras().get("contenuAAfficher").getClass().getName();
    String contenuAAfficher = "";
    for(Utilisateur utilisateur : utilisateurs)
    {
      contenuAAfficher += "[" + utilisateur.getId() + "] " + utilisateur.getPrenom() + " - " + utilisateur.getNom() + " - " + utilisateur.getEmail() + "\n";
    }
    
    affichageResultat.setText(contenuAAfficher);



    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.liste_utilisateurs, menu);
    return true;
  }

}
