package dcc.tarea4.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import dcc.tarea4.models.Miembro;
import java.util.List;

public interface MiembroRepository extends JpaRepository<Miembro, Integer> {

    List<Miembro> findTop5ByOrderByFechaRegistroDesc();

}

