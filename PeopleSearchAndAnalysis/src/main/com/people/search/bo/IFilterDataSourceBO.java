package main.com.people.search.bo;

import java.util.List;
import java.util.Set;

import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;
import main.com.people.entity.ApplicableDataSources;
import main.com.people.entity.InputDataSources;




/**
 * This interface is business object for the actual data sources
 * 
 * @author gur36857
 * 
 */
public interface IFilterDataSourceBO {

	/**
	 * This method return the filtered data sources for actual execution
	 * @param availableSources
	 * @param inputSources
	 * @return {@link ApplicableDataSources}
	 */
	public List<ApplicableDataSources> filterDataSources(List<Source> availableSources,
			Set<InputDataSources> inputSources);
}
