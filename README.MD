#Ethic Assignment

1. You may run the application using Main.java which is loading sample values from graph.csv.
2. Unit tests are located at util.RouteHelperTest.java in order to test the functionality implemented in RouteHelper.java

Given the fact that there was a limited definition to the problem, I  had to to make some assumptions. Some of the assumptions include that the value for each node will be a character from 'A-Z'.
This assumption led me to use a adjacency matrix as a data structure to store a graph in memory because of the given limited capacity it will not take too much space in memory (26 X 26)

Since space was not going to really be a major issue I was able to utilize the O(1) time to determine if there is an edge at a give n vertex.

I did have to search for every vertex for an edge to find the number of paths for some of my algorithm implementation but given the possible values are from 'A-Z' it will not take too much time, 
if the value were a bigger range I would take a considerable amount of space and find the number of paths would be a bit slower.

If that be the case I would try to consider using a adjacency list to represent a graph in memory. 



