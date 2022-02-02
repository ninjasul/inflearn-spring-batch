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

                    // 기본적으로 Step은 Tasklet을 무한반복 실행하도록 설정되어 있음.
                    // 무한반복 실행을 방지하기 위해서는 RepeatStatus.FINISHED 로 리턴 해 주어야 함.
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
