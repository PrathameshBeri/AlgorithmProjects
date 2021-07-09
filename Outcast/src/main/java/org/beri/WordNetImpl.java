package org.beri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WordNetImpl implements WordNet {

    String[] nouns;


    HashMap<String, List<Integer>> nounz;

    //Bag<Integer>[] adjMat;

    Digraph digraph;

    SAPImpl helper;

    WordNetImpl() throws IOException {
        Path p = Paths.get("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC projects\\AlgorithmsProject\\src\\main\\resources\\synsets.txt");
        nounz = new HashMap<String, List<Integer>>();
        nouns = Files.lines(p).
                map(x -> x.split(",")).
                map(x -> {
                    return x[1];
                }).
                toArray(String[]::new);

        Files.lines(p).forEach(x ->{
            String[] s = x.split(",");
            String[] individual = s[1].split(" ");
            for(String i : individual){
                List<Integer> wordNos = nounz.getOrDefault(i, new ArrayList<>());
                wordNos.add(Integer.parseInt(s[0]));
                nounz.put(i, wordNos);
            }
        });


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
        return nounz.containsKey(word);
    }

    @Override
    public int distance(String A, String B) throws Exception {

       // int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
      //  int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);
        Iterable<Integer> a = findNoun(A);
        Iterable<Integer> b = findNoun(B);

            return helper.length(a, b);

    }

    public Iterable<Integer> findNoun(String noun){

       // int m = Arrays.binarySearch(nouns, noun, String::compareTo);

        return nounz.get(noun);

    }

    @Override
    public String sap(String A, String B) {
      //  int a = Arrays.binarySearch(nouns, A, String::compareToIgnoreCase);
        //int b = Arrays.binarySearch(nouns, B, String::compareToIgnoreCase);

        List<Integer> a = nounz.get(A);
        List<Integer> b = nounz.get(B);
        int sca = helper.ancestor(a,b);
        return nouns[sca];
    }


}
