package main.com.people.search.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;
import main.com.people.entity.ApplicableDataSources;
import main.com.people.entity.InputDataSources;
import main.com.people.search.bo.IFilterDataSourceBO;




/**
 * This is the implmentation class of IFilterDataSourceBO
 * 
 * @author gur36857
 * 
 */
public class FilterDataSourceBOImpl implements IFilterDataSourceBO {

	@Override
	public List<ApplicableDataSources> filterDataSources(
			List<Source> availableSources, Set<InputDataSources> inputSources) {
		System.out
				.println("Filtering actual data sources for further execution...");
		List<ApplicableDataSources> applicableDataSourcesList = null;

		if (!availableSources.isEmpty()) {
			ApplicableDataSources applicableDataSources = null;
			applicableDataSourcesList = new ArrayList<ApplicableDataSources>();
			for (Source source : availableSources) {

				for (InputDataSources inputDataSources : inputSources) {
					if (source.getName().equalsIgnoreCase(
							inputDataSources.getDataSource())) {
						applicableDataSources = new ApplicableDataSources();
						applicableDataSources.setSourceName(source.getName());
						applicableDataSources.setSourceUrl(source.getUrl());
						applicableDataSources.setMaxLimit(source
								.getMaxlimit());
						applicableDataSources.setLimit(inputDataSources.getLimit());
						applicableDataSourcesList.add(applicableDataSources);
						break;
					}
				}
			}

		}
		return applicableDataSourcesList;
	}

}
