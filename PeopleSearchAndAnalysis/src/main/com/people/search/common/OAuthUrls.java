package main.com.people.search.common;

import main.com.people.search.twitter.entity.TimeLineTweetParameters;

/**
 * This class contains all the oAuth urls we can add the url is local db as well later
 * @author GUR36857
 * 
 */
public class OAuthUrls 
{

	/**
	 * 
	 * @return {@link String}
	 */
	public static String getTwitterBearerTokenUrl() {
		return "https://api.twitter.com/oauth2/token";
	}
	
	/**
	 * 
	 * @param timeLineParameters
	 * @return {@link String}
	 */
	public static String getTwitterTimelineUrl(
			TimeLineTweetParameters timeLineParameters) {

		String timeLineUrl = null;

		if (timeLineParameters != null) {

			if (timeLineParameters.isUserId()) {
				timeLineUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json?user_id="
						+ timeLineParameters.getUserId() + "&";
			} else {
				timeLineUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="
						+ timeLineParameters.getScreenName() + "&";
			}
			if (timeLineParameters.isCount()) {
				timeLineUrl += "count=" + timeLineParameters.getCount() + "&";
			}
			if (timeLineParameters.isMaxId()) {
				timeLineUrl += "max_id=" + timeLineParameters.getMaxId() + "&";

			}
			if (timeLineParameters.isSinceId()) {
				timeLineUrl += "since_id=" + timeLineParameters.getSinceId()
						+ "&";
			}
			timeLineUrl = timeLineUrl.substring(0, (timeLineUrl.length() - 1));
		}

		System.out.println("timeLineUrl: " + timeLineUrl);
		return timeLineUrl;
	}
}