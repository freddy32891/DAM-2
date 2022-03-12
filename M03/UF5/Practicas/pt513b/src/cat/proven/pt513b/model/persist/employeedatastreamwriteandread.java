/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513b.model.persist;

import cat.proven.pt513b.model.Address;
import cat.proven.pt513b.model.Employee;
import static cat.proven.pt513b.model.persist.EmployeeCsvWriteAndRead.toCsvEmployee;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc Bernaola Bruach
 */
public class EmployeeDataStreamWriteAndRead implements EmployeeFilePersistInterface{
    
    @Override
     /**
     * Save
     * @param data get a employee's list.
     * @return 1 if successfully, or 0 in case of error.
     */
    public void save(List<Employee> data,String path) {
        int result = 0;
        File f = new File(path);
        try {
	    DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
            for (int i = 0; i < data.size(); i++) {
                dos.writeUTF(data.get(i).getName());
                dos.writeUTF(data.get(i).getPhone());
                dos.writeInt(data.get(i).getAge());
                dos.writeBoolean(data.get(i).isSenior());
                dos.writeDouble(data.get(i).getSalary());
                dos.writeUTF(data.get(i).getAddress().getStreetName());
                dos.writeUTF(data.get(i).getAddress().getZipCode());
                dos.writeInt(data.get(i).getAddress().getStreetNumber());
            }
            dos.close();
        } catch (IOException e) {
            System.out.println("Input or output problem related to this exception:");
            e.printStackTrace();
        }   
    }
    /**
     * 
     * @return a list of employees.
     */
    @Override
    public List<Employee> load(String path) {
        List<Employee> lista = new ArrayList<Employee>();
        DataInputStream salida;
        File f = new File(path);
        try{
            salida = new DataInputStream(new FileInputStream(f));
            try {
                while (true) {
                    Employee e = new Employee();
                    Address ad = new Address();
                    e.setName(salida.readUTF());
                    e.setPhone(salida.readUTF());
                    e.setAge(salida.readInt());
                    e.setSalary(salida.readDouble());
                    e.setSenior(salida.readBoolean());
                    ad.setStreetName(salida.readUTF());
                    ad.setZipCode(salida.readUTF());
                    ad.setStreetNumber(salida.readInt());
                    e.setAddress(ad);
                    lista.add(e);
                }
            } catch (EOFException eof) {
                
                try {
                    salida.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (IOException ex) {
                Logger.getLogger(EmployeeDataStreamWriteAndRead.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeDataStreamWriteAndRead.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
