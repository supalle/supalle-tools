package com.supalle.tools.code;

import com.supalle.tools.code.bytecode.ClassMain;
import org.junit.jupiter.api.Test;

import static com.supalle.tools.code.bytecode.ClassMain.toFullClassName;

public class ClassTest {

    @Test
    void toFullClassNameTest() {
        Object object = new Object();
        System.out.println(toFullClassName(object.getClass()));
        object = new Object[0];
        System.out.println(toFullClassName(object.getClass()));
        object = new Object[3][10];
        System.out.println(toFullClassName(object.getClass()));
        object = new Object[3][10][100];
        System.out.println(toFullClassName(object.getClass()));
        object = new Object[3][10][10][10];
        System.out.println(toFullClassName(object.getClass()));
        object = new int[3][10][10][10];
        System.out.println(toFullClassName(object.getClass()));
        object = 100;
        System.out.println(toFullClassName(object.getClass()));
        object = 100.0d;
        System.out.println(toFullClassName(object.getClass()));
        System.out.println(toFullClassName(int.class));
    }

}
