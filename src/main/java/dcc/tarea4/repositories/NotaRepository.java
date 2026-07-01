package dcc.tarea4.repositories;

import dcc.tarea4.models.Nota;
import dcc.tarea4.models.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByActividad(Actividad actividad);
    @Query("SELECT COALESCE(AVG(n.valor), 0) FROM Nota n WHERE n.actividad.id = :id")
    double promedioPorActividad(@Param("id") Integer id);
}