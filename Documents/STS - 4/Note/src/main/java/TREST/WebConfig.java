package TREST;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer 
{
	@Value("${cors.originPaterns}")
	private String corsOriginPatters = "";
	
    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() 
    {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        Jackson2ObjectMapperBuilder.xml();
        return builder;
    }
    
	@Override
	public void addCorsMappings(CorsRegistry registry) 
	{
		var allowedOrigins = corsOriginPatters.split(",");
		registry.addMapping("/**").
		allowedMethods("GET", "POST", "PUT", "DELETE").
		allowedOrigins(allowedOrigins).allowCredentials(true);
	}
	
	
}
