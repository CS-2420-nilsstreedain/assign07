package assign07;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a vertex in a directed graph. The vertex is generic.
 * 
 * @author Erin Parker, Paul Nuffer & Nils Streedain
 * @version March 10, 2021
 */
public class Vertex<T> {

	// used to ID the Vertex
	private T ID;

	// adjacency list
	private LinkedList<Edge<T>> adj;
	
	private int distanceFromStart;
	
	private Vertex<T> previous;

	/**
	 * Creates a new Vertex object, using the given ID.
	 * 
	 * @param ID - string used to IDentify this Vertex
	 */
	public Vertex(T ID) {
		this.ID = ID;
		this.adj = new LinkedList<Edge<T>>();
		distanceFromStart = -1;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public T getID() {
		return ID;
	}
	
	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<T> otherVertex) {
		adj.add(new Edge<T>(otherVertex));
	}
	
	/**
	 * @return an iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Edge<T>> edges() {
		return adj.iterator();
	}
	
	/**
	 * Generates and returns a textual representation of this Vertex
	 */
	public String toString() {
		String s = "Vertex " + ID + " adjacent to verticies ";
		Iterator<Edge<T>> itr = adj.iterator();
		while (itr.hasNext())
			s += itr.next() + " ";
		return s;
	}
	
	public int getDistance() {
		return distanceFromStart;
	}

	public void setDistance(int distance) {
		distanceFromStart = distance;
	}
	
	public Vertex<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
	}
}
