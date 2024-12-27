package com.supalle.tools.code.beanwrapper;

import com.supalle.tools.code.BeanWrappers;
import com.supalle.tools.code.model.Student;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.cglib.beans.BeanCopier;

import java.nio.file.Paths;

@State(Scope.Benchmark)
public class BeanWrapperBenchmark {

    private final Student bean = Student.mock();

    @Benchmark
    public Object setter() {
        Student newBean = new Student();
        final Student bean = this.bean;
        newBean.setFirstname(bean.getFirstname());
        newBean.setLastname(bean.getLastname());
        {
            newBean.setAge(bean.getAge());
            newBean.setHeight(bean.getHeight());
            newBean.setWeight(bean.getWeight());
            newBean.setHomeAddress(bean.getHomeAddress());
            newBean.setMother(bean.getMother());
            newBean.setFather(bean.getFather());
            newBean.setBrotherList(bean.getBrotherList());
            newBean.setSisterList(bean.getSisterList());
            newBean.setSchoolList(bean.getSchoolList());
            newBean.setHobbyList(bean.getHobbyList());
            newBean.setWhetherToMarry(bean.isWhetherToMarry());
            newBean.setCreatedBy(bean.getCreatedBy());
            newBean.setCreatedTime(bean.getCreatedTime());
            newBean.setUpdatedBy(bean.getUpdatedBy());
            newBean.setUpdatedTime(bean.getUpdatedTime());
        }
        return newBean;
    }

    @Benchmark
    public Object supalleBeanWrapper() {
        return BeanWrappers.copy(bean);
    }

    private final BeanCopier beanCopier = BeanCopier.create(Student.class, Student.class, true);

    // @Benchmark
    public Object cglibCopier() {
        Student student = new Student();
        beanCopier.copy(bean, student, null);
        return student;
    }

    public static void main(String[] args) {
//        PropertyDescriptor[] properties = WrapperUtil.findProperties(Student.class);
//        int index = 1;
//        for (PropertyDescriptor property : properties) {
//            String s = "case %d: return o.%s();\n";
//            System.out.printf(s, index++, property.getReadMethod().getName());
//        }
//        System.out.println();
//        System.out.println();
//          index = 1;
//        for (PropertyDescriptor property : properties) {
//            String s = "case %d: o.%s((%s)v);return;\n";
//            System.out.printf(s, index++, property.getWriteMethod().getName(),property.getPropertyType().getTypeName());
//        }
//
//        for (PropertyDescriptor property : properties) {
//            System.out.println(String.format("this.propertyAccesses[i] = new PA(\"%s\", %s.class, i);", property.getName(), property.getPropertyType().getTypeName()));
//        }
//
        BeanWrapperBenchmark benchmark = new BeanWrapperBenchmark();
        System.out.println(benchmark.setter());
        System.out.println(benchmark.supalleBeanWrapper());
        // System.out.println(benchmark.cglibCopier());
    }

    public static void setV(Object o) {
        setI((int) o);
    }

    public static void setI(int o) {
        System.out.println(o);
    }
}
