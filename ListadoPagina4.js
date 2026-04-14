function Filtrar() {
    var seleccionMiembro = document.getElementById("FiltroUsuario").value.toLowerCase();
    var seleccionActividad = document.getElementById("FiltroActividad").value.toLowerCase();
    
    var tabla = document.getElementById("tabla-usuarios");
    var filas = tabla.getElementsByTagName("tr");

    for (var i = 0; i < filas.length; i++) {
        var celdas = filas[i].getElementsByTagName("td");
        
        if (celdas.length > 0) {
            var textoMiembro = celdas[1].textContent.toLowerCase();
            var textoActividad = celdas[4].textContent.toLowerCase();
            var coincideMiembro = (seleccionMiembro === "todos" || textoMiembro.includes(seleccionMiembro));
            var coincideActividad = (seleccionActividad === "todos" || textoActividad.includes(seleccionActividad));

            if (coincideMiembro && coincideActividad) {
                filas[i].style.display = ""; 
            } else {
                filas[i].style.display = "none"; 
            }
        }
    }
}

document.getElementById("boton-actividades").onclick = function() {
    window.location.href = "ActividadesPagina2.html";
};

document.getElementById("boton-registro").onclick = function() {
    window.location.href = "RegistroPagina1.html";
};

