//package io.github.websterrodrigues.ms_api_entities.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfiguration {
//
//    @Value("${spring.datasource.url}")
//    String url;
//
//    @Value("${spring.datasource.username}")
//    String username;
//
//    @Value("${spring.datasource.password}")
//    String password;
//
//    @Value("${spring.datasource.driver-class-name}")
//    String driver;
//
//    @Bean
//    public DataSource hikariDataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(url);
//        config.setUsername(username);
//        config.setPassword(password);
//        config.setDriverClassName(driver);
//
//        config.setMaximumPoolSize(10); //Máximo de conexões
//        config.setMinimumIdle(1); //Tamanho inicial do pool
//        config.setPoolName("entities-db-pool");
//        config.setMaxLifetime(600000); //Tempo máximo de vida da conexão 600 mil ms (10 min) -> Gerencia conexões
//        config.setConnectionTimeout(100000); //Timeout de conexão
//        config.setConnectionTestQuery("select 1"); //Teste de conexão
//
//        return new HikariDataSource(config); //Hikari DataSource exige uma HikariConfig
//    }
//}
