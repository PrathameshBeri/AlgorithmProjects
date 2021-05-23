package org.beri;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class SAPtest {
    public static void main(String args[]) throws FileNotFoundException {



        Scanner sc = new Scanner(new File("C:\\Users\\HP\\Desktop\\Spring projects\\JDBC" +
                " projects\\AlgorithmsProject\\src\\main\\resources\\digraph25.txt"));
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        Digraph d = new Digraph(vertices);

        for(int i = 0; i < edges; i++){
            String[] ed = sc.nextLine().split("\\s+");
            int x = Integer.parseInt(ed[0]);
            int y = Integer.parseInt(ed[1]);
            d.addEdge(x, y);
        }

        SAPImpl sapImp = new SAPImpl(d);
        //System.out.println(sapImp.path(3,11));

        System.out.println(sapImp.length(Arrays.asList(13,23,24), Arrays.asList(6,16,17)));

    }
}
