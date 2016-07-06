package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	private IAirbag airbag;
	private ServiceReference airbagRef;
	private IGps gps;
	private ServiceReference gpsRef;
	
	private EmergencyMonitor monitor;
	
	
	@Override
	public void start(BundleContext ctx) throws Exception {
		System.out.println("launching");
		monitor = new EmergencyMonitor();
		gpsRef = ctx.getServiceReference(IGps.class.getName());
		airbagRef = ctx.getServiceReference(IAirbag.class.getName());
		
		if(gpsRef == null || airbagRef == null) {
			System.err.println("Unable to acquire GPS or airbag!");
			return;
		}
		
		gps = (IGps)ctx.getService(gpsRef);
		airbag = (IAirbag)ctx.getService(airbagRef);
		if(gps == null || airbag == null) {
			System.err.println("Unable to acquire GPS or airbag");
			return;
		}
		
		monitor.setAirbag(airbag);
		monitor.setGps(gps);
		monitor.startup();
	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		monitor.shutdown();
		if(gpsRef != null) {
			ctx.ungetService(gpsRef);
		}
		if(airbagRef != null) {
			ctx.ungetService(airbagRef);
		}
		System.out.println("Terminating");
	}
	
}
