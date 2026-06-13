from flask import Flask, render_template, request, redirect, url_for, jsonify
from extensions import db
import os
from collections import Counter

app = Flask(__name__)

app.config["SQLALCHEMY_DATABASE_URI"] = "mysql+pymysql://root:programacionweb@localhost:3306/tarea2"
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
app.config["UPLOAD_FOLDER"] = os.path.join(os.getcwd(), "uploads")

db.init_app(app)

from models.modelo import Miembro, Actividad, Comentario, crearMiembro, crearActividad, obtenerComunas, obtenerMiembros, ultimosMiembros

@app.route("/")
def index():
    ultimos = ultimosMiembros()
    return render_template("PortadaPagina1.html", ultimos=ultimos)

@app.route("/registrar", methods=["GET", "POST"])
def registrar():
    if request.method == "POST":
        nombre = request.form.get("usuario")
        correo = request.form.get("correo")
        telefono = request.form.get("telefono")
        comuna_id = request.form.get("comuna")
        tipo_miembro = request.form.get("cargo")
        nuevo_miembro = crearMiembro(
            nombre=nombre,
            email=correo,
            telefono=telefono,
            comuna_id=comuna_id,
            tipo=tipo_miembro)
        actividad_nombre = request.form.get("actividad")
        actividad_tipo = request.form.get("tipo")
        dia = request.form.get("dia")
        hora_inicio = request.form.get("hora_inicio")
        duracion = request.form.get("duracion")
        descripcion = request.form.get("descripcion")
        crearActividad(
            miembro_id=nuevo_miembro.id,
            nombre=actividad_nombre,
            tipo=actividad_tipo,
            dia=dia,
            hora_inicio=hora_inicio,
            duracion=duracion,
            descripcion=descripcion)
        return redirect(url_for("actividades"))
    comunas = obtenerComunas()
    return render_template("RegistroPagina1.html", comunas=comunas)

@app.route("/actividades")
def actividades():
    actividades = Actividad.query.all()
    return render_template(
        "ActividadesPagina2.html",
        actividades=actividades)

@app.route("/api/actividad/<int:id>")
def obtenerActividad(id):
    actividad = Actividad.query.get_or_404(id)
    return jsonify({
        "id": actividad.id,
        "nombre": actividad.nombre,
        "tipo": actividad.tipo,
        "descripcion": actividad.descripcion,
        "dia": actividad.dia,
        "hora_inicio": actividad.hora_inicio,
        "duracion": actividad.duracion})

@app.route("/api/comentarios/<int:actividad_id>")
def obtenerComentarios(actividad_id):
    comentarios = Comentario.query.filter_by(
        actividad_id=actividad_id
        ).all()
    datos = []
    for comentario in comentarios:
        datos.append({
            "nombre": comentario.nombre,
            "texto": comentario.texto,
            "fecha": comentario.fecha.strftime("%d-%m-%Y %H:%M")})
    return jsonify(datos)

@app.route("/api/comentarios", methods=["POST"])
def crearComentario():
    datos = request.get_json()
    comentario = Comentario(
        nombre=datos["nombre"],
        texto=datos["texto"],
        actividad_id=datos["actividad_id"])
    db.session.add(comentario)
    db.session.commit()
    return jsonify({"ok": True})

@app.route("/estadisticas")
def estadisticas():
    return render_template("EstadisticasPagina3.html")

@app.route("/api/conteoMiembros")
def conteoMiembros():
    miembros = Miembro.query.all()
    contador = Counter()
    for miembro in miembros:
        fecha = miembro.fecha_registro.strftime("%Y-%m-%d")
        contador[fecha] += 1
    fechas = sorted(contador.keys())
    datos = {
        "categorias": fechas,
        "valores": [contador[f] for f in fechas]}
    return jsonify(datos)

@app.route("/api/conteoActividades")
def conteoActividades():
    actividades = Actividad.query.all()
    contador = Counter()
    for actividad in actividades:
        contador[actividad.tipo] += 1
    datos = []
    for tipo, cantidad in contador.items():
        datos.append({
            "name": tipo,
            "y": cantidad
        })
    return jsonify(datos)

@app.route("/api/conteoComunas")
def conteoComunas():
    actividades = Actividad.query.all()
    contador = Counter()
    for actividad in actividades:
        comuna = (
            actividad
            .miembro
            .comuna
            .nombre)
        contador[comuna] += 1
    comunas = list(contador.keys())
    cantidades = list(contador.values())
    return jsonify({
        "comunas": comunas,
        "cantidades": cantidades})

@app.route("/miembros")
def miembros():
    lista_miembros = obtenerMiembros()
    return render_template("ListadoPagina4.html", miembros=lista_miembros)


if __name__ == "__main__":
    app.run(debug=True)

