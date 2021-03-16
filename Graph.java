package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is not generic and assumes that a string name is stored
 * at each vertex.
 *
 * @author Erin Parker, Paul Nuffer & Nils Streedain
 * @version March 10, 2021
 */
public class Graph<GraphType> {

	// the graph -- a set of vertices (GraphType mapped to Vertex instance)
	private HashMap<GraphType, Vertex<GraphType>> vertices;

	/**
	 * This method must use the depth-first search algorithm presented in lecture to
	 * determine whether there is a path from the vertex with srcData to the vertex
	 * with dstData in the graph.
	 * 
	 * @param srcData - the beginning vertex to start at
	 * @param dstData - the vertex to reach
	 * @return true if the two vertexes are connected, false otherwise
	 * @throws IllegalArgumentException if there does not exist a vertex in the
	 *                                  graph with srcData or dstData
	 */
	public boolean areConnected(GraphType srcData, GraphType dstData) {
		if (vertices.get(srcData).equals(null) || vertices.get(dstData).equals(null))
			throw new IllegalArgumentException();
		
		for (Vertex<GraphType> vertex : vertices.values())
			vertex.setDistance(-1);
		
		vertices.get(srcData).setDistance(0);
		
		return this.areConnectedPriv(vertices.get(srcData), dstData);
	}
	
	private boolean areConnectedPriv(Vertex<GraphType> x, GraphType dstData) {
		while (x.edges().hasNext()) {
			Edge<GraphType> e = x.edges().next();
			Vertex<GraphType> w = e.getOtherVertex();
			if (w.getDistance() == -1) {
				if (w.getID().equals(dstData))
					return true;
				w.setDistance(1);
				areConnectedPriv(w, dstData);
			}
		}
		return false;
	}

	/**
	 * This method must use the breadth-first search algorithm presented in lecture
	 * to find a shortest path from the vertex with srcData to the vertex with
	 * dstData in the graph.
	 * 
	 * @param <GraphType> The generic type for the ID of each vertex in the graph
	 * @param srcData     - the beginning vertex to start at
	 * @param dstData     - the vertex to find the shortest path to
	 * @return an ordered list containing the shortest path from srcData to dstData
	 * @throws IllegalArgumentException if there does not exist a vertex in the
	 *                                  graph with srcData, and likewise for
	 *                                  dstData. Also, throws an
	 *                                  IllegalArgumentException if there does not
	 *                                  exist a path between the two vertices.
	 */
	public List<GraphType> shortestPath(GraphType srcData, GraphType dstData) throws IllegalArgumentException {

		if (vertices.get(srcData).equals(null) || vertices.get(dstData).equals(null)) 
			throw new IllegalArgumentException();
		
		for (Vertex<GraphType> vertex : vertices.values())
			vertex.setDistance(-1);

		Queue<Vertex<GraphType>> verticesToVisit = new LinkedList<Vertex<GraphType>>();

		verticesToVisit.offer(vertices.get(srcData));
		while (verticesToVisit.size() > 0) {
			Vertex<GraphType> x = verticesToVisit.poll();
			while (x.edges().hasNext()) {
				Edge<GraphType> e = x.edges().next();
				Vertex<GraphType> w = e.getOtherVertex();
				if (w.getDistance() == -1) {
					w.setDistance(x.getDistance() + 1);
					w.setPrevious(x);
					verticesToVisit.offer(w);
				}
			}
		}

		if (vertices.get(dstData).getPrevious().equals(null)) {
			throw new IllegalArgumentException();
		}

		LinkedList<GraphType> path = new LinkedList<>();

		verticesToVisit.offer(vertices.get(dstData));
		while (verticesToVisit.size() > 0) {
			Vertex<GraphType> x = verticesToVisit.poll();

			path.addFirst(x.getID());
			if (!x.getPrevious().equals(null))
				verticesToVisit.offer(x.getPrevious());
		}

		return path;
	}

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<GraphType, Vertex<GraphType>>();

	}

	/**
	 * Adds to the graph a directed edge from the vertex with ID "ID1" to the vertex
	 * with ID "ID2". (If either vertex does not already exist in the graph, it is
	 * added.)
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
	 * Generates the DOT encoding of this graph as string, which can be pasted into
	 * http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");

		// for every vertex
		for (Vertex<GraphType> v : vertices.values()) {
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

		for (Vertex<GraphType> v : vertices.values())
			result.append(v + "\n");

		return result.toString();
	}
}
