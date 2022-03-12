/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeap;

import cat.proven.storeap.model.Fridge;
import cat.proven.storeap.model.Product;
import cat.proven.storeap.model.Store;
import cat.proven.storeap.model.Tv;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author FreddySoft
 */
public class Tester {
static Scanner myscan = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Product p1 = new Product("TV1", "Television with 50 inches", 1000.0, 4);
//        System.out.println(p1.toString());
//        
//        Product p2 = new Product ("TV2");
//        if(p1.equals(p2)){
//            System.out.println("p1=p2");
//        }else System.out.println("p1!=p2");
//        //taste
//        //TRY TO ADD NEW OBJECT
//        Store myStore = new Store(5);
//        Product p =null;
//        boolean b=myStore.addProduct(p);
        
    Tv tv1 =new Tv("Tv1", "Desc tv1", 600.0, 4, 52 );
        System.out.println(tv1.toString());
        
        Fridge fr1 =new Fridge("Fr1", "Desc fr1", 1200.0, 6, 220, true );
        System.out.println(fr1.toString());
        List<Product> lista = new ArrayList<>();
        lista.add(fr1);
        lista.add(tv1);
        System.out.println("\nIMprimimos la lista una vez añadidos los productos");
        System.out.println(lista.toString()+"\n");
        System.out.format("%d. %s\n", 12, lista);
        System.out.println("");
        System.out.println("IMprimimos con un for-each");
        lista.forEach((obj) -> {
            System.out.println(obj.toString());
    });  
        System.out.print("\nDIme el codigo del producto que desea buscar: ");
        
        String codigo=myscan.nextLine();
        Product p= new Product(codigo);
        
//        if(lista.contains(new Product (p))){
//            System.out.println("Encontrado");
//            for (int i=0;lista.size()<i;i++) {          
//            System.out.println(lista.get(i));
//        }}else{
//            System.out.println("No encontrado");
//        }

    for (int i =0;i<lista.size();i++){
    if(lista.get(i).getId().equals(p.getId())){
        System.out.println("Encontrado");
        System.out.println(lista.get(i));
    }
    
    }
        System.out.println("Vbuscar de otra manera");
            
    if(!lista.contains(p)){
        System.out.println("No encontrado");
    }else{
        System.out.println("Encontrado");
        
    }
        
    
////        int ubicacionEnArray = lista.indexOf(new Product(codigo));
//        if (ubicacionEnArray >= 0) {
//			// En este caso si existe se imprime por pantalla y fin
//			System.out.println(lista.get(ubicacionEnArray));
//		}else{
//			System.out.println("El producto no existe en el array!");
//		}
        
        
//        System.out.println("DELETE");
//        
//        Product removed =lista.remove(ubicacionEnArray);
//        if(removed!=null){
//            System.out.println("BOrrado con exito");
//        }else{
//            System.out.println("NO se ha borrado");
//        }
//        
System.out.println("\nImprimir despues de buscar");        
for (Product obj : lista) {
        System.out.println(obj.toString());
    }  
//        int i= lista.indexOf(new Product("Fr1"));
//        Product p=new Product ("Fr1");
//        
//        if(lista.contains(p)){
//            System.out.println(p);
//        }else{
//            System.out.println("no se ha encontrado el producto");
//        }
        
        //System.out.println(lista);
    }
    
    
    
}
