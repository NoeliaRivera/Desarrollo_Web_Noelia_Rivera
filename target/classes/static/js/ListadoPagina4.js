function Filtrar() {
    var seleccionMiembro = document.getElementById("FiltroUsuario").value;
    var seleccionActividad = document.getElementById("FiltroActividad").value;

    var filas = document.querySelectorAll("#tabla-usuarios tr");

    filas.forEach(fila => {
        var celdas = fila.getElementsByTagName("td");
        if (celdas.length === 0) return;

        var tipoMiembro = celdas[1].textContent.trim().toLowerCase();
        var tipoActividad = celdas[4].textContent.trim().toLowerCase();

        var okMiembro = (seleccionMiembro === "todos" || tipoMiembro === seleccionMiembro);
        var okAct = (seleccionActividad === "todos" || tipoActividad === seleccionActividad);

        fila.style.display = (okMiembro && okAct) ? "" : "none";
    });
}