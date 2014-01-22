<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.ambSocialNetwork.data.model.Utilisateur,
                eu.telecom_bretagne.ambSocialNetwork.service.IServiceUtilisateur,
                eu.telecom_bretagne.ambSocialNetwork.front.utils.ServicesLocator,
                java.util.List"%>

<%
  IServiceUtilisateur serviceUtilisateur = (IServiceUtilisateur) ServicesLocator.getInstance().getRemoteInterface("ServiceUtilisateur");
  List<Utilisateur> utilisateurs = serviceUtilisateur.listeDesUtilisateurs();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Mise à jour d'un utilisateur</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>
  
  <body>
		<h2>Liste des utilisateur référencés :</h2>
		
		<table id="affichage">
		  <tr>
		    <th>Identifiant</th>
		    <th>Nom + prenom</th>
		    <th>Email</th>
        <th>Mise à jour</th>
		  </tr>
		  <%
		  for(Utilisateur utilisateur : utilisateurs)
		  {
		    %>
		    <tr>
		     <td><%=utilisateur.getId()%></td>
		     <td><%=utilisateur.getNom()%> <%=utilisateur.getPrenom()%></td>
		     <td><%=utilisateur.getEmail()%></td>
		     <td>
		       <a href="maj_utilisateur.jsp?id=<%=utilisateur.getId()%>"><img src="images/mise_a_jour.png" border="0" alt="Mise à jour de l'utilisateur"/></a>
		     </td>
		    </tr>
		    <%
		  }
		  %>
		</table>
  </body>
</html>