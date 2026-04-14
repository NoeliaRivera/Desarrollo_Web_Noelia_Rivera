function CamposExtra() {
    var cargos = document.getElementsByName("cargo");
    var cargoElement = null;
    for (var i = 0; i < cargos.length; i++) {
        if (cargos[i].checked) {
            cargoElement = cargos[i];
        }
    }
    if (!cargoElement) return;

    var cargo = cargoElement.value;
    var ExtraEstudiante = document.getElementById("extra-estudiante");
    var ExtraAcademico = document.getElementById("extra-academico");
    var ExtraFuncionario = document.getElementById("extra-funcionario");

    ExtraEstudiante.style.display = "none";
    ExtraAcademico.style.display = "none";
    ExtraFuncionario.style.display = "none";

    if (cargo === "pregrado" || cargo === "postgrado") {
        ExtraEstudiante.style.display = "block";
    } else if (cargo === "académico") {
        ExtraAcademico.style.display = "block";
    } else if (cargo === "funcionario") {
        ExtraFuncionario.style.display = "block";
    }
}

function validar() {
    var form = document.Registro; 
    var esValido = true;
    var nombre = form.usuario.value.trim();
    var rut = form.rut.value.trim();
    var correo = form.correo.value.trim();
    var cargos = document.getElementsByName("cargo");
    var cargoRadio = null;
    
    for (var i = 0; i < cargos.length; i++) {
        if (cargos[i].checked) {
            cargoRadio = cargos[i];
        }
    }

    var correoInput = document.getElementById("correo");    
    var errNombre = document.getElementById("error-nombre");
    var errRut = document.getElementById("error-rut");
    var errCorreo = document.getElementById("error-correo");
    var errCargo = document.getElementById("error-cargo");
    var errAño = document.getElementById("error-año");
    var errFacultad = document.getElementById("error-facultad");
    var errTrabajo = document.getElementById("error-trabajo");

    errNombre.className = "error";
    errRut.className = "error";
    errCorreo.className = "error";
    errCargo.className = "error";
    errAño.className = "error";
    errFacultad.className = "error";
    errTrabajo.className = "error";

    if (nombre.length < 3) {
        errNombre.className = "error visible";
        esValido = false;
    }

    var rutRegex = /^\d{7,8}-[\dkK]$/;
    if (!rutRegex.test(rut)) {
        errRut.className = "error visible";
        esValido = false;
    }

    if (correo.length === 0 || !correoInput.checkValidity()) {
        errCorreo.className = "error visible";
        esValido = false;
    }

    if (!cargoRadio) {
        errCargo.className = "error visible";
        esValido = false;
    } else {
        var valorCargo = cargoRadio.value;
        if (valorCargo === "pregrado" || valorCargo === "postgrado") {
            var añoVal = form.año.value.trim();
            var añoRegex = /^\d{4}$/;
            if (!añoRegex.test(añoVal)) {
                errAño.className = "error visible";
                esValido = false;
            }
        } else if (valorCargo === "académico") {
            var codigo = form.código.value.trim();
            var codigoRegex = /^[a-zA-Z]{2}\d{4}-\d$/;
            if (codigo === "" || !codigoRegex.test(codigo)) {
                errFacultad.className = "error visible";
                esValido = false;
            }
        } else if (valorCargo === "funcionario") {
            var trabajo = form.Trabajo.value.trim(); 
            if (trabajo === "") {
                errTrabajo.className = "error visible";
                esValido = false;
            }
        }
    }

    return esValido;
}