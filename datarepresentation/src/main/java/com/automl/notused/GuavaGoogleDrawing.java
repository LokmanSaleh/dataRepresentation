package com.automl.notused;

import com.google.common.graph.*;

public class GuavaGoogleDrawing {

	static class Node {

		private final String name;

		public Node(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}

	public static void main(String argv[]) {

		Node root = new Node("root");

		MutableGraph<Node> myGraph = GraphBuilder.directed().build();

		Node node1 = new Node(" 1 ");

		Node node2 = new Node(" 2 ");

		Node node3 = new Node(" 3 ");

		Node node4 = new Node(" 4 ");
		
		Node node5 = new Node(" 5 ");

		myGraph.putEdge(root, node1);

		myGraph.putEdge(root, node2);

		myGraph.putEdge(root, node3);

		myGraph.putEdge(node2, node4);

		myGraph.putEdge(node2, node5);
		
		
		myGraph.
		
		// Print the nodes Depth First
		System.out.println("==============Dept First==============");

		Traverser.forGraph(myGraph).depthFirstPostOrder(root)

				.forEach(x -> System.out.println(x));

		// Print the nodes Bread First
		System.out.println("==============Breath First==============");

		Traverser.forGraph(myGraph).breadthFirst(root)

				.forEach(x -> System.out.println(x));

	}

}