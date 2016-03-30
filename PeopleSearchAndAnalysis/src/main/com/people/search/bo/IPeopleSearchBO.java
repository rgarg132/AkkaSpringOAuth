package main.com.people.search.bo;

/**
 * This is the main business class to start people search
 * @author GUR36857
 *
 */
public interface IPeopleSearchBO {
	
	/**
	 * This method begin the search by filtering the data sources on the inputed text 
	 * @param inputText
	 */
	public void beginSearch(String inputText);

}
