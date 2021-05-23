package org.beri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
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
        return Arrays.binarySearch(nouns, word, String::compareToIgnoreCase) > 0;
    }

    @Override
    public int distance(String A, String B) {

        int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
        int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);
        return helper.length(a, b);
    }

    @Override
    public String sap(String A, String B) {
        int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
        int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);
        int sca = helper.ancestor(a,b);
        return nouns[sca];
    }


}
