package core;

/**
 * @author Winash
 */
public class ManagedInstance {

    private final String clazz;
    private final Object instance;

    public ManagedInstance(Object instance) {
        this.instance = instance;
        this.clazz = instance.getClass().getCanonicalName();
    }

    private OnMethodEnter onMethodEnter;
    private OnMethodExit onMethodExit;

    @Override
    public String toString() {
        return "ManagedInstance - {" +
                "instance=" + instance +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
