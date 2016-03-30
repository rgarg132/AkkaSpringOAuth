package main.com.people.search.twitter.entity;

/**
 * 
 * @author gur36857
 *
 */
public class TweetsContainer {

	private String createdAt;
	private String id;
	//rivate String idStr;
	private String source;
	private String text;
	private String geo;
	private String lang;
	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the geo
	 */
	public String getGeo() {
		return geo;
	}
	/**
	 * @param geo the geo to set
	 */
	public void setGeo(String geo) {
		this.geo = geo;
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TweetsContainer [createdAt=" + createdAt + ", id=" + id + ", source=" + source + ", text=" + text
				+ ", geo=" + geo + ", lang=" + lang + "]";
	}
	
	
	
	
}
