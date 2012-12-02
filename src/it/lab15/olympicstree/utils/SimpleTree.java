/*
* Copyright 1997-2012 SoftSolutions! srl 
* All Rights Reserved. 
* 
* NOTICE: All information contained herein is, and remains the property of SoftSolutions! srl 
* The intellectual and technical concepts contained herein are proprietary to SoftSolutions! srl and 
* may be covered by EU, U.S. and other Foreign Patents, patents in process, and 
* are protected by trade secret or copyright law. 
* Dissemination of this information or reproduction of this material is strictly forbidden 
* unless prior written permission is obtained from SoftSolutions! srl.
* Any additional licenses, terms and conditions, if any, are defined in file 'LICENSE.txt', 
* which may be part of this software package.
*/
 
package it.lab15.olympicstree.utils;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
 
 
/**  
 *
 * Purpose: this class is mainly for ...  
 *
 * Project Name : CSVReader 
 * First created by: marco.bonati 
 * Creation date: 30/nov/2012 
 * 
 **/
public class SimpleTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Graph<V, E> where V is the type of the vertices
		// and E is the type of the edges
		Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
		// Add some vertices. From above we defined these to be type Integer.
		g.addVertex((Integer)1);
		g.addVertex((Integer)2);
		g.addVertex((Integer)3);
		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.
		g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
		g.addEdge("Edge-B", 2, 3);
		// Let's see what we have. Note the nice output from the
		// SparseMultigraph<V,E> toString() method
		System.out.println("The graph g = " + g.toString());
		
		
		// Note that we can use the same nodes and edges in two different graphs.
		Graph<Integer, String> g2 = new SparseMultigraph<Integer, String>();
		g2.addVertex((Integer)1);
		g2.addVertex((Integer)2);
		g2.addVertex((Integer)3);
		g2.addEdge("Edge-A", 1,3);
		g2.addEdge("Edge-B", 2,3, EdgeType.DIRECTED);
		g2.addEdge("Edge-C", 3, 2, EdgeType.DIRECTED);
		g2.addEdge("Edge-P", 2,3); // A parallel edge
		System.out.println("The graph g2 = " + g2.toString());

	}

}
