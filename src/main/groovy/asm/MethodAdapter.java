package asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author Winash
 */
public class MethodAdapter extends AdviceAdapter {
    private int access;
    private String name;
    private ClassAnnotationAdapter classAnnotationAdapter;
    private boolean managed;
    private String className;

    protected MethodAdapter(final int api, final MethodVisitor mv, final int access, final String name, final String desc) {
        super(Opcodes.ASM4, mv, access, name, desc);
        this.access = access;
        this.name = name;
    }

    public void setClassAnnotationAdapter(ClassAnnotationAdapter classAnnotationAdapter) {
        this.classAnnotationAdapter = classAnnotationAdapter;
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (!name.equals("<init>"))
            return;
        super.visitLdcInsn(classAnnotationAdapter.getName());
        super.visitVarInsn(ALOAD, 0);
        super.visitMethodInsn(INVOKESTATIC, "core/Inspector", "register", "(Ljava/lang/String;Ljava/lang/Object;)V");

    }

    public void setClassName(String className) {
        this.className = className;
    }
}
