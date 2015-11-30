## Overview

A collection of all the data store related utility classes that help the web service implementations perform `CRUD` operations on the
data store. 

Each web service has an associated data store utility class typically of the name `{WebServiceFunctionality}DataSource.java` that's usually
hidden to the web service. The web service implementations are encouraged to instead use the `DataSources.java`, a convenience static utility method
twin of such data source classes, to obtain the right implementation of the data source class and perform `CRUD` operations.

The underlying database is the [`Google Datastore`](https://cloud.google.com/datastore/), so this module uses the `Datastore` API in combination with 
[`Objectify`](https://github.com/objectify/objectify) for simplicity and easy of use.

Read the package description for more info [here](apidocs/index.html).