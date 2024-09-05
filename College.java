package application;

import java.util.LinkedList;



public class College implements Comparable<College> {

    float x ;
    float y;
    String name ;
    LinkedList<Edge> edges = new LinkedList<>();
    private double maxInt = Double.MAX_VALUE;
    private College prev;


    public College(String name,float x, float y) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public void addAdjacentCollage(College c, double distance) {//add adjacent city to linked list
        edges.add(new Edge(c, distance)); //GRAPH
    }
    
//	public void setc() {
//		
//		for (College col : Collages) {
//			if (col.name.equals(source.getName())) {
//				col.setCost(0);
//			} else
//				col.setToMaxInt();
//			PQ.add(col);
//		}
//
//	}

    public void setToMaxInt() {
        maxInt = Double.MAX_VALUE;
        prev = null;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public College getPrev() {
        return prev;
    }

    public double getCost() {
        return maxInt;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    /*public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }*/

    public void setPrev(College prev) {
        this.prev = prev;
    }

    public void setCost(double cost) {
        this.maxInt = cost;
    }
   /* public LinkedList<Edge> getAdjacentsColl() {
        return edges;
    }*/

    @Override
    public int compareTo(College o) {
        if (this.maxInt > o.maxInt)
            return 1;
        else if (this.maxInt < o.maxInt)
            return -1;
        return 0;
    }
}
