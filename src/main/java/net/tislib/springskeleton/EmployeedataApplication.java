package net.tislib.springskeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.projection.ProjectionFactory;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EmployeedataApplication {

    @Bean
    public ProjectionFactory getProjectionFactory() {
        return new CollectionAwareProjectionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(EmployeedataApplication.class, args);
    }

}
