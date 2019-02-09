import model.RouteNode;
import util.RouteHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String file = "graph.csv";
        List<RouteNode> routeNodes = loadData(file);

        String distanceAlongCertainRouteInput = "ABC";
        char firstRouteInput = 'C';
        char secondRouteInput = 'C';
        char firstShortestPathRouteInput = 'A';
        char secondShortestPathRouteInput = 'C';

        int[][] adjacencyMatrix = RouteHelper.generateAdjacencyMatrix(routeNodes);
        int distanceAlongCertainRoute = RouteHelper.getDistanceAlongCertainRoute(distanceAlongCertainRouteInput, adjacencyMatrix);
        int numberOfRoutesBetweenTwoTownsWithinThreeStops = RouteHelper.getNumberOfRoutesBetweenTwoTownsWithinThreeStops(firstRouteInput, secondRouteInput, adjacencyMatrix);
        int distance = 30;
        int numberOfRoutesBetweenTwoTownsLessThanDistance = RouteHelper.getNumberOfRoutesBetweenTwoTownsLessThanDistance(firstRouteInput, secondRouteInput, adjacencyMatrix, distance);
        int theShortestPathBetweenTwoRoutes = RouteHelper.getTheShortestPathBetweenTwoRoutes(firstShortestPathRouteInput, secondShortestPathRouteInput, adjacencyMatrix);


        System.out.println("The distance of route " + distanceAlongCertainRouteInput + " is " + distanceAlongCertainRoute);
        System.out.println("The number of routes between " + firstRouteInput + " and " + secondRouteInput + " is " + numberOfRoutesBetweenTwoTownsWithinThreeStops);
        System.out.println("The number of routes between " + firstRouteInput + " and " + secondRouteInput + " within " + distance + " is " + numberOfRoutesBetweenTwoTownsLessThanDistance);
        System.out.println("The shortest path between " + firstShortestPathRouteInput + " and " + secondShortestPathRouteInput + " is " + theShortestPathBetweenTwoRoutes);


    }

    private static List<RouteNode> loadData(String file) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        List<RouteNode> routeNodes = new ArrayList<RouteNode>();
        String regex = " ";

        try {

            br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(file)));
            while ((line = br.readLine()) != null) {
                String replace = line.replaceAll(regex, "");
                String[] nodes = replace.split(cvsSplitBy);
                for (String node : nodes) {
                    RouteNode routeNode = new RouteNode();
                    if (node.length() == 3) {
                        routeNode.setVertex(node.charAt(0));
                        routeNode.setEdge(node.charAt(1));
                        routeNode.setWeight(Character.getNumericValue(node.charAt(2)));
                        routeNodes.add(routeNode);
                    }

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return routeNodes;
    }


}

