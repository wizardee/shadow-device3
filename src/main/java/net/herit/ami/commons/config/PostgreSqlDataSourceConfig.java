package net.herit.ami.commons.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(value="net.herit.ami", annotationClass = PostgreSqlDataSourceMapper.class, sqlSessionFactoryRef = "postgresqlSqlSessionFactory")
@EnableTransactionManagement
public class PostgreSqlDataSourceConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.postgresql.datasource")
	public HikariConfig postgresqlHikariConfig() {
		return new HikariConfig();
	}
	
	@Bean(name = "postgresqlDataSource", destroyMethod = "close")
	public DataSource postgresqlDataSource() {		
		DataSource dataSource = new HikariDataSource(postgresqlHikariConfig());
		return dataSource;
	}

	@Bean(name = "postgresqlSqlSessionFactory")
	public SqlSessionFactory postgresqlSqlSessionFactory(@Qualifier("postgresqlDataSource") DataSource postgresqlDataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(postgresqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mapper/postgresql/*.xml"));
		
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "postgresqlSqlSessionTemplate")
	public SqlSessionTemplate postgresqlSqlSessionTemplate(SqlSessionFactory postgresqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(postgresqlSqlSessionFactory);
	}

}
