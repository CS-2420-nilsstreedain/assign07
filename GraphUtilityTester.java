package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphUtilityTester {
	
	Vertex<Integer> vertex1;
	Vertex<Integer> vertex2;
	Vertex<Integer> vertex3;

	Graph<Integer> emptyGraph;
	Graph<Integer> notConnectedGraph;
	
	Graph<Integer> chainGraph;
	Graph<Integer> cycleGraph;
	Graph<Integer> treeGraph;
	Graph<Integer> complexCyclicGraph;
	Graph<Integer> complexAcyclicGraph;
	Graph<Integer> largeAcyclicGraph;
	Graph<Integer> largeCyclicGraph;
	Graph<Integer> largerAcyclicGraph;
 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		// Vertex and Edge class tests
		vertex1 = new Vertex<>(1);
		vertex2 = new Vertex<>(2);
		vertex3 = new Vertex<>(3);
		vertex1.addEdge(vertex2);
		vertex1.addEdge(vertex3);
		
		// Graph class tests
		notConnectedGraph = new Graph<>();
		notConnectedGraph.addEdge(1, 2);
		notConnectedGraph.addEdge(3, 4);
		notConnectedGraph.addEdge(5, 6);
		
		chainGraph = new Graph<>();
		chainGraph.addEdge(1, 2);
		chainGraph.addEdge(2, 3);
		chainGraph.addEdge(3, 4);

		cycleGraph = new Graph<>();
		cycleGraph.addEdge(1, 2);
		cycleGraph.addEdge(2, 3);
		cycleGraph.addEdge(3, 1);

		treeGraph = new Graph<>();
		treeGraph.addEdge(1, 2);
		treeGraph.addEdge(1, 3);
		treeGraph.addEdge(1, 4);

		complexCyclicGraph = new Graph<>();
		complexCyclicGraph.addEdge(1, 2);
		complexCyclicGraph.addEdge(1, 3);
		complexCyclicGraph.addEdge(1, 4);
		complexCyclicGraph.addEdge(2, 1);
		complexCyclicGraph.addEdge(3, 5);
		complexCyclicGraph.addEdge(3, 6);
		complexCyclicGraph.addEdge(3, 7);
		complexCyclicGraph.addEdge(4, 8);

		complexAcyclicGraph = new Graph<>();
		complexAcyclicGraph.addEdge(1, 2);
		complexAcyclicGraph.addEdge(1, 3);
		complexAcyclicGraph.addEdge(1, 4);
		complexAcyclicGraph.addEdge(2, 5);
		complexAcyclicGraph.addEdge(3, 5);
		complexAcyclicGraph.addEdge(3, 6);
		complexAcyclicGraph.addEdge(3, 7);
		complexAcyclicGraph.addEdge(4, 8);
		
		largeAcyclicGraph = new Graph<>();
		largeAcyclicGraph.addEdge(1, 2);
		largeAcyclicGraph.addEdge(2, 3);
		largeAcyclicGraph.addEdge(1, 4);
		largeAcyclicGraph.addEdge(4, 5);
		largeAcyclicGraph.addEdge(5, 3);
		largeAcyclicGraph.addEdge(1, 6);
		largeAcyclicGraph.addEdge(6, 7);
		largeAcyclicGraph.addEdge(7, 8);
		largeAcyclicGraph.addEdge(8, 3);
		
		largeCyclicGraph = new Graph<>();
		largeCyclicGraph.addEdge(1, 2);
		largeCyclicGraph.addEdge(2, 3);
		largeCyclicGraph.addEdge(1, 4);
		largeCyclicGraph.addEdge(4, 2);
		largeCyclicGraph.addEdge(1, 6);
		largeCyclicGraph.addEdge(6, 4);
		largeCyclicGraph.addEdge(3, 1);
		
		largerAcyclicGraph = new Graph<>();
		largerAcyclicGraph.addEdge(1, 2);
		largerAcyclicGraph.addEdge(1, 3);
		largerAcyclicGraph.addEdge(1, 4);
		largerAcyclicGraph.addEdge(1, 5);
		
		largerAcyclicGraph.addEdge(2, 3);
		largerAcyclicGraph.addEdge(2, 4);
		largerAcyclicGraph.addEdge(2, 5);
		largerAcyclicGraph.addEdge(2, 6);
		
		largerAcyclicGraph.addEdge(3, 4);
		largerAcyclicGraph.addEdge(3, 5);
		largerAcyclicGraph.addEdge(3, 6);
		
		largerAcyclicGraph.addEdge(4, 5);
		largerAcyclicGraph.addEdge(4, 6);
		
		largerAcyclicGraph.addEdge(5, 6);
	}

