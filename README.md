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

Contiene todas las interfaces gráficas desarrolladas utilizando Java Swing.

Incluye:

- Ventana principal.
- Menús.
- Paneles de registro.
- Paneles de visualización.
- Paneles de administración.

---

## model

Contiene las clases que representan el dominio del problema.

Entre ellas:

- Persona
- Cliente
- GuiaTuristico
- Proveedor
- EmpresaExterna
- EmpresaTransporte
- EmpresaAlojamiento
- Vehiculo
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

Durante el desarrollo del proyecto se implementaron distintos conceptos de Programación Orientada a Objetos.

## Encapsulamiento

Los atributos de las clases se encuentran encapsulados mediante modificadores de acceso privados y son manipulados a través de métodos públicos.

## Herencia

Se implementaron jerarquías de clases para reutilizar comportamiento común.

Ejemplos:

- Persona
  - Cliente
  - GuiaTuristico
  - Proveedor

- EmpresaExterna
  - EmpresaTransporte
  - EmpresaAlojamiento

- Servicio
  - ServicioTransporte
  - ServicioAlojamiento
  - RutaGastronomica
  - PaseoLacustre
  - ExcursionCultural

## Composición

El sistema utiliza composición para representar relaciones entre objetos.

Ejemplos:

- Empresa → Vehículos
- Empresa → Alojamientos
- Cliente → Reservas
- Reserva → Servicio

## Polimorfismo

Los gestores almacenan referencias utilizando las clases base (`Persona`, `Servicio`, `EmpresaExterna`) permitiendo trabajar con sus distintas subclases.

## Interfaces

Se utiliza la interfaz `Registrable` para representar las entidades registrables dentro del sistema.

## Excepciones personalizadas

Se implementaron excepciones para validar distintos tipos de datos, evitando el ingreso de información inválida.

---

# Persistencia

La información del sistema se guarda automáticamente en archivos de texto mediante la clase `RegistroService`.

Al iniciar la aplicación, todos los registros almacenados son cargados automáticamente.

---

# Tecnologías utilizadas

- Java
- Java Swing
- NetBeans
- Programación Orientada a Objetos
- Persistencia mediante archivos de texto

---

# Autor

Felipe Ignacio Saldías Cofré

Proyecto desarrollado para la asignatura **Programación Orientada a Objetos**.
