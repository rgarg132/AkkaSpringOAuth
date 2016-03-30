package main.com.people.search.factory;


import org.springframework.context.ApplicationContext;

import akka.actor.Extension;
import akka.actor.Props;

/**
 * This is the Akka Extension implementation that will be used to create new
 *	actor by calling Spring actor producer factory
 * @author GUR36857
 *
 */

public class AkkaExtensionImpl implements Extension
{
	
	private ApplicationContext applicationContext  ;
	/**
	 * 
	 * @param applicationContext
	 */
	public void initialize(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
	
	/**
	 * 
	 * @param beanActorName
	 * @return {@link Props}
	 */
	public Props actorFactory(String beanActorName)
	{
		return Props.create(SpringActorProducerImpl.class , applicationContext , beanActorName); 
	}

}
