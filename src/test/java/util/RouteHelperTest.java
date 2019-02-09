package util;

import model.RouteNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class RouteHelperTest {


    @Test
    public void testGenerateAdjacencyMatrix() {
        int[][] matrix = loadTestMatrix();

        assertThat(matrix[RouteHelper.getIndex('A')][RouteHelper.getIndex('B')], equalTo(5));
        assertThat(matrix[RouteHelper.getIndex('B')][RouteHelper.getIndex('C')], equalTo(4));
        assertThat(matrix[RouteHelper.getIndex('C')][RouteHelper.getIndex('D')], equalTo(8));
        assertThat(matrix[RouteHelper.getIndex('D')][RouteHelper.getIndex('E')], equalTo(6));
        assertThat(matrix[RouteHelper.getIndex('A')][RouteHelper.getIndex('D')], equalTo(5));
        assertThat(matrix[RouteHelper.getIndex('C')][RouteHelper.getIndex('E')], equalTo(2));
        assertThat(matrix[RouteHelper.getIndex('E')][RouteHelper.getIndex('B')], equalTo(3));
        assertThat(matrix[RouteHelper.getIndex('A')][RouteHelper.getIndex('E')], equalTo(7));
    }


    @Test(expected = RuntimeException.class)
    public void testGetDistanceAlongCertainRouteWithNoSuchRoute() {
        int[][] matrix = loadTestMatrix();

        String route = "ABD";
        RouteHelper.getDistanceAlongCertainRoute(route, matrix);

    }

    @Test
    public void testGetDistanceAlongCertainRoute() {
        int[][] matrix = loadTestMatrix();

        String route = "ABC";
        int distanceAlongCertainRoute = RouteHelper.getDistanceAlongCertainRoute(route, matrix);
        int expected = 9;

        assertThat(distanceAlongCertainRoute, equalTo(expected));

    }

    @Test
    public void testGetNumberOfRoutesBetweenTwoTowns() {
        int[][] matrix = loadTestMatrix();

        char firstTown = 'C';
        char secondTown = 'C';
        int numberOfRoutesBetweenTwoTowns = RouteHelper.getNumberOfRoutesBetweenTwoTownsWithinThreeStops(firstTown, secondTown, matrix);
        int expected = 2;

        assertThat(numberOfRoutesBetweenTwoTowns, equalTo(expected));


    }

    @Test
    public void testGetNumberOfRoutesBetweenTwoTownsLessThanDistance() {
        int[][] matrix = loadTestMatrix();

        char firstTown = 'C';
        char secondTown = 'C';
        int distance = 30;
        int numberOfRoutesBetweenTwoTownsLessThanDistance = RouteHelper.getNumberOfRoutesBetweenTwoTownsLessThanDistance(firstTown, secondTown, matrix, distance);

        assertThat(numberOfRoutesBetweenTwoTownsLessThanDistance, equalTo(7));
    }

    @Test
    public void testGetTheShortestPathBetweenTwoRoutes() {
        int[][] matrix = loadTestMatrix();

        char firstTown = 'A';
        char secondTown = 'C';
        int theShortestPathBetweenTwoRoutes = RouteHelper.getTheShortestPathBetweenTwoRoutes(firstTown, secondTown, matrix);
        int expected = 9;

        assertThat(theShortestPathBetweenTwoRoutes, equalTo(expected));
    }

    @Test
    public void testGetTheShortestPathBetweenTwoRoutesWithNoSuchRoute() {
        int[][] matrix = loadTestMatrix();

        char firstTown = 'A';
        char secondTown = 'Z';
        int theShortestPathBetweenTwoRoutes = RouteHelper.getTheShortestPathBetweenTwoRoutes(firstTown, secondTown, matrix);
        int expected = 0;

        assertThat(theShortestPathBetweenTwoRoutes, equalTo(expected));
    }

    private int[][] loadTestMatrix() {
        List<RouteNode> list = new ArrayList<RouteNode>();

        list.add(new RouteNode('A', 'B', 5));
        list.add(new RouteNode('B', 'C', 4));
        list.add(new RouteNode('C', 'D', 8));
        list.add(new RouteNode('D', 'C', 8));
        list.add(new RouteNode('D', 'E', 6));
        list.add(new RouteNode('A', 'D', 5));
        list.add(new RouteNode('C', 'E', 2));
        list.add(new RouteNode('E', 'B', 3));
        list.add(new RouteNode('A', 'E', 7));

        return RouteHelper.generateAdjacencyMatrix(list);
    }
}