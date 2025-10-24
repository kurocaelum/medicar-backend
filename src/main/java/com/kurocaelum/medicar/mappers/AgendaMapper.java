package com.kurocaelum.medicar.mappers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.kurocaelum.medicar.dto.AgendaCreationDTO;
import com.kurocaelum.medicar.dto.AgendaDTO;
import com.kurocaelum.medicar.entities.Agenda;
import com.kurocaelum.medicar.entities.Consulta;
import com.kurocaelum.medicar.services.MedicoService;

@Mapper(componentModel = "spring", uses = { MedicoService.class })
public interface AgendaMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "medico", ignore = true)
	@Mapping(target = "horarios", ignore = true)
	void updateDia(Agenda source, @MappingTarget Agenda target);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "medico", source = "medicoId")
	@Mapping(target = "horarios", expression = "java(mapHorarios(source.horarios(), target))")
	void createFromDto(AgendaCreationDTO source, @MappingTarget Agenda target);
	
	default List<Consulta> mapHorarios(List<String> horarios, Agenda agenda) {
		if(horarios == null)
			return null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
		
		List<Consulta> list = new ArrayList<Consulta>(horarios.size());
		for(String horario: horarios) {
			if(horario != null) 
				list.add(new Consulta(null, agenda, LocalTime.parse(horario, formatter)));
		}
		
		return list;
	}
	
	default List<AgendaDTO> toAgendaDTO(List<Agenda> agendas, List<Long> medicos, List<String> crms, LocalDate data_inicio, LocalDate data_final){
		if(agendas == null)
			return null;

		List<AgendaDTO> list = new ArrayList<AgendaDTO>(agendas.size());
		for(Agenda agenda: agendas) {
			if(!agenda.getDia().isBefore(LocalDate.now())){
				if(
					(medicos != null && !medicos.contains(agenda.getMedico().getId())) ||
					(crms != null && !crms.contains(agenda.getMedico().getCrm())) ||
					(data_inicio != null && agenda.getDia().isBefore(data_inicio)) ||
					(data_final != null && agenda.getDia().isAfter(data_final))
				) continue;
				
				AgendaDTO dto = this.toAgendaDTO(agenda);
				if(!dto.horarios().isEmpty())
					list.add(dto);
			}
		}

		return list;
	}

	AgendaDTO toAgendaDTO(Agenda agenda);
	
	default List<String> map(List<Consulta> consultas) {
		if(consultas == null)
			return null;
		
		List<String> list = new ArrayList<>(consultas.size());
		for(Consulta consulta: consultas) {
			if(consulta.getDataAgendamento() == null && 
				( !consulta.getAgenda().getDia().isBefore(LocalDate.now()) && !consulta.getHorario().isBefore(LocalTime.now()) )
			) {
				list.add(this.map(consulta));
			}
		}
		
		return list;
	}
	
	default String map(Consulta consulta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
		
		return consulta.getHorario().format(formatter).toString();
	}
	
}
