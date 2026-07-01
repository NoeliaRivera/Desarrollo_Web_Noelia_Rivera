package dcc.tarea4.models;
import jakarta.persistence.*;

@Entity
@Table(name="comuna")
public class Comuna {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    public Integer getId() {
        return id;}

    public void setId(Integer id) {
        this.id = id;}

    public String getNombre() {
        return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;}

    public Region getRegion() {
        return region;}

    public void setRegion(Region region) {
        this.region = region;}
}