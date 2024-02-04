/**
 * @projectName answer-cloud
 * @package ui.config
 * @className ui.config.StaticResourceConfiguration
 */
package ui.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ui.properties.UiProperties;

/**
 * StaticResourceConfiguration
 * @description
 * @author answer_wx
 * @date 2024/1/24 18:15
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties(UiProperties.class)
public class StaticResourceConfiguration implements WebMvcConfigurer {
    private final UiProperties uiProperties;

    public StaticResourceConfiguration(UiProperties uiProperties) {
        this.uiProperties = uiProperties;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathContext = uiProperties.getPathContext();
        registry.addResourceHandler(pathContext+"/**").addResourceLocations("classpath:/META-INF/central-platform-ui/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String pathContext = uiProperties.getPathContext();
        registry.addViewController(pathContext+"/").setViewName("index.html");
    }
}