package application;


public class Edge {
	College adj ;
    double distance;

    public Edge(College adj, double distance) {
        this.adj = adj;
        this.distance = distance;
    }

    public College getAdj() {
        return adj;
    }

    public double getDistance() {
        return distance;
    }
	

}
