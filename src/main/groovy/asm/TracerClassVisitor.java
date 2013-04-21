package asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Winash
 */
public class TracerClassVisitor extends ClassVisitor {

    private boolean annotated = false;
    private ClassAnnotationAdapter classAnnotationAdapter;
    private String className;

    public TracerClassVisitor(int api, ClassVisitor cv,String className) {
        super(api, cv);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
        if(!annotated)
            return methodVisitor;
        methodVisitor = new MethodAdapter(Opcodes.ASM4, methodVisitor, access, name, desc);
        ((MethodAdapter)methodVisitor).setClassAnnotationAdapter(classAnnotationAdapter);
        ((MethodAdapter)methodVisitor).setClassName(className);

        return methodVisitor;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(desc, visible);
        if (desc.equals("Lcore/Managed;")) {
            this.annotated = true;
            classAnnotationAdapter = new ClassAnnotationAdapter(Opcodes.ASM4, annotationVisitor);
            return classAnnotationAdapter;
        }
        return annotationVisitor;
    }
}
