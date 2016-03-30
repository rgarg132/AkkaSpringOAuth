package main.com.people.search.actors;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import main.com.people.entity.ApplicableDataSources;
import main.com.people.search.twitter.actors.TwitterActor;

/**
 * 
 * @author GUR36857
 *
 */
public class SearchSystemActor {
	
	
	private int noOfSources = 0;
	private static ActorRef listenerActor = null;
	
	/**
	 * 
	 * @param applicableSources
	 * @param input
	 */
	public static void startActors(List<ApplicableDataSources> applicableSources , String input) // here need to remove input
	{
		System.out.println("SearchSystemActor::startActors --->In");
		if(!applicableSources.isEmpty())
		{
			ActorSystem systemActor = ActorSystem.create("systemActor");
			int sourcesCount = applicableSources.size();
			// need to implement factory design pattern here for creating actors object
			
			//creating listener actor 
			listenerActor  = systemActor.actorOf(Props.create(ListenerActor.class , sourcesCount),"listenerActor");
			
			ActorRef childActor = null;
			for(ApplicableDataSources sources : applicableSources)
			{
				// need to implement factory design pattern here for creating actors object
				
				childActor = systemActor.actorOf(Props.create(TwitterActor.class , sources , input),"TwitterActor");
				childActor.tell(new ProcessInputMessage(), ActorRef.noSender());
				
			}
			
		}
		System.out.println("SearchSystemActor::startActors --->Out");
	}

}
