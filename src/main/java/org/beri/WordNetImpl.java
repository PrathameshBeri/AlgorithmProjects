package org.beri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordNetImpl implements WordNet {

    String[] nouns;

    //Bag<Integer>[] adjMat;

    Digraph digraph;

    SAPImpl helper;

    WordNetImpl() throws IOException {
        Path p = Paths.get("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC projects\\AlgorithmsProject\\src\\main\\resources\\synsets.txt");
        nouns = Files.lines(p).
                map(x -> x.split(",")).
                map(x -> {
                    return x[1];
                }).
                toArray(String[]::new);
        digraph = new Digraph(nouns.length);
        // adjMat = new Bag[nouns.length];

        Path k = Paths.get("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC " +
                "projects\\AlgorithmsProject\\src\\main\\resources\\hypernyms.txt");
        Files.lines(k).
                map(x -> x.split(","))
                .forEach(x -> {
                    for (int i = 1; i < x.length; i++) {
                        digraph.addEdge(Integer.parseInt(x[0]), Integer.parseInt(x[i]));
                    }
                });

        helper = new SAPImpl(digraph);
    }

    @Override
    public Iterable<String> nouns() {
        return Arrays.asList(nouns);
    }

    @Override
    public boolean isNoun(String word) {
        return findNoun(word) > 0;
    }

    @Override
    public int distance(String A, String B) throws Exception {

       // int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
      //  int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);
        int a = findNoun(A);
        int b = findNoun(B);
        if(a >= 0 && b >= 0 ) {
            return helper.length(a, b);
        }
        else throw new Exception("Word not present");
    }

    public int findNoun(String noun){

        int m = Arrays.binarySearch(nouns, noun, String::compareTo);
        if(m > 0){
            return m;
        }else{
            String n = nouns[Math.abs(m) - 1];
            if(n.contains(noun)){
                return Math.abs(m) - 1;
            }else return -1;
        }
    }

    @Override
    public String sap(String A, String B) {
        int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
        int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);
        int sca = helper.ancestor(a,b);
        return nouns[sca];
    }


}
