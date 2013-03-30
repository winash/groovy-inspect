package console;

import groovy.lang.Closure;
import org.codehaus.groovy.tools.shell.IO;

import java.util.Iterator;
import java.util.Map;

public class ResultClosure extends Closure {
    private final String resultPrompt;
    private final IO io;

    public ResultClosure(final Object owner, final IO io, final String resultPrompt) {
        super(owner);
        this.io = io;
        this.resultPrompt = resultPrompt;
    }

    public Object call(final Object[] args) {
        Object result = args[0];
        Iterator iter;
        if (result instanceof Iterator) {
            iter = (Iterator) result;
        } else if (result instanceof Iterable) {
            iter = ((Iterable) result).iterator();
        } else if (result instanceof Object[]) {
            iter = new ArrayIterator((Object[]) result);
        } else if (result instanceof Map) {
            iter = ((Map) result).entrySet().iterator();
        } else {
            iter = new ArrayIterator(result);
        }

        while (iter.hasNext()) {
            this.io.out.println(this.resultPrompt + iter.next());
        }

        return null;
    }
}