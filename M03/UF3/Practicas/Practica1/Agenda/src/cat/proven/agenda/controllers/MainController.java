/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.controllers;

import cat.proven.agenda.model.Contact;
import cat.proven.agenda.model.Model;
import cat.proven.agenda.views.MainView;
import java.util.List;

/**
 *
 * @author fredd
 */
public class MainController {

    private final Model model;
    private final MainView view;

    public Model getModel() {
        return model;
    }

    public MainView getView() {
        return view;
    }

    public MainController(Model model) {
        this.model = model;
        this.view = new MainView(this);

    }

    /**
     * Method that catches the answer of the user and calls a method to process
     * the action selected
     *
     * @param action selected by the user
     */
    public void processAction(String action) {
        switch (action) {
            case "add":
                addContact();
                break;
            case "listAll":
                listAllContacts();
                break;
            case "search":
                searchContact();
                break;
            case "listByDateOfBirth":
                listByDateOfBirth();
                break;
            case "remove":
                removeAll();
                break;
            case "searchByName":
                searchByName();
                break;
            case "searchByPhone":
                searchByPhone();
                break;
            case "exit":
                exitApplication();
                break;
            default:
                view.showMessage("Opción no valida");
                break;
        }
    }

    /**
     * Method that exits the app
     */
    private void exitApplication() {
        if (view.confirmation("Are you sure you want to exit the application? (y/n)")) {
            view.setExit(true);
        }
    }

    /**
     * Method that asks the user to input a contact andd the calls the model to
     * add this contact to the file
     */
    private void addContact() {
        Contact contact = view.inputContact();
        if (contact != null) {
            int result = model.addContact(contact);
            if (result > 0) {
                view.showMessage("Contacto añadido con éxito");
            } else {
                view.showMessage("Contacto no añadido");
            }
        } else {
            view.showMessage("Contacto no añadido");
        }
    }

    /**
     * Method that calls the model to list all the contacts in the file. In case
     * of error it shows a message
     */
    private void listAllContacts() {
        List<Contact> contacts = model.searchAllContacts();
        if (contacts != null) {
            view.displayContactTable(contacts);
        } else {
            view.showMessage("Ha habido un error a la hora de buscar todos los contactos");
        }
    }

    /**
     * Method that calls the view to show the search contact
     */
    private void searchContact() {
        view.showSearchMenu();
    }

    /**
     * Method that asks the user to input the date of today, calls the dao to
     * search all the contacts whose birthday is today and prints the list to
     * the user. In case of error it shows a message.
     */
    private void listByDateOfBirth() {
        String date = view.inputAndValidateDate("Introduce la fecha de hoy: ");
        if (date != null) {
            List<Contact> contacts = model.searchWhereBirthdayIsToday(date);
            if (contacts != null) {
                view.displayContactTable(contacts);
            } else {
                view.showMessage("Contacts not founded");
            }
        }
    }

    /**
     * Method that removes all the contacts in tthe file. In case of error it
     * shows a message
     */
    private void removeAll() {
        int result = model.removeAllContacts();
        if (result > 0) {
            view.showMessage("Contactos borrados con exito");
        } else if (result == -1) {
            view.showMessage("El archivo no existe, contactos no borrados");
        } else {
            view.showMessage("Ha habido un error a la hora de borrar los contactos");
        }
    }

    /**
     * Method that asks the user the name of the contact to search and searches
     * all the contacts in the file by the given name.
     */
    private void searchByName() {
        String name = view.inputString("Introduce el nombre del contacto: ");
        if (name != null) {
            Contact contact = model.searchContactByName(name);
            if (contact != null) {
                view.showMessage(contact.toString());
            } else {
                view.showMessage("Contacto no encontrado");
            }
        } else {
            view.showMessage("El nombre introducido no es valido.");
        }
    }

    /**
     * Method that asks the user the phone of the contact to search and searches
     * all the contacts in the file by the given phone.
     */
    private void searchByPhone() {
        String phone = view.inputString("Introduce el numero del contacto: ");
        if (phone != null) {
            Contact contact = model.searchContactByPhone(phone);
            if (contact != null) {
                view.showMessage(contact.toString());

            } else {
                view.showMessage("Contacto no encontrado");
            }
        } else {
            view.showMessage("El numero introducido no es valido");
        }
    }
}
