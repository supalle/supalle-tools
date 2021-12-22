package com.supalle.tools.code.bytecode;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class ClassFile {
    private final short minorVersion = 0;// u2
    private final short majorVersion = 52;// u2
    private short accessFlags;// u2
    private String thisClass;// u2
    private String superClass;// u2
    private Set<String> interfaces;
    private List<MethodInfo> methods;

    public static ClassFile create(String thisClass) {
        ClassFile classFile = new ClassFile();
        classFile.thisClass = thisClass;
        return classFile;
    }

    public ClassFile accessFlags(short accessFlags) {
        this.accessFlags = accessFlags;
        return this;
    }

    public ClassFile thisClass(String thisClass) {
        this.thisClass = thisClass;
        return this;
    }

    public ClassFile superClass(String superClass) {
        this.superClass = superClass;
        return this;
    }

    public ClassFile interfaces(Set<String> interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public ClassFile methods(List<MethodInfo> methods) {
        this.methods = methods;
        return this;
    }

}
