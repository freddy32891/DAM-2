/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mati
 */
public class Main {

    private String[] mainMenu = {
        "Mostrar tot el material", "Buscar producte", "Realitzar nova comanda ", "Salir"
    };
    private final File file = new File("material.txt");
    private FileWriter fw;
    private PrintWriter pw;
    private FileReader fr;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
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

    private void run() {
        boolean exit = false;
        do {
            int opcion = mostrarMenuLeerOpcion(mainMenu);

            switch (opcion) {
                case 0:
                    mostrarMaterial();
                    break;
                case 1:
                    buscarProducte();
                    break;
                case 2:
                    realitzarComanda();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    mostrarMensaje("Opción no valida");
                    break;
            }

        } while (!exit);
    }

    private void mostrarMaterial() {
        BufferedReader br = null;
        int lineas = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                mostrarMensaje(linea);
                lineas++;
            }
            fr.close();
            mostrarMensaje(lineas/2+" materiales encontrados");
            
        } catch (Exception ex) {
            mostrarMensaje("Ha ocurrido un error al escribir en el fichero\nDescripción del error: " + ex.getMessage());
        }
    }

    private void buscarProducte() {
        String nom = inputString("Introdueix el nom del producte que vols buscar: ");
        BufferedReader br = null;
        boolean founded = false;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase(nom)) {
                    founded = true;
                }
            }
            fr.close();
            if (founded) {
                mostrarMensaje("El producte encara té stock");
            } else {
                mostrarMensaje("El producte no té stock");
            }
        } catch (Exception ex) {
            mostrarMensaje("Ha ocurrido un error al escribir en el fichero\nDescripción del error: " + ex.getMessage());
        }
    }

    private void realitzarComanda() {

        try {
            String producte = inputString("Quin producte vols adquirir? ");
            String quantitat = inputString("Quina quantitat d'aquest producte vols afegir? ");
            int q = Integer.parseInt(quantitat);
            File comanda = new File("comanda.txt");
            fw = new FileWriter(comanda);
            pw = new PrintWriter(fw);
            pw.println("Comanda Material Coronavirus\n" +
"\n" +
"L’Hospital de Bellvitge solicita adquirir el següent material: ");
            pw.println("Producte: " + producte);
            pw.println("Quantitat: " + q);
            pw.println("L’Hospitalet del Llobregat, juny 2020");
            pw.flush();
            mostrarMensaje("Comanda añadida con exito!!");

        } catch (NumberFormatException e) {
            mostrarMensaje("La cantidad introducida no es un numero\nComanda no añadida");
        } catch (Exception ex) {
            mostrarMensaje("Ha ocurrido un error al escribir en el fichero\nDescripción del error: " + ex.getMessage());
        }

    }

}
