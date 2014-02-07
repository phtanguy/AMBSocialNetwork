<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.data.model.CentreInteret,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceCentreInteret,
                eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator,
                java.util.List"%>

<%
  IServiceCentreInteret serviceCentreInteret = (IServiceCentreInteret) ServicesLocator.getInstance().getRemoteInterface("ServiceCentreInteret");
  List<CentreInteret> centresInteret = serviceCentreInteret.listeDesCentresInteret();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Liste des commentaires pour un centre d'intérêt</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Liste des commentaires pour un centre d'intérêt</h2>
    
    <form action="RestWebServices/centre_interet/comm_ci" method="post">
      <table id="affichage">
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
      </table>
      <p>
        <input type="submit" value="Chercher"/>
      </p>
    </form>
  </body>
</html>