// Vertex and Edge class tests
	@Test
	void connectingVertexesWithEdge() {
		Iterator<Edge<Integer>> edges = vertex1.edges();
		assertEquals(2, edges.next().getOtherVertex().getID());
		assertEquals(3, edges.next().getOtherVertex().getID());
	}

	@Test
	void multiVertexToString() {
		assertEquals("Vertex 1 adjacent to verticies 2 3", vertex1.toString());
	}
	
// Graph class tests
	// toString()
	@Test
	void emptyGraphToString() {
		assertThrows(NullPointerException.class, () -> {
			emptyGraph.toString();
		});
	}
	
	@Test
	void notConnectedGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2\n"
					+ "Vertex 2 adjacent to verticies\n"
					+ "Vertex 3 adjacent to verticies 4\n"
					+ "Vertex 4 adjacent to verticies\n"
					+ "Vertex 5 adjacent to verticies 6\n"
					+ "Vertex 6 adjacent to verticies\n", notConnectedGraph.toString());
	}
	
	@Test
	void chainGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2\n"
					+ "Vertex 2 adjacent to verticies 3\n"
					+ "Vertex 3 adjacent to verticies 4\n"
					+ "Vertex 4 adjacent to verticies\n", chainGraph.toString());
	}
	
	@Test
	void cycleGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2\n"
					+ "Vertex 2 adjacent to verticies 3\n"
					+ "Vertex 3 adjacent to verticies 1\n", cycleGraph.toString());
	}
	
	@Test
	void treeGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2 3 4\n"
					+ "Vertex 2 adjacent to verticies\n"
					+ "Vertex 3 adjacent to verticies\n"
					+ "Vertex 4 adjacent to verticies\n", treeGraph.toString());
	}
	
	@Test
	void complexCyclicGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2 3 4\n"
					+ "Vertex 2 adjacent to verticies 1\n"
					+ "Vertex 3 adjacent to verticies 5 6 7\n"
					+ "Vertex 4 adjacent to verticies 8\n"
					+ "Vertex 5 adjacent to verticies\n"
					+ "Vertex 6 adjacent to verticies\n"
					+ "Vertex 7 adjacent to verticies\n"
					+ "Vertex 8 adjacent to verticies\n", complexCyclicGraph.toString());
	}
	
	@Test
	void complexAcyclicGraphToString() {
		assertEquals( "Vertex 1 adjacent to verticies 2 3 4\n"
					+ "Vertex 2 adjacent to verticies 5\n"
					+ "Vertex 3 adjacent to verticies 5 6 7\n"
					+ "Vertex 4 adjacent to verticies 8\n"
					+ "Vertex 5 adjacent to verticies\n"
					+ "Vertex 6 adjacent to verticies\n"
					+ "Vertex 7 adjacent to verticies\n"
					+ "Vertex 8 adjacent to verticies\n", complexAcyclicGraph.toString());
	}
	
	// generateDot()
	@Test
	void emptyGraphGenerateDot() {
		assertThrows(NullPointerException.class, () -> {
			emptyGraph.generateDot();
		});
	}
	
	@Test
	void notConnectedGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	3 -> 4\n"
					+ "	5 -> 6\n"
					+ "}", notConnectedGraph.generateDot());
	}
	
	@Test
	void chainGraphGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	2 -> 3\n"
					+ "	3 -> 4\n"
					+ "}", chainGraph.generateDot());
	}
	
	@Test
	void cycleGraphGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	2 -> 3\n"
					+ "	3 -> 1\n"
					+ "}", cycleGraph.generateDot());
	}
	
	@Test
	void treeGraphGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	1 -> 3\n"
					+ "	1 -> 4\n"
					+ "}", treeGraph.generateDot());
	}
	
	@Test
	void complexCyclicGraphGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	1 -> 3\n"
					+ "	1 -> 4\n"
					+ "	2 -> 1\n"
					+ "	3 -> 5\n"
					+ "	3 -> 6\n"
					+ "	3 -> 7\n"
					+ "	4 -> 8\n"
					+ "}", complexCyclicGraph.generateDot());
	}
	
	@Test
	void complexAcyclicGraphGenerateDot() {
		assertEquals( "digraph d {\n"
					+ "	1 -> 2\n"
					+ "	1 -> 3\n"
					+ "	1 -> 4\n"
					+ "	2 -> 5\n"
					+ "	3 -> 5\n"
					+ "	3 -> 6\n"
					+ "	3 -> 7\n"
					+ "	4 -> 8\n"
					+ "}", complexAcyclicGraph.generateDot());
	}
	
	// areConnected()
	@Test
	void exceptionIfNoVertexWithSrcOrDst() {
		assertThrows(IllegalArgumentException.class, () -> {
			chainGraph.areConnected(1, 5);
		});
	}
		
	@Test
	void notConnectedGraphAreConnected() {
		assertTrue(notConnectedGraph.areConnected(1, 1));
		assertTrue(notConnectedGraph.areConnected(1, 2));
		assertFalse(notConnectedGraph.areConnected(1, 3));
		assertFalse(notConnectedGraph.areConnected(1, 4));
		assertFalse(notConnectedGraph.areConnected(1, 5));
		assertFalse(notConnectedGraph.areConnected(1, 6));

		assertFalse(notConnectedGraph.areConnected(2, 1));
		assertTrue(notConnectedGraph.areConnected(2, 2));
		assertFalse(notConnectedGraph.areConnected(2, 3));
		assertFalse(notConnectedGraph.areConnected(2, 4));
		assertFalse(notConnectedGraph.areConnected(2, 5));
		assertFalse(notConnectedGraph.areConnected(2, 6));

		assertFalse(notConnectedGraph.areConnected(3, 1));
		assertFalse(notConnectedGraph.areConnected(3, 2));
		assertTrue(notConnectedGraph.areConnected(3, 3));
		assertTrue(notConnectedGraph.areConnected(3, 4));
		assertFalse(notConnectedGraph.areConnected(3, 5));
		assertFalse(notConnectedGraph.areConnected(3, 6));

		assertFalse(notConnectedGraph.areConnected(4, 1));
		assertFalse(notConnectedGraph.areConnected(4, 2));
		assertFalse(notConnectedGraph.areConnected(4, 3));
		assertTrue(notConnectedGraph.areConnected(4, 4));
		assertFalse(notConnectedGraph.areConnected(4, 5));
		assertFalse(notConnectedGraph.areConnected(4, 6));

		assertFalse(notConnectedGraph.areConnected(5, 1));
		assertFalse(notConnectedGraph.areConnected(5, 2));
		assertFalse(notConnectedGraph.areConnected(5, 3));
		assertFalse(notConnectedGraph.areConnected(5, 4));
		assertTrue(notConnectedGraph.areConnected(5, 5));
		assertTrue(notConnectedGraph.areConnected(5, 6));

		assertFalse(notConnectedGraph.areConnected(6, 1));
		assertFalse(notConnectedGraph.areConnected(6, 2));
		assertFalse(notConnectedGraph.areConnected(6, 3));
		assertFalse(notConnectedGraph.areConnected(6, 4));
		assertFalse(notConnectedGraph.areConnected(6, 5));
		assertTrue(notConnectedGraph.areConnected(6, 6));
	}
	
	@Test
	void chainGraphAreConnected() {
		assertTrue(chainGraph.areConnected(1, 1));
		assertTrue(chainGraph.areConnected(1, 2));
		assertTrue(chainGraph.areConnected(1, 3));
		assertTrue(chainGraph.areConnected(1, 4));
		
		assertFalse(chainGraph.areConnected(2, 1));
		assertTrue(chainGraph.areConnected(2, 2));
		assertTrue(chainGraph.areConnected(2, 3));
		assertTrue(chainGraph.areConnected(2, 4));
		
		assertFalse(chainGraph.areConnected(3, 1));
		assertFalse(chainGraph.areConnected(3, 2));
		assertTrue(chainGraph.areConnected(3, 3));
		assertTrue(chainGraph.areConnected(3, 4));
		
		assertFalse(chainGraph.areConnected(4, 1));
		assertFalse(chainGraph.areConnected(4, 2));
		assertFalse(chainGraph.areConnected(4, 3));
		assertTrue(chainGraph.areConnected(4, 4));
	}
	
	@Test
	void cycleGraphAreConnected() {
		assertTrue(cycleGraph.areConnected(1, 1));
		assertTrue(cycleGraph.areConnected(1, 2));
		assertTrue(cycleGraph.areConnected(1, 3));
		
		assertTrue(cycleGraph.areConnected(2, 1));
		assertTrue(cycleGraph.areConnected(2, 2));
		assertTrue(cycleGraph.areConnected(2, 3));
		
		assertTrue(cycleGraph.areConnected(3, 1));
		assertTrue(cycleGraph.areConnected(3, 2));
		assertTrue(cycleGraph.areConnected(3, 3));
	}
	
	@Test
	void treeGraphAreConnected() {
		assertTrue(treeGraph.areConnected(1, 1));
		assertTrue(treeGraph.areConnected(1, 2));
		assertTrue(treeGraph.areConnected(1, 3));
		assertTrue(treeGraph.areConnected(1, 4));
		
		assertFalse(treeGraph.areConnected(2, 1));
		assertTrue(treeGraph.areConnected(2, 2));
		assertFalse(treeGraph.areConnected(2, 3));
		assertFalse(treeGraph.areConnected(2, 4));
		
		assertFalse(treeGraph.areConnected(3, 1));
		assertFalse(treeGraph.areConnected(3, 2));
		assertTrue(treeGraph.areConnected(3, 3));
		assertFalse(treeGraph.areConnected(3, 4));
		
		assertFalse(treeGraph.areConnected(4, 1));
		assertFalse(treeGraph.areConnected(4, 2));
		assertFalse(treeGraph.areConnected(4, 3));
		assertTrue(treeGraph.areConnected(4, 4));
	}
	
	@Test
	void complexCyclicGraphAreConnected() {
		assertTrue(complexCyclicGraph.areConnected(1, 1));
		assertTrue(complexCyclicGraph.areConnected(1, 2));
		assertTrue(complexCyclicGraph.areConnected(1, 3));
		assertTrue(complexCyclicGraph.areConnected(1, 4));
		assertTrue(complexCyclicGraph.areConnected(1, 5));
		assertTrue(complexCyclicGraph.areConnected(1, 6));
		assertTrue(complexCyclicGraph.areConnected(1, 7));
		assertTrue(complexCyclicGraph.areConnected(1, 8));

		assertTrue(complexCyclicGraph.areConnected(2, 1));
		assertTrue(complexCyclicGraph.areConnected(2, 2));
		assertTrue(complexCyclicGraph.areConnected(2, 3));
		assertTrue(complexCyclicGraph.areConnected(2, 4));
		assertTrue(complexCyclicGraph.areConnected(2, 5));
		assertTrue(complexCyclicGraph.areConnected(2, 6));
		assertTrue(complexCyclicGraph.areConnected(2, 7));
		assertTrue(complexCyclicGraph.areConnected(2, 8));

		assertFalse(complexCyclicGraph.areConnected(3, 1));
		assertFalse(complexCyclicGraph.areConnected(3, 2));
		assertTrue(complexCyclicGraph.areConnected(3, 3));
		assertFalse(complexCyclicGraph.areConnected(3, 4));
		assertTrue(complexCyclicGraph.areConnected(3, 5));
		assertTrue(complexCyclicGraph.areConnected(3, 6));
		assertTrue(complexCyclicGraph.areConnected(3, 7));
		assertFalse(complexCyclicGraph.areConnected(3, 8));

		assertFalse(complexCyclicGraph.areConnected(4, 1));
		assertFalse(complexCyclicGraph.areConnected(4, 2));
		assertFalse(complexCyclicGraph.areConnected(4, 3));
		assertTrue(complexCyclicGraph.areConnected(4, 4));
		assertFalse(complexCyclicGraph.areConnected(4, 5));
		assertFalse(complexCyclicGraph.areConnected(4, 6));
		assertFalse(complexCyclicGraph.areConnected(4, 7));
		assertTrue(complexCyclicGraph.areConnected(4, 8));

		assertFalse(complexCyclicGraph.areConnected(5, 1));
		assertFalse(complexCyclicGraph.areConnected(5, 2));
		assertFalse(complexCyclicGraph.areConnected(5, 3));
		assertFalse(complexCyclicGraph.areConnected(5, 4));
		assertTrue(complexCyclicGraph.areConnected(5, 5));
		assertFalse(complexCyclicGraph.areConnected(5, 6));
		assertFalse(complexCyclicGraph.areConnected(5, 7));
		assertFalse(complexCyclicGraph.areConnected(5, 8));

		assertFalse(complexCyclicGraph.areConnected(6, 1));
		assertFalse(complexCyclicGraph.areConnected(6, 2));
		assertFalse(complexCyclicGraph.areConnected(6, 3));
		assertFalse(complexCyclicGraph.areConnected(6, 4));
		assertFalse(complexCyclicGraph.areConnected(6, 5));
		assertTrue(complexCyclicGraph.areConnected(6, 6));
		assertFalse(complexCyclicGraph.areConnected(6, 7));
		assertFalse(complexCyclicGraph.areConnected(6, 8));

		assertFalse(complexCyclicGraph.areConnected(7, 1));
		assertFalse(complexCyclicGraph.areConnected(7, 2));
		assertFalse(complexCyclicGraph.areConnected(7, 3));
		assertFalse(complexCyclicGraph.areConnected(7, 4));
		assertFalse(complexCyclicGraph.areConnected(7, 5));
		assertFalse(complexCyclicGraph.areConnected(7, 6));
		assertTrue(complexCyclicGraph.areConnected(7, 7));
		assertFalse(complexCyclicGraph.areConnected(7, 8));

		assertFalse(complexCyclicGraph.areConnected(8, 1));
		assertFalse(complexCyclicGraph.areConnected(8, 2));
		assertFalse(complexCyclicGraph.areConnected(8, 3));
		assertFalse(complexCyclicGraph.areConnected(8, 4));
		assertFalse(complexCyclicGraph.areConnected(8, 5));
		assertFalse(complexCyclicGraph.areConnected(8, 6));
		assertFalse(complexCyclicGraph.areConnected(8, 7));
		assertTrue(complexCyclicGraph.areConnected(8, 8));
	}
	

	@Test
	void complexAcyclicGraphAreConnected() {
		assertTrue(complexAcyclicGraph.areConnected(1, 1));
		assertTrue(complexAcyclicGraph.areConnected(1, 2));
		assertTrue(complexAcyclicGraph.areConnected(1, 3));
		assertTrue(complexAcyclicGraph.areConnected(1, 4));
		assertTrue(complexAcyclicGraph.areConnected(1, 5));
		assertTrue(complexAcyclicGraph.areConnected(1, 6));
		assertTrue(complexAcyclicGraph.areConnected(1, 7));
		assertTrue(complexAcyclicGraph.areConnected(1, 8));

		assertFalse(complexAcyclicGraph.areConnected(2, 1));
		assertTrue(complexAcyclicGraph.areConnected(2, 2));
		assertFalse(complexAcyclicGraph.areConnected(2, 3));
		assertFalse(complexAcyclicGraph.areConnected(2, 4));
		assertTrue(complexAcyclicGraph.areConnected(2, 5));
		assertFalse(complexAcyclicGraph.areConnected(2, 6));
		assertFalse(complexAcyclicGraph.areConnected(2, 7));
		assertFalse(complexAcyclicGraph.areConnected(2, 8));

		assertFalse(complexAcyclicGraph.areConnected(3, 1));
		assertFalse(complexAcyclicGraph.areConnected(3, 2));
		assertTrue(complexAcyclicGraph.areConnected(3, 3));
		assertFalse(complexAcyclicGraph.areConnected(3, 4));
		assertTrue(complexAcyclicGraph.areConnected(3, 5));
		assertTrue(complexAcyclicGraph.areConnected(3, 6));
		assertTrue(complexAcyclicGraph.areConnected(3, 7));
		assertFalse(complexAcyclicGraph.areConnected(3, 8));

		assertFalse(complexAcyclicGraph.areConnected(4, 1));
		assertFalse(complexAcyclicGraph.areConnected(4, 2));
		assertFalse(complexAcyclicGraph.areConnected(4, 3));
		assertTrue(complexAcyclicGraph.areConnected(4, 4));
		assertFalse(complexAcyclicGraph.areConnected(4, 5));
		assertFalse(complexAcyclicGraph.areConnected(4, 6));
		assertFalse(complexAcyclicGraph.areConnected(4, 7));
		assertTrue(complexAcyclicGraph.areConnected(4, 8));

		assertFalse(complexAcyclicGraph.areConnected(5, 1));
		assertFalse(complexAcyclicGraph.areConnected(5, 2));
		assertFalse(complexAcyclicGraph.areConnected(5, 3));
		assertFalse(complexAcyclicGraph.areConnected(5, 4));
		assertTrue(complexAcyclicGraph.areConnected(5, 5));
		assertFalse(complexAcyclicGraph.areConnected(5, 6));
		assertFalse(complexAcyclicGraph.areConnected(5, 7));
		assertFalse(complexAcyclicGraph.areConnected(5, 8));

		assertFalse(complexAcyclicGraph.areConnected(6, 1));
		assertFalse(complexAcyclicGraph.areConnected(6, 2));
		assertFalse(complexAcyclicGraph.areConnected(6, 3));
		assertFalse(complexAcyclicGraph.areConnected(6, 4));
		assertFalse(complexAcyclicGraph.areConnected(6, 5));
		assertTrue(complexAcyclicGraph.areConnected(6, 6));
		assertFalse(complexAcyclicGraph.areConnected(6, 7));
		assertFalse(complexAcyclicGraph.areConnected(6, 8));

		assertFalse(complexAcyclicGraph.areConnected(7, 1));
		assertFalse(complexAcyclicGraph.areConnected(7, 2));
		assertFalse(complexAcyclicGraph.areConnected(7, 3));
		assertFalse(complexAcyclicGraph.areConnected(7, 4));
		assertFalse(complexAcyclicGraph.areConnected(7, 5));
		assertFalse(complexAcyclicGraph.areConnected(7, 6));
		assertTrue(complexAcyclicGraph.areConnected(7, 7));
		assertFalse(complexAcyclicGraph.areConnected(7, 8));

		assertFalse(complexAcyclicGraph.areConnected(8, 1));
		assertFalse(complexAcyclicGraph.areConnected(8, 2));
		assertFalse(complexAcyclicGraph.areConnected(8, 3));
		assertFalse(complexAcyclicGraph.areConnected(8, 4));
		assertFalse(complexAcyclicGraph.areConnected(8, 5));
		assertFalse(complexAcyclicGraph.areConnected(8, 6));
		assertFalse(complexAcyclicGraph.areConnected(8, 7));
		assertTrue(complexAcyclicGraph.areConnected(8, 8));
	}
	
	// shortestPath()
	@Test
	void exceptionSortestPathIfNoVertexWithSrcOrDst() {
		assertThrows(IllegalArgumentException.class, () -> {
			chainGraph.shortestPath(1, 5);
		});
	}
	
	@Test
	void notConnectedGraphPath() {
		LinkedList<Integer> oneToTwo = new LinkedList<>();
		oneToTwo.add(1);
		oneToTwo.add(2);
		assertEquals(oneToTwo, notConnectedGraph.shortestPath(1, 2));
		
		assertThrows(IllegalArgumentException.class, () -> {
			notConnectedGraph.shortestPath(1, 3);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			notConnectedGraph.shortestPath(4, 1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			notConnectedGraph.shortestPath(99, 55);
		});
	}
	
	@Test
	void chainGraphShortestPath() {
		LinkedList<Integer> oneToFour = new LinkedList<>();
		oneToFour.add(1);
		oneToFour.add(2);
		oneToFour.add(3);
		oneToFour.add(4);
		assertEquals(oneToFour, chainGraph.shortestPath(1, 4));
	}
	
	@Test 
	void cycleGraphShortestPath() {
		LinkedList<Integer> threeToTwo = new LinkedList<>();
		threeToTwo.add(3);
		threeToTwo.add(1);
		threeToTwo.add(2);

		assertEquals(threeToTwo, cycleGraph.shortestPath(3, 2));
	}
	
	@Test
	void complexCyclicGraphShortestPath() {
		LinkedList<Integer> name = new LinkedList<>();
		name.add(1);
		name.add(3);
		name.add(6);
		
		assertEquals(name, complexCyclicGraph.shortestPath(1,6));
	}
	
	@Test
	void largeAcyclicGraphShortestPath() {
		LinkedList<Integer> name = new LinkedList<>();
		name.add(1);
		name.add(2);
		name.add(3);
		
		assertEquals(name, largeAcyclicGraph.shortestPath(1,3));
		
	}
	
	@Test
	void largeCyclicGraphShortestPath() {
		LinkedList<Integer> oneToFour = new LinkedList<>();
		oneToFour.add(1);
		oneToFour.add(4);
		
		assertEquals(oneToFour, largeCyclicGraph.shortestPath(1,4));
		
		LinkedList<Integer> oneToTwo = new LinkedList<>();
		oneToTwo.add(1);
		oneToTwo.add(2);
		
		assertEquals(oneToTwo, largeCyclicGraph.shortestPath(1,2));
		
		LinkedList<Integer> twoToFour = new LinkedList<>();
		twoToFour.add(2);
		twoToFour.add(3);
		twoToFour.add(1);
		twoToFour.add(4);
		
		assertEquals(twoToFour, largeCyclicGraph.shortestPath(2,4));
		
		LinkedList<Integer> threeToTwo = new LinkedList<>();
		threeToTwo.add(3);
		threeToTwo.add(1);
		threeToTwo.add(2);
		
		assertEquals(threeToTwo, largeCyclicGraph.shortestPath(3,2));
	}
	
	@Test
	void largerAcyclicGraphShortestPath() {
		LinkedList<Integer> oneToSix = new LinkedList<>();
		oneToSix.add(1);
		oneToSix.add(2);
		oneToSix.add(6);
		
		assertEquals(oneToSix, largerAcyclicGraph.shortestPath(1,6));
		
		LinkedList<Integer> twoToSix = new LinkedList<>();
		twoToSix.add(2);
		twoToSix.add(6);
		
		assertEquals(twoToSix, largerAcyclicGraph.shortestPath(2,6));
		
		LinkedList<Integer> threeToSix = new LinkedList<>();
		threeToSix.add(3);
		threeToSix.add(6);
		
		assertEquals(threeToSix, largerAcyclicGraph.shortestPath(3,6));
	}
	
	// sort()
	@Test
	void exceptionIfCyclic() {
		assertThrows(IllegalArgumentException.class, () -> {
			cycleGraph.sort();
		});
	}
	
	@Test
	void emptyGraphSort() {
		assertThrows(NullPointerException.class, () -> {
			emptyGraph.sort();
		});
	}
	
	@Test
	void notConnectedSort() {
		assertTrue(notConnectedGraph.sortedCorrectly(notConnectedGraph.sort()));
	}
	
	@Test
	void chainGraphSort() {
		assertTrue(chainGraph.sortedCorrectly(chainGraph.sort()));
	}
	
	@Test
	void treeGraphSort() {
		assertTrue(treeGraph.sortedCorrectly(treeGraph.sort()));
	}
	
	@Test
	void complexAcyclicGraphSort() {
		assertTrue(complexAcyclicGraph.sortedCorrectly(complexAcyclicGraph.sort()));
	}
	
	@Test
	void largeAcyclicGraphSort() {
		assertTrue(largeAcyclicGraph.sortedCorrectly(largeAcyclicGraph.sort()));
	}
	
	@Test
	void largerAcyclicGraphSort() {
		assertTrue(largerAcyclicGraph.sortedCorrectly(largerAcyclicGraph.sort()));
	}
	
	@Test
	void falselySortedList() {
		
		List<Integer> falselySortedList = new LinkedList<>();
		falselySortedList.add(4);
		falselySortedList.add(3);
		falselySortedList.add(2);
		falselySortedList.add(1);
		
		assertFalse(chainGraph.sortedCorrectly(falselySortedList));
	}
	
//GraphUtility Tests
	

	@Test
	void buildListsFromDotAreConnected() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> destinations = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/assign07/largeCyclicGraph.txt", sources, destinations);
		assertTrue(GraphUtility.areConnected(sources, destinations, "1", "3"));
	}
	
	@Test
	void shortestPathCS() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> destinations = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/assign07/examplegraph7.dot", sources, destinations);
		ArrayList<String> compare = new ArrayList<>();
		compare.add("CS 1410");
		compare.add("CS 2420");
		compare.add("CS 3500");
		assertEquals(compare, GraphUtility.shortestPath(sources, destinations, "CS 1410", "CS 3500"));
	}
	
	@Test
	void shortestPathExample2() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> destinations = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/assign07/examplegraph2.dot", sources, destinations);
		ArrayList<String> compare = new ArrayList<>();
		compare.add("D");
		compare.add("B");
		assertEquals(compare, GraphUtility.shortestPath(sources, destinations, "D", "B"));
	}
	
	@Test
	void sortCS() {
		ArrayList<String> sources = new ArrayList<>();
		ArrayList<String> destinations = new ArrayList<>();
		GraphUtility.buildListsFromDot("src/assign07/examplegraph7.dot", sources, destinations);
		List<String> result = GraphUtility.sort(sources, destinations);
		System.out.println(result);
	}
	

	
}
