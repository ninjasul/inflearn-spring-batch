package com.inflearn.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class JobConfiguration {
    private static String JOB1_NAME = "batchJob1";
    private static String JOB2_NAME = "batchJob2";

    private static String STEP1_NAME = "step1";
    private static String STEP2_NAME = "step2";
    private static String STEP3_NAME = "step3";
    private static String STEP4_NAME = "step4";
    private static String STEP5_NAME = "step5";

    private static String FLOW1_NAME = "flow1";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /*@Bean
    public Job batchJob1() {
        return jobBuilderFactory.get(JOB1_NAME)
                .start(step1())
                .next(step2())
                .build();
    }*/

    @Bean
    public Job batchJob2() {
        return jobBuilderFactory.get(JOB2_NAME)
                .start(flow())
                .next(step5())
                .end()
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get(STEP1_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP1_NAME);

                    // 기본적으로 Step은 Tasklet을 무한반복 실행하도록 설정되어 있음.
                    // 무한반복 실행을 방지하기 위해서는 RepeatStatus.FINISHED 로 리턴 해 주어야 함.
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get(STEP2_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP2_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>(FLOW1_NAME);
        return flowBuilder.start(step3())
                .next(step4())
                .end();
    }

    private Step step3() {
        return stepBuilderFactory.get(STEP3_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP3_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step4() {
        return stepBuilderFactory.get(STEP4_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP4_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step5() {
        return stepBuilderFactory.get(STEP5_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP5_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
