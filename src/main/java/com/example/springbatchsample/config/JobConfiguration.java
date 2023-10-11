package com.example.springbatchsample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatchsample.prospect.entity.Prospect;

@Configuration
public class JobConfiguration {

    @Bean
    public ItemReader<Prospect> excelReader() {
        PoiItemReader<Prospect> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource("data/sample.xlsx"));
        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    @Bean
    public ItemWriter<Prospect> writer(ConsoleItemWriter consoleItemWriter) {
        return consoleItemWriter;
    }

    private RowMapper<Prospect> excelRowMapper() {
        BeanWrapperRowMapper<Prospect> rowMapper = new BeanWrapperRowMapper<>();
        rowMapper.setTargetType(Prospect.class);
        return rowMapper;
    }

    @Bean
    public Job excelJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("excelReaderJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager,
            ConsoleItemWriter consoleItemWriter) {
        return new StepBuilder("step10", jobRepository)
                .<Prospect, Prospect>chunk(1000, transactionManager)
                .reader(excelReader())
                .writer(writer(consoleItemWriter))
                .build();
    }

}
