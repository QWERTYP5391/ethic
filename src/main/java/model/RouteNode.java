package model;

public class RouteNode {

    private Character vertex;
    private Character edge;
    private Integer weight;

    public RouteNode(){

    }

    public RouteNode(Character vertex, Character edge, Integer weight) {
        this.vertex = vertex;
        this.edge = edge;
        this.weight = weight;
    }

    public Character getVertex() {
        return vertex;
    }

    public void setVertex(Character vertex) {
        this.vertex = vertex;
    }

    public Character getEdge() {
        return edge;
    }

    public void setEdge(Character edge) {
        this.edge = edge;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
