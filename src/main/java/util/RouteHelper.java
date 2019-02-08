package util;

import model.RouteNode;
import model.WeightedNode;

import java.util.*;

public class RouteHelper {

    private static final int CHARACTER_SIZE = 26;
    private static final int CHARACTER_OFFSET = 65;

    public static int[][] generateAdjacencyMatrix(List<RouteNode> routes) {
        int[][] adjacencyMatrix = new int[CHARACTER_SIZE][CHARACTER_SIZE];
        for (RouteNode routeNode : routes) {
            adjacencyMatrix[getIndex(routeNode.getVertex())][getIndex(routeNode.getEdge())] = routeNode.getWeight();
        }

        return adjacencyMatrix;
    }


    public static int getDistanceAlongCertainRoute(String route, int[][] adjacencyMatrix) {
        int distance = 0;

        for (int i = 0; i < route.length() - 1; i++) {
            char current = route.charAt(i);
            char next = route.charAt(i + 1);
            int weight = adjacencyMatrix[getIndex(current)][getIndex(next)];
            if (weight == 0) {
                throw new RuntimeException("There is no such route");
            }
            distance += weight;
        }

        return distance;
    }

    public static int getNumberOfRoutesBetweenTwoTowns(Character town1, Character town2, int[][] adjacencyMatrix, int level, int distance) {
        int ways = 0;
        int secondTownIndex = getIndex(town2);

        Queue<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();


        queue.add(String.valueOf(town1));

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {

                String val = queue.remove();
                visited.remove(val);
                int distanceAlongCertainRoute = getDistanceAlongCertainRoute(val, adjacencyMatrix);
                if (distanceAlongCertainRoute < distance) {
                    for (int j = 0; j < CHARACTER_SIZE; j++) {
                        int weight = adjacencyMatrix[(val.charAt(val.length() - 1)) - CHARACTER_OFFSET][j];
                        String route = val + getCharacter(j);
                        if (weight != 0 && !visited.contains(route) && route.length() <= level + 1 && getDistanceAlongCertainRoute(route, adjacencyMatrix) < distance) {
                            queue.add(route);
                            visited.add(route);
                            if (j == secondTownIndex) {
                                ways++;
                            }
                        }
                    }
                }
            }

        }

        return ways;

    }

    public static int getNumberOfRoutesBetweenTwoTownsWithinThreeStops(Character town1, Character town2, int[][] adjacencyMatrix) {
        return getNumberOfRoutesBetweenTwoTowns(town1, town2, adjacencyMatrix, 3, Integer.MAX_VALUE);

    }

    public static int getNumberOfRoutesBetweenTwoTownsLessThanDistance(Character town1, Character town2, int[][] adjacencyMatrix, int distance) {

        return getNumberOfRoutesBetweenTwoTowns(town1, town2, adjacencyMatrix, Integer.MAX_VALUE - 1, distance);

    }

    public static int getTheShortestPathBetweenTwoRoutes(Character town1, Character town2, int[][] adjacencyMatrix) {

        int secondTownIndex = getIndex(town2);

        PriorityQueue<WeightedNode> queue = new PriorityQueue<WeightedNode>();
        Set<String> visited = new HashSet<String>();

        queue.add(new WeightedNode(String.valueOf(town1), 0));

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                WeightedNode weightedVal = queue.remove();
                String val = weightedVal.getValue();
                visited.remove(val);
                for (int j = 0; j < CHARACTER_SIZE; j++) {
                    int weight = adjacencyMatrix[(val.charAt(val.length() - 1)) - CHARACTER_OFFSET][j];
                    String route = val + getCharacter(j);
                    if (weight != 0 && !visited.contains(route)) {
                        int distanceAlongCertainRoute = getDistanceAlongCertainRoute(route, adjacencyMatrix);
                        queue.add(new WeightedNode(route, distanceAlongCertainRoute));
                        visited.add(route);
                        if (j == secondTownIndex) {
                            return distanceAlongCertainRoute;
                        }
                    }
                }
            }


        }


        return 0;

    }

    public static int getIndex(Character vertex) {
        return vertex - CHARACTER_OFFSET;
    }

    public static char getCharacter(int index) {
        return (char) (index + CHARACTER_OFFSET);
    }

