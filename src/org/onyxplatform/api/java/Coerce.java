package org.onyxplatform.api.java;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.PersistentVector;

public class Coerce {
    
    private final static IFn kw;
    
    static {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("onyx.interop"));
        
        kw = Clojure.var("clojure.core", "keyword");
    }
    
    public static Object coerceWorkflow(Workflow wf) {
        IFn coerceWorkflow = Clojure.var("onyx.interop", "coerce-workflow");
        return coerceWorkflow.invoke(wf.workflow);
    }
    
    public static Object coerceCatalog(Catalog cat) {
        IFn coerceCatalog = Clojure.var("onyx.interop", "coerce-catalog");
        PersistentVector tasks = PersistentVector.EMPTY;
        
        for (Object entry : cat.tasks) {
            tasks = tasks.cons(((Task) entry).toMap());
        }
        
        return coerceCatalog.invoke(tasks);
    }
    
    public static Object coerceLifecycles(Lifecycles lifecycle) {
        IFn coerceLifecycles = Clojure.var("onyx.interop", "coerce-lifecycles");
        PersistentVector entries = PersistentVector.EMPTY;
        
        for (Object entry : lifecycle.calls) {
            entries = entries.cons(((LifecycleCalls) entry).toMap());
        }
        
        return coerceLifecycles.invoke(entries);
    }
    
    public static Object coerceTaskScheduler(TaskScheduler ts) {
        return kw.invoke(ts.toString());
    }
    
}