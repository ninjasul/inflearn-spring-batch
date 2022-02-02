package com.inflearn.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class HelloJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static String STEP1_NAME = "helloStep1";
    private static String STEP2_NAME = "helloStep2";

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }

    private Step helloStep1() {
        return stepBuilderFactory.get(STEP1_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP1_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step helloStep2() {
        return stepBuilderFactory.get(STEP2_NAME)
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed", STEP2_NAME);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
