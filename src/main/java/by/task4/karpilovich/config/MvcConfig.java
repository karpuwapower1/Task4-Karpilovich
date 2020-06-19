package by.task4.karpilovich.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	private static final String LOGIN_PAGE_PATH = "/login";
	private static final String LOGIN_PAGE_VIEW_NAME = "login";
	private static final String EMPTY_PAGE_PATH = "/login";
	private static final String EMPTY_PAGE_VIEW_NAME = "login";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(LOGIN_PAGE_PATH).setViewName(LOGIN_PAGE_VIEW_NAME);
        registry.addViewController(EMPTY_PAGE_PATH).setViewName(EMPTY_PAGE_VIEW_NAME);
    }
}