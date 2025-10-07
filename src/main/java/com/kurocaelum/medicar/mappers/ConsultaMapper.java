package com.kurocaelum.medicar.mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.dto.ConsultaDTO;
import com.kurocaelum.medicar.entities.Consulta;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

	@Mapping(target = "dia", source = "consulta.agenda.dia", dateFormat = "dd/MM/yyyy")
	@Mapping(target = "horario", source = "consulta.horario", dateFormat = "HH:mm")
	@Mapping(target = "dataAgendamento", source = "consulta.dataAgendamento", dateFormat = "yyyy-MM-dd HH:mm:ss")
	@Mapping(target = "medico", source = "consulta.agenda.medico")
	ConsultaDTO map(Consulta consulta);

	@Mapping(target = "id", ignore = true)
	void update(Consulta source, @MappingTarget Consulta target);

	default List<ConsultaDTO> mapConsultaToConsultaDTO(List<Consulta> consultas) {
		if (consultas == null)
			return null;
			
		List<ConsultaDTO> list = new ArrayList<ConsultaDTO>(consultas.size());
		for(Consulta consulta: consultas) {
			if (consulta.getAgenda().getDia().isAfter(LocalDate.now()) 
			|| (consulta.getAgenda().getDia().equals(LocalDate.now()) && (consulta.getHorario().isAfter(LocalTime.now())))) {
				list.add(this.map(consulta));
			}
		}

		return list;
	}
}
