package com.batch.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.provider.certpath.OCSPResponse;

/**
 * Created by USER on 2017-08-16.
 */
@Configuration
public class FlowConfiguration {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("Step 1 from inside flow foo");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("Step 2  from inside flow foo");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Flow foo() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("food");

        flowBuilder.start(step1())
                .next(step2())
                .end();

        return flowBuilder.build();
    }

}
