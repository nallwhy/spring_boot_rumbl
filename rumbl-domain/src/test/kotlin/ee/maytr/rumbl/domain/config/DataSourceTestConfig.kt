// package ee.maytr.rumbl.domain.config
//
// import org.springframework.boot.context.properties.ConfigurationProperties
// import org.springframework.boot.jdbc.DataSourceBuilder
// import org.springframework.context.annotation.Bean
// import org.springframework.context.annotation.Configuration
// import org.springframework.context.annotation.Primary
// import javax.sql.DataSource
//
// @Configuration
// class DataSourceTestConfig {
//     @Bean
//     @Primary
//     @ConfigurationProperties("datasource")
//     fun dataSource(): DataSource {
//         return DataSourceBuilder.create().build()
//     }
// }
