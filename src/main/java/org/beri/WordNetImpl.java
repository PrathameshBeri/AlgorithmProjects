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
    Bag<Integer>[] adjMat;

    WordNetImpl() throws IOException {
        Path p = Paths.get("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC projects\\AlgorithmsProject\\src\\main\\resources\\synsets.txt");
        nouns = Files.lines(p).
                map(x -> x.split(",")).
                map(x -> {
                    return x[1];
                }).
                toArray(String[]::new);
        adjMat = new Bag[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            adjMat[i] = new Bag<>();
        }

        Path k = Paths.get("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC projects\\AlgorithmsProject\\src\\main\\resources\\hypernyms.txt");
        Files.lines(k).
                map(x -> x.split(","))
                .forEach(x -> {
                    for (int i = 1; i < x.length; i++) {
                        adjMat[i].addItem(Integer.parseInt(x[i]));
                    }
                });
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
        return 0;
    }

    @Override
    public String sap(String A, String B) {
        return null;
    }
}
