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

        RouteHelper.getDistanceAlongCertainRoute("ABD", matrix);

    }

    @Test
    public void testGetDistanceAlongCertainRoute() {
        int[][] matrix = loadTestMatrix();

        int distanceAlongCertainRoute = RouteHelper.getDistanceAlongCertainRoute("ABC", matrix);
        assertThat(distanceAlongCertainRoute, equalTo(9));

    }

    @Test
    public void testGetNumberOfRoutesBetweenTwoTowns() {
        int[][] matrix = loadTestMatrix();

        int numberOfRoutesBetweenTwoTowns = RouteHelper.getNumberOfRoutesBetweenTwoTownsWithinThreeStops('C', 'C', matrix);

        assertThat(numberOfRoutesBetweenTwoTowns, equalTo(2));


    }

    @Test
    public void testGetNumberOfRoutesBetweenTwoTownsLessThanDistance() {
        int[][] matrix = loadTestMatrix();

        int numberOfRoutesBetweenTwoTownsLessThanDistance = RouteHelper.getNumberOfRoutesBetweenTwoTownsLessThanDistance('C', 'C', matrix, 30);

        assertThat(numberOfRoutesBetweenTwoTownsLessThanDistance, equalTo(7));
    }

    @Test
    public void testGetTheShortestPathBetweenTwoRoutes() {
        int[][] matrix = loadTestMatrix();

        int theShortestPathBetweenTwoRoutes = RouteHelper.getTheShortestPathBetweenTwoRoutes('A', 'C', matrix);

        assertThat(theShortestPathBetweenTwoRoutes, equalTo(9));
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