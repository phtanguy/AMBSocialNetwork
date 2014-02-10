package eu.telecom_bretagne.ambSocialNetwork.front.vaadin;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator;
import eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur;

@SuppressWarnings("serial")
@Theme("ambsocialnetworkservervaadin")
public class AmbSocialNetworkServerVaadinUI extends UI
{
  //-----------------------------------------------------------------------------
  private Table utilisateurList = new Table();
  private static final String ID              = "ID";
  private static final String NOM             = "Nom";
  private static final String PRENOM          = "Prénom";
  private static final String EMAIL           = "Email";
  private static final String MOT_DE_PASSE    = "Mot de passe";
  private static final String DESCRIPTION     = "Description";
  private static final String NB_COMMENTAIRES = "Nombre de commentaires";
  private static final String NB_AMIS         = "Nombre d'amis";
  private static final String NB_EST_AMI      = "Nombre \"est ami\"";
  
  private static IServiceUtilisateur serviceUtilisateur;
  static
  {
    try
    {
      serviceUtilisateur = (IServiceUtilisateur) ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
      System.out.println("####### Classe UtilisateurREST : composants EJB récupérés !");
      
    }
    catch (ServicesLocatorException e)
    {
      e.printStackTrace();
    }
  }
  //-----------------------------------------------------------------------------
  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = AmbSocialNetworkServerVaadinUI.class)
  public static class Servlet extends VaadinServlet
  {
  }
  //-----------------------------------------------------------------------------
  @Override
  protected void init(VaadinRequest request)
  {
    initUtilisateurList();
    
    final VerticalLayout layout = new VerticalLayout();
    layout.setMargin(true);
    setContent(layout);

//    Button button = new Button("Click Me");
//    button.addClickListener(new Button.ClickListener()
//    {
//      public void buttonClick(ClickEvent event)
//      {
//        layout.addComponent(new Label("Thank you for clicking"));
//      }
//    });
//    layout.addComponent(button);
    
    Label labelTitre = new Label("Liste des utilisateurs référencés");
    layout.addComponent(labelTitre);
    layout.addComponent(new Label(""));
    layout.addComponent(utilisateurList);
  }
  //-----------------------------------------------------------------------------
  private void initUtilisateurList()
  {
    utilisateurList.setContainerDataSource(populate());
    utilisateurList.setVisibleColumns(new Object[] { ID, NOM, PRENOM, EMAIL, MOT_DE_PASSE, DESCRIPTION, NB_COMMENTAIRES, NB_AMIS, NB_EST_AMI });
    utilisateurList.setSelectable(true);
    utilisateurList.setImmediate(true);

//    utilisateurList.addValueChangeListener(new Property.ValueChangeListener()
//    {
//      public void valueChange(ValueChangeEvent event)
//      {
//        Object contactId = contactList.getValue();
//        if (contactId != null)
//          editorFields.setItemDataSource(contactList.getItem(contactId));
//
//        editorLayout.setVisible(contactId != null);
//      }
//    });
  }
  //-----------------------------------------------------------------------------
  private Container populate()
  {
    IndexedContainer ic = new IndexedContainer();
    ic.addContainerProperty(ID,              Integer.class, 0);
    ic.addContainerProperty(NOM,             String.class, "");
    ic.addContainerProperty(PRENOM,          String.class, "");
    ic.addContainerProperty(EMAIL,           String.class, "");
    ic.addContainerProperty(MOT_DE_PASSE,    String.class, "");
    ic.addContainerProperty(DESCRIPTION,     String.class, "");
    ic.addContainerProperty(NB_COMMENTAIRES, Integer.class, 0);
    ic.addContainerProperty(NB_AMIS,         Integer.class, 0);
    ic.addContainerProperty(NB_EST_AMI,      Integer.class, 0);
    
    for(Utilisateur utilisateur : serviceUtilisateur.listeDesUtilisateurs())
    {
      Object o = ic.addItem();
      ic.getContainerProperty(o, ID).setValue(utilisateur.getId());
      ic.getContainerProperty(o, NOM).setValue(utilisateur.getNom());
      ic.getContainerProperty(o, PRENOM).setValue(utilisateur.getPrenom());
      ic.getContainerProperty(o, EMAIL).setValue(utilisateur.getEmail());
      ic.getContainerProperty(o, MOT_DE_PASSE).setValue(utilisateur.getMotDePasse());
      ic.getContainerProperty(o, DESCRIPTION).setValue(utilisateur.getDescription());
      ic.getContainerProperty(o, NB_COMMENTAIRES).setValue(utilisateur.getCommentaires().size());
      ic.getContainerProperty(o, NB_AMIS).setValue(utilisateur.getUtilisateurs2().size());
      ic.getContainerProperty(o, NB_EST_AMI).setValue(utilisateur.getUtilisateurs1().size());
    }
    return ic;
  }
  //-----------------------------------------------------------------------------
}