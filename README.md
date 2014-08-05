APIVersioningClassDesign
========================

A sample project that explains how chain of responsibility can be used to handle API versions (requests and responses) in place of the conventional multi-level heirarchy class design.

Original Blog: TBD

How to deploy and run
=====================
Simply clone and run the following commands on the directory where pom.xml is present.

1. mvn clean install

2. mvn exec:java

To test, you can run the following from console:

curl -X GET -H "Content-Type: application/xml" http://localhost:8081/users/v1.0.0/userid/100

or

curl -X GET -H "Content-Type: application/xml" http://localhost:8081/users/v2.0.0/userid/100

Dependencies
============
You will need java, Grizzly and Mongo instance. Also, you need to create a table called user in a DB called exampleDB and insert rows (run the below in the mongo console):

use exampleDB

db.user.insert({"id":"100", "name":"Ram", "country":"IN"})

