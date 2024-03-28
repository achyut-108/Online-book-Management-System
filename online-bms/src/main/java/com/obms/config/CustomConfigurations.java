package com.obms.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.obms.domain.inventory.ManageInventoryRequest;
import com.obms.entity.BookEntity;

import lombok.Data;

@Component
@Data
public class CustomConfigurations {
	
	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper =  new ModelMapper();
		modelMapper.addMappings(new PropertyMap<ManageInventoryRequest, BookEntity>() {
			@Override
			protected void configure() {
				map().setId(null);
			}
		});
		
	    return modelMapper;
	}
}
