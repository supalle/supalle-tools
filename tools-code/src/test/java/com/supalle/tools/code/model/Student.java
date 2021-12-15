package com.supalle.tools.code.model;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public class Student {

    private String firstname;
    private String lastname;
    private byte age;
    private int height;
    private double weight;
    private String homeAddress;
    private String mother;
    private String father;
    private List<String> brotherList;
    private List<String> sisterList;
    private List<String> teacherList;
    private List<String> schoolList;
    private List<String> hobbyList;
    private boolean whetherToMarry;
    private String createdBy;
    private Timestamp createdTime;
    private String updatedBy;
    private LocalDateTime updatedTime;

    public static Student mock() {
        Student student = new Student();
        student.setFirstname(UUID.randomUUID().toString());
        student.setLastname(UUID.randomUUID().toString());
        student.setAge((byte) 25);
        student.setHeight(175);
        student.setWeight(80.3d);
        student.setHomeAddress(UUID.randomUUID().toString());
        student.setMother(UUID.randomUUID().toString());
        student.setFather(UUID.randomUUID().toString());
        student.setBrotherList(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        student.setSisterList(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        student.setSchoolList(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        student.setHobbyList(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        student.setWhetherToMarry(true);
        student.setCreatedBy(UUID.randomUUID().toString());
        student.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        student.setUpdatedBy(UUID.randomUUID().toString());
        student.setUpdatedTime(LocalDateTime.now());
        return student;
    }

}
