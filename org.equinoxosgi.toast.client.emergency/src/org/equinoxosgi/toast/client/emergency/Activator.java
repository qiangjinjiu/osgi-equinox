package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.Airbag;
import org.equinoxosgi.toast.dev.gps.Gps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	private Airbag airbag;
	private Gps gps;
	private EmergencyMonitor monitor;
	
	@Override
	public void start(BundleContext arg0) throws Exception {
		System.out.println("launching");
		gps = new Gps();
		airbag = new Airbag();
		monitor = new EmergencyMonitor();
		monitor.setAirbag(airbag);
		monitor.setGps(gps);
		monitor.startup();
		airbag.deploy();
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		monitor.shutdown();
		System.out.println("Terminating");
	}
	
}
