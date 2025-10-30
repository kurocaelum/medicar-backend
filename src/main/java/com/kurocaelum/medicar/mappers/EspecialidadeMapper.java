package com.kurocaelum.medicar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.entities.Especialidade;

@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {
	
	@Mapping(target = "id", ignore = true)
	void update(Especialidade source, @MappingTarget Especialidade target);
	
}
