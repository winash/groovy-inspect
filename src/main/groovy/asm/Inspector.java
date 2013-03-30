package asm;

/**
 * @author Winash
 */

import console.RemoteConnectorCli;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Inspector {

    private static Instrumentation instrumentation;
    public static Map<String, List<Object>> store = new HashMap<String, List<Object>>();
    public static Map<String,List<String>> methods = new HashMap<String, List<String>>();


    public static void premain(String args, Instrumentation inst) throws Exception {
        instrumentation = inst;
        instrumentation.addTransformer(new TracerClassFileTransformer());
        new RemoteConnectorCli().init();

    }


    /**
     * Programmatic hook to dynamically load javaagent at runtime.
     */
    public static void initialize() {
        if (instrumentation == null) {
            //
        }
    }

    public static void register(String name, Object instance) {
        if (!store.containsKey(name)) {
            store.put(name, new ArrayList<Object>());
            store.get(name).add(instance);
        } else
            store.get(name).add(instance);
    }

    public static void addManagedMethod(String name,String methodName){
        if (!methods.containsKey(name)) {
            methods.put(name, new ArrayList<String>());
            methods.get(name).add(methodName);
        } else
            methods.get(name).add(methodName);
    }




}
