package com.supalle.tools.code.beanwrapper;


import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BeanWrapperRunner {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(BeanWrapperBenchmark.class.getName())
                // 预热3轮
                .warmupIterations(6)
                // 度量10轮
                .measurementIterations(5)
                .mode(Mode.Throughput)
                .forks(1)
                .threads(1)
//                .threads(Runtime.getRuntime().availableProcessors())
                .measurementTime(TimeValue.seconds(1))
                .warmupTime(TimeValue.seconds(1))
                .build();

        new Runner(opt).run();
    }

}
