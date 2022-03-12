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
public class SearchMenu extends Menu{

    public SearchMenu() {
        setTitle("BUSCAR CONTACTO");
        addOption(new Option("Buscar contacto por nombre", "searchByName"));
        addOption(new Option("Buscar contacto por telefono", "searchByPhone"));
    }
}
