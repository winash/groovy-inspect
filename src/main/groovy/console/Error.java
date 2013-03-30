package console;

import groovy.lang.Closure;
import org.codehaus.groovy.tools.shell.IO;

import java.io.BufferedReader;

public class Error extends Closure {

    private final IO io;

    public Error(final Object owner, final IO io) {
        super(owner);
        this.io = io;
    }

    public Object call(final Object[] args) {
        if (args.length > 0) {
            try {
                final Throwable e = (Throwable) args[0];
                String message = e.getMessage();
                if (null != message) {
                    message = message.replace("startup failed:", "");
                    io.err.println(message.trim());
                } else {
                    io.err.println(e);
                }
                e.printStackTrace(io.out);
                return null;
            } catch (Exception e) {
                io.out.println("Error: " + args[0]);
                return null;
            }
        } else {
            io.out.println("Oops.. I am Dead");
            return null;
        }
    }
}
