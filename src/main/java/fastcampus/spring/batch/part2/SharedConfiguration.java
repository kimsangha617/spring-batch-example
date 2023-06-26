package fastcampus.spring.batch.part2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SharedConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SharedConfiguration(JobBuilderFactory jobBuilderFactory,
                               StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job shareJob(){ // Job 은 실행 단위
        return jobBuilderFactory.get("shareJob")
                .incrementer(new RunIdIncrementer()) // Job이 실행 될때마다 자동 생성하는 parameterId
                .start(this.shareStep())
                .next(this.shareStep2())
                .build();
    }

    @Bean
    public Step shareStep() {
        return stepBuilderFactory.get("shareStep")
                .tasklet((contribution, chunkContext) -> {
                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext executionContext = stepExecution.getExecutionContext();
                    executionContext.putString("stepKey", "step execution context");

                    JobExecution jobExecution = stepExecution.getJobExecution();
                    JobInstance jobInstance = jobExecution.getJobInstance();
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
                    jobExecutionContext.putString("jobKey", "job execution context");
                    JobParameters jobParameters = jobExecution.getJobParameters();

                    log.info("jobName: {}, stepName: {}, parameter: {}",
                        jobInstance.getJobName(),
                        stepExecution.getStepName(),
                        jobParameters.getLong("run.id"));

                    return RepeatStatus.FINISHED;
                })
                        .build();
    }

    @Bean
    public Step shareStep2() {
        return stepBuilderFactory.get("shareStep")
                .tasklet((contribution, chunkContext) -> {
                    StepExecution stepExecution = contribution.getStepExecution();
                    ExecutionContext executionContext = stepExecution.getExecutionContext();
                    executionContext.putString("stepKey", "step execution context");

                    JobExecution jobExecution = stepExecution.getJobExecution();
                    JobInstance jobInstance = jobExecution.getJobInstance();
                    ExecutionContext jobExecutionContext = jobExecution.getExecutionContext();
                    jobExecutionContext.putString("jobKey", "job execution context");
                    JobParameters jobParameters = jobExecution.getJobParameters();

                    log.info("jobName: {}, stepName: {}, parameter: {}",
                            jobInstance.getJobName(),
                            stepExecution.getStepName(),
                            jobParameters.getLong("run.id"));

                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
