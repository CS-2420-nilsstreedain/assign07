package assign07;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is not generic and assumes that a string name is stored at each vertex.
 * 
 * @author Erin Parker, Paul Nuffer & Nils Streedain
 * @version March 10, 2021
 */
public class Graph<GraphType> {

	// the graph -- a set of vertices (GraphType mapped to Vertex instance)
	private HashMap<GraphType, Vertex<GraphType>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	private Graph() {
		vertices = new HashMap<GraphType, Vertex<GraphType>>();
	}
	
	/**
	 * Adds to the graph a directed edge from the vertex with ID "ID1" 
	 * to the vertex with ID "ID2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param ID1 - string ID for source vertex
	 * @param ID2 - string ID for destination vertex
	 */
	public void addEdge(GraphType ID1, GraphType ID2) {
		Vertex<GraphType> vertex1;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(ID1))
			vertex1 = vertices.get(ID1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<GraphType>(ID1);
			vertices.put(ID1, vertex1);
		}

		Vertex<GraphType> vertex2;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(ID2))
			vertex2 = vertices.get(ID2);
		// else, create a new object and add to graph
		else {
			vertex2 = new Vertex<GraphType>(ID2);
			vertices.put(ID2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
	}
	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for(Vertex<GraphType> v : vertices.values()) {
			// for every edge
			Iterator<Edge<GraphType>> edges = v.edges();
			while (edges.hasNext())
				dot.append("\t" + v.getID() + " -> " + edges.next() + "\n");
		}
		
		return dot.toString() + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for(Vertex<GraphType> v : vertices.values())
			result.append(v + "\n");
		
		return result.toString();
	}
}
