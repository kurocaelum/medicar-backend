package com.kurocaelum.medicar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.entities.Medico;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
	
	@Mapping(target = "id", ignore = true)
	void update(Medico source, @MappingTarget Medico target);
	
}
