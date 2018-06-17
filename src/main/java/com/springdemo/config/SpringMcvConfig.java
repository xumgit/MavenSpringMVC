package com.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class SpringMcvConfig {
	
	@Bean(name = "localeResolver")
	public LocaleResolver localeResolverBean() {
		return new SessionLocaleResolver();
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("lang/language");
		return source;
	}
}
