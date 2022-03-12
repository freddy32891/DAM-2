/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513;

import cat.proven.pt513b.model.Employee;
import cat.proven.pt513b.model.persist.EmployeeDataStreamWriteAndRead;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marc Bernaola Bruach
 */
public class MainDataStreamRead {
     String path = "files/empleadosStream.txt";
     Scanner sc = new Scanner(System.in);
     public static void main(String[] args) {  
        MainDataStreamRead myapp = new MainDataStreamRead();
        myapp.run();  
    }
     /**
      * Method to execute non static methods.
      */
    private void run() {
        List<Employee> lista= new ArrayList<Employee>();
        EmployeeDataStreamWriteAndRead eDSWR = new EmployeeDataStreamWriteAndRead();
        System.out.println("Ruta predeterminada: " + path + "Esta el fichero en otra ubicación? pulsa 1 si hay otra ubicacion, si es la predeterminada 0");
        int opcion=3;
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
            lista = eDSWR.load(path); 
            mostrarLista(lista);
        }catch(InputMismatchException ime){
            System.out.println("Ha habido un error al leer un valor. " + ime.getMessage());
            //ime.printStackTrace();
        }
    }
    /**
     * Show the list values.
     * @param data Arraylist that contains the data.
     */
    public void mostrarLista(List<Employee> data){
        for(int i=0;i<data.size();i++){
            System.out.println(data.get(i).toString()); 
        }
    }
}
