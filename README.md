# Llanquihue Tour App

## Descripción

Aplicación de escritorio desarrollada en Java utilizando Swing para la gestión de proveedores y servicios turísticos de la agencia turística **Llanquihue Tour**.

El sistema permite registrar distintos tipos de proveedores y servicios turísticos, visualizarlos y eliminarlos mediante una interfaz gráfica sencilla.

---

# Funcionalidades

La aplicación permite:

- Registrar proveedores de transporte.
- Registrar proveedores de alojamiento.
- Registrar guías turísticos.
- Registrar rutas gastronómicas.
- Registrar paseos lacustres.
- Registrar excursiones culturales.
- Visualizar los registros almacenados.
- Eliminar registros existentes.
- 
---

# Conceptos de Programación Orientada a Objetos utilizados

Durante el desarrollo del proyecto se aplicaron diversos conceptos de Programación Orientada a Objetos, entre ellos:

- Herencia
- Composición
- Polimorfismo
- Interfaces
- Encapsulamiento
- Sobrescritura de métodos
- Excepciones personalizadas
- Organización mediante paquetes
- Colecciones Genéricas

---

# Estructura del proyecto

```
src
│
├── data
│   └── GestorEntidades.java
│
├── model
│   ├── Persona.java
│   ├── ProveedorTransporte.java
│   ├── ProveedorAlojamiento.java
│   ├── GuiaTuristico.java
│   ├── ServicioTuristico.java
│   ├── RutaGastronomica.java
│   ├── PaseoLacustre.java
│   ├── ExcursionCultural.java
│   ├── Direccion.java
│   ├── Vehiculo.java
│   ├── Alojamiento.java
│   ├── Rut.java
│   ├── Correo.java
│   ├── Patente.java
│   └── Registrable.java
│
├── ui
│   ├── VentanaPrincipal.java
│   ├── PanelBase.java
│   ├── PanelMenuPrincipal.java
│   ├── PanelMenuAgregarRegistro.java
│   ├── PanelProveedorTransporte.java
│   ├── PanelProveedorAlojamiento.java
│   ├── PanelGuiaTuristico.java
│   ├── PanelRutaGastronomica.java
│   ├── PanelPaseoLacustre.java
│   ├── PanelExcursionCultural.java
│   ├── PanelVisualizarRegistros.java
│   └── PanelEliminarRegistro.java
│   └── Main.java
│
├── util
    ├── RutInvalidoException.java
    ├── CorreoInvalidoException.java
    └── PatenteInvalidaException.java


```

---

# Interfaz gráfica

La aplicación fue desarrollada utilizando **Java Swing**.

Se utiliza una única ventana principal (`JFrame`) sobre la cual se intercambian distintos paneles (`JPanel`) para cada funcionalidad del sistema.

---

# Validaciones

El sistema incorpora validaciones para:

- Campos obligatorios.
- Formato de RUT.
- Formato de correo electrónico.
- Formato de patente.
- Valores numéricos.
- Valores positivos cuando corresponde.

---


# Cómo ejecutar

1. Descargar y abrir el proyecto en NetBeans.
3. Ejecutar la clase `Main`.
4. Utilizar el menú principal para acceder a las distintas funcionalidades.

---

# Autor

Felipe Ignacio Saldías Cofré

Estudiante de la carrera Analista Programador Computacional DuocUC
