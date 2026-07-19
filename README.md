# Llanquihue Tour App

Proyecto final desarrollado para la asignatura de **Programación Orientada a Objetos**.

El sistema permite administrar la información de una agencia de turismo mediante una aplicación de escritorio desarrollada en **Java Swing**, aplicando los principales conceptos de Programación Orientada a Objetos, persistencia de datos y organización modular mediante paquetes.

---

# Objetivo

Desarrollar un sistema capaz de administrar los recursos de una agencia turística, permitiendo registrar y gestionar clientes, guías turísticos, empresas externas, vehículos, alojamientos, servicios turísticos y reservas, conservando la información mediante archivos de texto.

---

# Funcionalidades

El sistema permite:

- Registrar clientes.
- Registrar guías turísticos.
- Registrar empresas de transporte.
- Registrar empresas de alojamiento.
- Registrar vehículos asociados a empresas.
- Registrar alojamientos asociados a empresas.
- Registrar distintos tipos de servicios turísticos.
- Crear y eliminar reservas.
- Controlar automáticamente los cupos disponibles.
- Guardar y cargar la información desde archivos de texto.

---

# Organización del proyecto

El proyecto está organizado utilizando una arquitectura por paquetes.

```
src
│
├── UI
├── model
├── data
├── service
├── util
└── resources
```

## UI

Contiene todas las interfaces gráficas desarrolladas con Java Swing, incluyendo la ventana principal, menús, formularios de registro, paneles de administración y visualización.

### Ventana principal

- VentanaPrincipal
- PanelBase
- main

### Menús

- PanelMenuPrincipal
- PanelMenuEmpresas
- PanelMenuGuias
- PanelMenuServicios
- PanelMenuClientesReservas

### Registro de entidades

- PanelRegistrarCliente
- PanelRegistrarGuia
- PanelRegistrarEmpresaTransporte
- PanelRegistrarEmpresaAlojamiento
- PanelRegistrarVehiculo
- PanelRegistrarAlojamiento
- PanelRegistrarServicio
- PanelNuevaReserva

### Administración

- PanelAdministrarVehiculos
- PanelAdministrarAlojamientos
- PanelAdministrarReservas

### Selección

- PanelSeleccionTipoEmpresa
- PanelSeleccionTipoServicio
- PanelSeleccionClienteReservas

### Visualización

- PanelVisualizarClientes
- PanelVisualizarGuias
- PanelVisualizarEmpresas
- PanelVisualizarEmpresa
- PanelVisualizarProveedor
- PanelVisualizarVehiculos
- PanelVisualizarVehiculo
- PanelVisualizarAlojamientos
- PanelVisualizarAlojamiento
- PanelVisualizarServicios
- PanelVisualizarReserva

### Confirmaciones y eliminación

- PanelConfirmarReserva
- PanelConfirmarEliminarCliente
- PanelConfirmarEliminarReserva
- PanelEliminarCliente

### Componentes auxiliares

- PanelFormularioEmpresa
- EntidadItem

---

## model

Contiene las clases que representan el dominio del problema.


- Persona
- Rut
- Direccion
- Telefono
- Correo
- Cliente
- GuiaTuristico
- Proveedor
- EmpresaExterna
- EmpresaTransporte
- EmpresaAlojamiento
- Vehiculo
- Patente
- Alojamiento
- Servicio
- ServicioTransporte
- ServicioAlojamiento
- RutaGastronomica
- PaseoLacustre
- ExcursionCultural
- Reserva

---

## data

Contiene los gestores encargados de administrar las colecciones de objetos.

- GestorPersonas
- GestorEmpresas
- GestorServicios
- GestorReservas
- GestorEntidades

---

## service

Contiene la clase `RegistroService`, responsable de la persistencia de datos mediante archivos de texto.

---

## util

Contiene clases auxiliares y excepciones personalizadas utilizadas para validar la información ingresada.

Ejemplos:

- RutInvalidoException
- CorreoInvalidoException
- PatenteInvalidaException
- TelefonoInvalidoException
- RegistroDuplicadoException

---

## resources

Contiene los archivos utilizados para almacenar la información del sistema.

- clientes.txt
- empresas.txt
- guias.txt
- servicios.txt

---

# Conceptos de Programación Orientada a Objetos utilizados

Durante el desarrollo del proyecto se aplicaron distintos principios de Programación Orientada a Objetos.

## Herencia

El sistema utiliza herencia para reutilizar comportamiento común y especializar las distintas entidades del dominio.

### Jerarquía de personas

```text
Persona
│
├── Cliente
├── GuiaTuristico
└── Proveedor
```

### Jerarquía de empresas

```text
EmpresaExterna
│
├── EmpresaTransporte
└── EmpresaAlojamiento
```

### Jerarquía de servicios

```text
Servicio
│
├── RutaGastronomica
├── PaseoLacustre
├── ExcursionCultural
├── ServicioTransporte
└── ServicioAlojamiento
```

---

## Composición

Además de la herencia, el proyecto utiliza composición para representar relaciones entre objetos.

### Persona

```text
Persona
├── Rut
├── Correo
├── Direccion
└── Telefono
```

### Cliente

```text
Cliente
└── Reserva
    └── Servicio
```

### Empresa de transporte

```text
EmpresaTransporte
└── Vehiculo
    └── Patente
```

### Empresa de alojamiento

```text
EmpresaAlojamiento
└── Alojamiento
    └── Direccion
```

### Servicios especializados

```text
ServicioTransporte
├── EmpresaTransporte
└── Vehiculo

ServicioAlojamiento
├── EmpresaAlojamiento
└── Alojamiento

GuiaTuristico
├── RutaGastronomica
├── PaseoLacustre
└── ExcursionCultural
```

---

## Encapsulamiento

Todos los atributos del modelo fueron declarados como privados y son accedidos mediante métodos públicos, manteniendo el control sobre la modificación de los datos.

## Polimorfismo

Los gestores administran colecciones utilizando las clases base (`Persona`, `EmpresaExterna` y `Servicio`), permitiendo trabajar de forma uniforme con sus distintas especializaciones.

## Interfaces

Se implementó la interfaz `Registrable`, utilizada por las entidades que pueden ser registradas dentro del sistema.

## Excepciones personalizadas

Se implementaron excepciones específicas para validar distintos tipos de datos, evitando el ingreso de información inválida y mejorando el control de errores del sistema.
Se utiliza la interfaz `Registrable` para representar las entidades registrables dentro del sistema.

---

# Modo de ejecución

## Requisitos

Para ejecutar el proyecto es necesario contar con:

- Java JDK 17 o superior.
- Apache NetBeans (versión recomendada: 17 o superior).

## Ejecución desde NetBeans

1. Clonar o descargar el repositorio.
2. Abrir Apache NetBeans.
3. Seleccionar **Archivo → Abrir proyecto**.
4. Elegir la carpeta `LlanquihueTourApp`.
5. Esperar a que el IDE cargue el proyecto.
6. Ejecutar el proyecto presionando **Run Project** (F6).

## Persistencia de datos

El sistema utiliza archivos de texto para almacenar la información registrada.

Al iniciar la aplicación se cargan automáticamente los datos almacenados en la carpeta `resources`. Al cerrar el programa o realizar las operaciones correspondientes, los cambios se guardan nuevamente en dichos archivos.

## Inicio del sistema

Al ejecutar el proyecto se abre la ventana principal desde la clase `Main`, ubicada en el paquete `UI`, desde donde es posible acceder a todas las funcionalidades del sistema.

---

# Autor

Felipe Ignacio Saldías Cofré

Estudiante de la carrera Analista Programador Computacional en DuocUC


