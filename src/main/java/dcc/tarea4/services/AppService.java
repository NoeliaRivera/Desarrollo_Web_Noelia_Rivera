package dcc.tarea4.services;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import dcc.tarea4.models.Comuna;
import dcc.tarea4.models.Miembro;
import dcc.tarea4.models.Actividad;
import dcc.tarea4.models.Region;
import dcc.tarea4.repositories.ComunaRepository;
import dcc.tarea4.repositories.MiembroRepository;
import dcc.tarea4.repositories.ActividadRepository;
import dcc.tarea4.repositories.RegionRepository;
import dcc.tarea4.repositories.NotaRepository;

@Service
public class AppService {
    private final ComunaRepository comunaRepository;
    private final MiembroRepository miembroRepository;
    private final ActividadRepository actividadRepository;
    private final RegionRepository regionRepository;
    private final NotaRepository notaRepository;

    public AppService(ComunaRepository comunaRepository, MiembroRepository miembroRepository, ActividadRepository actividadRepository, RegionRepository regionRepository, NotaRepository notaRepository) {
        this.comunaRepository = comunaRepository;
        this.miembroRepository = miembroRepository;
        this.regionRepository = regionRepository;
        this.actividadRepository = actividadRepository;
        this.notaRepository = notaRepository;}

    public List<Comuna> obtenerComunas() {
        return comunaRepository.findAll();}

    public void registrarMiembro(
            String usuario,
            String correo,
            String telefono,
            Integer comunaId,
            String tipo,
            String nombreActividad,
            String descripcion,
            List<String> dia,
            String horaInicio,
            String duracion,
            String tipoActividad) {

        Comuna comuna = comunaRepository.findById(comunaId).orElseThrow();
        
        Miembro miembro = new Miembro();
        miembro.setNombre(usuario);
        miembro.setCorreo(correo);
        miembro.setTelefono(telefono);
        miembro.setTipo(tipo);
        miembro.setComuna(comuna);
        miembro.setFechaRegistro(LocalDateTime.now());
        
        miembroRepository.save(miembro);

        Actividad actividad = new Actividad();
        actividad.setNombreActividad(nombreActividad);
        actividad.setDescripcion(descripcion);
        actividad.setDia(String.join(",", dia));
        actividad.setHoraInicio(horaInicio);
        actividad.setDuracion(duracion);
        actividad.setTipo(tipoActividad);
        
        actividad.setMiembro(miembro);
    
        actividadRepository.save(actividad);}
            
    public List<Miembro> obtenerMiembros() {
        return miembroRepository.findAll();}

    public List<Miembro> obtenerUltimosMiembros() {
        return miembroRepository.findTop5ByOrderByFechaRegistroDesc();}

    public List<Actividad> obtenerActividades() {
        return actividadRepository.findAll();
    }
}