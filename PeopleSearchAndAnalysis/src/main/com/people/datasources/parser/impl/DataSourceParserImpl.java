package main.com.people.datasources.parser.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import main.com.people.datasources.parser.IDataSourceParser;
import main.com.people.datasources.parser.stub.objects.DataSources;
import main.com.people.datasources.parser.stub.objects.DataSources.Sources;

/**
 * This is the implementation class of IDataSourceParser
 * 
 * @author GUR36857
 * 
 */
public class DataSourceParserImpl implements IDataSourceParser {

	private String xmlFilePath = null;

	public DataSourceParserImpl(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	@Override
	public Sources parseSources() {

		Sources sources = null;
		if (xmlFilePath != null) {
			// System.out.println("parsing available data sources ...");
			JAXBContext context = null;
			Unmarshaller unmarshaller = null;
			try {
				context = JAXBContext.newInstance(DataSources.class);
				unmarshaller = context.createUnmarshaller();
			} catch (JAXBException e) {

				System.out.println("Unable to create jaxb context ..."
						+ e.getMessage());
			}
			try {
				DataSources dataSources = (DataSources) unmarshaller
						.unmarshal(new File(xmlFilePath));
				sources = dataSources.getSources().get(0);
			} catch (JAXBException jexExp) {
				System.out.println("unable to unmarshell .."
						+ jexExp.getMessage());
			}

		}
		return sources;
	}

}
