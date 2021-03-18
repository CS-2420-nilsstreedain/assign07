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

	// indegree of the vertex
	private int inDegree;
	
	// whether or not the vertex has been visited
	private boolean visited;
	
	// whether or not the vertex has a previous vertex
	private boolean hasPrevious;

	// the vertex's previous vertex
	private Vertex<T> previous;

	/**
	 * Creates a new Vertex object, using the given ID.
	 * 
	 * @param ID - string used to IDentify this Vertex
	 */
	public Vertex(T ID) {
		this.ID = ID;
		this.adj = new LinkedList<Edge<T>>();
		visited = false;
		hasPrevious = false;
		inDegree = 0;
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
	 * 
	 *  @return A String representation of this vertex
	 */
	public String toString() {
		StringBuilder result = new StringBuilder("Vertex " + ID + " adjacent to verticies");
		Iterator<Edge<T>> itr = adj.iterator();
		while (itr.hasNext())
			result.append(" " + itr.next());
		return result.toString();
	}

	/**
	 * Returns the inDegree of this vertex
	 * 
	 * @return inDegree
	 */
	public int getInDegree() {
		return inDegree;
	}

	/**
	 * Sets the inDegree of this vertex
	 * 
	 * @param inDegree
	 */
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	
	/**
	 * Returns whether or not this vertex has been visited
	 * 
	 * @return visited
	 */
	public boolean getVisited() {
		return visited;
	}

	/**
	 * Sets visited for this vertex
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Returns whether or not this vertex has a previous vertex
	 * 
	 * @return hasPrevious
	 */
	public boolean hasPrevious() {
		return hasPrevious;
	}

	/**
	 * Sets hasPrevioud for this vertex
	 * 
	 * @param hasPrevious
	 */
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	
	/**
	 * Returns the vertex leading to this vertex
	 * 
	 * @return previous
	 */
	public Vertex<T> getPrevious() {
		return previous;
	}

	/**
	 * Sets the previous vertex of this vertex
	 * 
	 * @param previous
	 */
	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
		hasPrevious = true;
	}
}
