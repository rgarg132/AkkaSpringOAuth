package main.com.people.search.twitter.entity;

/**
 * 
 * @author gur36857
 * 
 */
public class TimeLineTweetParameters {

	private boolean isScreenName;
	private String screenName;
	private boolean isUserId;
	private String userId;
	private boolean isCount;
	private long count;
	private boolean isMaxId;
	private String maxId;
	private boolean isSinceId;
	private String sinceId;
	/**
	 * @return the isScreenName
	 */
	public boolean isScreenName() {
		return isScreenName;
	}
	/**
	 * @param isScreenName the isScreenName to set
	 */
	public void setScreenName(boolean isScreenName) {
		this.isScreenName = isScreenName;
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * @return the isUserId
	 */
	public boolean isUserId() {
		return isUserId;
	}
	/**
	 * @param isUserId the isUserId to set
	 */
	public void setUserId(boolean isUserId) {
		this.isUserId = isUserId;
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
	/**
	 * @return the isCount
	 */
	public boolean isCount() {
		return isCount;
	}
	/**
	 * @param isCount the isCount to set
	 */
	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}
	
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
	/**
	 * @return the isMaxId
	 */
	public boolean isMaxId() {
		return isMaxId;
	}
	/**
	 * @param isMaxId the isMaxId to set
	 */
	public void setMaxId(boolean isMaxId) {
		this.isMaxId = isMaxId;
	}
	/**
	 * @return the maxId
	 */
	public String getMaxId() {
		return maxId;
	}
	/**
	 * @param maxId the maxId to set
	 */
	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}
	/**
	 * @return the isSinceId
	 */
	public boolean isSinceId() {
		return isSinceId;
	}
	/**
	 * @param isSinceId the isSinceId to set
	 */
	public void setSinceId(boolean isSinceId) {
		this.isSinceId = isSinceId;
	}
	/**
	 * @return the sinceId
	 */
	public String getSinceId() {
		return sinceId;
	}
	/**
	 * @param sinceId the sinceId to set
	 */
	public void setSinceId(String sinceId) {
		this.sinceId = sinceId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TimeLineTweetParameters [isScreenName=" + isScreenName + ", screenName=" + screenName + ", isUserId="
				+ isUserId + ", userId=" + userId + ", isCount=" + isCount + ", count=" + count + ", isMaxId=" + isMaxId
				+ ", maxId=" + maxId + ", isSinceId=" + isSinceId + ", sinceId=" + sinceId + "]";
	}
	
	
	

	
}
