function validarActividades(event) {
    event.preventDefault();
    var form = document.Actividades;
    var actividad = form.actividad.value.trim();
    var todosLosDias = document.getElementsByName("día");
    var diasCheckboxes = [];
    for (var i = 0; i < todosLosDias.length; i++) {
        if (todosLosDias[i].checked) {
            diasCheckboxes.push(todosLosDias[i]);
        }
    }
    var horas = form.horas.value;
    var archivo = form.archivo.value;
    var enlace = form.enlace.value.trim();
    
    var errActividad = document.getElementById("error-actividad");
    var errDias = document.getElementById("err-días");
    var errHoras = document.getElementById("err-horas");
    var errArchivo = document.getElementById("error-archivo");
    var errEnlace = document.getElementById("error-enlace");

    errActividad.className = "error";
    errDias.className = "error";
    errHoras.className = "error";
    errArchivo.className = "error";
    errEnlace.className = "error";

    var esValido = true;

    if (actividad.length < 3) {
        errActividad.className = "error visible";
        esValido = false;
    }

    if (diasCheckboxes.length === 0) {
        errDias.className = "error visible";
        esValido = false;
    }

    if (horas === "" || horas <= 0) {
        errHoras.className = "error visible";
        esValido = false;
    }

    if (archivo === "") {
        errArchivo.className = "error visible";
        esValido = false;
    }
    
    if (enlace === "") {
        errEnlace.className = "error visible";
        esValido = false;
    }

    if (esValido) {
        var listaDias = [];
        for (var i = 0; i < diasCheckboxes.length; i++) {
            listaDias.push(diasCheckboxes[i].value);
        }

        var contenedorResumen = document.getElementById("resumen-actividad");
        
        contenedorResumen.style.display = "block"; 
        
        contenedorResumen.innerHTML = "<h3>Resumen de tu actividad:</h3>" +
        "<p><strong>Actividad:</strong> " + actividad + "</p>" +
        "<p><strong>Días:</strong> " + listaDias.join(", ") + "</p>" +
        "<p><strong>Horas semanales:</strong> " + horas + "</p>" +
        "<p><em>Actividad registrada con éxito ¡Gracias!</em></p>";
    }

    return esValido;
}

document.getElementById("boton-estadisticas").onclick = function() {
    window.location.href = "EstadisticasPagina3.html";
};

document.getElementById("boton-usuarios").onclick = function() {
    window.location.href = "ListadoPagina4.html";
};

document.getElementById("boton-registro").onclick = function() {
    window.location.href = "RegistroPagina1.html";
};