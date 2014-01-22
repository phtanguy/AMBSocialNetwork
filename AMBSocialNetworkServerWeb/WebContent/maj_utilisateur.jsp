<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,
                eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator"%>

<%
  IServiceUtilisateur serviceUtilisateur = (IServiceUtilisateur) ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
  Utilisateur utilisateur = null;

  String id = request.getParameter("id");
  String action = request.getParameter("action");
  if(id != null && !id.equals(""))
  {
    int idUtilisateur = Integer.parseInt(id);
    utilisateur = serviceUtilisateur.getUtilisateur(idUtilisateur);
  }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Mise à jour d'un utilisateur</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Mise à jour d'un utilisateur</h2>
    
    <%
    if(utilisateur == null)
    {
      %>
      <h4>Utilisateur non trouvé !</h4>
      <a href="/AMBSocialNetworkServerWeb">Retour accueil</a>
      <%
    }
    else
    {
      %>
      <form action="RestWebServices/utilisateur/update" method="post">
        <input type="hidden" name="id" value="<%=utilisateur.getId()%>">
        <table id="affichage">
          <tr>
            <th style="width: 170px;">Identifiant :</th>
            <td>
              <input type="text" size="20" value="<%=utilisateur.getId()%>" disabled="disabled">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Nom :</th>
            <td>
              <input type="text" name="nom" size="20" maxlength="30" value="<%=utilisateur.getNom()%>">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Prénom :</th>
            <td>
              <input type="text" name="prenom" size="20" maxlength="30" value="<%=utilisateur.getPrenom()%>">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Email :</th>
            <td>
              <input type="text" name="email" size="50" maxlength="70" value="<%=utilisateur.getEmail()%>">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Mot de passe :</th>
            <td>
              <input type="text" name="mot_de_passe" size="10" maxlength="10" value="<%=utilisateur.getMotDePasse()%>">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">URL avatar :</th>
            <td>
              <input type="text" name="url_avatar" size="10" maxlength="10" value="<%=utilisateur.getUrlAvatar()%>">
            </td>
          </tr>
          <tr>
            <th>Description :</th>
            <td>
              <textarea rows="7" cols="70" name="description"><%=utilisateur.getDescription()%></textarea>
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Partage position :</th>
            <td>
              <input type="text" name="partage_position" size="10" maxlength="10" value="<%=utilisateur.getPartagePosition()%>">
            </td>
          </tr>
          <tr>
            <th style="width: 170px;">Partage position public :</th>
            <td>
              <input type="text" name="partage_position_public" size="10" maxlength="10" value="<%=utilisateur.getPartagePositionPublic()%>">
            </td>
          </tr>
          <%--
			          <tr>
			            <th style="width: 170px;">Latitude :</th>
			            <td>
			              <input type="text" name="latitude" size="10" maxlength="10" value="<%=utilisateur.getLatitude()%>">
			            </td>
			          </tr>
			          <tr>
			            <th style="width: 170px;">Longitude :</th>
			            <td>
			              <input type="text" name="longitude" size="10" maxlength="10" value="<%=utilisateur.getLongitude()%>">
			            </td>
			          </tr>
			          <tr>
			            <th style="width: 170px;">Cap :</th>
			            <td>
			              <input type="text" name="cap" size="10" maxlength="10" value="<%=utilisateur.getCap()%>">
			            </td>
			          </tr>
			          <tr>
			            <th style="width: 170px;">Vitesse :</th>
			            <td>
			              <input type="text" name="vitesse" size="10" maxlength="10" value="<%=utilisateur.getVitesse()%>">
			            </td>
			          </tr>
          --%>
        </table>
        <p>
          <input type="submit" value="Mettre à jour"/>
        </p>
      </form>
      <%
    }
    %>
  </body>
  
</html>