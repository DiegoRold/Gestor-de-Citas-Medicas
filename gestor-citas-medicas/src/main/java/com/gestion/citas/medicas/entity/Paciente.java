package com.gestion.citas.medicas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Paciente extends Usuario {

    private Integer edad;
    private String genero;

    @Column(name = "numero_seguro_medico")
    private String numeroSeguroMedico;
} 