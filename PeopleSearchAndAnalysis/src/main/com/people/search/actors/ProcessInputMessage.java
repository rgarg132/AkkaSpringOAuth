package main.com.people.search.actors;

import akka.actor.ActorRef;

/**
 * This is common message for all the data sources 
 * @author GUR36857
 *
 */
public class ProcessInputMessage {
	
	private String input;
	private long count;
	//private ActorRef listener;

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/*public ActorRef getListener() {
		return listener;
	}

	public void setListener(ActorRef listener) {
		this.listener = listener;
	}*/
	
	

}
