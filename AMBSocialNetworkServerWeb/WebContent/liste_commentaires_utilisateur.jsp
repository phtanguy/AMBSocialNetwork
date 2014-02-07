<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceCentreInteret,
                eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator,
                java.util.List"%>

<%
  IServiceUtilisateur   serviceUtilisateur   = (IServiceUtilisateur)   ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
  List<Utilisateur>   utilisateurs   = serviceUtilisateur.listeDesUtilisateurs();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Liste des commentaires pour un utilisateur</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Liste des commentaires pour un utilisateur</h2>
    
    <form action="RestWebServices/centre_interet/comm_ut" method="post">
      <table id="affichage">
        <tr>
          <th style="width: 170px;">Utilisateur :</th>
          <td>
            <select name="id_utilisateur">
              <%
              for(Utilisateur u : utilisateurs)
              {
                %>
                <option value="<%=u.getId()%>">[<%=u.getId()%>] <%=u.getPrenom() + " " + u.getNom()%>
                <%
              }
              %>
            </select>
          </td>
        </tr>
      </table>
      <p>
        <input type="submit" value="Chercher"/>
      </p>
    </form>
  </body>
</html>
