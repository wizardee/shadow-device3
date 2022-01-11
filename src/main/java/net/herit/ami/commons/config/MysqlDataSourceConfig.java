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
@MapperScan(value="net.herit.ami", annotationClass = MysqlDataSourceMapper.class, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@EnableTransactionManagement
public class MysqlDataSourceConfig {
	
	@Bean
	@ConfigurationProperties(prefix="spring.mysql.datasource")
	public HikariConfig mysqlHikariConfig() {
		return new HikariConfig();
	}
	
	@Bean(name = "mysqlDataSource", destroyMethod = "close")
	public DataSource mysqlDataSource() {
		DataSource dataSource = new HikariDataSource(mysqlHikariConfig());
		return dataSource;
	}

	@Bean(name = "mysqlSqlSessionFactory")
	public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mysqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mapper/mysql/*.xml"));
		
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "mysqlSqlSessionTemplate")
	public SqlSessionTemplate mysqlSqlSessionTemplate(SqlSessionFactory mysqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(mysqlSqlSessionFactory);
	}

}
