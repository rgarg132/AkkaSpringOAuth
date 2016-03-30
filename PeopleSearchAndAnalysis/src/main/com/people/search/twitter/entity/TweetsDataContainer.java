package main.com.people.search.twitter.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gur36857
 *
 */
public class TweetsDataContainer {

	private List<TweetsContainer> tweetsContainer;
	private BigInteger maxId;
	private BigInteger minId;
	private String userId;
	/**
	 * @return the tweetsContainer
	 */
	public List<TweetsContainer> getTweetsContainer() {
		return tweetsContainer;
	}
	/**
	 * @param tweetsContainer the tweetsContainer to set
	 */
	public void setTweetsContainer(List<TweetsContainer> tweetsContainer) {
		this.tweetsContainer = tweetsContainer;
	}
	/**
	 * @return the maxId
	 */
	public BigInteger getMaxId() {
		return maxId;
	}
	/**
	 * @param maxId the maxId to set
	 */
	public void setMaxId(BigInteger maxId) {
		this.maxId = maxId;
	}
	/**
	 * @return the minId
	 */
	public BigInteger getMinId() {
		return minId;
	}
	/**
	 * @param minId the minId to set
	 */
	public void setMinId(BigInteger minId) {
		this.minId = minId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TweetsDataContainer [tweetsContainer=" + tweetsContainer + ", maxId=" + maxId + ", minId=" + minId
				+ ", userId=" + userId + "]";
	}






}
