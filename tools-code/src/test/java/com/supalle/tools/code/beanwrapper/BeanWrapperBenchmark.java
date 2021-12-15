package com.supalle.tools.code.beanwrapper;

import com.supalle.tools.code.BeanWrappers;
import com.supalle.tools.code.model.Student;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

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

    public static void main(String[] args) {
        BeanWrapperBenchmark benchmark = new BeanWrapperBenchmark();
        System.out.println(benchmark.setter());
        System.out.println(benchmark.supalleBeanWrapper());
    }
}
