package com.gestion.citas.medicas.repository;

import com.gestion.citas.medicas.entity.Cita;
import com.gestion.citas.medicas.entity.Medico;
import com.gestion.citas.medicas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPaciente(Paciente paciente);
    List<Cita> findByMedico(Medico medico);
}
