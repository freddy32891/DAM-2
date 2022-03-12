/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.pt513b.model.persist;

import cat.proven.pt513b.model.Employee;
import java.util.List;

/**
 *
 * @author Marc Bernaola Bruach
 */
public interface EmployeeFilePersistInterface {
    void save(List<Employee> data,String path);
    List<Employee> load(String path);
}
