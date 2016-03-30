package main.com.people.search.factory;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/**
 * This is the Dependency injector factory to get new actor 
 * @author GUR36857
 *
 */
public class SpringActorProducerImpl implements IndirectActorProducer{

	private final ApplicationContext applicationContext;
	private final String beanActorName;
	
	/**
	 * 
	 * @param applicationContext
	 * @param beanActorName
	 */
	public  SpringActorProducerImpl(ApplicationContext applicationContext , String beanActorName) {
		
		this.applicationContext = applicationContext;
		this.beanActorName = beanActorName;
	}
	
	
	@Override
	public Class<? extends Actor> actorClass() {
		// TODO Auto-generated method stub
		return (Class<? extends Actor>)applicationContext.getType(beanActorName);
	}

	@Override
	public Actor produce() {
		// TODO Auto-generated method stub
		return (Actor)applicationContext.getBean(beanActorName);
	}

}
