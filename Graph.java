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
	 * (Driver Method) This method must use the depth-first search algorithm
	 * presented in lecture to determine whether there is a path from the vertex
	 * with srcData to the vertex with dstData in the graph.
	 * 
	 * @param srcData - the beginning vertex to start at
	 * @param dstData - the vertex to reach
	 * @return true if the two vertexes are connected, false otherwise
	 * @throws IllegalArgumentException if there does not exist a vertex in the
	 *                                  graph with srcData or dstData
	 */
	public boolean areConnected(GraphType srcData, GraphType dstData) throws IllegalArgumentException {
		// srcData and dstData must both exist in the graph, otherwise throw exception
		if (!vertices.containsKey(srcData) || !vertices.containsKey(dstData))
			throw new IllegalArgumentException();

		for (Vertex<GraphType> vertex : vertices.values())
			vertex.setVisited(false);
		// the first element is the start, so it is already visited
		vertices.get(srcData).setVisited(true);

		this.areConnectedPriv(vertices.get(srcData));
		// if the above recursive call reaches srcData, its visited state
		// will be set to true, so the below return will return whether
		// or not the recursive call from src reached dst
		return (vertices.get(dstData).getVisited());
	}

	/**
	 * Recursive method to perform depth-first search to determine whether there is
	 * a path from the vertex with srcData to the vertex with dstData in the graph.
	 * 
	 * @param x,       the previous vertex
	 * @param dstData, the vertex to reach
	 * 
	 */
	private void areConnectedPriv(Vertex<GraphType> x) {
		// iterate over all the current vertex's edges
		Iterator<Edge<GraphType>> xEdgesIterator = x.edges();
		while (xEdgesIterator.hasNext()) {
			Edge<GraphType> e = xEdgesIterator.next();
			// store vertex w as a vertex x points to
			Vertex<GraphType> w = e.getOtherVertex();
			// if w has not been visited yet set it to visited, and pass it back to the
			// recursive method to check
			// all its edges
			if (w.getVisited() == false) {
				w.setVisited(true);
				areConnectedPriv(w);
			}
		}

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
		// srcData and dstData must both exist in the graph, otherwise throw exception
		if (!vertices.containsKey(srcData) || !vertices.containsKey(dstData))
			throw new IllegalArgumentException();

		for (Vertex<GraphType> vertex : vertices.values()) {
			vertex.setVisited(false);
			vertex.setHasPrevious(false);
		}

		Queue<Vertex<GraphType>> verticesToVisit = new LinkedList<Vertex<GraphType>>();

		// add the first vertex to the queue
		vertices.get(srcData).setVisited(true);
		verticesToVisit.offer(vertices.get(srcData));
		// while there are elements in the queue, check where they direct to
		while (verticesToVisit.size() > 0) {
			Vertex<GraphType> x = verticesToVisit.poll();
			// check each edge that the current vertex x has
			Iterator<Edge<GraphType>> xEdgesIterator = x.edges();
			while (xEdgesIterator.hasNext()) {
				Edge<GraphType> e = xEdgesIterator.next();
				// set w as the destination of an edge from x
				Vertex<GraphType> w = e.getOtherVertex();
				// if the destination vertex w has not yet been visited, we're on the fastest
				// path to visit it. So, set its visited status to true, and add x as the
				// previous
				// vertex to w, the fastest way to get to w.
				if (w.getVisited() == false) {
					w.setVisited(true);
					w.setPrevious(x);
					// once the final element is reached, there is no need to give previous vertices
					// to the rest of the graph,
					// as they will not be used in rebuilding the list
					if (w.getID().equals(dstData)) {
						verticesToVisit.clear();
						break;
					}
					verticesToVisit.offer(w);
				}

			}
		}

		// if the destination vertex's previous value is null, it was never set to
		// anything, therefore never reached.
		// in this case, throw exception
		if (!vertices.get(dstData).hasPrevious())
			throw new IllegalArgumentException();

		LinkedList<GraphType> path = new LinkedList<>();

		// add the end vertex to the queue
		verticesToVisit.offer(vertices.get(dstData));
		while (verticesToVisit.size() > 0) {
			Vertex<GraphType> x = verticesToVisit.poll();
			// adds current vertex ID to the result path
			path.addFirst(x.getID());
			// if the previous vertex is not null, add it to the queue
			if (x.hasPrevious())
				verticesToVisit.offer(x.getPrevious());
		}

		return path;
	}

	/**
	 * This method topographically sorts the graph.
	 * 
	 * @param <Type> The generic type for the ID of each vertex in the graph
	 * @return a new list with all IDs sorted in topographical sort order
	 * @throws IllegalArgumentException if the graph contains a cycle
	 */
	public List<GraphType> sort() throws IllegalArgumentException {
		for (Vertex<GraphType> vertex : vertices.values())
			vertex.setInDegree(0);

		// determine the inDegree for all vertices in the graph
		for (Vertex<GraphType> vertex : vertices.values()) {
			Iterator<Edge<GraphType>> edges = vertex.edges();

			while (edges.hasNext()) {
				Edge<GraphType> edge = edges.next();

				Vertex<GraphType> destination = edge.getOtherVertex();
				// for every edge that points to a vertex, that vertex's inDegree is incremented
				// by 1.
				destination.setInDegree(destination.getInDegree() + 1);
			}
		}

		Queue<Vertex<GraphType>> verticesToVisit = new LinkedList<Vertex<GraphType>>();

		// queues all vertices with 0 inDegree
		for (Vertex<GraphType> vertex : vertices.values())
			if (vertex.getInDegree() == 0)
				verticesToVisit.offer(vertex);

		Queue<GraphType> sortedQueue = new LinkedList<GraphType>();

		// continues until all elements' inDegrees have reached 0 and have been removed
		// from
		// the verticesToVisit queue
		while (verticesToVisit.size() != 0) {
			// stores the current vertex
			Vertex<GraphType> x = verticesToVisit.poll();
			// adds the current vertex to the sortedQueue, as if it's being reached, its
			// inDegree is 0,
			// because only elements with inDegree 0 get added to verticesToVisit
			sortedQueue.offer(x.getID());

			// for every edge from Vertex x
			Iterator<Edge<GraphType>> edges = x.edges();
			while (edges.hasNext()) {
				Edge<GraphType> edge = edges.next();

				// for all vertexes vertex x reaches via its edges, decrement their inDegree by
				// 1
				Vertex<GraphType> w = edge.getOtherVertex();
				w.setInDegree(w.getInDegree() - 1);
				// if a vertex was decremented to inDegree 0, add to the queue so it can be
				// checked for
				// its destinations and added to the sortedQueue
				if (w.getInDegree() == 0)
					verticesToVisit.offer(w);
			}
		}

		// if the sortedQueue is smaller than the amount of vertices, there was a cycle
		// somewhere preventing
		// all the inDegrees from reaching 0. Therefore, throw error as cycles are not
		// permitted
		if (sortedQueue.size() < vertices.size())
			throw new IllegalArgumentException();

		return new LinkedList<GraphType>(sortedQueue);
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
