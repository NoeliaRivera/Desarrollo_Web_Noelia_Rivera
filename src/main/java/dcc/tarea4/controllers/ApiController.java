package dcc.tarea4.controllers;

import org.springframework.web.bind.annotation.RestController;

import dcc.tarea4.dto.ActividadDTO;
import dcc.tarea4.dto.NotaDTO;
import dcc.tarea4.models.Actividad;
import dcc.tarea4.models.Miembro;
import dcc.tarea4.services.ApiService;
import dcc.tarea4.services.AppService;
import dcc.tarea4.repositories.ActividadRepository;
import dcc.tarea4.repositories.NotaRepository;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final AppService appService;
    private final ApiService apiService;
    private final ActividadRepository actividadRepository;
    private final NotaRepository notaRepository;
    public ApiController(AppService appService, ApiService apiService, ActividadRepository actividadRepository, NotaRepository notaRepository) {
        this.apiService = apiService;
        this.appService = appService;
        this.actividadRepository = actividadRepository;
        this.notaRepository = notaRepository;}

        @GetMapping("/miembros")
        public List<Miembro> listarMiembros() {
            return appService.obtenerMiembros();}

        @GetMapping("/actividades/buscar")
        public List<ActividadDTO> buscar(@RequestParam String texto) {
            return apiService.buscarActividades(texto);}
    
        @PostMapping("/actividad/{id}/nota")
        public ResponseEntity<Double> evaluar(@PathVariable Integer id, @RequestBody NotaDTO dto){
            double promedio = apiService.guardarNota(id, dto.getValor());
            return ResponseEntity.ok(promedio);}

        @GetMapping("/actividad/{id}")
        public ActividadDTO getActividad(@PathVariable Integer id) {
            return apiService.obtenerActividad(id);}
}

       