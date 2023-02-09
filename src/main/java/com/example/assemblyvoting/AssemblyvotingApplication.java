package com.example.assemblyvoting;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Assembly Voting", version = "1.0", description = "Documentação da API"))
public class AssemblyvotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssemblyvotingApplication.class, args);
    }

}
