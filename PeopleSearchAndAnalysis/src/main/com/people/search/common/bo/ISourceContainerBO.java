package main.com.people.search.common.bo;

import java.util.List;
import java.util.Set;

import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;
import main.com.people.entity.InputDataSources;




/**
 * This class coantains the Input data sources
 * 
 * @author GUR36857
 * 
 */
public interface ISourceContainerBO {

	/**
	 * This method return the map of all the user selected data sources
	 * @return
	 */
	public Set<InputDataSources> inputDataSources();
	
	/**
	 * This method return all the available data sources
	 * @return 
	 */
	public List<Source> availableDataSources();

}
