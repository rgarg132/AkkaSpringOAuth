package main.com.people.search.actors;

import akka.actor.UntypedActor;
/**
 * This is the listener class of akka actors
 * @author GUR36857
 *
 */
public class ListenerActor extends UntypedActor  {

	int count = 0;
	int noOfSources = 0;
	
	public void preStart()
	{
		System.out.println("listener pre start called ....");
	}
	
	/*public ListenerActor(int noOfSources)
	{
		System.out.println("listener actor initlized ...");
		this.noOfSources = noOfSources;
	}*/
	
	public static class NoOfSources
	{
		private int noOfSources;

		public int getNoOfSources() {
			return noOfSources;
		}

		public void setNoOfSources(int noOfSources) {
			this.noOfSources = noOfSources;
		}
		
	}
	
	public static class Result
	{
			
		private String sourceName;
		public String getSourceName() {
			return sourceName;
		}

		public void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		
		
	}
	
	@Override
	public void onReceive(Object message) throws Exception {
		// TODO Auto-generated method stub
		if(message instanceof NoOfSources)
		{
			noOfSources = ((NoOfSources)message).getNoOfSources();
		}
		
		else if(message instanceof Result)
		{
			
			count++;
			Result result = (Result)message;
			System.out.println("Processing complete for "+result.getSourceName());
			if(count==noOfSources)
			{
				System.out.println("All process complete ...");				
				getContext().stop(getSender());
				getContext().stop(getSelf());
			}
			
		}
		else
		{
			unhandled(message);
		}
	}
	
	public void postStop()
	{
		System.out.println("listener actor stopped successfully ...");
		System.out.println("shutting down the system ...");
		getContext().system().terminate();
	}

}
