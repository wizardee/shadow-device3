package net.herit.ami.commons.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import net.herit.ami.commons.context.ApplicationContextProvider;
import net.herit.ami.commons.job.QuartzJobBeans;

public class BeanUtil {
	
	private BeanUtil() {
		throw new IllegalAccessError();
	}
	
	public static Object getBean(String beanId) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        
        if( applicationContext == null ) {
            throw new NullPointerException("Spring의 ApplicationContext init fail");
        }

        return applicationContext.getBean(beanId);
	}
	
	public static List<QuartzJobBeans> getQuartzJobBeans() {
		List<QuartzJobBeans> beanList = new ArrayList<>();
		
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        
        if( applicationContext == null ) {
            throw new NullPointerException("Spring의 ApplicationContext init fail");
        }
        
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
        	Object bean = applicationContext.getBean(beanName);
        	if (bean instanceof QuartzJobBeans) {
        		beanList.add((QuartzJobBeans) bean);
        	}
        }
        
        return beanList;
        
	}
	
}
