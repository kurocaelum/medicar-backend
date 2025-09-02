package com.kurocaelum.medicar.dto;

import com.kurocaelum.medicar.entities.Medico;
import java.util.List;

public record AgendaDTO(long id, Medico medico, String dia, List<String> horarios) {

}
