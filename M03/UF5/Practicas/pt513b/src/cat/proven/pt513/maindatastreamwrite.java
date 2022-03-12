/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513;

import cat.proven.pt513b.model.Address;
import cat.proven.pt513b.model.Employee;
import cat.proven.pt513b.model.persist.EmployeeCsvWriteAndRead;
import cat.proven.pt513b.model.persist.EmployeeDataStreamWriteAndRead;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *0
 * @author Marc Bernaola Bruach
 */
public class MainDataStreamWrite {
     List<Employee> lista= new ArrayList<Employee>();
     String path = "files/empleadosStream.txt";
     Scanner sc = new Scanner(System.in);
    public static void main(String[] args) { 
        MainDataStreamWrite myapp = new MainDataStreamWrite();
        myapp.run();    
    }
    /**
     * Method to use non static methods
     */
    public void run() {
        EmployeeDataStreamWriteAndRead eDataStreamWR = new EmployeeDataStreamWriteAndRead();
        loadData();
        System.out.println("Ruta predeterminada: " + path + "Si quieres cambiarla pulsa 1, si estas conforme 0");
        int opcion=0;
        try{
            opcion = sc.nextInt();
            switch(opcion){
            case 0:
                break;
            case 1: 
                System.out.println("Introduce el nombre de la ruta:");
                path = sc.next();
                break;       
            default:
                System.out.println("Opcion incorrecta, selecciona 0 o 1.");
                break;
            }
            eDataStreamWR.save(lista,path);
        }catch(InputMismatchException ime){
            System.out.println("Ha habido un error al leer un valor. " + ime.getMessage());
        } 
    }
    /**
     * 
     */
    private void loadData() {
        Employee p1 = new Employee("Marc","634534567",21,false,1905.25,new Address("Calle Mineria","08038",37));    
        Employee p2 = new Employee("Ariadna","737345355",19,false,1205.25,new Address("Carrer Corcega","08650",55));   
        Employee p3 = new Employee("Freddy","634534366",33,true,1512.25,new Address("Carrer del Bages ","08900",11));
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
    }
}
