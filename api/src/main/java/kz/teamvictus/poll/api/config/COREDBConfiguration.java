package kz.teamvictus.poll.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(
        entityManagerFactoryRef = "coredbEntityManager",
        transactionManagerRef = "coredbTransactionManager",
        basePackages = {
                "kz.teamvictus.poll.core.repository"
        }
)
public class COREDBConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.coredb.datasource")
    public DataSource coredbDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean(name = "coredbEntityManager")
    public LocalContainerEntityManagerFactoryBean coredbEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(coredbDataSource())
                .properties(hibernateProperties())
                .packages(
                        "kz.teamvictus.poll.core.model"
                )
                .persistenceUnit("coredbPU")
                .build();
    }

    @Primary
    @Bean(name = "coredbTransactionManager")
    public PlatformTransactionManager coredbTransactionManager(@Qualifier("coredbEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue())
                    );
        } catch (IOException e) {
            return new HashMap<String, Object>();
        }
    }

}


