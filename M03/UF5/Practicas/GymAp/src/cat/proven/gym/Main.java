/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gym;

import cat.proven.gym.exceptions.ActivityNotFoundedException;
import cat.proven.gym.exceptions.DuplicateActivityException;
import cat.proven.gym.exceptions.DuplicateUserException;
import cat.proven.gym.exceptions.EmptyActivityException;
import cat.proven.gym.exceptions.EmptyArrayListException;
import cat.proven.gym.exceptions.EmptyUserException;
import cat.proven.gym.exceptions.FullActivityException;
import cat.proven.gym.exceptions.UserNotFoundedException;
import cat.proven.gym.model.Activity;
import cat.proven.gym.model.Gym;
import cat.proven.gym.model.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author FreddySoft
 */
public class Main {

    //TODO
    //define menu and gym
    private Gym myGym;
    private String[] mainMenu = {
        "exit", "listallactivities", "addActivity", "modifyActivity", "removeActivity", "signUp", "unsubscribeUser", "modifyUser", "joinActivity",
        "unsubscribeActivity", "listUsersOfActivity", "listActivitiesOfUser", "listAvailableActivities"
    };

    private Properties properties;
    private Properties configProperties = new Properties();

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.run();
    }

    private void run() throws IOException {
        boolean exit = false; //flag to exit program.
        //create a Store
        myGym = new Gym();
        //load Data
        myGym.loadTestData();
        //instanciate properties in English by efault
        properties = initProperties("english");

        do {

            //show menu
            int selectedOption = showMenuAndReadOption(mainMenu);

            //process action
            switch (selectedOption) {
                case 0: //Exit
                    exit = true;
                    break;
                case 1: //List all activities
                    listAllActivities();
                    break;
                case 2: //Add an activity
                    addActivity();
                    break;
                case 3: //Modify an activity
                    modifyActivity();
                    break;
                case 4: //Remove activity
                    removeActivity();
                    break;
                case 5: //Sign up a user
                    signUpUser();
                    break;
                case 6: //Unsubscribe a user in the gym
                    unsubscribeUser();
                    break;
                case 7: //ModifyUser
                    modifyUser();
                    break;
                case 8: //inscribe a user to an activity
                    joinActivity();
                    break;
                case 9: //Remove a user of an activity
                    removeUserFromActivity();
                    break;
                case 10: //LIst all users of an activity
                    listUsersOfActivity();
                    break;
                case 11: //list all activities of an user
                    listAcitivitiesOfUser();
                    break;
                case 12: //list all available activities
                    listAvailableActivities();
                    break;
                default:
                    displayMessage("invalidOption");
                    break;
            }
        } while (!exit);
        displayMessage("goodbye");
    }

    //add display message
    /**
     * Method that shows the Menu and reads the option of the User
     *
     * @return the option selected by User In case of error, it sends -1
     */
    private int showMenuAndReadOption(String[] array) {
        int option = -1;
        for (int i = 0; i < array.length; i++) {
            System.out.format("%d. %s\n", i, translate(array[i]));
        }

        try {
            //read option.
            option = inputInt("selectAnOption");
        } catch (InputMismatchException e) {
            //System.out.println(e.getMessage());
            option = -1;
        }
        return option;
    }

    /**
     * Method that shows all the activities int he gym
     *
     * @returns the gym with all the activities or null. If data is null, it's
     * catched by EmptyArrayList and it's reported to user.
     */
    private void listAllActivities() {
        try {
            List<Activity> data = myGym.findAllActivities();
            displayActivityTable(data);

        } catch (EmptyArrayListException ex) {
            displayMessage("emptyArrayListActivitiesException");
        }
    }

    /**
     * Method that asks the user to input an activity. Then it's added in the
     * gym. If the activity is null, it's catched by EmptyActivityException and
     * it's reported to user. If the activity already exists, it's catched by
     * DuplicateActivityException and it's reported to user.
     */
    private void addActivity() {
        Activity newP = inputActivity();
        try {
            //add Activity to the Gym
            myGym.addActivity(newP);
            displayMessage("activitySuccesfullyAdded");

        } catch (EmptyActivityException ex) {
            displayMessage("EmptyActivityException");
            displayMessage("activityNotAdded");
        } catch (DuplicateActivityException ex) {
            displayMessage("duplicateActivityException");
            displayMessage("activityNotAdded");
        }
    }

    /**
     * Method that ask the user the name of the activity he wants to modify. It
     * searches the activity by the name and asks if he is sure to modify the
     * activity showed. If the answer is Y it is ask the user to input a new
     * Activity and the it modifies the Activity. Otherwise, it is not modified
     * and shows a message to user. if the name is null, it's catched by
     * EmptyActivityException and reports to user by a message if the activity
     * does not exist, it's catched by ActivityNotFoundedException and reports
     * to user by a message
     */
    private void modifyActivity() {
        String name = inputString("nameActivity");
        try {

            Activity oldActivity = myGym.findActivityByName(name);
            displayActivity(oldActivity);
            String option = inputString("areYouSureModify");
            if (option.equalsIgnoreCase("Y")) {
                displayMessage("inputData");
                Activity newActivity = inputActivity();
                myGym.modifyActivity(newActivity, oldActivity);
                displayMessage("activityModified");
            } else {
                displayMessage("activityNotModified");
            }

        } catch (EmptyActivityException ex) {
            displayMessage("emptyActivityException");
            displayMessage("activityNotModified");
        } catch (ActivityNotFoundedException ex) {
            displayMessage("activityNotFoundedException");
            displayMessage("activityNotModified");
        }

    }

    /**
     * Method that ask the user the name of the activity he wants to remove. It
     * searches the activity by the name and asks if he is sure to delete the
     * activity showing it. If the answer is Y it is removed from the Gym.
     * Otherwise, it is not removed and shoew a message to user. if the name is
     * null, it's catched by EmptyActivityException and reports to user by a
     * message if the activity does not exist, it's catched by
     * ActivityNotFoundedException and reports to user by a message
     */
    private void removeActivity() {
        try {
            String name = inputString("nameActivity");
            Activity found = myGym.findActivityByName(name);
            displayActivity(found);
            String option = inputString("areYouSureDeleteActivity");
            if (option.equalsIgnoreCase("Y")) {
                myGym.deleteActivity(found);
                displayMessage("activityModified");
            } else {
                displayMessage("activityNotRemoved");
            }
        } catch (EmptyActivityException ex) {
            displayMessage("emptyActivityException");
        } catch (ActivityNotFoundedException ex) {
            displayMessage("activityNotFoundedException");
        }
    }

    /**
     * Method that asks the user to input a new user. Then it's added in the
     * gym. If the user is null, it's catched by EmptyUserException and it's
     * reported to user. If the user already exists, it's catched by
     * DuplicateUserException and it's reported to user.
     */
    private void signUpUser() {
        try {
            User user = inputUser();
            myGym.addUser(user);
            displayMessage("userSuccesfullyAdded");
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
            displayMessage("userNotAdded");
        } catch (DuplicateUserException ex) {
            displayMessage("duplicateUserException");
            displayMessage("userNotAdded");
        }
    }

    /**
     * Method that asks the user the id of the user he wants to delete. It
     * searchs the user by the id and shows the user asking if he wants to
     * delete. In case that the answer is Y, the user is deleted, otherwise,
     * it's not deleted If the user is null, it's catched by EmptyUserException
     * and it's reported to user. If the user is not founded, it's catched by
     * UserNotFoundedException and it's reported to user.
     */
    private void unsubscribeUser() {
        try {
            String id = inputString("idUser");
            User user = myGym.findUserById(id);
            displayUser(user);
            String option = inputString("areYouSureDeleteUser");
            if (option.equalsIgnoreCase("Y")) {
                myGym.removeUser(user);
                displayMessage("userRemoved");
            } else {
                displayMessage("userNotRemoved");
            }
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
            displayMessage("userNotRemoved");
        } catch (UserNotFoundedException ex) {
            displayMessage("userNotFoundedException");
            displayMessage("userNotRemoved");
        }

    }

    /**
     * Method that asks the user the id of the user he wants to modify. It
     * searchs the user by the id and shows the user asking if he wants to
     * modify. In case that the answer is Y, aks to input a new user and
     * modifies the user, otherwise, it's not modified If the user is null, it's
     * catched by EmptyUserException and it's reported to user. If the user is
     * not founded, it's catched by UserNotFoundedException and it's reported to
     * user.
     */
    private void modifyUser() {
        try {
            String id = inputString("idUser");
            User oldUser = myGym.findUserById(id);
            displayUser(oldUser);
            String option = inputString("areYouSureModifyUser");
            if (option.equalsIgnoreCase("Y")) {
                displayMessage("inputData");
                User newUser = inputUser();
                myGym.modifyUser(newUser, oldUser);
                displayMessage("userModified");
            } else {
                displayMessage("userNotModified");
            }
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
            displayMessage("userNotModified");
        } catch (UserNotFoundedException ex) {
            displayMessage("userNotFoundedException");
            displayMessage("userNotModified");
        }
    }

    /**
     * Method that asks user the id of the user to add and the name of the
     * activity where the user will be added. Then it adds the user searched by
     * id to the activity searched by name.
     *
     * @return a Map with the user added in the activity If the id of the user
     * or the user is null, it's catched by EmptyUserException and it's reported
     * to user. If the name of the activity or the activity is null, it's
     * catched by EmptyActivityException and it's reported to user. If the
     * activity given is not founded it's catched by ActivityNotFoundedException
     * and it's reported to user. If the user doesn't exist in the arrayList of
     * Users, it's catched by UserNotFoundedException and it's reported to User.
     * If the User given is already in the activity, it's catched by
     * DuplicateUserException and it's reported to User. If the Activity has no
     * places left, it's catched by FullActivityException and it's reported to
     * User.
     */
    private void joinActivity() {
        try {
            String id = inputString("idUser");
            User user = myGym.findUserById(id);
            String name = inputString("nameActivity");
            Activity activity = myGym.findActivityByName(name);
            myGym.addUsertToActivity(user, activity);
            displayMessage("userSuccesfullyAdded");
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
            displayMessage("userNotAdded");
        } catch (EmptyActivityException ex) {
            displayMessage("emptyActivityException");
            displayMessage("userNotAdded");
        } catch (ActivityNotFoundedException ex) {
            displayMessage("activityNotFoundedException");
            displayMessage("userNotAdded");
        } catch (UserNotFoundedException ex) {
            displayMessage("userNotFoundedException");
            displayMessage("userNotAdded");
        } catch (DuplicateUserException ex) {
            displayMessage("duplicateUser");
            displayMessage("userNotAdded");
        } catch (FullActivityException ex) {
            displayMessage("fullActivityException");
            displayMessage("userNotAdded");
        }

    }

    /**
     * Method that asks the id of the user to remove and the activity where is
     * going to be deleted. Then searchs the user by the id and the activity by
     * the name. Shows the user founded and asks if he is sure to remove this
     * user. In case taht the option is Y, the user is removed, otherwise it's
     * not removed.
     *
     * If the new user is null, it's catched by EmptyUserException and it's
     * reported to user. If the Activity given is null, it's catched by
     * EmptyActivityException and it's reported to user. If the activity given
     * doesn't exist, it's catched by ActivityNotFoundedException and it's
     * reported to user. If the user doeesn't exist, it's catched by
     * UserNotFoundedException and it's reported to user.
     */
    private void removeUserFromActivity() {
        try {
            String id = inputString("idUser");
            User user = myGym.findUserById(id);
            String name = inputString("nameActivity");
            Activity actvity = myGym.findActivityByName(name);
            displayUser(user);
            String option = inputString("areYouSureDeleteUser");
            if (option.equalsIgnoreCase("Y")) {
                myGym.removeUserFromActivity(user, actvity);
                displayMessage("userRemoved");
            } else {
                displayMessage("userNotRemoved");
            }
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
            displayMessage("userNotRemoved");
        } catch (EmptyActivityException ex) {
            displayMessage("emptyActivityException");
            displayMessage("userNotRemoved");
        } catch (UserNotFoundedException ex) {
            displayMessage("userNotFoundedException");
            displayMessage("userNotRemoved");
        } catch (ActivityNotFoundedException ex) {
            displayMessage("activityNotFoundedException");
            displayMessage("userNotRemoved");
        }

    }

    /**
     * Method that asks the user the name of the activity where he wants to know
     * all the users. Then it founds the activity by the name and shows all the
     * users in the activity. If the name of the activity is null, it's catched
     * by EmptyActivityException and it's reported to user. If the activity
     * doesn't exist in the Gym, it's catched by ActivityNotFoundedException and
     * it's reported to user. If there's no users in the activity, it's catched
     * by EmptyArrayListException and it's reported to user.
     */
    private void listUsersOfActivity() {
        try {
            String name = inputString("nameActivity");
            Activity activity = myGym.findActivityByName(name);
            List<User> usersOfActivitiy = myGym.findUsersFromActivity(activity);
            displayUserTable(usersOfActivitiy);

        } catch (EmptyActivityException ex) {
            displayMessage("emptyActivityException");
        } catch (ActivityNotFoundedException ex) {
            displayMessage("activityNotFoundedException");
        } catch (EmptyArrayListException ex) {
            displayMessage("emptyArrayListUsersException");
        }
    }

    /**
     * Method that asks the id of the user. Then it founds all the acitivites of
     * this user. If the user is null, it's catched by EmptyUserException and
     * it's reported to User If the user is not founded, it's catched by
     * UserNotFoundedException and it's reported to User If the list is null,
     * it's catched by EmptyArrayList and it's reported to User
     */
    private void listAcitivitiesOfUser() {
        try {
            String id = inputString("idUser");
            User user = myGym.findUserById(id);
            List<Activity> activitiesOfUser = myGym.findActivitiesFromUser(user);
            displayActivityTable(activitiesOfUser);
        } catch (EmptyUserException ex) {
            displayMessage("emptyUserException");
        } catch (UserNotFoundedException ex) {
            displayMessage("userNotFoundedException");
        } catch (EmptyArrayListException ex) {
            displayMessage("emptyArrayListActivitiesException");
        }
    }

    /**
     * Method that asks the number of available activities needed. Then it
     * founds all the acitivites with this number of places available. If the
     * user is null, it's catched by EmptyUserException and it's reported to
     * User If the user is not founded, it's catched by UserNotFoundedException
     * and it's reported to User If the list is null, it's catched by
     * EmptyArrayList and it's reported to User
     */
    private void listAvailableActivities() {
        try {
            int n = inputInt("availableActivities");
            List<Activity> activitiesAvailable = myGym.findAvailableActivities(n);
            displayActivityTable(activitiesAvailable);
        } catch (EmptyArrayListException ex) {
            displayMessage("emptyArrayListActivitiesException");
        } catch (InputMismatchException ex) {
            displayMessage("incorrectFormatNumber");
        }
    }

    //View methods
    /**
     * Aks the user to input for a new Activity
     *
     * @ return an activity with the data or null in case of error
     *
     */
    private Activity inputActivity() {
        Activity activity = null;
        String name = inputString("nameActivity");
        String weekDay = inputString("dayStarts");
        String hour = inputString("hour");
        try {
            String sPrice = inputString("duration");
            int price = Integer.parseInt(sPrice);
            String sStock = inputString("capacity");
            int stock = Integer.parseInt(sStock);
            activity = new Activity(name, hour, weekDay, price, stock);

        } catch (NumberFormatException e) {
            System.out.println("formatException");
            activity = null;
        }
        return activity;
    }

    /**
     * Method that asks the user to input a new user
     *
     * @return an user with the data or null in case of error
     */
    private User inputUser() {
        User user = null;
        String id = inputString("idUser");
        String name = inputString("nameUser");
        String mail = inputString("mailUser");
        user = new User(id, name, mail);
        return user;
    }

    private void displayMessage(String x) {
        System.out.println(translate(x));
    }

    /**
     * Presents a message to user and read an answer
     *
     * @param message
     * @return the answer of user or an error if it's incorrect
     */
    private String inputString(String message) {
        displayMessage(message);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        return sc.next();
    }

    /**
     * Method that shows a message to user and reads an int number
     *
     * @param message to show to user
     * @return the answer of the user or -1 in case of error.
     */
    private int inputInt(String message) throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        int n = -1;
        displayMessage(message);
        n = sc.nextInt();

        return n;
    }

    /**
     * Translates a mesasge by the key
     *
     * @param key
     * @return an String with the translation
     */
    private String translate(String key) {
        return properties.getProperty(key);
    }

    /**
     * open translations files to modified the text to another languages
     *
     * @return properties with the choosen language
     */
    private Properties initProperties(String option) throws IOException {
        configProperties.load(new FileInputStream("Translations/translations.properties"));
        String idLanguage = configProperties.getProperty(option);
        configProperties.load(new FileInputStream("Translations/" + idLanguage + ".properties"));
        return configProperties;
    }

    /**
     * Displays an activity to User
     *
     * @param activity to display
     */
    private void displayActivity(Activity activity) {
        System.out.println(activity.toString());
    }

    /**
     * Displays an user
     *
     * @param user to display
     */
    private void displayUser(User user) {
        System.out.println(user.toString());
    }

    /**
     * Displays a List of all activities required
     *
     * @returns data to display
     */
    private void displayActivityTable(List<Activity> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).toString());
        }
    }

    /**
     * Displays a List of all users required
     *
     * @returns data to display
     */
    private void displayUserTable(List<User> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).toString());
        }
    }

}
