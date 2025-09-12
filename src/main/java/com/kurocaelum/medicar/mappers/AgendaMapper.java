package com.kurocaelum.medicar.mappers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.entities.Consulta;

@Mapper(componentModel = "spring")
public interface AgendaMapper {
	
	List<AgendaDTO> mapAgendaToAgendaDTO(List<Agenda> agendas);
	
	default List<String> map(List<Consulta> consultas) {
		if(consultas == null)
			return null;
		
		List<String> list = new ArrayList<>(consultas.size());
		for(Consulta consulta: consultas) {
			if(this.isHorarioDisponivel(consulta))
				list.add(this.map(consulta));
		}
		
		return list;
	}
	
	default String map(Consulta consulta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		return consulta.getHorario().format(formatter).toString();
	}
	
	default boolean isHorarioDisponivel(Consulta consulta) {
		return consulta.getDataAgendamento() == null;
	}
	
	@Mapping(target = "id", ignore = true)
	void update(Agenda source, @MappingTarget Agenda target);
	
}
