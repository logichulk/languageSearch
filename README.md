README


A. Requirements

To run this application, you will need:

1. Java 8 or above
2. Maven 3 or above
3. Jetty or any other Web container like Apache Tomcat, JBoss Wildfly, etc. (I used jetty-distribution-9.4.5.v20170502).


B. Steps

1. Extract the source to a location. This project can be imported into Eclipse if needed.

2. For Compilation, executing Junit tests and WAR file creation, open the root folder of the project in terminal and type 'mvn clean install'. Due to race conditions in the setup method of the Test case, one of the tests might fail though it is still rare. If that happens, just run the command again and you should have a WAR build ready which should have a size of around 1.4 MB.

3. Copy the generated war file lying in ${project_root}/target and paste under the server's 'webapps' directory. Start the server if it is not running. It is to be noted here that most modern servers like jetty have hotspot deployment and do not need a restart as they are able to detect changes in war files and load them automatically. If you are using an older server which was already started, then in this case you may need to restart it or go to the admin panel and reload the application from there.

4. Navigate to wherever you have deployed the app and now you can start playing with it.


C. Salient features of this solution

1. It uses hashing and indexing algorithm so that once the relationships have been formed, the search takes minimal time. This is a much more efficient approach as compared to the basic approach of searching for the input term by traversing the data every time.
2. The latency (a sum of server latency + round-trip of network latency) is shown in seconds on the screen after every valid search.
3. The results are arranged by ascending order of names.
4. Thee solution follows Service-oriented architecture and exposes RESTful service to the client-side HTML.
5. The client-side HTML uses AngularJS, Bootstrap and CSS transitions apart from the normal javascript.
6. Clicking on the name in results takes one to the wikipedia page about the language for more information.


D. How this solution can be improved

1. I have accessed the fields using reflection rather than direct getters. This means that if tomorrow there are records with 4 instead of 3 properties (Unstructured data), we will be able to manage it.
2. I have worked with interface rather than working with the concrete class that stores the actual data. This means this same solution can be extended to any other kind of record type with just some minor tweaks.
3. By adding a few more indexes, we can add Autocomplete feature for upto around 5-10 options.
