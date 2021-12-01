package com.supalle.tools.code;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Supalle
 */
public class JavaCodeCompiler {


    private final CompilerClassLoader wrapperClassLoader;
    private final JdkCompiler jdkCompiler;

    public JavaCodeCompiler(ClassLoader classloader) {
        this(classloader, null);
    }

    public JavaCodeCompiler(ClassLoader classloader, JdkCompiler jdkCompiler) {
        this.wrapperClassLoader = new CompilerClassLoader(classloader);
        this.jdkCompiler = Optional.ofNullable(jdkCompiler).orElse(JdkCompiler.INSTANCE);
    }

    public Class<?> compile(String referenceName, byte[] codeContentBytes) {
        return compile(referenceName, new String(codeContentBytes, StandardCharsets.UTF_8));
    }

    public Class<?> compile(String referenceName, String codeContent) {
        return compileAll(referenceName, codeContent).get(referenceName);
    }

    public Map<String, Class<?>> compileAll(String referenceName, byte[] codeContentBytes) {
        return compileAll(referenceName, new String(codeContentBytes, StandardCharsets.UTF_8));
    }

    public Map<String, Class<?>> compileAll(String referenceName, String codeContent) {
        Map<String, byte[]> classBytes = jdkCompiler.compileToClassBytes(referenceName, codeContent);
        Map<String, Class<?>> clazzMap = new HashMap<>(classBytes.size());

        for (Map.Entry<String, byte[]> entry : classBytes.entrySet()) {
            String className = entry.getKey();
            byte[] outClassBytes = entry.getValue();
            Class<?> clazz = this.wrapperClassLoader.defineClass(className, outClassBytes);
            clazzMap.put(className, clazz);
        }
        return Collections.unmodifiableMap(clazzMap);
    }

    private static class CompilerClassLoader extends URLClassLoader {

        public CompilerClassLoader(ClassLoader classLoader) {
            super(new URL[0], classLoader);
        }

        public Class<?> defineClass(String referenceName, byte[] bytes) {
            return super.defineClass(referenceName, bytes, 0, bytes.length);
        }
    }
}
