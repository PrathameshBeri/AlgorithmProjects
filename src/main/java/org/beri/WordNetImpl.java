package org.beri;

import java.util.Arrays;

public class WordNetImpl implements WordNet {

    String[] nouns;
    Integer[] adjMatrix;

    WordNetImpl(int numbers, String arra){
        nouns = new String[numbers];
        adjMatrix = new Integer[numbers];
    }

    @Override
    public Iterable<String> nouns() {
        return Arrays.asList(nouns);
    }

    @Override
    public boolean isNoun(String word) {
        return false;
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
