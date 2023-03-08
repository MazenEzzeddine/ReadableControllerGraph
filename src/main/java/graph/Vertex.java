package graph;

public class Vertex {
    int label;
    String topic;
    String consumerGroupName;
    boolean isVisited;

    Vertex(int label, String t, String cgn) {
        this.label = label;
        isVisited = false;
        topic = t;
        consumerGroupName =cgn;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                ", topic='" + topic + '\'' +
                ", consumerGroupName='" + consumerGroupName + '\'' +
                '}' + "\n";
    }
}
