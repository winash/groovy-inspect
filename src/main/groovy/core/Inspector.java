package core;

/**
 * @author Winash
 */

import asm.TracerClassFileTransformer;
import console.RemoteConnectorCli;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Inspector {

    private static Instrumentation instrumentation;
    public static Map<String, List<ManagedInstance>> store = new HashMap<String, List<ManagedInstance>>();


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
            store.put(name, new ArrayList<ManagedInstance>());
            store.get(name).add(new ManagedInstance(instance));
        } else
            store.get(name).add(new ManagedInstance(instance));
    }


}
