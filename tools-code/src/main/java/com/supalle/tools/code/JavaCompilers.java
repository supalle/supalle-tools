package com.supalle.tools.code;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Supalle
 */
public class JavaCompilers {

    private static final ConcurrentMap<ClassLoader, JavaCodeCompiler> COMPILER_CACHE = new ConcurrentHashMap<>();

    public static ConcurrentMap<ClassLoader, JavaCodeCompiler> getCompilerCache() {
        return COMPILER_CACHE;
    }

    public static JavaCodeCompiler getCompiler() {
        return getCompiler(null);
    }

    public static JavaCodeCompiler getCompiler(ClassLoader classLoader) {
        return getCompiler(classLoader, null);
    }

    public static JavaCodeCompiler getCompiler(ClassLoader classLoader, JdkCompiler jdkCompiler) {
        ClassLoader loader = (classLoader != null ? classLoader : getDefaultClassLoader());
        JavaCodeCompiler javaCodeCompiler = COMPILER_CACHE.get(loader);
        if (javaCodeCompiler == null) {
            synchronized (COMPILER_CACHE) {
                javaCodeCompiler = COMPILER_CACHE.get(loader);
                if (javaCodeCompiler != null) {
                    return javaCodeCompiler;
                }
                javaCodeCompiler = new JavaCodeCompiler(loader, jdkCompiler);
                COMPILER_CACHE.put(loader, javaCodeCompiler);
            }
        }
        return javaCodeCompiler;
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classloader = null;
        try {
            classloader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {
        }
        if (classloader == null) {
            classloader = JavaCodeCompiler.class.getClassLoader();
            if (classloader == null) {
                try {
                    classloader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ignored) {
                }
            }
        }
        return classloader;
    }
}
