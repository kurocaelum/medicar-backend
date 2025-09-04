package com.kurocaelum.medicar.mappers;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.Mapper;

import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.entities.Consulta;

@Mapper(componentModel = "spring")
public interface AgendaMapper {
	
	List<AgendaDTO> mapAgendaToAgendaDTO(List<Agenda> agendas);
	
	List<String> map(List<Consulta> consultas);
	
	default String map(Consulta consulta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		return consulta.getHorario().format(formatter).toString();
	}
	
}
