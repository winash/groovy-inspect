package core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Winash
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Managed {
    /**
     * @return unique name for Objects to be managed through the Managed
     */
    String name();
}
