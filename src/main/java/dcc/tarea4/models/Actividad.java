package dcc.tarea4.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "actividad")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_actividad")
    private String nombreActividad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "dia")
    private String dia;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "duracion")
    private String duracion;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy="actividad")
    private List<Nota> notas;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;

    public String getNombreActividad() {
    return nombreActividad;}

    public String getDescripcion() {
        return descripcion;}

    public String getDia() {
        return dia;}

    public String getHoraInicio() {
        return horaInicio;}

    public String getDuracion() {
        return duracion;}

    public String getTipo() {
        return tipo;}

    public Integer getId() {
        return id;}

    public Miembro getMiembro() {
        return miembro;}
    
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;}

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;}

    public void setDia(String dia) {
        this.dia = dia;}
    
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;}

    public void setDuracion(String duracion) {
        this.duracion = duracion;}

    public void setTipo(String tipo) {
        this.tipo = tipo;}

    public void setId(Integer id) {
        this.id = id;}

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;}
}