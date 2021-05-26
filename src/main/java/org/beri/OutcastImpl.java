package org.beri;

public class OutcastImpl implements Outcast{

    WordNet wn;

    public  OutcastImpl(WordNet wordnet) {

        wn = wordnet;
    }

    @Override
    public String outcast(String[] nouns) throws Exception {
        int max_dist = Integer.MIN_VALUE;
        String outcast = null;

        for(int i = 0; i < nouns.length; i++){
            int dist =0;
            for(String s : nouns){
                if(s.equals(nouns[i])) continue;

                dist += wn.distance(nouns[i], s);
            }
            if(dist > max_dist) {
                max_dist = dist;
                outcast = nouns[i];
            }
        }

        return outcast;
    }
}
