package com.supalle.tools.code.bytecode;

import lombok.Getter;

import java.util.List;

@Getter
public class MethodInfo {
    private short accessFlags;// u2
    private String name;
    private List<String> paramTypes;
    private String returnType;
    private List<CodeRow> codes;

    public static MethodInfo create(String name) {
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.name = name;
        return methodInfo;
    }

    public MethodInfo accessFlags(short accessFlags) {
        this.accessFlags = accessFlags;
        return this;
    }

    public MethodInfo paramTypes(List<String> paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    public MethodInfo returnType(String returnType) {
        this.returnType = returnType;
        return this;
    }

    public MethodInfo codes(List<CodeRow> codes) {
        this.codes = codes;
        return this;
    }

}
