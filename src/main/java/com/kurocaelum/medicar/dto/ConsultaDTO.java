package com.kurocaelum.medicar.dto;

import com.kurocaelum.medicar.entities.Medico;

public record ConsultaDTO(long id, String dia, String horario, String dataAgendamento, Medico medico) {}
