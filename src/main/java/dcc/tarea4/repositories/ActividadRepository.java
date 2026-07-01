package dcc.tarea4.repositories;

import dcc.tarea4.dto.ActividadDTO;
import dcc.tarea4.models.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {

    @Query("""
        SELECT a
        FROM Actividad a
        WHERE LOWER(a.nombreActividad) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(a.miembro.comuna.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
    """)
    List<Actividad> buscarActividades(String texto);

}