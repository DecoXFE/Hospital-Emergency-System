-- Reset database
--Derby does not support DROP TABLE IF EXISTS 

DROP TABLE PROCEDIMIENTOS;
DROP TABLE INCOMPATIBILIDADESDEPROCEDIMIENTOS;
DROP TABLE PROCEDIMIENTOSQUESUELENAPLICARSEJUNTOS;
DROP TABLE DESCRIPCIONESDEPROCEDIMIENTOS;
DROP TABLE CONSEJOS;
DROP TABLE LLAMADASNOCRITICAS;
DROP TABLE ACTIVACIONESDEOPERATIVOS;
DROP TABLE DECISIONESDETRASLADOAHOSPITAL;
DROP TABLE HOSPITALES;
DROP TABLE OPERATIVOS;
DROP TABLE LLAMADASCRITICAS;
DROP TABLE LLAMADASDEASEGURADOS;
DROP TABLE LLAMADASINFUNDADAS;
DROP TABLE LLAMADAS;
DROP TABLE POLIZASCONTRATADAS;
DROP TABLE POLIZAS;
DROP TABLE CONDUCTORESENTURNO;
DROP TABLE MEDICOSENTURNO;
DROP TABLE TURNOSDEOPERATIVO;
DROP TABLE OPERADORESENTURNO;
DROP TABLE TURNOSDEOPERADOR;
DROP TABLE CONSULTORIOS;
DROP TABLE VEHICULOS;
DROP TABLE MODELOS;
DROP TABLE MARCAS;

DROP TABLE ESTADOSDEVEHICULO;
DROP TABLE ESTADOSDEOPERATIVO;
DROP TABLE TIPOSDETURNODEOPERATIVO;
DROP TABLE TIPOSDETURNODEOPERADOR;

DROP TABLE DISPONIBILIDADES;
DROP TABLE VINCULACIONESCONLAEMPRESA;
DROP TABLE ROLESENEMPRESA;
DROP TABLE TIPOSDEDISPONIBILIDAD;
DROP TABLE TIPOSDEVINCULACION;
DROP TABLE TIPOSDEROL;
DROP TABLE EMPLEADOS;
DROP TABLE ASEGURADOS;
DROP TABLE PERSONAS;

DROP TABLE DIRECCIONES;

-- Datatype
create table DIRECCIONES
(
	Id SMALLINT not null,
	NombreDeLaVia VARCHAR(20) not null,
	Numero SMALLINT,
	Otros VARCHAR(20),
	CodigoPostal INT not null,
	Localidad VARCHAR(20) not null,
	Provincia VARCHAR(20) not null,
		PRIMARY KEY(Id)
);

-- Entity
create table PERSONAS
(
	Nif VARCHAR(9) not null primary key,
	Nombre VARCHAR(50) not null,
	Apellidos VARCHAR(50) not null,
	FechaDeNacimiento DATE not null,
	Telefono VARCHAR(12) not null,
	DireccionPostal SMALLINT not null,
		FOREIGN KEY(DireccionPostal) REFERENCES DIRECCIONES(Id)
);

--Entity
create table ASEGURADOS
(
	Nif VARCHAR(9) not null primary key,
	Sexo CHAR not null,
	numeroDeCuenta VARCHAR(24) not null,
	    FOREIGN KEY(Nif) REFERENCES PERSONAS(Nif)
);

-- Entity
create table EMPLEADOS
(
	Nif VARCHAR(9) not null primary key,
	FechaInicioEnEmpresa DATE not null,
	Password VARCHAR(10) not null,
            FOREIGN KEY(Nif) REFERENCES PERSONAS(Nif)
);

-- Enum
create table TIPOSDEROL
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEROL
VALUES  (1,'Gerente'),
        (2,'Operador'),
        (3,'Médico'),
        (4,'Conductor');

-- Enum
create table TIPOSDEVINCULACION
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPOSDEVINCULACION
VALUES  (1,'ConNormalidad'),
        (2,'FueraDeLaEmpresa'),
        (3,'EnERTE');

-- Enum
create table TIPOSDEDISPONIBILIDAD
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEDISPONIBILIDAD
VALUES  (1,'DeVacaciones'),
        (2,'DeBaja'),
	(3, 'Disponible');


-- Association
create table ROLESENEMPRESA
(
	FechaInicioEnPuesto DATE not null,
	Empleado VARCHAR(9) not null,
	FechaPermisoConduccion DATE,
	NumeroColegiadoMedico VARCHAR(9),
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPOSDEROL(IdTipo)
);

-- Association
create table VINCULACIONESCONLAEMPRESA
(
	FechaInicio DATE not null,
	FechaFin Date,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPOSDEVINCULACION(IdTipo) 
);

