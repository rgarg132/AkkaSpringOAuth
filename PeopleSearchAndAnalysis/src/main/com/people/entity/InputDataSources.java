package main.com.people.entity;

/**
 * This is the entity class for the input data sources
 * @author GUR36857
 *
 */
public class InputDataSources {
	
	private String dataSource;
	private long limit;
	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
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
		return "InputDataSources [dataSource=" + dataSource + ", limit=" + limit + "]";
	}
	
	
	
	

}
