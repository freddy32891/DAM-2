/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.model;

import cat.proven.agenda.model.persist.ContactDao;
import java.util.List;

/**
 *
 * @author fredd
 */
public class Model {

    private ContactDao dao;

    public Model() {
        dao = new ContactDao();
    }

    /**
     * Method that calls the dao to add a new contact in the file
     * @param contact to add
     * @return 1 if succesfully added or 0 in case of error
     */
    public int addContact(Contact contact) {
        return dao.add(contact);
    }

    /**
     * Method that calls the dao to search all the contacts in the file
     * @return a list with all the contacts founded or null in case of error
     */
    public List<Contact> searchAllContacts() {
        return dao.searchAll();
    }

    /**
     * Method that calls the dao to search all the contacts in the file where birthday is the given date
     * @param date given, (it's supposed to be the dato of today)
     * @return a list with all the contacts founded or null in case of error
     */
    public List<Contact> searchWhereBirthdayIsToday(String date) {
        return dao.selectWhereBirthdayIsToday(date);
    }

    /**
     * Method that calls the dao to remove all the contacts in the file
     * @return 1 if succesfully removed, -1 in case that the file doesn't exist or 0 in case of another error.
     */
    public int removeAllContacts() {
        return dao.removeAll();
    }

    /**
     * Method that calls the dao to search all the contacts by a given phone
     * @param phone given
     * @return the contact with the given phone or null in case of error
     */
    public Contact searchContactByPhone(String phone) {
        return dao.selectWherePhone(phone);
    }

    /**
     * Method that calls the dao to search a contact by a given name
     * @param name given
     * @return the contact with the given name or null in case of error
     */
    public Contact searchContactByName(String name) {
        return dao.selectWhereName(name);
    }

//    public int addFirstContact(Contact contact) {
//        return dao.addFirst(contact);
//    }

}
