package com.supalle.tools.code;

import com.supalle.tools.code.model.Student;

public class ToStringTest {
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
        System.out.println(ToString.of(fixedKeyMap, JsonFormat::format));
        System.out.println();
        Student student = Student.mock();
        System.out.println(student);
        System.out.println(ToString.of(student, JsonFormat::format));
    }

}
