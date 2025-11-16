package co.com.vehicle.r2dbc.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.postgresql.client.SSLMode;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;

import java.time.Duration;

@Configuration
public class PostgreSQLConnectionPool {
    /* Change these values for your project */
    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;
    public static final int DEFAULT_PORT = 5432;

    @Bean
    public ConnectionPool getConnectionConfig(PostgresqlConnectionProperties properties) {
        System.out.println("propiedades de conexion " + properties);
        try {
            PostgresqlConnectionConfiguration dbConfiguration = PostgresqlConnectionConfiguration.builder()
                    .host(properties.host()).port(properties.port())
                    .database(properties.database())
                    .schema(properties.schema())
                    .username(properties.username())
                    .sslMode(SSLMode.REQUIRE)
                    .password(properties.password()).build();

            ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
                    .connectionFactory(new PostgresqlConnectionFactory(
                            dbConfiguration))
                    .name("api-postgres-connection-pool")
                    .initialSize(INITIAL_SIZE).maxSize(MAX_SIZE)
                    .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                    .validationQuery("SELECT 1").build();

            return new ConnectionPool(poolConfiguration);
        } catch (Exception e) {
            System.out.println("getConnectionConfig " + e.getMessage());
            throw e;
        }
    }
}