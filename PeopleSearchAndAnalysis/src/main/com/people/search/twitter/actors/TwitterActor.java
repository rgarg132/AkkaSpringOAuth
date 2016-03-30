package main.com.people.search.twitter.actors;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import main.com.people.search.actors.ListenerActor;
import main.com.people.search.actors.ProcessInputMessage;
import main.com.people.search.twitter.bo.TwitterOAuthCallsBO;
import main.com.people.search.twitter.entity.TimeLineTweetParameters;
import main.com.people.search.twitter.entity.TweetsDataContainer;

/**
 * This is the twitter actor class
 * @author GUR36857
 *
 */
public class TwitterActor extends UntypedActor{

	private String input ;
	private long noOfTweets ;
	private ActorRef listener;
	
	private String barerToken ;
	
	private TwitterOAuthCallsBO twitterOAuthCallsBO ;
	private TimeLineTweetParameters timeLineTweetParameters;
	private TweetsDataContainer tweetsDataContainer;
	

	
	
	public TwitterOAuthCallsBO getTwitterOAuthCallsBO() {
		return twitterOAuthCallsBO;
	}

	public void setTwitterOAuthCallsBO(TwitterOAuthCallsBO twitterOAuthCallsBO) {
		this.twitterOAuthCallsBO = twitterOAuthCallsBO;
	}
	

	public void preStart()
	{
		System.out.println(" twitter prestart called");

		//twitterOAuthCallsBO = new TwitterOAuthCallsBOImpl();
		barerToken = twitterOAuthCallsBO.gerBarerToken();
		System.out.println("Barer token is : "+barerToken);

		//need some macahanism here to take input
	}
	
	/**
	 * 
	 * @param sources
	 * @param input
	 *//*
	public  TwitterActor(ApplicableDataSources sources , String input) //here need to remove input
	{
		System.out.println("Twitter Actor intilized ...");		
		this.input = input;
	}*/
	
	@Override
	public void onReceive(Object message) throws Exception {

		if(message instanceof ProcessInputMessage)
		{
			
			System.out.println("Twitter actor started working ...");
			ProcessInputMessage processInputMessage = (ProcessInputMessage)message;
			this.input = processInputMessage.getInput();
			this.noOfTweets = processInputMessage.getCount();
			//this.listener = processInputMessage.getListener();
			
			timeLineTweetParameters = new TimeLineTweetParameters();
			timeLineTweetParameters.setScreenName(this.input);
			timeLineTweetParameters.setCount(true);
			timeLineTweetParameters.setCount(this.noOfTweets);
			tweetsDataContainer = twitterOAuthCallsBO.getTweetsTimeLine(barerToken, timeLineTweetParameters);
			
			
			//for now sending close message to listener
			if(tweetsDataContainer!=null)
			{
				ListenerActor.Result result = new ListenerActor.Result();
				result.setSourceName(getSelf().path().name());
				
				//this.listener.tell(result, getSelf());
				getSender().tell(result, getSelf());
				//this.getContext().watch(getSender());
			}
			//System.out.println(tweetsDataContainer);
			
			//System.out.println(getContext().);
		}
		else if(message instanceof Terminated)
		{
			System.out.println("parent terminated ...");
		}
		else
		{
			unhandled(message);
		}

	}

	public void postStop()
	{
		System.out.println("Twitter actor stoppped successfully ...");
	}
}
