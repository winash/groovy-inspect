Groovy console to manage and monitor your app

1 > Create the jar gradle jar

2> Set up dependencies

3> Attach jar as a Java agent {-javaagent:/<your directory>/groovy-inspect-SNAPSHOT.jar}

4> Annotate your classes with annotation.Managed @Managed(name = "test")

5> Start your app

5> telnet localhost 9095

6> inspect> inspect.list()





