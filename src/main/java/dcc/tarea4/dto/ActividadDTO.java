package dcc.tarea4.dto;

public class ActividadDTO {
    public Integer id;
    public String nombreActividad;
    public String descripcion;
    public String dia;
    public String tipo;
    public String comuna;
    public String miembro;
    public String nota;
    public String horaInicio;
    public String duracion;

public ActividadDTO(Integer id,
                    String nombreActividad,
                    String descripcion,
                    String dia,
                    String tipo,
                    String comuna,
                    String miembro,
                    String nota,
                    String horaInicio,
                    String duracion) {
    this.id = id;
    this.nombreActividad = nombreActividad;
    this.descripcion = descripcion;
    this.dia = dia;
    this.tipo = tipo;
    this.comuna = comuna;
    this.miembro = miembro;
    this.nota = nota;
    this.horaInicio = horaInicio;
    this.duracion = duracion;}}