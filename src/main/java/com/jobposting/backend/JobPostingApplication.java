package com.jobposting.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JobPostingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobPostingApplication.class, args);
    }

}
