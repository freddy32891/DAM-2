/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.ficherosbinarios;

import cat.proven.ficherosbinarios.model.Hospital;
import cat.proven.ficherosbinarios.model.persist.HospitalDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class Main {

    private String[] mainMenu = {
        "Llistar hospital", "Afegir hospital", "Mostrar hospital disponible", "Mostrar hospitals ordenats per nom", "Eliminar hospital", "Salir"
    };

    private HospitalDao myHospital;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        boolean exit = false;
        myHospital = new HospitalDao();
        do {

            int opcion = mostrarMenuLeerOpcion(mainMenu);

            switch (opcion) {
                case 0:
                    listarTodos();
                    break;
                case 1:
                    anyadir();
                    break;
                case 2:
                    mostrarDisponibles();
                    break;
                case 3:
                    mostrarOrdenadosPorNombre();
                    break;
                case 4:
                    eliminar();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    mostrarMensaje("Opción no valida");
                    break;
            }

        } while (!exit);
    }

    private int mostrarMenuLeerOpcion(String[] mainMenu) {
        int option = -1;
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.format("%d. %s\n", i, mainMenu[i]);
        }
        Scanner myscan = new Scanner(System.in);
        try {
            mostrarMensaje("Select an option: ");
            option = myscan.nextInt();
        } catch (InputMismatchException e) {
            option = -1;
        }
        return option;
    }

    public void mostrarMensaje(String message) {
        System.out.println(message);
    }

    public String inputString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }

    public boolean confirmacion(String message) {
        String confirmation = inputString(message);
        confirmation = confirmation.toUpperCase();
        if (confirmation.contains("S")) {
            return true;
        } else {
            return false;
        }
    }

    private void listarTodos() {
        List<Hospital> hospitales = myHospital.getAll();
        if (hospitales != null) {
            printarHospitales(hospitales);
        } else {
            mostrarMensaje("Ha habido un error al mostrar los hospitales");
        }
    }

    private void anyadir() {
        Hospital hospital = escribirHospital();
        if (hospital != null) {
            boolean result = myHospital.anyadir(hospital);
            if (result) {
                mostrarMensaje("Hospital añadido correctamente");
            } else {
                mostrarMensaje("Hospital no añadido");
            }
        } else {
            mostrarMensaje("Los datos introducidos del hospital no son validos, revisa el numero de camas introducido");
        }
    }

    private void mostrarDisponibles() {
        try {
            List<Hospital> hospitales = myHospital.getAvailables();
            if (hospitales != null) {
                printarHospitales(hospitales);
            } else {
                mostrarMensaje("Hospitales no encontrados");
            }
        } catch (FileNotFoundException ex) {
            mostrarMensaje("El archivo no se existe, revisa los ficheros");
        } catch (ClassNotFoundException ex) {
            mostrarMensaje("No se encuentran objetos tipo hospital en el archivo");
        } catch (IOException ex) {
            mostrarMensaje("Ha habido un error al leer los hospitales del archivo.\nDescripción del error: " + ex.getMessage());
        }
    }

    private void mostrarOrdenadosPorNombre() {
        List<Hospital> hospitals = myHospital.getAll();
        if (hospitals != null) {
            Hospital[] lista = new Hospital[hospitals.size()];
            lista = hospitals.toArray(lista);
            Hospital[] listOrdenada = ordenar(lista);
            for (int i = 0; i < listOrdenada.length; i++) {
                mostrarMensaje(listOrdenada[i].toString());
            }
        }else{
            mostrarMensaje("Ha habido un problema al accceder a los hospitales");
        }
    }

    private Hospital[] ordenar(Hospital[] array) {
        int n = array.length;
        Hospital aux;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (array[j - 1].getNom().compareToIgnoreCase(array[j].getNom()) > 0) {
                    aux = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = aux;
                }
            }
        }
        return array;
    }

    private void eliminar() {
        String name = inputString("Introduce el nombre del hospital que deseas borrar: ");
        if (name != null) {
            Hospital hospital = myHospital.encontrar(name);
            if (hospital != null) {
                boolean result = myHospital.borrar(hospital);
                if (result) {
                    mostrarMensaje("Hospital borrado con exito");
                } else {
                    mostrarMensaje("Hospital no borrado");
                }
            } else {
                mostrarMensaje("Hospital no encontrado");
            }
        } else {
            mostrarMensaje("Nombre introducido incorrectamente");
        }
    }

    private void printarHospitales(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            mostrarMensaje(hospital.toString());
        }
        mostrarMensaje("Hospitales encontrados: " + hospitals.size());
    }

    private Hospital escribirHospital() {
        Hospital hospital = null;
        try {
            String nom = inputString("Introduce el nombre del hospital: ");
            String sLlits = inputString("Introduce el numero de camas del hospital: ");
            boolean completo = confirmacion("El hospital esta completo? (s/n) ");
            int llits = Integer.parseInt(sLlits);
            hospital = new Hospital(nom, llits, completo);
        } catch (NumberFormatException ex) {
            hospital = null;
        }
        return hospital;
    }

}
