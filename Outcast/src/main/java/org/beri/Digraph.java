package org.beri;

import java.util.Iterator;

public class Digraph {

    int vertices;
    Bag<Integer>[] adjacencyMatrix;

   Digraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyMatrix = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyMatrix[i] = new Bag<>();
        }
    }

    void addEdge(int fromEdge, int toEdge) {
        adjacencyMatrix[fromEdge].addItem(toEdge);
    }

    Iterator getIteratorForVertex(int vertex) {
        return adjacencyMatrix[vertex].getAll();
    }

    Iterator<Integer> getIterable(int vert) {return adjacencyMatrix[vert].getAll();}

    void printGraphStructure() {
        int count = 0;
        for (Bag b : adjacencyMatrix) {
            System.out.print(count + " -> ");
            Iterator f = b.getAll();
            while (f.hasNext()) {
                System.out.print(" " + f.next());
            }
            count++;
            System.out.println();
        }
    }


  Digraph reverseGraph() {

        Bag<Integer>[] reverseGraph = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            reverseGraph[i] = new Bag<>();
            for (Iterator it = getIteratorForVertex(i); it.hasNext(); ) {
                reverseGraph[i].addItem((int) it.next());
            }
        }

       Digraph digraph = new Digraph(reverseGraph().vertices);
      digraph.adjacencyMatrix = reverseGraph;
      return digraph;
    }
}