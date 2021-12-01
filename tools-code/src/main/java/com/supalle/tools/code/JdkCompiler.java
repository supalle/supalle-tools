package com.supalle.tools.code;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.CharBuffer;
import java.util.*;

/**
 * @author Supalle
 */
public class JdkCompiler {

    public static final JdkCompiler INSTANCE = new JdkCompiler();

    private final JavaCompiler compiler;
    private final JavaFileManager javaFileManager;
    private final List<String> compilerArgs;

    public JdkCompiler(String... compilerArgs) {
        this(compilerArgs == null ? null : Arrays.asList(compilerArgs));
    }

    public JdkCompiler(List<String> compilerArgs) {
        // 获取系统Java编译器
        this.compiler = ToolProvider.getSystemJavaCompiler();
        // 获取Java文件管理器
        StandardJavaFileManager fileManager = this.compiler.getStandardFileManager(null, null, null);
        this.javaFileManager = new WrapperJavaFileManager(fileManager);
        // 编译参数
        if (compilerArgs == null || compilerArgs.isEmpty()) {
            compilerArgs = Arrays.asList("-source", "1.8", "-target", "1.8");//,"-Xlint:none"
        }
        this.compilerArgs = compilerArgs;
    }

    public Map<String, byte[]> compileToClassBytes(String referenceName, String codeContent) {
        //生成编译任务
        WrapperJavaFileObject wrapperJavaFileObject = new WrapperJavaFileObject(referenceName, codeContent);
        List<JavaFileObject> compilationUnits = Collections.singletonList(wrapperJavaFileObject);
        JavaCompiler.CompilationTask task;
        try (PrintWriter printWriter = new PrintWriter(new ByteArrayOutputStream())) {
            task = compiler.getTask(printWriter, this.javaFileManager, null
                    , this.compilerArgs, null, compilationUnits);
        }
        //执行编译任务
        task.call();
        return Collections.unmodifiableMap(wrapperJavaFileObject.getOutClassBytesMap());
    }

    @SuppressWarnings("all")
    private static class WrapperJavaFileObject extends SimpleJavaFileObject {
        private final String referenceName;
        private final String codeContent;
        private Map<String, byte[]> outClassBytesMap = new LinkedHashMap<>();

        public WrapperJavaFileObject(String referenceName, String codeContent) {
            super(URI.create(referenceName.substring(referenceName.lastIndexOf('.') + 1) + ".java"), Kind.SOURCE);
            this.referenceName = referenceName;
            this.codeContent = codeContent;
        }

        public CharBuffer getCharContent(boolean b) {
            return CharBuffer.wrap(this.codeContent);
        }

        public Map<String, byte[]> getOutClassBytesMap() {
            return outClassBytesMap;
        }

        public void setOutClassBytesMap(Map<String, byte[]> outClassBytesMap) {
            this.outClassBytesMap = outClassBytesMap;
        }

    }

    private static class WrapperClassFileObject extends SimpleJavaFileObject {
        private final String className;
        private final WrapperJavaFileObject wrapperJavaFileObject;

        public WrapperClassFileObject(String className, WrapperJavaFileObject wrapperJavaFileObject) {
            super(URI.create(className + ".class"), Kind.CLASS);
            this.className = className;
            this.wrapperJavaFileObject = wrapperJavaFileObject;
        }

        public OutputStream openOutputStream() {
            return new FilterOutputStream(new ByteArrayOutputStream()) {
                public void close() throws IOException {
                    out.close();
                    ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
                    wrapperJavaFileObject.getOutClassBytesMap().put(className, bos.toByteArray());
                }
            };
        }
    }

    private static class WrapperJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

        protected WrapperJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        public JavaFileObject getJavaFileForOutput(Location location,
                                                   String className,
                                                   JavaFileObject.Kind kind,
                                                   FileObject sibling) throws IOException {
            if (kind == JavaFileObject.Kind.CLASS) {
                return new WrapperClassFileObject(className, (WrapperJavaFileObject) sibling);
            } else {
                return super.getJavaFileForOutput(location, className, kind, sibling);
            }
        }

    }

}
