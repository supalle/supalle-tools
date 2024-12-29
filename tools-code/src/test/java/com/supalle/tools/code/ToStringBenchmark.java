package com.supalle.tools.code;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(1)
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
// @BenchmarkMode(Mode.AverageTime)
// @OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 6, time = 1, timeUnit = TimeUnit.SECONDS)
public class ToStringBenchmark {

    private FixedKeyMap<String, Object> fixedKeyMap;
    private final Student bean = new Student();

    {
        FixedKeyIndexes<String> fixedKeyIndexes = FixedKeyIndexes.of("firstname", "lastname", "age", "height", "weight", "homeAddress", "mother", "father");
        FixedKeyMap<String, Object> fixedKeyMap = new FixedKeyMap<>(fixedKeyIndexes);
        fixedKeyMap.put("firstname", "张三");
        fixedKeyMap.put("lastname", "尼古拉");
        fixedKeyMap.put("age", (byte) (10));
        fixedKeyMap.put("height", 185);
        fixedKeyMap.put("weight", 50.5D);
        fixedKeyMap.put("homeAddress", "homeAddress");
        fixedKeyMap.put("mother", "mother");
        fixedKeyMap.put("father", "father");

        bean.firstname = "张三";
        bean.lastname = "尼古拉";
        bean.age = (byte) (10);
        bean.height = 185;
        bean.weight = 50.5D;
        bean.homeAddress = "homeAddress";
        bean.mother = "mother";
        bean.father = "father";
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ToStringBenchmark.class.getName())
                .build();
        new Runner(options).run();
    }


    @Benchmark
    public Object toStringForJsonFormat() {
        return JsonFormat.format(fixedKeyMap);
    }

    @Benchmark
    public Object toStringForFastjson2() {
        return JSON.toJSONString(fixedKeyMap);
    }

    @Benchmark
    public Object beanToStringForJsonFormat() {
        return JsonFormat.format(bean);
    }

    @Benchmark
    public Object beanToStringForFastjson2() {
        return JSON.toJSONString(bean);
    }

    @Data
    // @NoArgsConstructor
    // @AllArgsConstructor
    // public
    static class Student {
        private String firstname;
        private String lastname;
        private byte age;
        private int height;
        private double weight;
        private String homeAddress;
        private String mother;
        private String father;
    }
}
