package main.com.people.search.twitter.bo;

import main.com.people.search.twitter.entity.TimeLineTweetParameters;
import main.com.people.search.twitter.entity.TweetsDataContainer;

/**
 * This is a business object for Twitter Oauth calls
 * @author GUR36857
 *
 */
public interface TwitterOAuthCallsBO {

	/**
	 * 
	 * @return {@link String}
	 */
	public String gerBarerToken();
	
	/**
	 * 
	 * @param bearerToken
	 * @param timeLineTweetParameters
	 * @return TweetsDataContainer
	 */
	public TweetsDataContainer getTweetsTimeLine(String bearerToken,
			TimeLineTweetParameters timeLineTweetParameters);
	
}
