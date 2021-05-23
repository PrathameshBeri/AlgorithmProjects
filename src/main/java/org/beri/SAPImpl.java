package org.beri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SAPImpl implements SAP {

    Digraph graph;
    int vertices;
    int[] edgeTo;
    boolean[] visited;
    int commonAncestor = -1;
    int length;
    List<Integer> path;
    boolean ancestorFound;


    public SAPImpl(Digraph d) {

        graph = d;
        vertices = d.vertices;
        edgeTo = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            edgeTo[i] = -1;
        }
        visited = new boolean[vertices];
    }


    @Override
    public int length(int v, int w) {
        List<Integer> s = (List<Integer>) path(v, w);
        return s.size() - 1;
    }

    public Iterable<Integer> path(int v, int w) {
        clearResults();
        modifiedDFS(v);
        modifiedDFS(w);
        List<Integer> path2 = findPath(commonAncestor);
        Collections.reverse(path);
        if (path2.size() > 1) {
            for (int i = 1; i < path2.size(); i++) {
                path.add(path2.get(i));
            }
        }
        return path;
    }

    @Override
    public int ancestor(int v, int w) {
        clearResults();
        modifiedDFS(v);
        modifiedDFS(w);
        return commonAncestor;
    }

    @Override
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return sap("length", v, w);
    }

    @Override
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        //one variable to store the shortest common length yet
        //one variable to store the common ancestor associated with that path till now
        //return whichever is asked


        return sap("ancestor", v, w);
    }


    public int sap(String s, Iterable<Integer> v, Iterable<Integer> w) {

        int shortPath = Integer.MAX_VALUE;
        int localCommonAncestor = -1;

        for (int e : v) {

            for (int f : w) {

                List<Integer> l = (List<Integer>) path(e, f);
                if (l.size() < shortPath) {
                    shortPath = l.size();
                    localCommonAncestor = commonAncestor;
                }

            }

        }

        if (s.equals("length"))
            return shortPath - 1;
        else return localCommonAncestor;
    }

    public void modifiedDFS(int e) {
        if (!ancestorFound) {
            visited[e] = true;
            for (Iterator<Integer> it = graph.getIterable(e); it.hasNext(); ) {
                int m = it.next();
                if (!visited[m]) {
                    modifiedDFS(m);
                    edgeTo[m] = e;
                } else {
                    commonAncestor = m;
                    ancestorFound = true;
                    path = findPath(m);
                    edgeTo[m] = e;
                    return;
                }
            }
        }
    }

    public void clearResults() {
        ancestorFound = false;
        for (int i = 0; i < vertices; i++) {
            edgeTo[i] = -1;
            visited[i] = false;
        }

    }

    public List<Integer> findPath(int e) {
        List<Integer> l = new ArrayList<>();
        l.add(e);
        while (edgeTo[e] > 0) {
            l.add(edgeTo[e]);
            e = edgeTo[e];
        }
        return l;
    }
}
