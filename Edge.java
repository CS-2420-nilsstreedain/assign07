package assign07;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker, Paul Nuffer & Nils Streedain
 * @version March 10, 2021
 */
public class Edge<VertexType> {
	
	// destination of this directed edge
	private Vertex<VertexType> dst;
	
	/** Creates an Edge object, given the Vertex that is the destination.
	 *  (The Vertex that stores this Edge object is the source.)
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex<VertexType> dst) {
		this.dst = dst;
	}
	
	/**
	 * @return the destination Vertex of this Edge
	 */
	public Vertex<VertexType> getOterVertex() {
		return this.dst;
	}
	
	/**
	 * Returns the ID of the destination Vertex as a textual representation of this Edge.
	 */
	public String toString() {
		return this.dst.getID().toString();
	}

}
