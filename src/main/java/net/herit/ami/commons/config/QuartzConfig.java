//package net.herit.ami.commons.config;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//public class QuartzConfig {
//	private final DataSource dataSource;
//	private final ApplicationContext applicationContext;
//	private final PlatformTransactionManager transactionManager;
//
//	@Bean
//	public SchedulerFactoryBean schedulerFactory() {
//		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
//		jobFactory.setApplicationContext(applicationContext);
//		schedulerFactoryBean.setJobFactory(jobFactory);
//		schedulerFactoryBean.setTransactionManager(transactionManager);
//		schedulerFactoryBean.setDataSource(dataSource);
//		schedulerFactoryBean.setOverwriteExistingJobs(true);
//		schedulerFactoryBean.setAutoStartup(true);
//		schedulerFactoryBean.setQuartzProperties(quartzProperties());
//
//		return schedulerFactoryBean;
//	}
//    
//	@Bean
//	public Properties quartzProperties() {
//		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//
//		Properties properties = null;
//		try {
//			propertiesFactoryBean.afterPropertiesSet();
//			properties = propertiesFactoryBean.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return properties;
//	}
//
//	private final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
//		private AutowireCapableBeanFactory beanFactory;
//
//		public void setApplicationContext(final ApplicationContext context) {
//			beanFactory = context.getAutowireCapableBeanFactory();
//		}
//
//		@Override
//		protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
//			final Object job = super.createJobInstance(bundle);
//			beanFactory.autowireBean(job);
//			return job;
//		}
//	}
//
//}