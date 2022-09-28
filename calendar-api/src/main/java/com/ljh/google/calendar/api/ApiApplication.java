package com.ljh.google.calendar.api;

import com.ljh.google.calendar.core.SimpleEntity;
import com.ljh.google.calendar.core.SimpleEntityRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@EnableJpaAuditing
@EntityScan("com.ljh.google.calendar.core")
@EnableJpaRepositories("com.ljh.google.calendar.core")
@Controller
@SpringBootApplication
public class ApiApplication {

    private final SimpleEntityRepository repository;

    public ApiApplication(SimpleEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SimpleEntity> findAll(){
        return repository.findAll();
    }

    @GetMapping("/save")
    public SimpleEntity saveOne() {
        final SimpleEntity entity = new SimpleEntity();
        entity.setName("ljh");
        return repository.save(new SimpleEntity());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
