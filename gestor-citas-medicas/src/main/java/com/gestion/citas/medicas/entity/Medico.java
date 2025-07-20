package com.gestion.citas.medicas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Medico extends Usuario {

    @Column(nullable = false)
    private String especialidad;

    @Column(name = "numero_colegiado", nullable = false, unique = true)
    private String numeroColegiado;
} 