-- Association
create table DISPONIBILIDADES
(
	FechaInicio DATE not null,
	FechaFin DATE,
	Empleado VARCHAR(9) not null,
	Disponibilidad SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Disponibilidad) REFERENCES TIPOSDEDISPONIBILIDAD(IdTipo)
);

--Enum 
create table TIPOSDETURNODEOPERATIVO (
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPOSDETURNODEOPERATIVO
VALUES (1,'DeDia7'),
       (2,'DeNoche19');

--Enum 
create table TIPOSDETURNODEOPERADOR (
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDETURNODEOPERADOR 
VALUES (1,'DeMañana7'),
       (2,'DeTarde15'),
       (3,'DeNoche23');

--Enum 
create table ESTADOSDEOPERATIVO (
	IdEstado SMALLINT not null,
	NombreEstado VARCHAR(20) not null unique,
		PRIMARY KEY(IdEstado)
);

INSERT INTO ESTADOSDEOPERATIVO 
VALUES (1,'disponible'),
       (2,'activado'),
       (3,'bloqueado');

--Enum 
create table ESTADOSDEVEHICULO (
	IdEstado SMALLINT not null,
	NombreEstado VARCHAR(20) not null unique,
		PRIMARY KEY(IdEstado)
);

INSERT INTO ESTADOSDEVEHICULO
VALUES (1,'enServicio'),
       (2,'enTaller'),
       (3,'deBaja');

create table MARCAS (
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
		PRIMARY KEY(Id)
);

create table MODELOS (
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null,
	Marca SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Marca) REFERENCES MARCAS(Id)
);

create table VEHICULOS (
	Matricula VARCHAR(8) not null,
	UbicacionLatitud REAL not null,
	UbicacionLongitud REAL not null,
	FechaAlta DATE not null,
	Estado SMALLINT not null,
	Modelo SMALLINT not null,
		PRIMARY KEY(Matricula),
		FOREIGN KEY(Estado) REFERENCES ESTADOSDEVEHICULO(IdEstado),
		FOREIGN KEY(Modelo) REFERENCES MODELOS(Id)
);

create table CONSULTORIOS (
	Id SMALLINT not null,
	Direccion SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Direccion) REFERENCES DIRECCIONES(Id)
);

create table TURNOSDEOPERADOR (
	Id INT not null,
	FechaTurno DATE not null,
	FechaCreacion DATE not null,
	TipoDeTurno SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(TipoDeTurno) REFERENCES TIPOSDETURNODEOPERADOR(IdTipo)

);

create table OPERADORESENTURNO (
	Turno INT not null,
	Operador VARCHAR(9) not null,
		PRIMARY KEY(Turno,Operador),
		FOREIGN KEY(Turno) REFERENCES TURNOSDEOPERADOR(Id),
		FOREIGN KEY(Operador) REFERENCES EMPLEADOS(Nif)
);

create table TURNOSDEOPERATIVO (
	Id INT not null,
	FechaTurno DATE not null,
	FechaCreacion DATE not null,
	TipoDeTurno SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(TipoDeTurno) REFERENCES TIPOSDETURNODEOPERATIVO(IdTipo)
);

create table MEDICOSENTURNO (
	Turno INT not null,
	Medico VARCHAR(9) not null,
		PRIMARY KEY(Turno,Medico),
		FOREIGN KEY(Turno) REFERENCES TURNOSDEOPERATIVO(Id),
		FOREIGN KEY(Medico) REFERENCES EMPLEADOS(Nif)
);

create table CONDUCTORESENTURNO (
	Turno INT not null,
	Conductor VARCHAR(9) not null,
		PRIMARY KEY(Turno,Conductor),
		FOREIGN KEY(Turno) REFERENCES TURNOSDEOPERATIVO(Id),
		FOREIGN KEY(Conductor) REFERENCES EMPLEADOS(Nif)
);

create table POLIZAS (
	NumeroPoliza VARCHAR(15) not null,
	FechaInicio DATE not null,
	FechaVencimiento DATE not null,
		PRIMARY KEY(NumeroPoliza)
);

create table POLIZASCONTRATADAS (
	Asegurado VARCHAR(9) not null,
	Poliza VARCHAR(15) not null,
		PRIMARY KEY(Asegurado,Poliza),
		FOREIGN KEY(Asegurado) REFERENCES ASEGURADOS(Nif),
		FOREIGN KEY(Poliza) REFERENCES POLIZAS(NumeroPoliza)
);


create table LLAMADAS (
	Id INT not null,
	NumeroTelefonoOrigen VARCHAR(12) not null,
	FechaInicio DATE not null,
	HoraInicio TIME not null,
	FechaFin DATE not null,
	HoraFin TIME not null,
	NombreComunicante VARCHAR(100) not null,
	AtendidaPor VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(AtendidaPor) REFERENCES EMPLEADOS(Nif)
);


