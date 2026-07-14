package service;
/**
 * Clase que se utiliza para gestionar los registros y las consultas de datos en el sistema de LlanquihueTour
 */
import model.*;

import java.io.*;
import java.util.*;

public class RegistroService {

    private ArrayList<Persona> registros;
    private String rutaArchivo;

    
    /**
     * Constructor de la clase RegistroService
     * @param rutaArchivo       ruta del archivo donde se encuentran los registros 
     */
    public RegistroService(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.registros = new ArrayList<>();
        this.cargarDesdeArchivo();
    }

    /**
     * Método que carga los registros de un archivo a una lista.
     */
    public void cargarDesdeArchivo() {

        registros.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";");

                String tipo = datos[0];

                if (tipo.equalsIgnoreCase("TRANSPORTE")) {

                    ProveedorTransporte proveedor = new ProveedorTransporte(datos[1], new Rut(datos[2]), new Correo(datos[3]), new Direccion(datos[4], Integer.parseInt(datos[5]), datos[6], datos[7]), new Vehiculo(datos[8], new Patente(datos[9]), Integer.parseInt(datos[10])));
                    
                    registros.add(proveedor);

                } else if (tipo.equalsIgnoreCase("ALOJAMIENTO")) {

                    ProveedorAlojamiento proveedor = new ProveedorAlojamiento(datos[1], new Rut(datos[2]), new Correo(datos[3]), new Direccion(datos[4], Integer.parseInt(datos[5]), datos[6], datos[7]), new Alojamiento(datos[8], Integer.parseInt(datos[9]), new Direccion(datos[10], Integer.parseInt(datos[11]), datos[12], datos[13])));

                    registros.add(proveedor);
                }
            }

            System.out.println("Archivo cargado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }
    /**
     * Método que muestra todos los registros cargados a la lista.
     */
    public void mostrarRegistros() {

        if (registros.isEmpty()) {
            System.out.println("No existen registros.");
            return;
        }

        for (Persona persona : registros) {
            System.out.println("-----------------------------");
            System.out.println(persona);
        }
    }

    /**
    * Método que muestra solo los proveedores de transporte.
    */
    public void mostrarProveedoresTransporte() {

        boolean encontrado = false;

        for (Persona persona : registros) {

            if (persona instanceof ProveedorTransporte) {
                System.out.println("-----------------------------");
                System.out.println(persona);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No existen proveedores de transporte registrados.");
        }
    }

    /**
     * Método que muestra solo los proveedores de alojamiento.
     */
    public void mostrarProveedoresAlojamiento() {

        boolean encontrado = false;

        for (Persona persona : registros) {

            if (persona instanceof ProveedorAlojamiento) {
                System.out.println("-----------------------------");
                System.out.println(persona);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No existen proveedores de alojamiento registrados.");
        }
    }

    /**
     * Método que busca el nombre de un proveedor registrado en la lista.
     * @param nombreBuscado     Nombre del proveedor a buscar en la lista.
     */
    public void buscarPorNombre(String nombreBuscado) {

        boolean encontrado = false;

        for (Persona persona : registros) {
            if (persona.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("-----------------------------");
                System.out.println(persona);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No existen registros con ese nombre.");
        }
    }
    /**
     * Método que busca el rut de un proveedor registrado en la lista.
     * @param rutBuscado        Rut del proveedor a buscar en la lista.
     */
    public void buscarPorRut(String rutBuscado) {

        boolean encontrado = false;

        for (Persona persona : registros) {
            if (persona.getRut().getNumero().equalsIgnoreCase(rutBuscado)) {
                System.out.println("-----------------------------");
                System.out.println(persona);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro un registro con ese RUT.");
        }
    }

    /**
     * Método para agregar desde la consola un proveedor a la lista y a el archivo.
     * @param scanner       Inputs del usuario que corresponden a los datos solicitados.
     */
    public void agregarProveedorAlArchivo(Scanner scanner) {

        try {
            System.out.println("\n=== AGREGAR PROVEEDOR ===");
            System.out.println("1. Proveedor de transporte");
            System.out.println("2. Proveedor de alojamiento");
            System.out.print("Seleccione una opcion: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion != 1 && opcion != 2) {
                System.out.println("Opcion invalida.");
                return;
            }

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("RUT: ");
            String rutIngresado = scanner.nextLine();

            if (existeRut(rutIngresado)) {
                System.out.println("Ya existe un proveedor registrado con ese RUT.");
                return;
            }

            Rut rut = new Rut(rutIngresado);

            System.out.print("Correo: ");
            Correo correo = new Correo(scanner.nextLine());

            System.out.println("\nDireccion del proveedor");

            System.out.print("Calle: ");
            String calle = scanner.nextLine();

            System.out.print("Numero: ");
            int numero = Integer.parseInt(scanner.nextLine());

            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine();

            System.out.print("Region: ");
            String region = scanner.nextLine();

            Direccion direccion = new Direccion(calle, numero, ciudad, region);

            String lineaArchivo;

            if (opcion == 1) {

                System.out.print("Tipo de vehiculo: ");
                String tipoVehiculo = scanner.nextLine();

                System.out.print("Patente: ");
                Patente patente = new Patente(scanner.nextLine());

                System.out.print("Asientos disponibles: ");
                int asientos = Integer.parseInt(scanner.nextLine());

                Vehiculo vehiculo = new Vehiculo(tipoVehiculo, patente, asientos);

                ProveedorTransporte proveedor = new ProveedorTransporte(nombre, rut, correo, direccion, vehiculo);

                registros.add(proveedor);

                lineaArchivo = "TRANSPORTE;" + nombre + ";" + rut + ";" + correo + ";" + calle + ";" + numero + ";" + ciudad + ";" + region + ";" + tipoVehiculo + ";" + patente + ";" + asientos;

            } else {

                System.out.print("Nombre alojamiento: ");
                String nombreAlojamiento = scanner.nextLine();

                System.out.print("Habitaciones disponibles: ");
                int habitaciones = Integer.parseInt(scanner.nextLine());

                System.out.println("\nDireccion del alojamiento");

                System.out.print("Calle alojamiento: ");
                String calleAlojamiento = scanner.nextLine();

                System.out.print("Numero alojamiento: ");
                int numeroAlojamiento = Integer.parseInt(scanner.nextLine());

                System.out.print("Ciudad alojamiento: ");
                String ciudadAlojamiento = scanner.nextLine();

                System.out.print("Region alojamiento: ");
                String regionAlojamiento = scanner.nextLine();

                Direccion direccionAlojamiento = new Direccion(calleAlojamiento, numeroAlojamiento, ciudadAlojamiento, regionAlojamiento);

                Alojamiento alojamiento = new Alojamiento(nombreAlojamiento, habitaciones, direccionAlojamiento);

                ProveedorAlojamiento proveedor = new ProveedorAlojamiento(nombre, rut, correo, direccion, alojamiento);

                registros.add(proveedor);

                lineaArchivo = "ALOJAMIENTO;" + nombre + ";" + rut + ";" + correo + ";" + calle + ";" + numero + ";" + ciudad + ";" + region + ";" + nombreAlojamiento + ";" + habitaciones + ";" + calleAlojamiento + ";" + numeroAlojamiento + ";" + ciudadAlojamiento + ";" + regionAlojamiento;
            }

            guardarLineaEnArchivo(lineaArchivo);

            System.out.println("Proveedor agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    /**
     * Método que agrega un proveedor con sus datos al archivo.
     * @param linea                 String que corresponde a todos los datos del proveedor separados por ";".
     * @throws IOException          En caso de que ocurra un error al leer el archivo.
     */
    private void guardarLineaEnArchivo(String linea) throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            pw.println(linea);
        }
        
    }
    
    /**
     * Método para ver si un rut ya existe en el registro o no.
     * @param rutBuscado        RUT que se desea evaluar.
     * @return                  true ya existe, false si no.
     */
    private boolean existeRut(String rutBuscado) {

    for (Persona persona : registros) {

        if (persona.getRut().getNumero().equalsIgnoreCase(rutBuscado)) {
            return true;
        }
    }
    
    return false;
    }
}