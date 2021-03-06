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
    boolean ancestorFound = false;
    boolean firstRound = false;


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
        firstRound = true;
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
        return sap("length", v, w).get(0);
    }

    @Override
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        //one variable to store the shortest common length yet
        //one variable to store the common ancestor associated with that path till now
        //return whichever is asked


        return sap("ancestor", v, w).get(0);
    }


    public List<Integer> shortestPaths(Iterable<Integer> v, Iterable<Integer> w){
        return sap("path",v,w);
    }


    public List<Integer> sap(String s, Iterable<Integer> v, Iterable<Integer> w) {

        int shortPath = Integer.MAX_VALUE;
        int localCommonAncestor = -1;
        List<Integer> lpath = null ;
        for (int e : v) {

            for (int f : w) {

                List<Integer> l = (List<Integer>) path(e, f);
                if (l.size() < shortPath) {
                    shortPath = l.size();
                    localCommonAncestor = commonAncestor;
                    lpath = l;
                }

            }

        }

        if (s.equals("length"))
            return Collections.singletonList(shortPath - 1);
        else if(s.equals("path")){
            return lpath;
        }
        else return Collections.singletonList(localCommonAncestor);
    }

    public void modifiedDFS(int e) {
        visited[e] = true;
        for (Iterator<Integer> it = graph.getIterable(e); it.hasNext(); ) {
            int m = it.next();
            if (!visited[m]) {
                modifiedDFS(m);
                edgeTo[m] = e;
            } else if (visited[m] && firstRound && !ancestorFound) {
                commonAncestor = m;
                ancestorFound = !ancestorFound; //change this pretty dumb
                path = findPath(m);
                edgeTo[m] = e;
                return;
            } else {
                return;
            }
        }

    }

    public void clearResults() {
        ancestorFound = false;
        path = new ArrayList<>();
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
