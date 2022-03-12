/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gym;

import cat.proven.gym.model.Activity;
import cat.proven.gym.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author fredd
 */
public class Tester {

    private static List<Activity> activities = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static Map<Activity, List<User>> enrolements = new HashMap<>();

    public static void main(String[] args) {
        loadTestData();
        printMap();

    }

    public static void loadTestData() {
        Activity a;

        a = new Activity("Cardio", "09:00 am", "Mondays and Fridays", 1, 24);
        Activity b = new Activity("Running", "10:00 am", "Mondays and Tuesday", 2, 20);
        Activity c = new Activity("Zumba", "07:00 pm", "Mondays and Wednesdays", 1, 34);
        activities.add(a);
        activities.add(b);
        activities.add(c);
        enrolements.putIfAbsent(a, users);
        enrolements.put(c, users);
        User carlos, mar;
        carlos = new User("3", "Carlos perez", "carlosperez@gmail.com");
        mar = new User("2", "Mar Sanchez", "marsanchez@gmail.com");
        users.add(mar);
        users.add(carlos);
        users.add(new User("1", "Freddy", "freddyachata@gmail.com"));
    }

    public static void printMap() {
        for (Map.Entry<Activity, List<User>> entrada : enrolements.entrySet()) {
            Activity key = entrada.getKey();
            List<User> value = entrada.getValue();
            System.out.println("Activity: " + key + "\nUsers: \n" + value.toString());

            // do stuff 
        }
    }

}
