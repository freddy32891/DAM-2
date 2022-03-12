/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.views;

/**
 *
 * @author fredd
 */
public class MainMenu extends Menu {

    public MainMenu() {
        setTitle("AGENDA DE CONTACTOS");
        addOption(new Option("Añadir contacto", "add"));
        addOption(new Option("Listar contactos", "listAll"));
        addOption(new Option("Buscar contacto (por nombre o por teléfono)", "search"));
        addOption(new Option("Listar contactos que cumplen años hoy", "listByDateOfBirth"));
        addOption(new Option("Borrar toda la agenda", "remove"));
        addOption(new Option("Salir", "exit"));

    }
}
