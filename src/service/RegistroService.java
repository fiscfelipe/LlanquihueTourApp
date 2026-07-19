package service;

import data.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import model.*;

/**
* Gestiona la persistencia de la información del sistema mediante archivos
* de texto ubicados en la carpeta resources.
*
* @author Felipe Saldías
*/
public class RegistroService {

        private static final String ARCHIVO_CLIENTES =
                "src/resources/clientes.txt";

        private static final String ARCHIVO_GUIAS =
                "src/resources/guias.txt";

        private static final String ARCHIVO_EMPRESAS =
                "src/resources/empresas.txt";

        private static final String ARCHIVO_SERVICIOS =
                "src/resources/servicios.txt";

        private static final String TIPO_CLIENTE = "CLIENTE";
        private static final String TIPO_RESERVA = "RESERVA";
        private static final String TIPO_GUIA = "GUIA";
        private static final String TIPO_EMPRESA = "EMPRESA";
        private static final String TIPO_VEHICULO = "VEHICULO";
        private static final String TIPO_ALOJAMIENTO = "ALOJAMIENTO";
        private static final String TIPO_RUTA = "RUTA";
        private static final String TIPO_PASEO = "PASEO";
        private static final String TIPO_EXCURSION = "EXCURSION";
        private static final String TIPO_TRANSPORTE = "TRANSPORTE";
        private static final String TIPO_SERVICIO_ALOJAMIENTO = "SERVICIO_ALOJAMIENTO";

        /**
         * Constructor de la clase RegistroService.
         */
        public RegistroService() {
        }

        /**
         * Guarda toda la información del sistema en los archivos
         * correspondientes.
         *
         * @param gestorEntidades Gestor que contiene todas las entidades del
         * sistema.
         */
        public void guardarTodo(GestorEntidades gestorEntidades) {

                if (gestorEntidades == null) {

                throw new IllegalArgumentException(
                        "El gestor de entidades no puede ser nulo.");

                }

                guardarClientes(gestorEntidades.getGestorPersonas());
                guardarGuias(gestorEntidades.getGestorPersonas());
                guardarEmpresas(gestorEntidades.getGestorEmpresas());
                guardarServicios(gestorEntidades.getGestorServicios());

        }

        /**
         * Carga toda la información del sistema desde los archivos
         * correspondientes.
         *
         * @param gestorEntidades Gestor que recibirá las entidades cargadas.
         */
        public void cargarTodo(GestorEntidades gestorEntidades) {

                if (gestorEntidades == null) {

                throw new IllegalArgumentException(
                        "El gestor de entidades no puede ser nulo.");

                }

                cargarGuias(gestorEntidades.getGestorPersonas());
                cargarEmpresas(gestorEntidades.getGestorEmpresas());
                cargarServicios(
                        gestorEntidades.getGestorServicios(),
                        gestorEntidades.getGestorPersonas(),
                        gestorEntidades.getGestorEmpresas());

                cargarClientes(
                        gestorEntidades.getGestorPersonas(),
                        gestorEntidades.getGestorServicios(),
                        gestorEntidades.getGestorReservas());

        }

