package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class collects running times for methods of GraphUtility.
 * 
 * @author Erin Parker, Paul Nuffer, Nils Streedain
 * @version March 10, 2021
 */
public class GraphUtilityTimer {

	public static void main(String[] args) {
		System.out.println("\nN\t\tnanoTime");

		int incr = 1000;
		for (int probSize = 1000; probSize <= 15000; probSize += incr) {

			int timesToLoop = 10000;
			
			Graph<String> graph = generateRandomGraph(probSize, true);

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			long stopTime, midpointTime, startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();
			
			for (int i = 0; i < timesToLoop; i++) {
				 graph.areConnected("v1", "v2");
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(probSize + "\t" + String.format("%.5f", averageTime));
		}
	}

	/**
	 * Private helper method to generate random dot files for timing
	 * 
	 * @param filename    - name of generated file
	 * @param vertexCount - number of vertices in graph
	 */
	private static void generateRandomDotFile(String filename, int vertexCount, boolean cyclic) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(filename);
		} catch (IOException e) {
			System.out.println(e);
		}

		Random rng = new Random(1235967);

		// randomly construct either a graph or a digraph
		out.print("di");
		out.println("graph G {");

		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;

		if (cyclic) {
			// randomly connect the vertices using 2 * |V| edges
			for (int i = 0; i < 2 * vertexCount; i++)
				out.println("\t" + vertex[rng.nextInt(vertexCount)] + "->" + vertex[rng.nextInt(vertexCount)]);
		} else {
			for (int i = 0; i < vertexCount - 1; i++)
				out.println("\t" + vertex[i] + "->" + vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);
		}

		out.println("}");
		out.close();
	}
	
	private static Graph<String> generateRandomGraph(int vertexCount, boolean cyclic) {
		Random rng = new Random(1235967);
		Graph<String> graph = new Graph<>();
		
		// generate a list of vertices
		String[] vertex = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			vertex[i] = "v" + i;
		
		if(cyclic) 
			for (int i = 0; i < 2 * vertexCount; i++)
				graph.addEdge(vertex[rng.nextInt(vertexCount)], vertex[rng.nextInt(vertexCount)]);
		else 
			for (int i = 0; i < vertexCount - 1; i++)
				graph.addEdge(vertex[i], vertex[i + 1 + rng.nextInt(vertexCount - (i + 1))]);
		
		return graph;
	}
}
