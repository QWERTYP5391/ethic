import model.RouteNode;
import util.RouteHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        String line;
        String cvsSplitBy = ",";
        List<RouteNode> routeNodes = new ArrayList<RouteNode>();
        String regex = " ";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(file)))) {
            while ((line = br.readLine()) != null) {
                String[] nodes = line.replaceAll(regex, "").split(cvsSplitBy);
                Arrays.stream(nodes).filter(node -> node.length() == 3).
                        forEach(node -> routeNodes.add(new RouteNode(node.charAt(0), node.charAt(1), Character.getNumericValue(node.charAt(2)))));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routeNodes;
    }


}

