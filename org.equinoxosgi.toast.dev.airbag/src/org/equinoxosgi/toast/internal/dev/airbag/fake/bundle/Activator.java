package org.equinoxosgi.toast.internal.dev.airbag.fake.bundle;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.internal.dev.airbag.fake.FakeAirbag;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private FakeAirbag airbag;
	private ServiceRegistration registration;
	
		/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext ctx) throws Exception {
		airbag = new FakeAirbag();
		airbag.startup();
		registration = ctx.registerService(IAirbag.class.getName(), airbag, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext ctx) throws Exception {
		registration.unregister();
		airbag.shutdown();
	}

}
