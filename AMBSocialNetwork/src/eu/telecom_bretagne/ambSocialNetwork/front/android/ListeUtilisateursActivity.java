package eu.telecom_bretagne.ambSocialNetwork.front.android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import eu.telecom_bretagne.ambSocialNetwork.R;
import eu.telecom_bretagne.ambSocialNetwork.data.model.dto.UtilisateurDTO;

public class ListeUtilisateursActivity extends ListActivity
{
  //-----------------------------------------------------------------------------
  private static final String TAG_ID          = "id_utilisateur";
  private static final String TAG_NOM_COMPLET = "nom_complet_utilisateur";
  private static final String TAG_EMAIL       = "email_utilisateur";
  //-----------------------------------------------------------------------------
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste_utilisateurs);
    
    @SuppressWarnings("unchecked")
    ArrayList<UtilisateurDTO> utilisateurs = (ArrayList<UtilisateurDTO>) this.getIntent().getExtras().get("listeDesUtilisateurs");
    
    /**
     * Updating parsed JSON data into ListView
     * */
    ListView lv = getListView();
    ArrayList<HashMap<String, String>> listeAffichageUtilisateurs = new ArrayList<HashMap<String,String>>();
    for(UtilisateurDTO utilisateur : utilisateurs)
    {
      HashMap<String,String> utilisateurAffichage = new HashMap<String, String>();
      utilisateurAffichage.put(TAG_ID,          utilisateur.getId() + "");
      utilisateurAffichage.put(TAG_NOM_COMPLET, utilisateur.getNom() + " " + utilisateur.getPrenom());
      utilisateurAffichage.put(TAG_EMAIL,       utilisateur.getEmail());
      listeAffichageUtilisateurs.add(utilisateurAffichage);
    }
    
    ListAdapter adapter = new SimpleAdapter(ListeUtilisateursActivity.this, 
                                            listeAffichageUtilisateurs,
                                            R.layout.list_item_utilisateur,
                                            new String[] { TAG_ID, TAG_NOM_COMPLET, TAG_EMAIL}, 
                                            new int[] { R.id.id_utilisateur, R.id.nom_complet_utilisateur, R.id.email_utilisateur});
    
    lv.setAdapter(adapter);

  }
  //-----------------------------------------------------------------------------
  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.liste_utilisateurs2, menu);
    return true;
  }
  //-----------------------------------------------------------------------------
}
