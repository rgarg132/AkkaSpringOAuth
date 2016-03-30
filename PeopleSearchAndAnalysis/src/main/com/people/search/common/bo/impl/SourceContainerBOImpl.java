package main.com.people.search.common.bo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.com.people.datasources.parser.IDataSourceParser;
import main.com.people.datasources.parser.stub.objects.DataSources.Sources;
import main.com.people.datasources.parser.stub.objects.DataSources.Sources.Source;
import main.com.people.entity.InputDataSources;
import main.com.people.search.common.bo.ISourceContainerBO;

/**
 * This is implementation class of ISourceContainerBO
 * 
 * @author GUR36857
 * 
 */
public class SourceContainerBOImpl implements ISourceContainerBO {

	private String COLUMN_SEPERATOR = ",";
	private IDataSourceParser dataSourceParserBO = null;
	private String inputSource = null;

	public SourceContainerBOImpl(String inputSource) {
		this.inputSource = inputSource;
	}

	public IDataSourceParser getDataSourceParserBO() {
		return dataSourceParserBO;
	}

	public void setDataSourceParserBO(IDataSourceParser dataSourceParserBO) {
		this.dataSourceParserBO = dataSourceParserBO;
	}

	@Override
	public Set<InputDataSources> inputDataSources() {

		System.out.println("Reading data sources file to start process ...");
		Set<InputDataSources> dataSources = null;
		File file = null;
		try {
			// file = new File("resources/InputSources.csv");// here need to add
			// some file loader
			// class

			file = new File(inputSource);
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				System.out.println("Retrieving user data sources ...");
				dataSources = new HashSet<InputDataSources>();

				String line = null;
				String[] lineArray = null;
				InputDataSources inputDataSources = null;
				int count =0;
				while ((line = reader.readLine()) != null) {
					if(count>0)
					{
						lineArray = line.split(COLUMN_SEPERATOR);
						inputDataSources = new InputDataSources();
						inputDataSources.setDataSource(lineArray[0]);
						System.out.println("count ... "+lineArray[1]);
						inputDataSources.setLimit(Long.parseLong(lineArray[1]));
						dataSources.add(inputDataSources);
					}
					count ++;
				}

			}
		} catch (FileNotFoundException fileExp) {
			System.out.println("unable to read data sources file "
					+ fileExp.getMessage());
		} catch (IOException ioExp) {
			System.out.println("unable to read data sources file "
					+ ioExp.getMessage());
		}

		return dataSources;
	}

	@Override
	public List<Source> availableDataSources() {
		System.out.println("Reading available data sources ...");
		List<Source> listSource = null;
		// dataSourceParserImpl = new DataSourceParserImpl();

		// String xmlFilePath = "resources/DataSources.xml";// here need to add
		// some
		// file loader class
		Sources sources = null;
		try {
			// sources = iDataSourceParser.parseSources(xmlFilePath);
			sources = dataSourceParserBO.parseSources();
			listSource = sources.getSource();
		} catch (Exception e) {
			System.out
			.println("error while retriving available data sources ..."
					+ e.getMessage());
		}

		return listSource;
	}

}
