package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.tools.classfile.CompilationID_attribute;

class GraphUtilityTester {
	
	Vertex<Integer> vertex1;
	Vertex<Integer> vertex2;
	Vertex<Integer> vertex3;

	Graph<Integer> chainGraph;
	Graph<Integer> cycleGraph;
	Graph<Integer> treeGraph;
	Graph<Integer> complexCyclicGraph;
	Graph<Integer> complexAcyclicGraph;

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
	
}
