package org.beri;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TestClass {
    public static void main(String args[]) throws IOException {

        WordNet wn = new WordNetImpl();
        System.out.println(wn.isNoun("Ahura"));
        System.out.println(wn.isNoun("zoomorphism"));
        System.out.println(wn.isNoun("zygodactyl_foot"));
        System.out.println(wn.isNoun("zoology zoological_science"));
        System.out.println(wn.isNoun("mama papa bear"));
    }
}
