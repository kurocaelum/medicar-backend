package com.kurocaelum.medicar.dto;

import java.util.List;

public record AgendaCreationDTO(long medicoId, String dia, List<String> horarios) {

}
