package org.beri;

public interface WordNet {

    public Iterable<String> nouns();
    public boolean isNoun(String word);
    public int distance(String A, String B) throws Exception;
    public String sap(String A, String B);
    //constructor as well
}
