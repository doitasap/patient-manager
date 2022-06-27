package doitasap.me.patient;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import javax.persistence.EntityManager;

@SpringBootApplication
public class PatientManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientManagerApplication.class, args);
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {    return new Java8TimeDialect();  }
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){return new JPAQueryFactory(entityManager);}

}
