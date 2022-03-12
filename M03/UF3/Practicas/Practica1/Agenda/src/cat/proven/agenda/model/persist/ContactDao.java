/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.model.persist;

import cat.proven.agenda.model.Contact;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fredd
 */
public class ContactDao {

    private final String officeFileLocation = "resources/agenda.bin";
    private File file;

    public ContactDao() {
        this.file = new File(this.officeFileLocation);
    }

    /**
     * Method that adds a contact in to the file by calling the method addFirstTime if the file doesn't exist or addNotFirstTime otherwise 
     * @param contact given by the user to add
     * @return 1 if succesfully added or 0 in case of error
     */
    public int add(Contact contact) {
        int result = 0;
        if (!file.exists()) {
            result = addFirstTime(contact);
        } else {
            result = addNotFristTime(contact);
        }
        return result;
    }

    /**
     * Method that writes a contact in the existing file
     * @param contact to add
     * @return 1 if succesfully added or 0 in case of error
     */
    private int addNotFristTime(Contact contact) {
        MyObjectOutputStream oos;
        int result = 0;
        try {
            oos = new MyObjectOutputStream(
                    new FileOutputStream(file, true));
            oos.writeUnshared(contact);
            result = 1;
            oos.close();
        } catch (IOException ex) {
            result = 0;
        }
        return result;
    }

    /**
     * Method that reads all the contacts in the file
     * @return a list with all the contacts or null in case of error.
     * Prevents FileNotFoundedException if the file doesn't exist.
     * Prevents ClassNotFoundedException if the objects in the file are not Contacts
     * Prevents IOException if there's another error.
     */
    public List<Contact> searchAll()  {
        List<Contact> contacts = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new FileInputStream(file));
            Object aux = ois.readObject();
            while (aux != null) {
                if (aux instanceof Contact) {
                    contacts.add((Contact) aux);
                }
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            contacts = new ArrayList<>();
        } catch (IOException ex) {
            contacts = null;
        } catch (ClassNotFoundException ex) {
            contacts = null;
        }
        return contacts;
    }

    /**
     * Method that selects all the contacts in the file and removes (in the list) all the contacts whose birthday is not today.
     * @param date given by user
     * @return a list with all the contacts whose birthday is today or null in case of error.
     */
    public List<Contact> selectWhereBirthdayIsToday(String date) {
        String d = date.substring(0, 5);
        List<Contact> contacts = searchAll();
        if (contacts != null) {
            for (int i = 0; i < contacts.size(); i++) {
                if (!contacts.get(i).getDateOfBirth().startsWith(d)) {
                    contacts.remove(contacts.get(i));
                }
            }
        }
        return contacts;
    }

    /**
     * Method that removes the file where all the saved contacts are located.
     * @return 1 if succesfully deleted, -1 in case that the file doesn't exist or 0 in case of another error.
     */
    public int removeAll() {
        int result = 0;
        try {
            if (file.exists()) {
                if (file.delete()) {
                    result = 1;
                } else {
                    result = 0;
                }
            } else {
                result = -1;
            }
        } catch (Exception ex) {
            result = 0;
        }
        return result;
    }

    /**
     * Method that selects a contact by a given phone
     * @param phone given by user
     * @return the contact with the given phone or null in case of error.
     */
    public Contact selectWherePhone(String phone) {
        List<Contact> contacts = searchAll();
        Contact contact = null;
        if (contacts != null) {
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getPhone().equalsIgnoreCase(phone)) {
                    contact = contacts.get(i);
                    break;
                }
            }
        }
        return contact;
    }

    /**
     * Method that selects a contact by a given name
     * @param name given by user
     * @return the contact with the given name or null in case of error.
     */
    public Contact selectWhereName(String name) {
        List<Contact> contacts = searchAll();
        Contact contact = null;
        if (contacts != null) {
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                    contact = contacts.get(i);
                }
            }
        }
        return contact;
    }

    /**
     * Method that creates the file where the contacts are going to be written and writes a contact given by the user
     * @param contact to add
     * @return 1 if succesfully added or 0 in case of error
     */
    public int addFirstTime(Contact contact) {
        int result = 0;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(file));
            oos.writeObject(contact);
            result = 1;
            oos.close();
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

}
