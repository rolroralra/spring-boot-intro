package hello.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

//@Configuration
@Slf4j
public class DbConfig {
    @Bean
    public DataSource dataSource() {
        log.info("DataSource Bean 등록");

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager() {
        log.info("TransactionManager Bean 등록");

//        return new DataSourceTransactionManager(dataSource());
        return new JdbcTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        log.info("JdbcTemplate Bean 등록");
        return new JdbcTemplate(dataSource());
    }
}
