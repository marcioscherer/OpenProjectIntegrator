package br.com.procempa.openproject.integration.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;



@ApplicationPath("resources")
public class OPRestIntegrationServiceStarter extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public OPRestIntegrationServiceStarter() {
		singletons.add(new OPRestIntegrationService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
