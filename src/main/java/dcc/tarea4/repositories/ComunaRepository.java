package dcc.tarea4.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import dcc.tarea4.models.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

}