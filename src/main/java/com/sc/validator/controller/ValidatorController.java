package com.sc.validator.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Map;

@RestController
public class ValidatorController {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    @PostMapping("/yaml")
    public String validateYAML(@RequestBody String yamlData) {

        Yaml yaml = new Yaml();
        /*InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("customer.yaml");
        Map<String, Object> obj = yaml.load(inputStream);*/
        try {
            Map<String, Object> obj = yaml.load(yamlData);
            System.out.println(obj);
            return "Validated";
        } catch (ClassCastException e) {
            return "Invalid YAML!";
        } catch (Exception e) {
            return "Error occured in validation";
        }
    }
}