# PeopleSearchProject
Note: I am using this project for my self-study only, nothing is related with any organization .This project is under development. (Working for twitter as of now). 

It's an Akka -Spring -Hadoop based OSINT (open source Intelligence) project .Where user can search the people over the social networking sites.
Both user input sources (InputSources.csv) and available/supported (dataSources.xml) sources are configurable.

Following are the key features of this project:

1. OAuth implementation (Twitter as of now).
2. Spring Dependency Injection (XML configuration).
3. Factory pattern creator for AKKA actors (IndirectActorProducer) and Akka Extension.
4. JAXB implementation to unmarshall the xsd for the available data sources to verify that input data source is supported.

Ongoing Implementations: 

1. Adding more resources.  
2. Spring MVC implementation to dynamically generate the list of available sources to select on GUI instead of inputsources.csv. 
3. Add Admin/User panel and store the user information using Hibernate (JPA). 
4. Save data in configurable Hadoop cluster. 
