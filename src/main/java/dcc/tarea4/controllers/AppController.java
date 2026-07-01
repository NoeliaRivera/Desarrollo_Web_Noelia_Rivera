package dcc.tarea4.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dcc.tarea4.services.AppService;

@Controller
public class AppController{
    private final AppService appService;
    
    public AppController(AppService appService) {
        this.appService = appService;}
        
    @GetMapping("/")
    public String portada(Model models) {
        models.addAttribute("ultimos", appService.obtenerUltimosMiembros());
        return "PortadaPagina1";}

    @GetMapping("/registro")
    public String registro(Model models) {
        models.addAttribute("comunas",
        appService.obtenerComunas());
        return "RegistroPagina1";}

    @PostMapping("/registro")
    public String registrar(
        @RequestParam String usuario,
        @RequestParam String correo,
        @RequestParam String telefono,
        @RequestParam Integer comuna,
        @RequestParam String cargo,
        @RequestParam String nombreActividad,
        @RequestParam String descripcion,
        @RequestParam List<String> dia,
        @RequestParam String horaInicio,
        @RequestParam String duracion,
        @RequestParam String tipo) {
            appService.registrarMiembro(usuario, correo, telefono, comuna, cargo, nombreActividad, descripcion, dia, horaInicio, duracion, tipo);
            return "redirect:/miembros";}

    @GetMapping("/actividades")
    public String actividades(Model models) {
        models.addAttribute("actividades", appService.obtenerActividades());
        return "ActividadesPagina2";}

    @GetMapping("/estadisticas")
    public String estadisticas(Model models) {
        return "EstadisticasPagina3";}


    @GetMapping("/miembros")
    public String miembros(Model models) {
        models.addAttribute("miembros", appService.obtenerMiembros());
        return "ListadoPagina4";}
}