//    public static int getNumberOfRoutesBetweenTwoTowns(Character town1, Character town2, int[][] adjacencyMatrix) {
//        int ways = 0;
//        int firstTownIndex = getIndex(town1);
//        int secondTownIndex = getIndex(town2);
//
//        Queue<String> queue = new LinkedList<String>();
//        Set<String> visited = new HashSet<String>();
//
//
//        queue.add(String.valueOf(town1));
//
//        while (!queue.isEmpty()) {
//            for (int i = 0; i < queue.size(); i++) {
//
//                String val = queue.remove();
//                visited.remove(val);
//                for (int j = 0; j < CHARACTER_SIZE; j++) {
//                    int weight = adjacencyMatrix[(val.charAt(val.length() - 1)) - CHARACTER_OFFSET][j];
//                    String route = val + getCharacter(j);
//                    if (weight != 0 && !visited.contains(j) && route.length() <= 4) {
//                        queue.add(route);
//                        visited.add(route);
//                        if (j == secondTownIndex) {
//                            ways++;
//                        }
//                    }
//                }
//            }
//
///       }
//
//        return ways;
//
//    }


//    public static int getNumberOfRoutesBetweenTwoTownsLessThanDistance(Character town1, Character town2, int[][] adjacencyMatrix, int distance) {
//
//        int ways = 0;
//        int secondTownIndex = getIndex(town2);
//
//        Queue<String> queue = new LinkedList<String>();
//        Set<String> visited = new HashSet<String>();
//
//
//        queue.add(String.valueOf(town1));
//
//        while (!queue.isEmpty()) {
//            for (int i = 0; i < queue.size(); i++) {
//                String val = queue.remove();
//                visited.remove(val);
//                int distanceAlongCertainRoute = getDistanceAlongCertainRoute(val, adjacencyMatrix);
//                if (distanceAlongCertainRoute < distance) {
//                    for (int j = 0; j < CHARACTER_SIZE; j++) {
//                        int weight = adjacencyMatrix[(val.charAt(val.length() - 1)) - CHARACTER_OFFSET][j];
//                        String route = val + getCharacter(j);
//                        if (weight != 0 && !visited.contains(route) && getDistanceAlongCertainRoute(route, adjacencyMatrix) < distance) {
//                            queue.add(route);
//                            visited.add(route);
//                            if (j == secondTownIndex) {
//                                ways++;
//                            }
//                        }
//                    }
//                }
//
//
//            }
//
//        }
//
//        return ways;
//
//    }



//    public static int getTheShortestPathBetweenTwoRoutes(Character town1, Character town2, int[][] adjacencyMatrix) {
//
//        int secondTownIndex = getIndex(town2);
//
//        Queue<String> queue = new LinkedList<String>();
//        Set<String> visited = new HashSet<String>();
//
//        queue.add(String.valueOf(town1));
//
//        while (!queue.isEmpty()) {
//            for (int i = 0; i < queue.size(); i++) {
//                String val = queue.remove();
//                visited.remove(val);
//                for (int j = 0; j < CHARACTER_SIZE; j++) {
//                    int weight = adjacencyMatrix[(val.charAt(val.length() - 1)) - CHARACTER_OFFSET][j];
//                    String route = val + getCharacter(j);
//                    if (weight != 0 && !visited.contains(route)) {
//                        queue.add(route);
//                        visited.add(route);
//                        if (j == secondTownIndex) {
//                            return getDistanceAlongCertainRoute(route, adjacencyMatrix);
//                        }
//                    }
//                }
//            }
//
//
//        }
//
//
//        return 0;
//    }


    }
