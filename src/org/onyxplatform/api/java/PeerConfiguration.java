package org.onyxplatform.api.java;

import clojure.lang.PersistentHashMap;
import java.util.Map;

public class PeerConfiguration 
	extends OnyxEntity
{
	protected static String coerceKw = "peer-config";

    	public PeerConfiguration () {
	    	super();
    	}
    	
    	private PeerConfiguration(PeerConfiguration cfg) {
	    	super( cfg.entry );
	}

	protected PersistentHashMap coerce(Map<String, Object> jMap) {
		return (PersistentHashMap) super.castTypesFn.invoke( coerceKw, jMap);
	}
}
