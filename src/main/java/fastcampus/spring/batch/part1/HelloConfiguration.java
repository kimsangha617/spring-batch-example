package fastcampus.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class HelloConfiguration {

    private final JobBuilderFactory jobBuilderFactory; // Job을 만들수 있도록 제공하는 클래스
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloJob(){ // Job 은 실행 단위
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer()) // Job이 실행 될때마다 자동 생성하는 parameterId
                .start(this.helloStep())
                .build();
    }

    @Bean
    public Step helloStep(){ // Step은 Job의 실행 단위, 한 개의 Job 은 여러개의 Step 을 가질수 있다
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
