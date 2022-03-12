package cat.proven.gym.model;

import cat.proven.gym.exceptions.ActivityNotFoundedException;
import cat.proven.gym.exceptions.DuplicateActivityException;
import cat.proven.gym.exceptions.DuplicateUserException;
import cat.proven.gym.exceptions.EmptyActivityException;
import cat.proven.gym.exceptions.EmptyArrayListException;
import cat.proven.gym.exceptions.EmptyUserException;
import cat.proven.gym.exceptions.FullActivityException;
import cat.proven.gym.exceptions.UserNotFoundedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author FreddySoft
 */
public class Gym {

    //atributes
    private List<Activity> activities;
    private List<User> users;
    private Map<Activity, List<User>> enrolements;

    //constructor
    public Gym() {
        this.activities = new ArrayList<>();
        this.users = new ArrayList<>();
        this.enrolements = new HashMap<>();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<User> getUsers() {
        return users;
    }

    public Map<Activity, List<User>> getEnrolements() {
        return enrolements;
    }
    

    /**
     * Method that finds all activities in the data
     *
     * @return the arrayList of all the activities
     * @throws EmptyArrayListException in case that the list of activities is
     * empty.
     */
    public List<Activity> findAllActivities() throws EmptyArrayListException {
        if (activities != null) {
            return activities;
        } else {
            throw new EmptyArrayListException("The arrayList of Activities is empty");
        }
    }

    /**
     * Method that adds an Activity on to the ArrayList of activities and
     * instanciates a new position on the HashMap.
     *
     * @param newP given to add
     * @return true if succesfully added
     * @throws DuplicateActivityException, in case that the Activity is
     * duplicated.
     * @throws EmptyActivityException, in case that the activity is null.
     */
    public boolean addActivity(Activity newP) throws DuplicateActivityException, EmptyActivityException {
        boolean addedSuccesfully = false;
        if (newP != null) {
            if (!activities.contains(newP)) {
                if (!enrolements.containsKey(newP)) {
                    activities.add(newP);
                    enrolements.putIfAbsent(newP, new ArrayList<>());//Instanciates a new position in enrolements avoiding duplicate key in the Map
                    addedSuccesfully = true;
                } else {
                    throw new DuplicateActivityException("The activity already exists in Map of enrolements");
                }
            } else {
                throw new DuplicateActivityException("The activity already exists in the arrayList of activities");
            }
        } else {
            addedSuccesfully = false;
            throw new EmptyActivityException("The array given to add is null");
        }

        return addedSuccesfully;
    }

    /**
     * Method that modifies an Activity on the arrayList of activities and in
     * the HashMap of enrolements.
     *
     * @param newAct the new Activity with the modifications.
     * @param oldActivity the old activity to be modified.
     * @return true if the activity is succesfully modified.
     * @throws EmptyActivityException, in case that the newActivity is null.
     * @throws ActivityNotFoundedException, in case that the oldActivity is not
     * founded.
     */
    public boolean modifyActivity(Activity newAct, Activity oldActivity) throws ActivityNotFoundedException, EmptyActivityException {
        boolean correctlyModified = false;
        if (newAct != null) {
            if (oldActivity != null) {
                if (activities.contains(oldActivity)) {
                    if (enrolements.containsKey(oldActivity)) {
                        activities.set(findActivityIndex(oldActivity.getName()), newAct);//replacing the oldActivity with the newActivity in the ArrayList of activities
                        enrolements.put(newAct, users); //replacing the oldActivity with the newActivity in the HashMap of enrolements
                        correctlyModified = true;
                    } else {
                        throw new ActivityNotFoundedException("The activity which was going to be modified is not in the Map of enrolements");
                    }
                } else {
                    throw new ActivityNotFoundedException("The activity which was going to be modified is not in the arrayList of activities");
                }
            } else {
                throw new EmptyActivityException("The old activity which was going to be modified is null.");
            }
        } else {
            throw new EmptyActivityException("The new activity with the modifications is null.");
        }
        
     
        return correctlyModified;
    }

    /**
     * Method that deletes an Activity on the arrayList of activities and in the
     * HashMap of enrolements.
     *
     * @param activity to delete
     * @return true if it's succesfully deleted.
     * @throws EmptyActivityException, in case that the Activity given by the
     * user is null
     * @throws ActivityNotFoundedException, in case that the Activity is not
     * founded
     *
     */
    public boolean deleteActivity(Activity activity) throws ActivityNotFoundedException, EmptyActivityException {
        boolean correctlyRemoved = false;

        if (activity != null) {
            if (activities.contains(activity)) {
                if (enrolements.containsKey(activity)) {
                    activities.remove(activity);//removing the activity  in the ArrayList of activities by the index
                    enrolements.remove(activity); //removing the activity in the HashMap of enrolements
                    correctlyRemoved = true;
                } else {
                    throw new ActivityNotFoundedException("The activity which was going to be mmodified is not in the Map of enrolements");
                }
            } else {
                throw new ActivityNotFoundedException("The activity which was going to be mmodified is not in the arrayList of activities");
            }
        } else {
            throw new EmptyActivityException("The old activity which was going to be modified is null.");
        }
        return correctlyRemoved;
    }

    /**
     * Method that registers an User to the ArrayList of users
     *
     * @param user to add
     * @return true if it's succesfully added
     * @throws EmptyUserException in case that the user is null
     * @throws DuplicateUserException in case that the user already exists in
     * the arrayList of users
     */
    public boolean addUser(User user) throws EmptyUserException, DuplicateUserException {
        boolean correctlyAdded = false;
        if (user != null) {
            if (!users.contains(user)) {
                correctlyAdded = users.add(user);
            } else {
                throw new DuplicateUserException("The user is already in the arrayList of users");
            }
        } else {
            throw new EmptyUserException("The user given is null");
        }
        return correctlyAdded;
    }

    /**
     * Method that removes an User from the ArrayList of users and in all the
     * activities where he is in
     *
     * @param user to remove
     * @return true if is succesfully added.
     * @throws EmptyUserException, in case that the user is null.
     * @throws UserNotFoundedException, in case that the user is not founded.
     */
    public boolean removeUser(User user) throws UserNotFoundedException, EmptyUserException {
        boolean correctlyRemoved = false;
        if (user != null) {
            if (users.contains(user)) {
                users.remove(user);
                for (Map.Entry<Activity, List<User>> entrada : enrolements.entrySet()) {
                    Activity key = entrada.getKey();
                    List<User> value = entrada.getValue();
                    if (value.contains(user)) {
                        List<User> isUser = enrolements.get(key);
                        isUser.remove(user);
                        enrolements.replace(key, users, isUser);
                    }
                }

            } else {
                throw new UserNotFoundedException("The user is not founded");
            }
        } else {
            throw new EmptyUserException("The user given is null");
        }
        return correctlyRemoved;
    }

    /**
     * Method that modifies an User on the arrayList of users and in the HashMap
     * of enrolements.
     *
     * @param newUser the new User with the modifications.
     * @param oldUser the old User to be modified.
     * @return true if the activity is succesfully modified, if not, it returns
     * false.
     * @throws EmptyUserException, in case that the newUser is null.
     * @throws UserNotFoundedException, in case that the oldUser is not founded.
     */
    public boolean modifyUser(User newUser, User oldUser) throws UserNotFoundedException, EmptyUserException {
        boolean correctlyModified = false;
        if (newUser != null) {
            if (oldUser != null) {
                if (users.contains(oldUser)) {
                    users.set(findUserIndex(oldUser.getId()), newUser);
                    for (Map.Entry<Activity, List<User>> entrada : enrolements.entrySet()) {
                        Activity key = entrada.getKey();
                        List<User> value = entrada.getValue();
                        if (value.contains(oldUser)) {
                            List<User> isUser = enrolements.get(key);
                            isUser.set(findActivityIndex(oldUser.getName()), newUser);
                            enrolements.replace(key, users, isUser);
                        }
                    }
                } else {
                    throw new UserNotFoundedException("The activity which was going to be modified is not in the arrayList of activities");
                }
            } else {
                throw new EmptyUserException("The old activity which was going to be modified is null.");
            }
        } else {
            throw new EmptyUserException("The new activity with the modifications is null.");
        }
        return correctlyModified;

    }

    /**
     * Method that adds an user to an activity
     *
     * @param user that wants to subscribe to an activity
     * @param activity where the user wants to join
     * @return true if it's succesfully added to the activity
     * @throws EmptyUserException in case that the new user is null.
     * @throws EmptyActivityException in case that the Activity given is null.
     * @throws ActivityNotFoundedException in case that the activity given is
     * not founded.
     * @throws UserNotFoundedException in case that the user doeesn't exist in
     * the arrayList of Users
     * @throws DuplicateUserException in case that the User given is already in
     * the activity
     * @throws FullActivityException in case that the Activity has no places
     * left
     */
    public boolean addUsertToActivity(User user, Activity activity) throws EmptyUserException, EmptyActivityException, ActivityNotFoundedException, UserNotFoundedException, DuplicateUserException, FullActivityException {
        boolean correctlyAdded = false;
        if (user != null) {
            if (activity != null) {
                if (users.contains(user)) {
                    if (activities.contains(activity)) {
                        if (enrolements.containsKey(activity)) {
                            List<User> enrolled = enrolements.get(activity);
                            if (!enrolled.contains(user)) {
                                if (enrolled.size() < activity.getCapacity()) {
                                    correctlyAdded = enrolled.add(user);
                                    enrolements.replace(activity, users, enrolled);
                                } else {
                                    throw new FullActivityException("The activity is full");
                                }
                            } else {
                                throw new DuplicateUserException("The user already exists in the Map of enrollements");
                            }
                        } else {
                            throw new ActivityNotFoundedException("The activity doesn't exist in the Map of enrolements");
                        }
                    } else {
                        throw new ActivityNotFoundedException("The activity doesn't exist in the arrayList of activities");
                    }
                } else {
                    throw new UserNotFoundedException("The user doesn't exist in the ArrayList of users");
                }
            } else {
                throw new EmptyActivityException("The activity given is null");
            }
        } else {
            throw new EmptyUserException("The user given is null");
        }

        return correctlyAdded;

    }

    /**
     * Method that removes an user to an activity
     *
     * @param user that wants to unsubscribe to an activity
     * @param activity where the user wants to unsubscribe
     * @return true if it's succesfully added to the activity.
     * @throws EmptyUserException in case that the new user is null.
     * @throws EmptyActivityException in case that the Activity given is null.
     * @throws ActivityNotFoundedException in case that the activity given
     * doesn't exist.
     * @throws UserNotFoundedException in case that the user doeesn't exist
     */
    public boolean removeUserFromActivity(User user, Activity activity) throws EmptyUserException, EmptyActivityException, ActivityNotFoundedException, UserNotFoundedException {
        boolean correctlyRemoved = false;
        if (user != null) {
            if (activity != null) {
                if (users.contains(user)) {
                    if (activities.contains(activity)) {
                        if (enrolements.containsKey(activity)) {
                            List<User> usersOfActivity = enrolements.get(activity);
                            if (usersOfActivity.contains(user)) {
                                correctlyRemoved = usersOfActivity.remove(user);//remove the user in the arrayList of user in the activity given
                                enrolements.replace(activity, users, usersOfActivity); //replace the arrayList of user for the new arrayist of users withouth the uuser given.
                            } else {
                                throw new UserNotFoundedException("The user given is not in this activity");
                            }
                        } else {
                            throw new ActivityNotFoundedException("The activity given is not in the map of enrolements");
                        }
                    } else {
                        throw new ActivityNotFoundedException("The activity doesn't exist in the arrayList of activities.");
                    }
                } else {
                    throw new UserNotFoundedException("The user doesn't exist in the arrayList of users");
                }
            } else {
                throw new EmptyActivityException("The activity given is null");
            }
        } else {
            throw new EmptyUserException("The user given is null");
        }
        return correctlyRemoved;
    }

    /**
     * Method that finds all users from an activity given
     *
     * @param activity where you want to search for users
     * @return an ArrayList of all users of the activity
     * @throws EmptyActivityException in case that the Activity given is null.
     * @throws ActivityNotFoundedException in case that the activity given
     * doesn't exist.
     * @throws EmptyArrayListException in case that the arrayList is empty.
     */
    public List<User> findUsersFromActivity(Activity activity) throws EmptyActivityException, ActivityNotFoundedException, EmptyArrayListException {
        List<User> listUsers = null;
        if (activity != null) {
            if (enrolements.containsKey(activity)) {
                listUsers = enrolements.get(activity);
                if (listUsers == null) {
                    throw new EmptyArrayListException("There's no users in this activity");
                }
            } else {
                throw new ActivityNotFoundedException("The activity doesn't exist");
            }
        } else {
            throw new EmptyActivityException("The activity given is null");
        }
        return listUsers;

    }

    /**
     * Method that finds all activities from a user given
     *
     * @param user given from whom you want to find his activities
     * @return an arrayList with all the activities of the user
     * @throws EmptyUserException in case that the user given is null.
     * @throws UserNotFoundedException in case that the user doeesn't exist.
     * @throws EmptyArrayListException in case that the arrayList is empty.
     */
    public List<Activity> findActivitiesFromUser(User user) throws EmptyUserException, UserNotFoundedException, EmptyArrayListException {
        List<Activity> activitiesOfUser = new ArrayList<>();
        if (user != null) {
            if (users.contains(user)) {
                for (Map.Entry<Activity, List<User>> entrada : enrolements.entrySet()) {
                    Activity key = entrada.getKey();
                    List<User> value = entrada.getValue();
                    if (value.contains(user)) {
                        activitiesOfUser.add(key);
                    }
                }
                if (activitiesOfUser != null) {
                    return activitiesOfUser;
                } else {
                    throw new EmptyArrayListException("The arrayList is null");
                }
            } else {
                throw new UserNotFoundedException("The user is not founded in the Gym");
            }
        } else {
            throw new EmptyUserException("The user given is null");
        }

    }

    /**
     * Method that finds all the activities with available places.
     *
     * @param available number of available places needed.
     * @return an ArrayList of Activities with the number of avaialable
     * activities given
     * @throws EmptyArrayListException if the ArrayList is empty.
     */
    public List<Activity> findAvailableActivities(int available) throws EmptyArrayListException {
        List<Activity> availableActivities = new ArrayList<>();
        for (Map.Entry<Activity, List<User>> entrada : enrolements.entrySet()) {
            Activity key = entrada.getKey();
            List<User> value = entrada.getValue();
            int placesFree = key.getCapacity() - value.size();
            if (placesFree >= available) {
                availableActivities.add(key);
            }
        }
        if (availableActivities != null) {
            return availableActivities;
        } else {
            throw new EmptyArrayListException("The arrayList is null");
        }
    }

    /**
     * Method that load data
     */
    public void loadTestData() {
        Activity a;
        a = new Activity("Cardio", "21:00", "Friday", 1, 24);
        Activity b = new Activity("Running", "10:00", "Monday", 2, 20);
        Activity c = new Activity("Zumba", "07:00", "Wednesday", 1, 34);
        activities.add(a);
        activities.add(b);
        activities.add(c);
        enrolements.putIfAbsent(a, users);
        ArrayList<User> usuaris = new ArrayList<>();

        User carlos, mar;
        carlos = new User("CR", "Carlos perez", "carlosperez@gmail.com");
        mar = new User("MR", "Mar Sanchez", "marsanchez@gmail.com");
        users.add(mar);
        users.add(carlos);
        users.add(new User("FR", "Freddy", "freddyachata@gmail.com"));
        usuaris.add(carlos);
        enrolements.putIfAbsent(b, users);
        enrolements.put(c, usuaris);
    }

    public boolean enrollUserToActivity(Activity activity, User user) throws FullActivityException {
        boolean result = false;
        List<User> enrolled = enrolements.get(activity);
        if (enrolled.size() < activity.getCapacity()) {
            result = enrolled.add(user);
        } else {
            throw new FullActivityException(String.format("Activitat plena"));
        }
        return result;
    }

    /**
     * Method that finds the index of an Activity
     *
     * @param name of the Activity
     * @return the position of the activity or -1 in case of error
     */
    private int findActivityIndex(String name) {
        int position = -1;
        Activity activity = new Activity(name);
        if (activities.contains(activity)) {
            position = activities.indexOf(activity);
        } else {
            position = -1;
        }
        return position;
    }

    /**
     * Method that finds the index of an user
     *
     * @param id of the user
     * @return the position of the user or -1 in case of error
     */
    private int findUserIndex(String id) {
        int position = -1;
        User user = new User(id);
        if (users.contains(user)) {
            position = users.indexOf(user);
        } else {
            position = -1;
        }
        return position;
    }

    /**
     * Method that searches an Activity by the name
     *
     * @param name of the activity
     * @return the activity searched by name or null in case of error
     * @throws ActivityNotFoundedException in case that the activity is not
     * founded
     */
    public Activity findActivityByName(String name) throws ActivityNotFoundedException {
        Activity found = null;
        int index = activities.indexOf(new Activity(name));
        if (index >= 0) {
            found = activities.get(index);
        } else {
            throw new ActivityNotFoundedException("The activity doesn't exist");
        }
        return found;
    }

    /**
     * Method that searches an user by the id
     *
     * @param id of the user
     * @return the user searched by id or null in case of error.
     * @throws UserNotFoundedException in case that the user is not founded
     */
    public User findUserById(String id) throws UserNotFoundedException {
        User found = null;
        int index = users.indexOf(new User(id));
        if (index >= 0) {
            found = users.get(index);
        } else {
            throw new UserNotFoundedException("The user doesn't exist");
        }
        return found;
    }

}
