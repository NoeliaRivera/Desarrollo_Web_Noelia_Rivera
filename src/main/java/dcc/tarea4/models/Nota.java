package dcc.tarea4.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="actividad_id")
    private Actividad actividad;
    
    private Integer valor;
    
    public Nota() {}
    
    public Long getId() {
        return id;}
    
    public void setId(Long id) {
        this.id = id;}
    
    public Actividad getActividad() {
        return actividad;}
    
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;}
    
    public Integer getValor() {
        return valor;}
    
    public void setValor(Integer valor) {
        this.valor = valor;}
}