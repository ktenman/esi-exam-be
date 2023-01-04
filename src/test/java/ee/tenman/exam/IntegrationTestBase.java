package ee.tenman.exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.tenman.exam.configuration.DataSourceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@Import(DataSourceConfiguration.class)
@Slf4j
public abstract class IntegrationTestBase {

    @Resource
    protected MockMvc mockMvc;
    @Resource
    protected ObjectMapper objectMapper;

    @Resource
    protected DriverManagerDataSource dataSource;

    @BeforeEach
    public void clearDatabaseBeforeTestMethod() {
        try {
            log.info("Executing sql script to clear database");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("clear_database.sql"));
            log.info("Database cleared");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}