        /**
         * Guarda todos los clientes y sus reservas en el archivo de clientes.
         *
         * Las reservas de cada cliente se escriben inmediatamente después de la
         * línea correspondiente al cliente.
         *
         * @param gestorPersonas Gestor que contiene los clientes registrados.
         */
        private void guardarClientes(GestorPersonas gestorPersonas) {

                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(ARCHIVO_CLIENTES))) {

                for (Cliente cliente : gestorPersonas.getClientes()) {

                        escribirLinea(
                                writer,
                                TIPO_CLIENTE,
                                cliente.getRut().toString(),
                                cliente.getNombre(),
                                cliente.getCorreo().toString(),
                                cliente.getTelefono().toString(),
                                cliente.getDireccion().getCalle(),
                                String.valueOf(
                                        cliente.getDireccion().getNumero()),
                                cliente.getDireccion().getCiudad(),
                                cliente.getDireccion().getRegion());

                        for (Reserva reserva : cliente.getReservas()) {

                        escribirLinea(
                                writer,
                                TIPO_RESERVA,
                                String.valueOf(reserva.getCodigo()),
                                reserva.getServicio()
                                        .obtenerIdentificador(),
                                String.valueOf(
                                        reserva.getCantidadPersonas()));

                        }

                }

                } catch (IOException e) {

                throw new IllegalStateException(
                        "No fue posible guardar los clientes.", e);

                }

        }

        /**
         * Carga todos los clientes y sus reservas desde el archivo de clientes.
         *
         * Este método será implementado en la siguiente etapa. Los servicios se
         * reciben para poder reconstruir la relación existente entre cada reserva
         * y el servicio correspondiente.
         *
         * @param gestorPersonas Gestor que recibirá los clientes cargados.
         * @param gestorServicios Gestor utilizado para buscar los servicios de las
         * reservas.
         */
        private void cargarClientes(GestorPersonas gestorPersonas, GestorServicios gestorServicios, GestorReservas gestorReservas) {

                Cliente clienteActual = null;
                int mayorCodigo = 0;

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(ARCHIVO_CLIENTES))) {

                        String linea;

                        while ((linea = reader.readLine()) != null) {

                        String[] datos = separarLinea(linea);

                        switch (datos[0]) {

                                case TIPO_CLIENTE:

                                clienteActual = new Cliente(
                                        datos[2],
                                        new Rut(datos[1]),
                                        new Correo(datos[3]),
                                        new Telefono(datos[4]),
                                        new Direccion(
                                                datos[5],
                                                Integer.parseInt(datos[6]),
                                                datos[7],
                                                datos[8]));

                                gestorPersonas.agregarPersona(clienteActual);

                                break;

                                case TIPO_RESERVA:

                                if (clienteActual == null) {

                                        throw new IllegalStateException(
                                                "Se encontró una reserva sin cliente.");

                                }

                                Servicio servicio =
                                        gestorServicios.buscarServicio(datos[2]);

                                if (servicio == null) {

                                        throw new IllegalStateException(
                                                "No existe el servicio "
                                                + datos[2]
                                                + " asociado a la reserva.");

                                }

                                Reserva reserva = new Reserva(
                                        servicio,
                                        Integer.parseInt(datos[3]));

                                reserva.setCodigo(
                                        Integer.parseInt(datos[1]));

                                gestorReservas.agregarReservaCargada(
                                        clienteActual,
                                        reserva);

                                if (reserva.getCodigo() > mayorCodigo) {

                                        mayorCodigo = reserva.getCodigo();

                                }

                                break;

                                default:

                                throw new IllegalStateException(
                                        "Tipo de registro desconocido: "
                                        + datos[0]);

                        }

                        }

                        gestorReservas.setSiguienteCodigo(mayorCodigo + 1);

                } catch (Exception e) {

                        throw new IllegalStateException(
                                "No fue posible cargar los clientes.", e);

                }

        }

        /**
         * Guarda todos los guías turísticos en el archivo de guías.
         *
         * @param gestorPersonas Gestor que contiene los guías registrados.
         */
        private void guardarGuias(
                GestorPersonas gestorPersonas) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(ARCHIVO_GUIAS))) {

                for (Persona persona : gestorPersonas.getPersonas()) {

                        if (persona instanceof GuiaTuristico) {

                                GuiaTuristico guia =
                                        (GuiaTuristico) persona;

                                escribirLinea(writer,
                                        TIPO_GUIA,
                                        guia.getRut().toString(),
                                        guia.getNombre(),
                                        guia.getCorreo().toString(),
                                        guia.getTelefono().toString(),
                                        guia.getDireccion().getCalle(),
                                        String.valueOf(guia.getDireccion().getNumero()),
                                        guia.getDireccion().getCiudad(),
                                        guia.getDireccion().getRegion(),
                                        guia.getEspecialidad(),
                                        guia.getIdiomas(),
                                        String.valueOf(guia.getAniosExperiencia()));

                        }

                }

                } catch (IOException e) {

                        throw new IllegalStateException(
                                "No fue posible guardar los guías.", e);

                }

        }

        /**
         * Carga todos los guías turísticos desde el archivo de guías.
         *
         * @param gestorPersonas Gestor que recibirá los guías cargados.
         */
        private void cargarGuias(
                GestorPersonas gestorPersonas) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(ARCHIVO_GUIAS))) {

                String linea;

                while ((linea = reader.readLine()) != null) {

                        String[] datos = separarLinea(linea);

                        if (!TIPO_GUIA.equals(datos[0])) {

                                throw new IllegalStateException(
                                        "Tipo de registro desconocido: "
                                        + datos[0]);

                        }

                        GuiaTuristico guia = new GuiaTuristico(
                                datos[2],
                                new Rut(datos[1]),
                                new Correo(datos[3]),
                                new Telefono(datos[4]),
                                new Direccion(
                                        datos[5],
                                        Integer.parseInt(datos[6]),
                                        datos[7],
                                        datos[8]),
                                datos[9],
                                datos[10],
                                Integer.parseInt(datos[11]));

                        gestorPersonas.agregarPersona(guia);

                }

                } catch (Exception e) {

                        throw new IllegalStateException(
                                "No fue posible cargar los guías.", e);

                }

        }

        /**
         * Guarda todas las empresas y sus recursos en el archivo de empresas.
         *
         * @param gestorEmpresas Gestor que contiene las empresas registradas.
         */
        private void guardarEmpresas(GestorEmpresas gestorEmpresas) {

                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(ARCHIVO_EMPRESAS))) {

                        for (EmpresaExterna empresa :
                                gestorEmpresas.getEmpresas()) {

                                Proveedor proveedor = empresa.getProveedor();

                                if (empresa instanceof EmpresaTransporte) {

                                        EmpresaTransporte empresaTransporte =
                                                (EmpresaTransporte) empresa;

                                        escribirLinea(
                                                writer,
                                                TIPO_EMPRESA,
                                                TIPO_TRANSPORTE,
                                                empresaTransporte.getNombre(),
                                                empresaTransporte.getCorreo().toString(),
                                                proveedor.getRut().toString(),
                                                proveedor.getNombre(),
                                                proveedor.getCorreo().toString(),
                                                proveedor.getTelefono().toString(),
                                                proveedor.getDireccion().getCalle(),
                                                String.valueOf(
                                                        proveedor.getDireccion().getNumero()),
                                                proveedor.getDireccion().getCiudad(),
                                                proveedor.getDireccion().getRegion(),
                                                proveedor.getCargo());

                                        for (Vehiculo vehiculo :
                                                empresaTransporte.getVehiculos()) {

                                                escribirLinea(
                                                        writer,
                                                        TIPO_VEHICULO,
                                                        vehiculo.getTipo(),
                                                        vehiculo.getPatente().toString(),
                                                        String.valueOf(
                                                                vehiculo.getCapacidad()));

                                        }

                                } else if (empresa instanceof EmpresaAlojamiento) {

                                        EmpresaAlojamiento empresaAlojamiento =
                                                (EmpresaAlojamiento) empresa;

                                        escribirLinea(
                                                writer,
                                                TIPO_EMPRESA,
                                                TIPO_SERVICIO_ALOJAMIENTO,
                                                empresaAlojamiento.getNombre(),
                                                empresaAlojamiento.getCorreo().toString(),
                                                proveedor.getRut().toString(),
                                                proveedor.getNombre(),
                                                proveedor.getCorreo().toString(),
                                                proveedor.getTelefono().toString(),
                                                proveedor.getDireccion().getCalle(),
                                                String.valueOf(
                                                        proveedor.getDireccion().getNumero()),
                                                proveedor.getDireccion().getCiudad(),
                                                proveedor.getDireccion().getRegion(),
                                                proveedor.getCargo());

                                        for (Alojamiento alojamiento :
                                                empresaAlojamiento.getAlojamientos()) {

                                                escribirLinea(
                                                        writer,
                                                        TIPO_ALOJAMIENTO,
                                                        alojamiento.getNombre(),
                                                        alojamiento.getTipo(),
                                                        String.valueOf(
                                                                alojamiento.getCapacidad()),
                                                        alojamiento.getDireccion().getCalle(),
                                                        String.valueOf(
                                                                alojamiento.getDireccion().getNumero()),
                                                        alojamiento.getDireccion().getCiudad(),
                                                        alojamiento.getDireccion().getRegion());

                                        }

                                }

                        }

                } catch (IOException e) {

                        throw new IllegalStateException(
                                "No fue posible guardar las empresas.", e);

                }

        }

        /**
         * Carga todas las empresas y sus recursos desde el archivo de empresas.
         *
         * @param gestorEmpresas Gestor que recibirá las empresas cargadas.
         */
        private void cargarEmpresas(GestorEmpresas gestorEmpresas) {

                EmpresaExterna empresaActual = null;

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(ARCHIVO_EMPRESAS))) {

                        String linea;

                        while ((linea = reader.readLine()) != null) {

                                String[] datos = separarLinea(linea);

                                switch (datos[0]) {

                                        case TIPO_EMPRESA:

                                                Proveedor proveedor = new Proveedor(
                                                        datos[5],
                                                        new Rut(datos[4]),
                                                        new Correo(datos[6]),
                                                        new Telefono(datos[7]),
                                                        new Direccion(
                                                                datos[8],
                                                                Integer.parseInt(datos[9]),
                                                                datos[10],
                                                                datos[11]),
                                                        datos[12]);

                                                if (TIPO_TRANSPORTE.equals(datos[1])) {

                                                        empresaActual = new EmpresaTransporte(
                                                                datos[2],
                                                                new Correo(datos[3]),
                                                                proveedor);

                                                } else if (TIPO_SERVICIO_ALOJAMIENTO
                                                        .equals(datos[1])) {

                                                        empresaActual = new EmpresaAlojamiento(
                                                                datos[2],
                                                                new Correo(datos[3]),
                                                                proveedor);

                                                } else {

                                                        throw new IllegalStateException(
                                                                "Tipo de empresa desconocido: "
                                                                + datos[1]);

                                                }

                                                gestorEmpresas.agregarEmpresa(empresaActual);
                                                break;

                                        case TIPO_VEHICULO:

                                                if (!(empresaActual
                                                        instanceof EmpresaTransporte)) {

                                                        throw new IllegalStateException(
                                                                "Se encontró un vehículo sin "
                                                                + "empresa de transporte.");

                                                }

                                                EmpresaTransporte empresaTransporte =
                                                        (EmpresaTransporte) empresaActual;

                                                empresaTransporte.agregarVehiculo(
                                                        new Vehiculo(
                                                                datos[1],
                                                                new Patente(datos[2]),
                                                                Integer.parseInt(datos[3])));
                                                break;

                                        case TIPO_ALOJAMIENTO:

                                                if (!(empresaActual
                                                        instanceof EmpresaAlojamiento)) {

                                                        throw new IllegalStateException(
                                                                "Se encontró un alojamiento sin "
                                                                + "empresa de alojamiento.");

                                                }

                                                EmpresaAlojamiento empresaAlojamiento =
                                                        (EmpresaAlojamiento) empresaActual;

                                                empresaAlojamiento.agregarTipoAlojamiento(
                                                        new Alojamiento(
                                                                datos[1],
                                                                datos[2],
                                                                Integer.parseInt(datos[3]),
                                                                new Direccion(
                                                                        datos[4],
                                                                        Integer.parseInt(datos[5]),
                                                                        datos[6],
                                                                        datos[7])));
                                                break;

                                        default:

                                                throw new IllegalStateException(
                                                        "Tipo de registro desconocido: "
                                                        + datos[0]);

                                }

                        }

                } catch (Exception e) {

                        throw new IllegalStateException(
                                "No fue posible cargar las empresas.", e);

                }

        }

        /**
         * Guarda todos los servicios turísticos en el archivo de servicios.
         *
         * @param gestorServicios Gestor que contiene los servicios registrados.
         */
        private void guardarServicios(GestorServicios gestorServicios) {

                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(ARCHIVO_SERVICIOS))) {

                        for (Servicio servicio : gestorServicios.getServicios()) {

                                if (servicio instanceof RutaGastronomica) {

                                        RutaGastronomica ruta =
                                                (RutaGastronomica) servicio;

                                        escribirLinea(
                                                writer,
                                                TIPO_RUTA,
                                                ruta.getNombre(),
                                                ruta.getFecha().toString(),
                                                ruta.getHoraInicio().toString(),
                                                String.valueOf(
                                                        ruta.getDuracion().getSeconds()),
                                                String.valueOf(ruta.getCuposMaximos()),
                                                String.valueOf(
                                                        ruta.getPrecioPorPersona()),
                                                ruta.getGuia().getRut().toString(),
                                                String.valueOf(ruta.getNumeroParadas()));

                                } else if (servicio instanceof PaseoLacustre) {

                                        PaseoLacustre paseo =
                                                (PaseoLacustre) servicio;

                                        escribirLinea(
                                                writer,
                                                TIPO_PASEO,
                                                paseo.getNombre(),
                                                paseo.getFecha().toString(),
                                                paseo.getHoraInicio().toString(),
                                                String.valueOf(
                                                        paseo.getDuracion().getSeconds()),
                                                String.valueOf(paseo.getCuposMaximos()),
                                                String.valueOf(
                                                        paseo.getPrecioPorPersona()),
                                                paseo.getGuia().getRut().toString(),
                                                paseo.getEmbarcacion(),
                                                paseo.getLugarDestino());

                                } else if (servicio instanceof ExcursionCultural) {

                                        ExcursionCultural excursion =
                                                (ExcursionCultural) servicio;

                                        escribirLinea(
                                                writer,
                                                TIPO_EXCURSION,
                                                excursion.getNombre(),
                                                excursion.getFecha().toString(),
                                                excursion.getHoraInicio().toString(),
                                                String.valueOf(
                                                        excursion.getDuracion().getSeconds()),
                                                String.valueOf(
                                                        excursion.getCuposMaximos()),
                                                String.valueOf(
                                                        excursion.getPrecioPorPersona()),
                                                excursion.getGuia().getRut().toString(),
                                                excursion.getLugarDestino());

                                } else if (servicio instanceof ServicioTransporte) {

                                        ServicioTransporte transporte =
                                                (ServicioTransporte) servicio;

                                        escribirLinea(
                                                writer,
                                                TIPO_TRANSPORTE,
                                                transporte.getNombre(),
                                                transporte.getFecha().toString(),
                                                transporte.getHoraInicio().toString(),
                                                String.valueOf(
                                                        transporte.getDuracion().getSeconds()),
                                                String.valueOf(
                                                        transporte.getCuposMaximos()),
                                                String.valueOf(
                                                        transporte.getPrecioPorPersona()),
                                                transporte.getEmpresa().getNombre(),
                                                transporte.getVehiculo()
                                                        .getPatente().toString(),
                                                transporte.getLugarOrigen(),
                                                transporte.getLugarDestino());

                                } else if (servicio instanceof ServicioAlojamiento) {

                                        ServicioAlojamiento alojamiento =
                                                (ServicioAlojamiento) servicio;

                                        escribirLinea(
                                                writer,
                                                TIPO_SERVICIO_ALOJAMIENTO,
                                                alojamiento.getNombre(),
                                                alojamiento.getFecha().toString(),
                                                alojamiento.getHoraInicio().toString(),
                                                String.valueOf(
                                                        alojamiento.getDuracion().getSeconds()),
                                                String.valueOf(
                                                        alojamiento.getCuposMaximos()),
                                                String.valueOf(
                                                        alojamiento.getPrecioPorPersona()),
                                                alojamiento.getEmpresa().getNombre(),
                                                alojamiento.getAlojamiento().getNombre());

                                }

                        }

                } catch (IOException e) {

                        throw new IllegalStateException(
                                "No fue posible guardar los servicios.", e);

                }

        }

        /**
         * Carga todos los servicios turísticos desde el archivo de servicios.
         *
         * @param gestorServicios Gestor que recibirá los servicios cargados.
         */
        private void cargarServicios(
                GestorServicios gestorServicios,
                GestorPersonas gestorPersonas,
                GestorEmpresas gestorEmpresas) {

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(ARCHIVO_SERVICIOS))) {

                        String linea;

                        while ((linea = reader.readLine()) != null) {

                                String[] datos = separarLinea(linea);
                                Servicio servicio;

                                LocalDate fecha = LocalDate.parse(datos[2]);
                                LocalTime horaInicio = LocalTime.parse(datos[3]);
                                Duration duracion = Duration.ofSeconds(
                                        Long.parseLong(datos[4]));
                                int cuposMaximos = Integer.parseInt(datos[5]);
                                int precioPorPersona = Integer.parseInt(datos[6]);

                                switch (datos[0]) {

                                        case TIPO_RUTA:

                                                servicio = new RutaGastronomica(
                                                        datos[1],
                                                        fecha,
                                                        horaInicio,
                                                        duracion,
                                                        cuposMaximos,
                                                        precioPorPersona,
                                                        buscarGuia(
                                                                gestorPersonas, datos[7]),
                                                        Integer.parseInt(datos[8]));
                                                break;

                                        case TIPO_PASEO:

                                                servicio = new PaseoLacustre(
                                                        datos[1],
                                                        fecha,
                                                        horaInicio,
                                                        duracion,
                                                        cuposMaximos,
                                                        precioPorPersona,
                                                        buscarGuia(
                                                                gestorPersonas, datos[7]),
                                                        datos[8],
                                                        datos[9]);
                                                break;

                                        case TIPO_EXCURSION:

                                                servicio = new ExcursionCultural(
                                                        datos[1],
                                                        fecha,
                                                        horaInicio,
                                                        duracion,
                                                        cuposMaximos,
                                                        precioPorPersona,
                                                        buscarGuia(
                                                                gestorPersonas, datos[7]),
                                                        datos[8]);
                                                break;

                                        case TIPO_TRANSPORTE:

                                                EmpresaExterna empresaEncontrada =
                                                        gestorEmpresas.buscarEmpresa(datos[7]);

                                                if (!(empresaEncontrada
                                                        instanceof EmpresaTransporte)) {

                                                        throw new IllegalStateException(
                                                                "No existe la empresa de "
                                                                + "transporte " + datos[7] + ".");

                                                }

                                                EmpresaTransporte empresaTransporte =
                                                        (EmpresaTransporte) empresaEncontrada;

                                                servicio = new ServicioTransporte(
                                                        datos[1],
                                                        fecha,
                                                        horaInicio,
                                                        duracion,
                                                        cuposMaximos,
                                                        precioPorPersona,
                                                        empresaTransporte,
                                                        buscarVehiculo(
                                                                empresaTransporte, datos[8]),
                                                        datos[9],
                                                        datos[10]);
                                                break;

                                        case TIPO_SERVICIO_ALOJAMIENTO:

                                                EmpresaExterna empresaAlojamientoEncontrada =
                                                        gestorEmpresas.buscarEmpresa(datos[7]);

                                                if (!(empresaAlojamientoEncontrada
                                                        instanceof EmpresaAlojamiento)) {

                                                        throw new IllegalStateException(
                                                                "No existe la empresa de "
                                                                + "alojamiento " + datos[7] + ".");

                                                }

                                                EmpresaAlojamiento empresaAlojamiento =
                                                        (EmpresaAlojamiento)
                                                                empresaAlojamientoEncontrada;

                                                servicio = new ServicioAlojamiento(
                                                        datos[1],
                                                        fecha,
                                                        horaInicio,
                                                        duracion,
                                                        cuposMaximos,
                                                        precioPorPersona,
                                                        empresaAlojamiento,
                                                        buscarAlojamiento(
                                                                empresaAlojamiento, datos[8]));
                                                break;

                                        default:

                                                throw new IllegalStateException(
                                                        "Tipo de servicio desconocido: "
                                                        + datos[0]);

                                }

                                gestorServicios.agregarServicio(servicio);

                        }

                } catch (Exception e) {

                        throw new IllegalStateException(
                                "No fue posible cargar los servicios.", e);

                }

        }

        /**
         * Busca un guía turístico por su RUT.
         *
         * @param gestorPersonas Gestor que contiene los guías.
         * @param rut RUT del guía buscado.
         * @return Guía turístico encontrado.
         */
        private GuiaTuristico buscarGuia(
                GestorPersonas gestorPersonas, String rut) {

                Persona persona = gestorPersonas.buscarPersona(rut);

                if (!(persona instanceof GuiaTuristico)) {

                        throw new IllegalStateException(
                                "No existe el guía turístico " + rut + ".");

                }

                return (GuiaTuristico) persona;

        }

        /**
         * Busca un vehículo por patente dentro de una empresa.
         *
         * @param empresa Empresa de transporte.
         * @param patente Patente del vehículo buscado.
         * @return Vehículo encontrado.
         */
        private Vehiculo buscarVehiculo(
                EmpresaTransporte empresa, String patente) {

                for (Vehiculo vehiculo : empresa.getVehiculos()) {

                        if (vehiculo.getPatente().toString()
                                .equalsIgnoreCase(patente)) {

                                return vehiculo;

                        }

                }

                throw new IllegalStateException(
                        "No existe el vehículo " + patente
                        + " en la empresa " + empresa.getNombre() + ".");

        }

        /**
         * Busca un alojamiento por nombre dentro de una empresa.
         *
         * @param empresa Empresa de alojamiento.
         * @param nombre Nombre del alojamiento buscado.
         * @return Alojamiento encontrado.
         */
        private Alojamiento buscarAlojamiento(
                EmpresaAlojamiento empresa, String nombre) {

                for (Alojamiento alojamiento : empresa.getAlojamientos()) {

                        if (alojamiento.getNombre().equalsIgnoreCase(nombre)) {

                                return alojamiento;

                        }

                }

                throw new IllegalStateException(
                        "No existe el alojamiento " + nombre
                        + " en la empresa " + empresa.getNombre() + ".");

        }

        /**
         * Escribe una línea en un archivo separando los datos mediante punto y
         * coma.
         *
         * @param writer Flujo utilizado para escribir en el archivo.
         * @param datos Datos que serán escritos en la línea.
         * @throws IOException Si ocurre un error durante la escritura.
         */
        private void escribirLinea(
                BufferedWriter writer,
                String... datos) throws IOException {

                writer.write(String.join(";", datos));
                writer.newLine();

        }

        /**
         * Separa los campos de una línea utilizando el punto y coma como
         * delimitador.
         *
         * @param linea Línea que se desea separar.
         * @return Arreglo que contiene los campos de la línea.
         */
        private String[] separarLinea(String linea) {

                return linea.split(";", -1);

        }

}