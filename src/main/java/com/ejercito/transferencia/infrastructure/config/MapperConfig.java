package com.ejercito.transferencia.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
