package hello.config;

import static org.junit.jupiter.api.Assertions.*;

import hello.member.MemberRepository;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;

@SpringBootTest
@Slf4j
class DbConfigTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void checkBean() {
        log.info("dataSource: {}", dataSource);
        log.info("transactionManager: {}", transactionManager);
        log.info("jdbcTemplate: {}", jdbcTemplate);

        assertNotNull(dataSource);
        assertNotNull(transactionManager);
        assertNotNull(jdbcTemplate);
    }
}