create table LLAMADASINFUNDADAS (
	Id INT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Id) REFERENCES LLAMADAS(Id)
);

create table LLAMADASDEASEGURADOS (
	Id INT not null,
	DescripcionDeLaEmergencia VARCHAR(255) not null,
	Paciente VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Id) REFERENCES LLAMADAS(Id),
		FOREIGN KEY(Paciente) REFERENCES ASEGURADOS(Nif)
);

create table LLAMADASCRITICAS (
	Id INT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Id) REFERENCES LLAMADAS(Id)
);

create table OPERATIVOS (
	Id INT not null,
	FechaCreacion DATE not null,
	Estado SMALLINT not null,
	Turno INT not null,
	Base SMALLINT not null,
	Vehiculo VARCHAR(8) not null,
	Conductor VARCHAR(9) not null,
	Medico VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Estado) REFERENCES ESTADOSDEOPERATIVO(IdEstado),
		FOREIGN KEY(Turno) REFERENCES TURNOSDEOPERATIVO(Id),
		FOREIGN KEY(Base) REFERENCES CONSULTORIOS(Id),
		FOREIGN KEY(Vehiculo) REFERENCES VEHICULOS(Matricula),
		FOREIGN KEY(Conductor) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Medico) REFERENCES EMPLEADOS(Nif)
);

create table HOSPITALES (
	Id SMALLINT not null,
	Nombre VARCHAR(50) not null,
	Direccion SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Direccion) REFERENCES DIRECCIONES(Id)
);

create table DECISIONESDETRASLADOAHOSPITAL (
	Id INT not null,
	FechaInicioTraslado DATE not null,
	HoraInicioTraslado TIME not null,
	DestinoDeTraslado SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(DestinoDeTraslado) REFERENCES HOSPITALES(Id)
);

create table ACTIVACIONESDEOPERATIVOS (
	Id INT not null,
	DireccionDondeAcudir SMALLINT not null,
	FechaActivación DATE not null,
	HoraActivación TIME not null,
	OperativoActivado INT not null,
	FechaSeHaceCargoMedico DATE,
	HoraSeHaceCargoMedico TIME,
	FechaCierre DATE,
	HoraCierre TIME,
	DecisionTrasladoAHospital INT,
		PRIMARY KEY(Id),
		FOREIGN KEY(DireccionDondeAcudir) REFERENCES DIRECCIONES(Id),
		FOREIGN KEY(DecisionTrasladoAHospital) REFERENCES DECISIONESDETRASLADOAHOSPITAL(Id)
);

create table LLAMADASNOCRITICAS (
	Id INT not null,
	EsLeve BOOLEAN not null,
	RequiereOperativo INT,
		PRIMARY KEY(Id),
		FOREIGN KEY(RequiereOperativo) REFERENCES ACTIVACIONESDEOPERATIVOS(Id)
);

create table CONSEJOS (
	Descripcion VARCHAR(255) not null,
	Resultado VARCHAR(255) not null,
	Soluciona BOOLEAN not null,
	Llamada INT not null,
		FOREIGN KEY(Llamada) REFERENCES LLAMADASNOCRITICAS(Id)
);

create table DESCRIPCIONESDEPROCEDIMIENTOS (
	Id SMALLINT not null,
	Nombre VARCHAR(50) not null,
	Descripcion VARCHAR(255) not null,
	Variantes VARCHAR(255) not null,
		PRIMARY KEY(Id)
);

create table PROCEDIMIENTOSQUESUELENAPLICARSEJUNTOS (
	Id1 SMALLINT not null,
	Id2 SMALLINT not null,
		PRIMARY KEY(Id1,Id2),
		FOREIGN KEY(Id1) REFERENCES DESCRIPCIONESDEPROCEDIMIENTOS(Id),
		FOREIGN KEY(Id2) REFERENCES DESCRIPCIONESDEPROCEDIMIENTOS(Id)
);

create table INCOMPATIBILIDADESDEPROCEDIMIENTOS (
	Id1 SMALLINT not null,
	Id2 SMALLINT not null,
		PRIMARY KEY(Id1,Id2),
		FOREIGN KEY(Id1) REFERENCES DESCRIPCIONESDEPROCEDIMIENTOS(Id),
		FOREIGN KEY(Id2) REFERENCES DESCRIPCIONESDEPROCEDIMIENTOS(Id)
);

create table PROCEDIMIENTOS (
	ResultadoObservado VARCHAR(255) not null,
	FechaInicio DATE not null,
	HoraInicio TIME not null,
	Descripcion SMALLINT not null,
	SeAplicaEn INT not null,
		FOREIGN KEY(Descripcion) REFERENCES DESCRIPCIONESDEPROCEDIMIENTOS(Id),
		FOREIGN KEY(SeAplicaEn) REFERENCES ACTIVACIONESDEOPERATIVOS(Id)
);