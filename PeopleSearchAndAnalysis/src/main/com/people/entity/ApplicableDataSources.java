package main.com.people.entity;

/**
 * This entity contains the actual data sources that need to execute and its parameters 
 * @author gur36857
 *
 */
public class ApplicableDataSources {
	
	private String sourceName;
	private String sourceUrl;
	private String maxLimit;
	private String OauthUrl;
	private long limit;
	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}
	/**
	 * @param sourceUrl the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	/**
	 * @return the maxLimit
	 */
	public String getMaxLimit() {
		return maxLimit;
	}
	/**
	 * @param maxLimit the maxLimit to set
	 */
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}
	/**
	 * @return the oauthUrl
	 */
	public String getOauthUrl() {
		return OauthUrl;
	}
	/**
	 * @param oauthUrl the oauthUrl to set
	 */
	public void setOauthUrl(String oauthUrl) {
		OauthUrl = oauthUrl;
	}	
	
	
	/**
	 * @return the limit
	 */
	public long getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(long limit) {
		this.limit = limit;
	}
		
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicableDataSources [sourceName=" + sourceName + ", sourceUrl=" + sourceUrl + ", maxLimit=" + maxLimit
				+ ", OauthUrl=" + OauthUrl + ", limit=" + limit + "]";
	}
	
	

}
