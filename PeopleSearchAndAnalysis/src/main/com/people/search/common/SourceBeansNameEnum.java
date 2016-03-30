package main.com.people.search.common;

/**
 * This is the enumerator for all the bean of data source actors
 * @author GUR36857
 *
 */
public enum SourceBeansNameEnum {

	
	TWITTER("twitter","twitterActor") , FACEBOOK ("facebook","facebookActor") , LISTENER("listener","listenerActor");
	
	private final String source;
	private final String beanName;
	
	
	/**
	 * 
	 * @param source
	 * @param beanName
	 */
	private SourceBeansNameEnum(String source , String beanName)
	{
		this.source = source;
		this.beanName = beanName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSource()
	{
		return this.source;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBeanName()
	{
		return this.beanName;
	}
	
	/**
	 * 
	 * @param source
	 * @return {@link String}
	 */
	public static String getBeanName(String source)
	{
		String beanName = null;
		for(SourceBeansNameEnum enums : SourceBeansNameEnum.values())
		{
			if(enums.getSource().equalsIgnoreCase(source))
			{
				return enums.getBeanName();
			}
		}
		return beanName;
	}
}
