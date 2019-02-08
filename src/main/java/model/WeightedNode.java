package model;

public class WeightedNode implements Comparable<WeightedNode> {

    private String value;
    private Integer weight;

    public WeightedNode(String value, Integer weight) {
        this.value = value;
        this.weight = weight;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public int compareTo(WeightedNode o) {
        int compare = this.weight.compareTo(o.weight);

        if (compare == 0) {
            return this.value.compareTo(o.value);
        } else {
            return compare;
        }
    }
    }
