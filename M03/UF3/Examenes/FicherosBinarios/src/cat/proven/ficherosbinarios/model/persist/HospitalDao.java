/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.ficherosbinarios.model.persist;

import cat.proven.ficherosbinarios.model.Hospital;
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

/**
 *
 * @author mati
 */
public class HospitalDao {

    private final String path = "hospital.bin";
    private File file;

    public HospitalDao() {
        this.file = new File(this.path);
    }

    public List<Hospital> getAll() {
        List<Hospital> hospitals = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new FileInputStream(file));
            Object aux = ois.readObject();
            while (aux != null) {
                if (aux instanceof Hospital) {
                    hospitals.add((Hospital) aux);
                }
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e1) {
            try {
                ois.close();
            } catch (IOException ex) {

            }
        } catch (FileNotFoundException ex) {
            hospitals = new ArrayList<>();
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            hospitals = null;
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            hospitals = null;
            System.out.println(ex.getMessage());
        }
        return hospitals;
    }

    public boolean anyadir(Hospital hospital) {
        if (!file.exists()) {
            return anyadirPrimeraVez(hospital);
        } else {
            return anyadirNoPrimeraVez(hospital);
        }
    }

    private boolean anyadirPrimeraVez(Hospital hospital) {
        boolean result = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(file));
            oos.writeObject(hospital);
            result = true;
            oos.close();
        } catch (Exception e) {
            result = false;
            System.out.println(e.getMessage());
        }
        return result;
    }

    private boolean anyadirNoPrimeraVez(Hospital hospital) {
        boolean result = false;
        MyObjectOutputStream oos;
        try {
            oos = new MyObjectOutputStream(
                    new FileOutputStream(file, true));
            oos.writeUnshared(hospital);
            result = true;
            oos.close();
        } catch (IOException ex) {
            result = false;
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public List<Hospital> getAvailables() throws ClassNotFoundException, IOException {
        List<Hospital> hospitals = getAll();
        if (hospitals != null) {
            for (int i = 0; i < hospitals.size(); i++) {
                if (hospitals.get(i).isComplet()) {
                    hospitals.remove(hospitals.get(i));
                }
            }
        } else {
            hospitals = null;
        }
        return hospitals;
    }

    public Hospital encontrar(String name) {
        List<Hospital> hospitales = getAll();
        Hospital hospital = null;
        if (hospitales != null) {
            for (int i = 0; i < hospitales.size(); i++) {
                if (hospitales.get(i).getNom().equalsIgnoreCase(name)) {
                    hospital = hospitales.get(i);
                }
            }
        }
        return hospital;
    }

    public boolean borrar(Hospital hospital) {
        boolean result = false;
        List<Hospital> hospitales = getAll();
        try {
            if (hospitales != null) {
                result = hospitales.remove(hospital);
                if (file.delete()) {
                    for (int i = 0; i < hospitales.size(); i++) {
                        anyadir(hospitales.get(i));
                    }
                }
            }
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

}
