package console;

import groovy.lang.Closure;

public class Prompt extends Closure {

    private final String inputPrompt;

    public Prompt(final Object owner, final String inputPrompt) {
        super(owner);
        this.inputPrompt = inputPrompt;
    }

    public Object call() {
        return this.inputPrompt;
    }
}
