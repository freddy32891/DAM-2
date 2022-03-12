/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513b.model.persist;

import cat.proven.pt513b.model.Address;
import cat.proven.pt513b.model.Employee;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marc Bernaola Bruach
 */
public class EmployeeCsvWriteAndRead implements EmployeeFilePersistInterface {
    
    final String myDelimiter = ";";  //delimiter used to separate fields.
    /**
     * 
     * @param obj
     * @param delimiter
     * @return 
     */
    public static String toCsvEmployee(Employee obj, String delimiter) {
        return String.format("%s%s %s%s %s%s %s%s %s%s %s",
        obj.getName(),delimiter,obj.getPhone(),delimiter,obj.getAge(),delimiter,obj.isSenior(),delimiter,obj.getSalary(),delimiter,toCsvAddress(obj.getAddress(),";"));
    }
    /**
     * 
     * @param obj
     * @param delimiter
     * @return 
     */
    public static String toCsvAddress(Address obj, String delimiter) {
        return String.format("%s%s %s%s %s%s",
        obj.getStreetName(), 
        delimiter, 
        obj.getZipCode(), 
        delimiter, 
        obj.getStreetNumber(), 
        delimiter);
    }
     /**
     * fromCsv() Converts CSV String to object.
     * @param String s: string to be converted.
     * @param String delimiter: delimiter to be used between fields.
     * @return Person object with data contained in String s.
     */
    public static Employee fromCsvToEmployee(String s, String delimiter) {
        int objNumFields = 8;  //number of attributes of Employee.
        Employee p = null;
        String[] tokens = s.split(delimiter);
        if (tokens.length == 8) {
            String name = tokens[0].trim();
            String phone = tokens[1].trim();
            int age = Integer.parseInt(tokens[2].trim());
            boolean senior= Boolean.parseBoolean(tokens[3].trim());
            double salary = Double.parseDouble(tokens[4].trim());
            String direccion;
            direccion = tokens[5].trim() +delimiter+ tokens[6].trim()+delimiter + tokens[7].trim();
            p = new Employee(name,phone,age,senior,salary,fromCsvToAddress(direccion,";"));
        } else {
            p = null;
        }
        return p;
    }
     /**
     * fromCsv() Converts CSV String to object.
     * @param String s: string to be converted.
     * @param String delimiter: delimiter to be used between fields.
     * @return Address object with data contained in String s.
     */
    public static Address fromCsvToAddress(String s, String delimiter) {
        int objNumFields = 3;  //number of attributes of Address.
        Address p = null;
        String[] tokens = s.split(delimiter);
        if (tokens.length == 3) {
           String streetName = tokens[0].trim();
           String zipCode = tokens[1].trim();
           int streetNumber = Integer.parseInt(tokens[2].trim());
            p = new Address(streetName,zipCode,streetNumber);
        } else {
            p = null;
        }
        return p;
    }
    /**
     * Load the csv file and pass the lines that it contains into another method
     * @param path is the place of file is ubicated
     * @return a list of employees
     */
    @Override
    public List<Employee> load(String path) {
        List<Employee> lista = new ArrayList<Employee>();
        String line;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                while ((line = br.readLine()) != null) {
                    lista.add(fromCsvToEmployee(line,";"));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Exception info:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input or output problem related to this exception:");
            e.printStackTrace();
        }
        return lista;
    }
     /**
     * Save into a file the list of Employees converted in format Csv
     * @param data get a employee's list
     * @param path is the place of file is ubicated
     */
    @Override
    public void save(List<Employee> data,String path) {
        int result=0;
        String linea;
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                for(int i=0;i<data.size();i++){
                    //recorrer la lista y para cada persona hacer lo del metodo de arriba
                    String lineaGuardar = toCsvEmployee(data.get(i),";");
                    writer.write(lineaGuardar + "\n");
                }
            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
             
    }
}