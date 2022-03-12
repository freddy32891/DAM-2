package cat.proven.agenda.views;

import cat.proven.agenda.controllers.MainController;
import cat.proven.agenda.model.Contact;
import cat.proven.agenda.model.Model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fredd
 */
public class MainView {

    private MainController controller;
    private boolean exit;
    private MainMenu mainMenu;
    private SearchMenu searchMenu;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public MainView(MainController controller) {
        this.controller = controller;
        this.mainMenu = new MainMenu();
        this.searchMenu = new SearchMenu();
    }

    public MainView(MainController aThis, Model model) {
    }

    /**
     * Method that shows the menu until the seected options is exit
     */
    public void show() {
        this.exit = false;
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            controller.processAction(action);
        } while (!exit);
    }

    /**
     * Method that shows the search menu a calls the controller to procces the
     * action selected by the user.
     */
    public void showSearchMenu() {
        searchMenu.show();
        controller.processAction(searchMenu.getSelectedOptionActionCommand());
    }

    /**
     * Method that displays a message
     *
     * @param message to show
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method that prompts a message and reads and answer from user
     *
     * @param message the message to prompt
     * @return user's answer or null in case of error.
     */
    public String inputString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();

    }

    /**
     * Method that sohws a list of products
     *
     * @param data list to show
     */
    public void displayContactTable(List<Contact> data) {
        if (data != null) {
            for (Contact contact : data) {
                showMessage(contact.toString());
            }
            showMessage("Contactos encontrados: " + data.size());
        }
    }

    /**
     * Method that ask the user to input sll the paramethers of a Contact,
     * validating all the paramethers.
     *
     * @return the product with all the paramethers o null in case of error
     */
    public Contact inputContact() {
        Contact contact = null;
        try {
            String name = inputString("Introduce el nombre: ");
            String phone = inputString("Introduce el telefono: ");
            if (validatePhone(phone)) {
                String mail = inputString("Introduce el mail: ");
                if (validateMail(mail)) {
                    String dateOfBirth = inputAndValidateDate("Introduce la fecha de nacimiento: ");
                    if (validateDate(dateOfBirth)) {
                        String postalCode = inputString("Introduce el codigo postal: ");
                        if (validatePostalCode(postalCode)) {
                            String location = inputString("Introduce la localidad: ");
                            contact = new Contact(name, phone, mail, dateOfBirth, location, postalCode);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            contact = null;
        }
        return contact;
    }

    /**
     * Method that validates a phone given
     *
     * @param phone
     * @return true if the phone is a number and it hasn't got more than 15
     * characters or false otherwise
     */
    private boolean validatePhone(String phone) {
        boolean valid = false;
        if (isNumeric(phone)) {
            if (phone.length() > 15) {
                showMessage("El telefono introducido no puede ser mayor a 15 numeros");
                valid = false;
            } else {
                valid = true;
            }
        } else {
            showMessage("El telefono introducido debe ser un numero");
            valid = false;
        }
        return valid;
    }

    /**
     * Method that validates a mail given
     *
     * @param mail given
     * @return true if it is well introduced or false otherwise
     */
    private boolean validateMail(String mail) {
        boolean valid = false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mail);
        if (mather.find() == true) {
            valid = true;
        } else {
            showMessage("El email ingresado no es válido.");
            valid = false;
        }
        return valid;
    }

    /**
     * Method that validates a postal code given
     *
     * @param postalCode given
     * @return true if the postal code is valid or false otherwise
     */
    private boolean validatePostalCode(String postalCode) {
        boolean valid = true;
        if (isNumeric(postalCode)) {
            if (postalCode.length() != 5) {
                showMessage("El codigo postal solo puede contener 5 numeros");
                valid = false;
            } else {
                valid = true;
            }
        } else {
            showMessage("El codigo postal introducido debe ser un numero");
            valid = false;
        }
        return valid;
    }

    /**
     * Method that asks the user to input a date and validates it
     *
     * @param message to show
     * @return the date with an specific format or null in case of error.
     */
    public String inputAndValidateDate(String message) {
        showMessage(message);
        String day = inputString("Introduce el dia (dd): ");
        String month = inputString("Introduce el mes (mm): ");
        String year = inputString("Introduce el año (yyyy): ");
        String dateOfBirth = day + "/" + month + "/" + year;
        if (day.length() == 2 & month.length() == 2 && year.length() == 4) {
            if (!validateDate(dateOfBirth)) {
                dateOfBirth = null;
            }
        } else {
            dateOfBirth = null;
        }
        return dateOfBirth;
    }

    /**
     * Method that validates a date given
     *
     * @param date given
     * @return true if succesfully validate or false otherwise
     */
    public boolean validateDate(String date) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(date);
        } catch (ParseException e) {
            showMessage("La fecha introducida no es valida");
            return false;
        }
        return true;
    }

    /**
     * Method that tells you if a given string is numeric
     *
     * @param numericString given
     * @return true if it's a number, false otherwise
     */
    private boolean isNumeric(String numericString) {
        try {
            Integer.parseInt(numericString);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Method that prints a message and asks the user a confirmation
     *
     * @param message message to show
     * @return true if the user introduces an Y as an answer, false otherwise
     */
    public boolean confirmation(String message) {
        String confirmation = inputString(message);
        confirmation = confirmation.toUpperCase();
        if (confirmation.contains("Y")) {
            return true;
        } else {
            return false;
        }
    }

}
