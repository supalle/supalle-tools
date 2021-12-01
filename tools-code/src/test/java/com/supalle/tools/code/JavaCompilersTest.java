package com.supalle.tools.code;

import org.junit.jupiter.api.Test;

class JavaCompilersTest {

    @Test
    void testCompiler() {
        JavaCodeCompiler compiler = JavaCompilers.getCompiler();

        Class<?> clazz = compiler.compile("com.supalle.tools.code.Bean", "package com.supalle.tools.code;\n" +
                "\n" +
                "public class Bean {\n" +
                "\n" +
                "    private String firstname;\n" +
                "\n" +
                "    private String lastname;\n" +
                "}\n");

        System.out.println(clazz);

        JavaCompilers.getCompilerCache().clear();
        compiler = JavaCompilers.getCompiler();

         clazz = compiler.compile("com.supalle.tools.code.Bean", "package com.supalle.tools.code;\n" +
                "\n" +
                "public class Bean {\n" +
                "\n" +
                "    private String firstname;\n" +
                "\n" +
                "    private String lastname;\n" +
                "}\n");

        System.out.println(clazz);
    }

}