<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Poi,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Service,
                eu.telecom_bretagne.ambSocialNetwork.data.model.Commentaire,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,eu.telecom_bretagne.ambSocialNetwork.service.IServicePoint,eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator,java.util.List"%>

<%
  IServicePoint       servicePoint       = (IServicePoint)       ServicesLocator.getInstance().getRemoteInterface("ServicePoint");
  List<Poi>         pois         = servicePoint.listeDesPois();
  List<Service>     services     = servicePoint.listeDesServices();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Liste des commentaires pour un point (POI ou service)</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
    <h2>Liste des commentaires pour un point (POI ou service)</h2>
    
    <form action="RestWebServices/point/comm_point" method="post">
      <table id="affichage">
        <tr>
          <th style="width: 170px;">Centre d'intérêt :</th>
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
      </table>
      <p>
        <input type="submit" value="Chercher"/>
      </p>
    </form>
  </body>
</html>
