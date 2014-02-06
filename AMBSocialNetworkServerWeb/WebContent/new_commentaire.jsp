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
  IServiceCentreInteret serviceCentreInteret = (IServiceCentreInteret) ServicesLocator.getInstance().getRemoteInterface("ServiceCentreInteret");
  
  List<Utilisateur>   utilisateurs   = serviceUtilisateur.listeDesUtilisateurs();
  List<CentreInteret> centresInteret = serviceCentreInteret.listeDesCentresInteret();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Ajout d'un commentaire</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Ajout d'un commentaire</h2>
    
    <form action="RestWebServices/centre_interet/new_comment" method="post">
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
        <tr>
          <th style="width: 170px;">Centre d'intérêt :</th>
          <td>
            <select name="id_centre_interet">
              <%
              for(CentreInteret ci : centresInteret)
              {
                %>
                <option value="<%=ci.getId()%>">[<%=ci.getId()%>] <%=ci.getNom() + " (description = " + ci.getDescription() + ")"%>
                <%
              }
              %>
            </select>
          </td>
        </tr>
        <tr>
          <th style="width: 170px;">Commentaire :</th>
          <td>
            <textarea rows="7" cols="70" name="contenu"></textarea>
          </td>
        </tr>
        <tr>
          <th style="width: 170px;">Partage :</th>
          <td>
            <input type=radio name="partage_public" value="true" checked="checked">Public
            <input type=radio name="partage_public" value="false">Privé
          </td>
        </tr>
      </table>
      <p>
        <input type="submit" value="Poster"/>
      </p>
    </form>
  </body>
</html>
