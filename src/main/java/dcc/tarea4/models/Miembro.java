package dcc.tarea4.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "miembro")
public class Miembro {
    
    public Miembro() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    @OneToMany(mappedBy = "miembro")
    private List<Actividad> actividades;

    public Integer getId() {
        return id;}
    
    public void setId(Integer id) {
        this.id = id;}
    
    public String getNombre() {
        return nombre;}
    
    public void setNombre(String nombre) {
        this.nombre = nombre;}
    
    public String getCorreo() {
        return correo;}
    
    public void setCorreo(String correo) {
        this.correo = correo;}
    
    public String getTelefono() {
        return telefono;}
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;}
    
    public String getTipo() {
        return tipo;}
    
    public void setTipo(String tipo) {
        this.tipo = tipo;}
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;}
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;}
    
    public Comuna getComuna() {
        return comuna;}

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;}

    public List<Actividad> getActividades() {
        return actividades;}

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;}

}