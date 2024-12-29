package com.supalle.tools.code;

import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedKeyMapTest {
    static final int SIZE = 100;

    public static void main(String[] args) {
        FixedKeyIndexes<String> fixedKeyIndexes = FixedKeyIndexes.of("firstname", "lastname", "age", "height", "weight", "homeAddress", "mother", "father");
        System.out.println(fixedKeyIndexes);
        FixedKeyMap<String, Object> fixedKeyMap = new FixedKeyMap<>(fixedKeyIndexes);
        fixedKeyMap.put("firstname", "张三");
        fixedKeyMap.put("lastname", "尼古拉");
        fixedKeyMap.put("age", (byte) (10));
        fixedKeyMap.put("height", 185);
        fixedKeyMap.put("weight", 50.5D);
        fixedKeyMap.put("homeAddress", "homeAddress");
        fixedKeyMap.put("mother", "mother");
        fixedKeyMap.put("father", "father");
        System.out.println(fixedKeyMap);
        System.out.println("Total memory size of fixedKeyMap: " + GraphLayout.parseInstance(fixedKeyMap).totalSize());

        int size = 100;
        System.out.printf("字段数：%d ， 行数：%d ：\n", fixedKeyIndexes.size(), size);
        System.out.println("Total memory size of buildFixedKeyMaps  : " + GraphLayout.parseInstance(buildFixedKeyMaps(size)).totalSize());
        System.out.println("Total memory size of buildHashMaps      : " + GraphLayout.parseInstance(buildHashMaps(size)).totalSize());
        System.out.println("Total memory size of buildStudents      : " + GraphLayout.parseInstance(buildStudents(size)).totalSize());
        System.gc();
        size = 1000;
        System.out.printf("字段数：%d ， 行数：%d ：\n", fixedKeyIndexes.size(), size);
        System.out.println("Total memory size of buildFixedKeyMaps  : " + GraphLayout.parseInstance(buildFixedKeyMaps(size)).totalSize());
        System.out.println("Total memory size of buildHashMaps      : " + GraphLayout.parseInstance(buildHashMaps(size)).totalSize());
        System.out.println("Total memory size of buildStudents      : " + GraphLayout.parseInstance(buildStudents(size)).totalSize());
        System.gc();
        size = 10000;
        System.out.printf("字段数：%d ， 行数：%d ：\n", fixedKeyIndexes.size(), size);
        System.out.println("Total memory size of buildFixedKeyMaps  : " + GraphLayout.parseInstance(buildFixedKeyMaps(size)).totalSize());
        System.out.println("Total memory size of buildHashMaps      : " + GraphLayout.parseInstance(buildHashMaps(size)).totalSize());
        System.out.println("Total memory size of buildStudents      : " + GraphLayout.parseInstance(buildStudents(size)).totalSize());
        System.gc();
        size = 100000;
        System.out.printf("字段数：%d ， 行数：%d ：\n", fixedKeyIndexes.size(), size);
        System.out.println("Total memory size of buildFixedKeyMaps  : " + GraphLayout.parseInstance(buildFixedKeyMaps(size)).totalSize());
        System.out.println("Total memory size of buildHashMaps      : " + GraphLayout.parseInstance(buildHashMaps(size)).totalSize());
        System.out.println("Total memory size of buildStudents      : " + GraphLayout.parseInstance(buildStudents(size)).totalSize());
    }

    public static List<Map<String, Object>> buildFixedKeyMaps(int size) {
        long start = System.currentTimeMillis();
        FixedKeyIndexes<String> fixedKeyIndexes = FixedKeyIndexes.of("firstname", "lastname", "age", "height", "weight", "homeAddress", "mother", "father");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FixedKeyMap<String, Object> map = new FixedKeyMap<>(fixedKeyIndexes);
            map.put("firstname", "张三" + i);
            map.put("lastname", "尼古拉" + i);
            map.put("age", (byte) (10 + i));
            map.put("height", 185 + i);
            map.put("weight", 50.5D + i);
            map.put("homeAddress", "homeAddress" + i);
            map.put("mother", "mother" + i);
            map.put("father", "father" + i);
            list.add(map);
        }
        System.out.printf("buildFixedKeyMaps size %d: %5d ms. ", size, (System.currentTimeMillis() - start));
        return list;
    }

    public static List<Map<String, Object>> buildHashMaps(int size) {
        long start = System.currentTimeMillis();
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HashMap<String, Object> map = new HashMap<>(3, 1);
            map.put("firstname", "张三" + i);
            map.put("lastname", "尼古拉" + i);
            map.put("age", (byte) (10 + i));
            map.put("height", 185 + i);
            map.put("weight", 50.5D + i);
            map.put("homeAddress", "homeAddress" + i);
            map.put("mother", "mother" + i);
            map.put("father", "father" + i);
            list.add(map);
        }
        System.out.printf("buildFixedKeyMaps size %d: %5d ms. ", size, (System.currentTimeMillis() - start));
        return list;
    }

    public static List<Student> buildStudents(int size) {
        long start = System.currentTimeMillis();
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Student student = new Student();
            student.firstname = "张三" + i;
            student.lastname = "尼古拉" + i;
            student.age = (byte) (10 + i);
            student.height = 185 + i;
            student.weight = 50.5D + i;
            student.homeAddress = "homeAddress" + i;
            student.mother = "mother" + i;
            student.father = "father" + i;
            list.add(student);
        }
        System.out.printf("buildFixedKeyMaps size %d: %5d ms. ", size, (System.currentTimeMillis() - start));
        return list;
    }

    static class Student {
        String firstname;
        String lastname;
        byte age;
        int height;
        double weight;
        String homeAddress;
        String mother;
        String father;
    }
}
