package main.com.people.search.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.com.people.search.bo.IPeopleSearchBO;
import main.com.people.search.bo.impl.PeopleSearchBOImpl;
import main.com.people.search.common.OAuthConstants;
import main.com.people.search.factory.AkkaExtensionImpl;



/**
 * 
 * @author GUR36857
 * 
 */
public class InvokePeopleSearch {

	public static void main(String[] args) {
		//System.out.println("People search process started ...");
		String inputValue = userInput();
		/*System.out.println("Start searching for " + inputValue
				+ " over the given source web ...");*/
		
		System.setProperty(OAuthConstants.HTTP_PROXY_HOST,OAuthConstants.PROXY_HOST);
		System.setProperty(OAuthConstants.HTTP_PROXY_PORT, OAuthConstants.PROXY_PORT);
		
		System.setProperty(OAuthConstants.HTTPS_PROXY_HOST,OAuthConstants.PROXY_HOST);
		System.setProperty(OAuthConstants.HTTPS_PROXY_PORT, OAuthConstants.PROXY_PORT);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		
		//Initializing akka extension with the application context object , because Extension is One per actor System.
		//so we should change it in a way to create only when creating actor system 
		AkkaExtensionImpl akkaExtension = (AkkaExtensionImpl)context.getBean("akkaExtension");
		akkaExtension.initialize(context);
		
		IPeopleSearchBO peopleSearchBO = (PeopleSearchBOImpl) context.getBean("peopleSearchBO");
		peopleSearchBO.beginSearch(inputValue);
		
	}

	/**
	 * This mehtod take the input from the user to start the process
	 * 
	 * @return {@link String}
	 */
	private static String userInput() {
		String input = null;
		System.out.print("Please enter name/object to serach --> ");
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			input = inputReader.readLine();
		} catch (IOException ioExp) {
			System.out.println("IO Execption while reading input "
					+ ioExp.getMessage());
		} catch (Exception exp) {

			System.out.println("Execption while reading input "
					+ exp.getMessage());
		}

		if (input.equals("")) {
			userInput();
		}
		return input;

	}

}
