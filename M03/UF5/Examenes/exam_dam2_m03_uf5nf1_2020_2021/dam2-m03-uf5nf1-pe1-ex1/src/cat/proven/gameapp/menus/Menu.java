package cat.proven.gameapp.menus;

/** Menu.java
 * Class Menu
 * Encapsulates the creation and use of a generic menu
 * @author Jose
 * @version
 */
import java.util.*;

public class Menu {
	/*======Attributes======*/
	List<Option> options;
	String title;	
	/* Constructor */
	/** Menu()
	 * Menu constructor
	 * @param none
	 * @return nothing
	 */
	public Menu(String title) {
		this.title = title;
		this.options = new ArrayList<Option>();
	}
	/*======Accessors======*/
	/** getTitle()
	 * gets the menu title
	 * @param none
	 * @return the title
	 */
	 public String getTitle() {
		 return title;
	}
	/** get()
	 * gets the option with the specified index
	 * @param int index: the index of the option to get
	 * @return the option is the specified position of the list
	 */
	 public Option get(int index) {
		 return options.get(index);
	}	
	/*======Methods=======*/
	/** add()
	 * adds an option to the list
	 * @param Option option: the option to be added
	 * @return nothing
	 */
	 public void add(Option option) {
		 options.add(option);
	}
	/** remove()
	 * removes an option from the list
	 * @param Option option: the option to be removed
	 * @return true if option has been found, false otherwise
	 */
	 public boolean remove(Option option) {
		 return options.remove(option);
	}
	/** remove()
	 * removes an option (given its position) from the list
	 * @param int index: the position of the option in the list
	 * @return the option deleted
	 */
	 public Option remove(int index) {
		 return options.remove(index);
	}	
	/** toString()
	 * converts the menu to string
	 * @param none
	 * @return String conversion
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		/*
		for (Option o: options) {
			sb.append(o.toString());
		}
		*/
		sb.append("============"+getTitle()+"============");
		ListIterator<Option> it = options.listIterator();
		while (it.hasNext()) {
			sb.append( it.next().toString() );
		}
		/*
		for (ListIterator<Option> it = options.listIterator();it.hasNext();) {
			sb.append( it.next().toString() );
		}
		* */
		return sb.toString();
	}	
	/** show()
	 * shows the menu
	 * @param none
	 * @return nothing
	 */
	public void show() {
		System.out.format( "============%s============\n", title );
		ListIterator<Option> it = options.listIterator();
		int idOption = 0;
		while (it.hasNext()) {
			System.out.format( "[%d] %s\n", idOption, it.next().toString() );
			idOption++;
		}
	}
	/** choose()
	 * gets input from user to select an option
	 * if error, it returns de default value -1
	 * @param none
	 * @return a value identifying the selected option
	 */
	public int choose() {
		System.out.print("Choose an option: ");
		Scanner sc = new Scanner(System.in);
		int opt=-1;
		try {
			opt = sc.nextInt();
			if ((opt<0) || (opt>=options.size())) {
				opt = -1;
			}
		} catch (InputMismatchException ime) {
			opt = -1;
		}
		return opt;
	}	
}
