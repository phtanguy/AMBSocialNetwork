<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Poi,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Service,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,eu.telecom_bretagne.ambSocialNetwork.service.IServicePoint,eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator,java.util.List"%>

<%
  IServiceUtilisateur serviceUtilisateur = (IServiceUtilisateur) ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
  IServicePoint       servicePoint       = (IServicePoint)       ServicesLocator.getInstance().getRemoteInterface("ServicePoint");
  
  List<Utilisateur> utilisateurs = serviceUtilisateur.listeDesUtilisateurs();
  List<Poi>         pois         = servicePoint.listeDesPois();
  List<Service>     services     = servicePoint.listeDesServices();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Ajout d'un commentaire</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Ajout d'un commentaire</h2>
    
    <form action="RestWebServices/point/new_comment" method="post">
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
          <th style="width: 170px;">Point � commenter :</th>
          <td>
            <select name="id_point">
              <%
              for(Poi poi : pois)
              {
                %>
                <option value="<%=poi.getId()%>">POI : [<%=poi.getId()%>] <%=poi.getType()%>
                <%
              }
              for(Service service : services)
              {
                %>
                <option value="<%=service.getId()%>">SERVICE [<%=service.getId()%>] <%=service.getType() + " (description = " + service.getDescription() + ")"%>
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
            <input type=radio name="partage_public" value="false">Priv�
          </td>
        </tr>
      </table>
      <p>
        <input type="submit" value="Poster"/>
      </p>
    </form>
  </body>
</html>
