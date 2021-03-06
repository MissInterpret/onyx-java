package org.onyxplatform.api.java;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.PersistentArrayMap;

public class API {

    static {       
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("onyx.api"));
    }
    
    public static Object startEnv(EnvConfiguration envConfig) {
        IFn startEnv = Clojure.var("onyx.api", "start-env");        
        Object coercedConfig = envConfig.toCljMap();
        return startEnv.invoke(coercedConfig);
    }
    
    public static Object startPeerGroup(PeerConfiguration peerConfig) {
        IFn startPeerGroup = Clojure.var("onyx.api", "start-peer-group");
        Object coercedConfig = peerConfig.toCljMap();
        return startPeerGroup.invoke(coercedConfig);
    }
    
    public static Object startPeers(int nPeers, Object peerGroup) {
        IFn startPeers = Clojure.var("onyx.api" , "start-peers");
        return startPeers.invoke(nPeers, peerGroup);
    }
    
    public static Object shutdownEnv(Object env) {
        IFn shutdownEnv = Clojure.var("onyx.api", "shutdown-env");
        return shutdownEnv.invoke(env);
    }
    
    public static Object shutdownPeerGroup(Object peerGroup) {
        IFn shutdownPeerGroup = Clojure.var("onyx.api", "shutdown-peer-group");
        return shutdownPeerGroup.invoke(peerGroup);
    }
    
    public static Object shutdownPeers(Object peers) {
        IFn shutdownPeer = Clojure.var("onyx.api", "shutdown-peers");
        return shutdownPeer.invoke(peers);
    }
    
    public static boolean submitJob(PeerConfiguration peerConfig, Job job) {

        IFn submitJob = Clojure.var("onyx.api", "submit-job");
        
	Object coercedJob = job.toCljMap();
        Object coercedConfig = peerConfig.toCljMap();
        
        return (boolean) submitJob.invoke(coercedConfig, coercedJob);
    }
    
    public static boolean killJob(PeerConfiguration peerConfig, String jobID) {
        IFn killJob = Clojure.var("onyx.api", "kill-job");
        Object coercedConfig = peerConfig.toCljMap();
        return (boolean) killJob.invoke(coercedConfig, jobID);
    }
    
    public static boolean gc(PeerConfiguration peerConfig) {
        IFn gc = Clojure.var("onyx.api", "gc");
        Object coercedConfig = peerConfig.toCljMap();
        return (boolean) gc.invoke(coercedConfig);
    }
    
    public static boolean awaitJobCompletion(PeerConfiguration peerConfig, String jobID) {
        IFn awaitJobCompletion = Clojure.var("onyx.api", "await-job-completion");
        Object coercedConfig = peerConfig.toCljMap();
        return (boolean) awaitJobCompletion.invoke(coercedConfig, jobID);
    }
}
