package asm;

import org.objectweb.asm.AnnotationVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Winash
 */
public class ClassAnnotationAdapter extends AnnotationVisitor {

    String name;

    public String getName() {
        return name;
    }

    public ClassAnnotationAdapter(int api, AnnotationVisitor av) {
        super(api, av);
    }

    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
        if(name.equals("name"))
            this.name = (String) value;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        return super.visitAnnotation(name, desc);
    }
}
