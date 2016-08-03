package br.com.procempa.openproject.integration.service;

import java.util.List;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.procempa.openproject.ejb.OPPersistanceServiceEJB;
import br.com.procempa.openproject.integration.entity.TotalTime;
import br.com.procempa.openproject.integration.entity.Users;
import br.com.procempa.openproject.integration.entity.WorkPackages;
import br.com.procempa.openproject.ldap.LdapAuthentication;

@Path("/integrator")
public class OPRestIntegrationService {
	@Inject
	OPPersistanceServiceEJB ejb;

	@GET
	@Path("/{param}")
	public Response echo(@PathParam("param") String msg) {
		return Response.status(200).entity(msg).build();
	}
	
	@GET
	@Path("workpackages/{param}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<WorkPackages> getWorkPackages(@PathParam("param") String userId) {
		List<WorkPackages> packages = ejb.getWorkPackages(userId);

		return packages;
	}
	
	@GET
	@Path("token/{param}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getEmailFromSecurityToken(@PathParam("param") String token) {
		return  ejb.geEmailFromSecurityToken(token);
	}

	@GET
	@Path("user/{param}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Users getUserInfoFromSecurityToken(@PathParam("param") String token) {
		return ejb.getUsersInfoFromSecurityToken(token);
	}
	
	@PUT
	@Path("timeentry/{userId}/{packageId}/{projectId}/{time}")
	public Response addTimeEntry(@PathParam("userId") int userId, @PathParam("packageId") int packageId,  
			@PathParam("projectId") int projectId,@PathParam("time") float time) {
		ejb.addTimeEntry(userId, packageId, projectId, time);

		return Response.status(201).build();
	}
	
	@PUT
	@Path("workpackages/{workPackageId}/{statusId}")
	public Response updateWorkpackageStatus(@PathParam("workPackageId") int workPackageId, @PathParam("statusId") int statusId) {
		ejb.updateWorkpackageStatus(workPackageId, statusId);

		return Response.status(202).build();
	}

	
	@GET
	@Path("timeentry/{userId}/")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<TotalTime> addTimeEntry(@PathParam("userId") int userId) {
		return ejb.geTotalNumberHours(userId); 
	}

	@POST
	@Path("user/authenticate")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response ldapAuthentication(@FormParam("userName") String userName,
            @FormParam("password") String password, @FormParam("url") String url) {
		try {
			LdapAuthentication.authenticateUser(userName, password, url);
			return Response.status(200).build();
		} catch (LoginException e) {
			return Response.status(401).build();
		} 
		
	}
	

}