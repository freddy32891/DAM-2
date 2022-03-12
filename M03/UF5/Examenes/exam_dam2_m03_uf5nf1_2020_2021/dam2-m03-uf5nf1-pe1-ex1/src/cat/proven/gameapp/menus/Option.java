package cat.proven.gameapp.menus;

/** Option.java
 * Class Option
 * Encapsulates one option for a generic menu
 * @author Jose
 * @version
 */
public class Option {
	/*=======Attributes=======*/
	private String description;
	/*=======Constructors=======*/
	/** Option()
	 * Option constructor
	 * @param String desc: description value
	 * @return nothing
	 */
	public Option(String desc) {
		description = desc;
	}
	/*=======Methods=======*/
	/** toString()
	 * converts option to string
	 * @param none
	 * @return String conversion
	 */
	public String toString() {
		return description;
	}	
}
