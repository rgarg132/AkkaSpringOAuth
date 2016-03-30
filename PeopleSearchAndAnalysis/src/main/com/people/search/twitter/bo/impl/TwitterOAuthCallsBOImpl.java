package main.com.people.search.twitter.bo.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import main.com.people.search.common.OAuthConstants;
import main.com.people.search.common.OAuthUrls;
import main.com.people.search.twitter.bo.TwitterOAuthCallsBO;
import main.com.people.search.twitter.entity.TimeLineTweetParameters;
import main.com.people.search.twitter.entity.TweetsContainer;
import main.com.people.search.twitter.entity.TweetsDataContainer;
/**
 * 
 * @author GUR36857
 *
 */
public class TwitterOAuthCallsBOImpl implements TwitterOAuthCallsBO {

	private String AUTHORIZAION = "Authorization";
	private String BEARER = "Bearer ";
	private String base64Key  = "";
	
	
	@Override
	public String gerBarerToken() {
		System.out.println("TwitterOAuthCallsBOImpl::getBearerToken --> In");
		String accessToken = null;
		StringBuffer buffer = new StringBuffer();
		base64Key = DatatypeConverter
				.printBase64Binary((OAuthConstants.TWITTER_CONSUMER_KEY + ":" + OAuthConstants.TWITTER_SECRET_KEY)
						.getBytes());
		
		//System.out.println("base64Key: "+base64Key);

		URL twitterBearerTokenUrl;
		try {
			twitterBearerTokenUrl = new URL(
					OAuthUrls.getTwitterBearerTokenUrl());

		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Invalid Request  " + e);
			throw new RuntimeException("Invalid Request  " + e);
		}

		HttpURLConnection urlConnection;
		try {
			
			urlConnection = (HttpURLConnection) twitterBearerTokenUrl
					.openConnection();

			urlConnection.setRequestMethod("POST");
			urlConnection
					.setRequestProperty(AUTHORIZAION, "Basic " + base64Key);
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset="
							+ OAuthConstants.UTF_CHARSET);

			urlConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(
					urlConnection.getOutputStream());
			wr.writeBytes("grant_type=client_credentials");
			wr.flush();
			wr.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			String read;
			while ((read = reader.readLine()) != null) {
				buffer.append(read);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to connect with Twitter " + e);
			throw new RuntimeException("Unable to connect with Twitter " + e);
		}
		accessToken = buffer.toString();
		String[] tokens = accessToken.split(",");
		for (String token : tokens) {
			String[] parts = token.split(":");
			if (parts.length != 2) {
				throw new RuntimeException("Unexpected auth response");
			} else {
				if (parts[0].equals("\"access_token\"")) {
					parts[1] = parts[1].replace("\"", "");
					parts[1] = parts[1].replace("}", "");
					accessToken = parts[1];
					break;
				}
			}
		}
		//System.out.println("accessToken: "+accessToken);
		System.out.println("TwitterOAuthCallsBOImpl::getBearerToken --> Out");
		return accessToken;
	}


	@Override
	public TweetsDataContainer getTweetsTimeLine(String bearerToken,
			TimeLineTweetParameters timeLineTweetParameters) {
		System.out.println("TwitterOAuthCallsBOImpl::getTweetsTimeLine -->In");
		TweetsDataContainer tweetsDataContainer = null;
		String tweetTimeLineUrl = null;
		HttpURLConnection timeLineHttpConnection = null;
		StringBuffer buffer = new StringBuffer();
		if (bearerToken != null) {
			tweetTimeLineUrl = OAuthUrls
					.getTwitterTimelineUrl(timeLineTweetParameters);
			System.out.println(tweetTimeLineUrl);
			timeLineHttpConnection = getGETRequestURL(bearerToken,
					tweetTimeLineUrl);
			BufferedReader reader;
			try {
				reader = new BufferedReader(new InputStreamReader(
						timeLineHttpConnection.getInputStream()));
				String read;
				while ((read = reader.readLine()) != null) {
					buffer.append(read);
				}
				// System.out.println(buffer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ObjectMapper mapper = null;
			try {
				mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(buffer.toString());
				// System.out.println("root node .. " + rootNode);
				JsonNode userNode = rootNode.get(0).path("user");
				// System.out.println("userNode"+userNode..get("id").toString());
				Iterator<JsonNode> iterator = rootNode.iterator();
				JsonNode node = null;
				tweetsDataContainer = new TweetsDataContainer();
				List<TweetsContainer> tweetsList = new ArrayList<TweetsContainer>();
				TweetsContainer tweetsContainer = null;
				List<BigInteger> idList = new ArrayList<BigInteger>();
				while (iterator.hasNext()) {
					node = iterator.next();
					tweetsContainer = new TweetsContainer();
					tweetsContainer.setId(node.get("id").toString());
					tweetsContainer.setText(node.get("text").toString());
					 System.out.println(tweetsContainer.getText());
					tweetsContainer.setSource(node.get("source").toString());
					tweetsContainer.setCreatedAt(node.get("created_at")
							.toString());
					tweetsContainer.setGeo(node.get("geo").toString());
					tweetsContainer.setLang(node.get("lang").toString());
					tweetsList.add(tweetsContainer);
					idList.add(new BigInteger(node.get("id").toString()));

				}
				Collections.sort(idList);
				// System.out.println("idList : "+idList);
				tweetsDataContainer.setMaxId(idList.get(0));
				// System.out.println("userNode.get().toString() "+userNode.get("id").toString());
				if (userNode.get("id").toString() != null) {
					tweetsDataContainer
							.setUserId(userNode.get("id").toString());
				}
				tweetsDataContainer.setTweetsContainer(tweetsList);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		System.out.println("TwitterOAuthCallsBOImpl::getTweetsTimeLine -->Out");
		return tweetsDataContainer;
	}

	/**
	 * 
	 * @param accessToken
	 * @param url
	 * @return HttpURLConnection
	 */
	public HttpURLConnection getGETRequestURL(String accessToken, String url) {
		HttpURLConnection getUrlConnection = null;
		URL requestUrl = null;
		try {
			requestUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid Request  " + e);
		}
		try {
			getUrlConnection = (HttpURLConnection) requestUrl.openConnection();

			getUrlConnection.setRequestMethod("GET");
			getUrlConnection.setRequestProperty(AUTHORIZAION, BEARER
					+ accessToken);
			getUrlConnection.setDoOutput(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to request the process ");
		}
		return getUrlConnection;
	}
}
