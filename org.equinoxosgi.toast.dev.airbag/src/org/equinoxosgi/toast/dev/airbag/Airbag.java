package org.equinoxosgi.toast.dev.airbag;

import java.util.ArrayList;
import java.util.List;

public class Airbag {
	private List<IAirbagListener> listeners;

	public Airbag() {
		super();
		listeners = new ArrayList<IAirbagListener>();
	}
	
	public synchronized void addListener(IAirbagListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void deploy() {
		for (IAirbagListener listener : listeners) {
			listener.deployed();
		}
	}
	
	public synchronized void removeListener(IAirbagListener listener) {
		listeners.remove(listener);
	}
	
}
