from extensions import db
from sqlalchemy.orm import relationship, joinedload
from sqlalchemy import ForeignKey
from datetime import datetime

DB_NAME = "tarea2"
DB_USERNAME = "cc5002"
DB_PASSWORD = "programacionweb"
DB_HOST = "localhost"
DB_PORT = 3306

class Region(db.Model):
    __tablename__ = "region"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(200), nullable=False)
    comunas = relationship("Comuna", back_populates="region")

class Comuna(db.Model):
    __tablename__ = "comuna"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(200), nullable=False)
    region_id = db.Column(db.Integer, ForeignKey("region.id"), nullable=False)
    region = relationship("Region", back_populates="comunas")
    miembros = relationship("Miembro", back_populates="comuna")

class Miembro(db.Model):
    __tablename__ = "miembro"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(255), nullable=False)
    email = db.Column(db.String(80), nullable=False)
    telefono = db.Column(db.String(15), nullable=False)
    tipo = db.Column(db.String(50), nullable=False)
    fecha_registro = db.Column(db.DateTime, default=datetime.utcnow)
    comuna_id = db.Column(db.Integer, ForeignKey("comuna.id"), nullable=False)
    comuna = relationship("Comuna", back_populates="miembros")
    actividades = relationship("Actividad", back_populates="miembro", cascade="all, delete")

class Actividad(db.Model):
    __tablename__ = "actividad"
    id = db.Column(db.Integer, primary_key=True)
    miembro_id = db.Column(db.Integer, ForeignKey("miembro.id"), nullable=False)
    dia = db.Column(db.String(20), nullable=False)
    hora_inicio = db.Column(db.String(5), nullable=False)
    duracion = db.Column(db.String(5), nullable=False)
    tipo = db.Column(db.String(50), nullable=False)
    nombre = db.Column(db.String(100), nullable=False)
    descripcion = db.Column(db.Text)
    comentarios = relationship("Comentario", back_populates="actividad", cascade="all, delete")
    miembro = relationship("Miembro", back_populates="actividades")

class Comentario(db.Model):
    __tablename__ = "comentario"
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(80), nullable=False)
    texto = db.Column(db.String(300), nullable=False)
    fecha = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    actividad_id = db.Column(db.Integer, ForeignKey("actividad.id"), nullable=False)
    actividad = relationship("Actividad", back_populates="comentarios")

def crearMiembro(nombre, email, telefono, comuna_id, tipo):
    nuevo = Miembro(nombre=nombre, email=email, telefono=telefono, comuna_id=comuna_id, tipo=tipo)
    db.session.add(nuevo)
    db.session.commit()
    return nuevo

def crearActividad(miembro_id, dia, hora_inicio, duracion, tipo, nombre, descripcion):
    actividad = Actividad(miembro_id=miembro_id, dia=dia, hora_inicio=hora_inicio, duracion=duracion, tipo=tipo, nombre=nombre, descripcion=descripcion)
    db.session.add(actividad)
    db.session.commit()
    return actividad

def obtenerComunas():
    return (db.session.query(Comuna)
    .order_by(Comuna.nombre)
    .all())

def obtenerMiembros():
    return (db.session.query(Miembro)
    .options(joinedload(Miembro.actividades))
    .order_by(Miembro.fecha_registro.desc())
    .all())

def ultimosMiembros():
    return (db.session.query(Miembro)
    .options(joinedload(Miembro.actividades))
    .order_by(Miembro.fecha_registro.desc())
    .limit(5)
    .all())