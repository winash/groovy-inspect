package console;

import groovy.lang.Binding;
import jline.History;
import org.codehaus.groovy.tools.shell.Groovysh;
import org.codehaus.groovy.tools.shell.IO;
import org.codehaus.groovy.tools.shell.InteractiveShellRunner;

import java.io.File;
import java.io.IOException;

public class Console {

    private static final String INPUT = "inspect> ";
    private static final String OUTPUT = "-->";
    private static final String HISTORY = "history";
    private Binding binding;


    public void startConsoleOnPort(final IO io) {
        io.out.println();

        final Groovysh groovy = new Groovysh(binding, io);

        groovy.setResultHook(new ResultClosure(groovy, io, OUTPUT));
        groovy.setHistory(new History());

        final InteractiveShellRunner runner = new InteractiveShellRunner(groovy, new Prompt(groovy, INPUT));
        runner.setErrorHandler(new Error(runner, io));
        try {
            runner.setHistory(new History(new File(System.getProperty("user.home") + "/" + HISTORY)));
            runner.run();
        } catch (java.lang.Error e) {
            System.out.println("An error occured");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Console() {
        this.binding = new Binding();
        this.binding.setVariable("inspect",new ConsoleBinding());
    }

}