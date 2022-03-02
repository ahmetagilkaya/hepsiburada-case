package tr.com.hepsiburada.rest_api.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import tr.com.hepsiburada.core.mapper.DTOMapper;

@Configuration
@RequiredArgsConstructor
public class ApplicationStartupConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DTOMapper dtoMapper() {
        return new DTOMapper(new ModelMapper());
    }

}
