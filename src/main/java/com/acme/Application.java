package com.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

import javax.persistence.EntityManagerFactory;

/**
 * Created by bdraraujo on 16-04-12.
 */
@ComponentScan("com.acme")
@Configuration
@EntityScan("com.acme.types")
@EnableAutoConfiguration
@EnableJpaRepositories("com.acme.repository")
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    /*@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(LoadTimeWeaver loadTimeWeaver) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        //emf.setPersistenceProviderClass(org.datanucleus.api.jpa.PersistenceProviderImpl.class);
        //Map<String, PersistenceUnitMetaData> puProperties = Maps.newHashMap();

        //PersistenceProviderImpl persistenceProvider = new PersistenceProviderImpl();
        //EntityManagerFactory emf = persistenceProvider.createEntityManagerFactory("jpa.unit", puProperties);
        emf.setPersistenceUnitName("jpaunit");
        //emf.setPackagesToScan("com.acme.types");
        //emf.setLoadTimeWeaver(loadTimeWeaver);
        *//*Properties jpaProperties = new Properties();
        jpaProperties.put("datanucleus.ConnectionURL", "appengine");
        jpaProperties.put("datanucleus.appengine.ignorableMetaDataBehavior", "NONE");*//*
        //emf.setJpaProperties(jpaProperties);

        return emf;
    }

    @Bean
    public LoadTimeWeaver loadTimeWeaverBean() {
        return new org.springframework.instrument.classloading.SimpleLoadTimeWeaver();
    }*/


    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
