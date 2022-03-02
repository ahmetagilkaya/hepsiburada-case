package tr.com.hepsiburada.view_producer_app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tr.com.hepsiburada.core.helper.FileHelper;
import tr.com.hepsiburada.core.mapper.DTOMapper;

import java.util.TimeZone;

@Configuration
@RequiredArgsConstructor
public class ApplicationStartupConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        mapper.setTimeZone(TimeZone.getDefault());

        mapper.registerModule(module);
        return mapper;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DTOMapper dtoMapper() {
        return new DTOMapper(new ModelMapper());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public FileHelper fileHelper() {
        return new FileHelper();
    }

}
