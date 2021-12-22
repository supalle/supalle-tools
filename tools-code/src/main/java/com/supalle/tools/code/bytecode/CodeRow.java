package com.supalle.tools.code.bytecode;

import lombok.Getter;

@Getter
public class CodeRow {
    private byte opcode;
    private Object[] params;

    public static CodeRow of(byte opcode, Object... params) {
        CodeRow codeRow = new CodeRow();
        codeRow.opcode = opcode;
        codeRow.params = params;
        return codeRow;
    }
}
