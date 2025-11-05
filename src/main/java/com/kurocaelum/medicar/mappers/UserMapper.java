package com.kurocaelum.medicar.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
	void update(User source, @MappingTarget User target);

}
