package com.Xposindia.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.Xposindia.*")
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySources({
//    @PropertySource("file:/etc/databases/vcservice/db.properties")
//    @PropertySource("file:/opt/tomcat-8/webapps/databases/Vehicle/db.properties")
    @PropertySource({ "classpath:db.properties" }) 
})

public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager jpaTransMan(){
        JpaTransactionManager jtManager = new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
        return jtManager;
    }
    
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(getDataSource());
		lcemfb.setPersistenceUnitName("xposDefault");

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		lcemfb.setPersistenceProvider(jpaVendorAdapter.getPersistenceProvider());
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		lcemfb.setJpaDialect(new HibernateJpaDialect());
		LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		lcemfb.setLoadTimeWeaver(loadTimeWeaver);
		return lcemfb;
	}

	@Bean
	public DataSource getDataSource() {
		return getDataSource(env.getRequiredProperty("dataSource.databaseName"));
	}

	private DataSource getDataSource(String dbName) {
		Properties props = createDatasourceProperty(dbName);
		HikariConfig config = new HikariConfig(props);
		config.setMaximumPoolSize(50);
		config.setConnectionTimeout(3 * 4000);
		config.setAutoCommit(false);
		config.setIdleTimeout(2 * 60 * 1000);
		return new HikariDataSource(config);
	}

	private Properties createDatasourceProperty(String dbName) {
		Properties props = new Properties();
//		props.setProperty("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		props.setProperty("dataSourceClassName", "com.mysql.cj.jdbc.MysqlDataSource");
		props.setProperty("dataSource.user", env.getRequiredProperty("dataSource.user"));
		props.setProperty("dataSource.password",env.getRequiredProperty("dataSource.password"));
		props.setProperty("dataSource.databaseName", dbName);
		props.setProperty("dataSource.portNumber", env.getRequiredProperty("dataSource.portNumber"));
		props.setProperty("dataSource.serverName", env.getRequiredProperty("dataSource.address"));

		return props;
	}
}
