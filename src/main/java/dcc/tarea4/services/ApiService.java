package dcc.tarea4.services;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import dcc.tarea4.dto.ActividadDTO;
import dcc.tarea4.models.Actividad;
import dcc.tarea4.models.Nota;
import dcc.tarea4.repositories.ActividadRepository;
import dcc.tarea4.repositories.NotaRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ApiService {

    private final ActividadRepository actividadRepository;
    private final NotaRepository notaRepository;

    public ApiService(ActividadRepository actividadRepository,
                      NotaRepository notaRepository) {
                        this.actividadRepository = actividadRepository;
                        this.notaRepository = notaRepository;}

    public List<ActividadDTO> buscarActividades(String texto) {
    if (texto == null || texto.length() < 3) {
        return new ArrayList<>();}
    List<Actividad> actividades = actividadRepository.buscarActividades(texto);
    List<ActividadDTO> resultado = new ArrayList<>();

    for (Actividad a : actividades) {
        String nota;
        double promedio = notaRepository.promedioPorActividad(a.getId());
        if (promedio == 0) {
            nota = "Sin notas";}
            
        else {nota = String.format("%.1f", promedio);}

        resultado.add(new ActividadDTO(
            a.getId(),
            a.getNombreActividad(),
            a.getDescripcion(),
            a.getDia(),
            a.getTipo(),
            a.getMiembro().getComuna().getNombre(),
            a.getMiembro().getNombre(),
            nota,
            a.getHoraInicio(),
            a.getDuracion()
        ));
    }
    return resultado;}

public ActividadDTO obtenerActividad(Integer id) {
    Actividad a = actividadRepository.findById(id).orElseThrow();
    double promedio = notaRepository.promedioPorActividad(id);
    String nota = (promedio == 0)
        ? "Sin notas"
        : String.format("%.1f", promedio);
    return new ActividadDTO(
        a.getId(),
        a.getNombreActividad(),
        a.getDescripcion(),
        a.getDia(),
        a.getTipo(),
        a.getMiembro().getComuna().getNombre(),
        a.getMiembro().getNombre(),
        nota,
        a.getHoraInicio(),
        a.getDuracion());}

    public double guardarNota(Integer actividadId, int valor) {
        if (valor < 1 || valor > 7) {
            throw new IllegalArgumentException("Nota inválida");}

        Actividad actividad = actividadRepository.findById(actividadId).orElseThrow(() -> new RuntimeException("Actividad no existe"));
        Nota nota = new Nota();
        nota.setValor(valor);
        nota.setActividad(actividad);
        notaRepository.save(nota);
            return notaRepository.promedioPorActividad(actividadId);
}
}