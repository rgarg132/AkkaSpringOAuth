package main.com.people.datasources.parser;

import main.com.people.datasources.parser.stub.objects.DataSources.Sources;
import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;

/**
 * This file unmarshe the data sources from the xml
 * @author GUR36857
 *
 */
public interface IDataSourceParser {
	
	/**
	 * 
	 * @return {@link Sources}
	 */
	public Sources parseSources();

}
