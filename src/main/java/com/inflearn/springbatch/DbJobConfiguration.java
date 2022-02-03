package com.inflearn.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class DbJobConfiguration {
    private static String JOB_NAME = "job";
    private static String STEP1_NAME = "step1";
    private static String STEP2_NAME = "step2";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1())
                .next(step2())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get(STEP1_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP1_NAME);
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
}
