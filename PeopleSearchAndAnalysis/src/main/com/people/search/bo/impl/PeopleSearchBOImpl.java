package main.com.people.search.bo.impl;

import java.util.List;
import java.util.Set;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;
import main.com.people.entity.ApplicableDataSources;
import main.com.people.entity.InputDataSources;
import main.com.people.search.actors.ListenerActor;
import main.com.people.search.actors.ProcessInputMessage;
import main.com.people.search.actors.SearchSystemActor;
import main.com.people.search.bo.IFilterDataSourceBO;
import main.com.people.search.bo.IPeopleSearchBO;
import main.com.people.search.common.SourceBeansNameEnum;
import main.com.people.search.common.bo.ISourceContainerBO;
import main.com.people.search.factory.AkkaExtensionImpl;
import main.com.people.search.twitter.actors.TwitterActor;



/**
 * This is the implementation class of IPeopleSearchBO
 * @author GUR36857
 *
 */
public class PeopleSearchBOImpl implements IPeopleSearchBO{

	private ISourceContainerBO sourceContainerBO ;
	private IFilterDataSourceBO filterDataSourceBO;
	private AkkaExtensionImpl akkaExtension;



	public ISourceContainerBO getSourceContainerBO() {
		return sourceContainerBO;
	}




	public void setSourceContainerBO(ISourceContainerBO sourceContainerBO) {
		this.sourceContainerBO = sourceContainerBO;
	}




	public IFilterDataSourceBO getFilterDataSourceBO() {
		return filterDataSourceBO;
	}




	public void setFilterDataSourceBO(IFilterDataSourceBO filterDataSourceBO) {
		this.filterDataSourceBO = filterDataSourceBO;
	}




	public AkkaExtensionImpl getAkkaExtension() {
		return akkaExtension;
	}




	public void setAkkaExtension(AkkaExtensionImpl akkaExtension) {
		this.akkaExtension = akkaExtension;
	}




	@Override
	public void beginSearch(String inputText) {

		System.out.println("PeopleSearchBOImpl::beginSearch -->In");

		//sourceContainerBO = new SourceContainerBOImpl();
		//filterDataSourceBO = new FilterDataSourceBOImpl();

		Set<InputDataSources> inputDataSources = sourceContainerBO.inputDataSources();
		if(!inputDataSources.isEmpty())
		{
			List<Source> availableDataSources = sourceContainerBO.availableDataSources();
			if(!availableDataSources.isEmpty())
			{
				List <ApplicableDataSources> applicableDataSources = filterDataSourceBO.filterDataSources(availableDataSources, inputDataSources);
				if(!applicableDataSources.isEmpty())
				{
					//calling system actor to start actor engine
					//SearchSystemActor.startActors(applicableDataSources, inputText); //commented as add Spring D.I factory

					startActors(applicableDataSources, inputText);

					/*for(ApplicableDataSources dataSources:applicableDataSources)
					{
						System.out.println(dataSources);
					}*/
				}
				else
				{
					System.out.println("No data sources match found...");
				}
			}
			else
			{
				System.out.println("No available data sources found ...");
			}
		}
		else
		{
			System.out.println("No input data sources found ...");
		}
		System.out.println("PeopleSearchBOImpl::beginSearch -->Out");
	}


	/**
	 * 
	 * @param applicableDataSources
	 * @param inputText
	 */
	public void startActors(List<ApplicableDataSources> applicableDataSources, String inputText)
	{

		//int noOfSources = 0;
		ActorRef listenerActor = null;
		System.out.println("PeopleSearchBOImpl::startActors --->In");
		if(!applicableDataSources.isEmpty())
		{
			ActorSystem systemActor = ActorSystem.create("systemActor");
			int sourcesCount = applicableDataSources.size();			

			//creating listener actor -- need to create a bean for that as well later on
			//listenerActor  = systemActor.actorOf(Props.create(ListenerActor.class , sourcesCount),"listenerActor");
			
			//calling via factory
			String listenerBeanName =  SourceBeansNameEnum.getBeanName(SourceBeansNameEnum.LISTENER.getSource());
			System.out.println(listenerBeanName);
			listenerActor = systemActor.actorOf(akkaExtension.actorFactory(listenerBeanName),listenerBeanName);
			
			ListenerActor.NoOfSources noOfSources =  new ListenerActor.NoOfSources();
			noOfSources.setNoOfSources(sourcesCount);
			
			listenerActor.tell(noOfSources, ActorRef.noSender());
			
			ActorRef childActor = null;
			
			ProcessInputMessage processInputMessage = null;
			
			for(ApplicableDataSources sources : applicableDataSources)
			{
				
				processInputMessage = 	new ProcessInputMessage();
				processInputMessage.setInput(inputText);
				processInputMessage.setCount(sources.getLimit());
				//processInputMessage.setListener(listenerActor);
				
				// without factory

				//childActor = systemActor.actorOf(Props.create(TwitterActor.class , sources , inputText),"TwitterActor");
				//childActor.tell(new ProcessInputMessage(), ActorRef.noSender());

				//calling via factory
				String beanName = SourceBeansNameEnum.getBeanName(sources.getSourceName());
				System.out.println("bean name : "+beanName);

				ActorRef childActor1 = systemActor.actorOf(akkaExtension.actorFactory(beanName), beanName);
				childActor1.tell(processInputMessage, listenerActor);

			}

		}
		System.out.println("PeopleSearchBOImpl::startActors --->Out");
	}
}
