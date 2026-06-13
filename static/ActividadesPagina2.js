let actividadSeleccionada = null;
function mostrarActividad(id) {
    actividadSeleccionada = id;
    fetch(`/api/actividad/${id}`)
    .then(response => response.json())
    .then(data => {
        let html = `
            <p>Actividad:${data.nombre}</p>
            <p>Tipo: ${data.tipo}</p>
            <p>Descripción: ${data.descripcion}</p>
            <p>Días: ${data.dia}</p>
            <p>Hora inicio: ${data.hora_inicio}</p>
            <p>Duración: ${data.duracion}</p>`;
        document.getElementById(
            "detalleActividad"
        ).innerHTML = html;
        cargarComentarios(actividadSeleccionada);
    });
}

function cargarComentarios(id){
    fetch(`/api/comentarios/${id}`)
    .then(response => response.json())
    .then(data => {
        const contenedor = document.getElementById("listaComentarios");
        contenedor.innerHTML = "";

        for (let comentario of data) {
            const div = document.createElement("div");
            div.className = "comentario";
            const nombre = document.createElement("strong");
            nombre.textContent = comentario.nombre;
            const fecha = document.createElement("span");
            fecha.textContent = ` (${comentario.fecha})`;
            const br = document.createElement("br");
            const texto = document.createElement("p");
            texto.textContent = comentario.texto;
            div.appendChild(nombre);
            div.appendChild(fecha);
            div.appendChild(br);
            div.appendChild(texto);
            contenedor.appendChild(div);
        }
    });
}

document
.getElementById("formComentario")
.addEventListener("submit", function(e){
    console.log("Formulario enviado");
    e.preventDefault();

    if(actividadSeleccionada === null){
        alert("Seleccione una actividad");
        return;
    }

    console.log("Actividad:", actividadSeleccionada);

    fetch("/api/comentarios", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"},
        body: JSON.stringify({
            actividad_id: actividadSeleccionada,
            nombre: document.getElementById(
                "nombreComentario").value,
            texto: document.getElementById(
                "Comentario").value
        })
    })

    .then(response => {
        console.log("Respuesta recibida");
        return response.json();})
    .then(data => {
        console.log(data);
        cargarComentarios(actividadSeleccionada);
        document
        .getElementById("formComentario")
        .reset();
    });
});