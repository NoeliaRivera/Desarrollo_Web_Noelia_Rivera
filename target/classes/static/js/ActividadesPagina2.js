let actividadSeleccionada = null;
function mostrarActividad(id) {
    actividadSeleccionada = id;
    fetch(`/api/actividad/${id}`)
    .then(response => response.json())
    .then(data => {
        let html = `<p><b>Actividad:</b> ${data.nombreActividad}</p>
        <p><b>Tipo:</b> ${data.tipo}</p>
        <p><b>Descripción:</b> ${data.descripcion}</p>
        <p><b>Día:</b> ${data.dia}</p>
        <p><b>Hora inicio:</b> ${data.horaInicio}</p>
        <p><b>Duración:</b> ${data.duracion}</p>`;

        document.getElementById("detalleActividad").innerHTML = html;
    });

}

document.getElementById("buscador").addEventListener("input", function () {
    const texto = this.value.trim();

    if (texto.length < 3) {
        document.getElementById("resultados").innerHTML = "";
        return;
    }

    fetch(`/api/actividades/buscar?texto=${texto}`)
        .then(res => res.json())
        .then(data => {
            if (data.length === 0) {
                document.getElementById("resultados").innerHTML = "<p>No se encontraron actividades.</p>";
                return;}

            let html = "";

            for (let a of data) {
                html += `<div>
                <b>${highlight(a.nombreActividad, texto)}</b><br>
                ${highlight(a.descripcion, texto)}<br>
                Comuna: ${highlight(a.comuna, texto)}<br>
                Miembro: ${a.miembro}<br>
                Nota: ${a.nota}<br>

                <button onclick="evaluar(${a.id})">Evaluar</button></div><hr>`;
            }
            document.getElementById("resultados").innerHTML = html;
        });
});

function evaluar(id) {
    const valor = parseInt(prompt("Ingrese nota del 1 al 7 (donde 1 es muy malo y 7 excelente)"));

    if (isNaN(valor) || valor < 1 || valor > 7) {
        alert("Nota inválida");
        return;
    }

    fetch(`/api/actividad/${id}/nota`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ valor: valor })
    })
    .then(res => res.json())
    .then(data => {
        alert("Nota guardada. Promedio: " + data);
        document.getElementById("buscador").dispatchEvent(
            new Event("input"));
    });
}

function highlight(text, query) {
    return text.replace(
        new RegExp(query, "gi"),
        match => `<mark>${match}</mark>`
    );
}