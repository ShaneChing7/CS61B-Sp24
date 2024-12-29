package main;

public class WordNet {

    Graph graph;

    public WordNet(String synsetFile, String hyponymFile){

        graph  = new Graph();
    }

    public String doSomething() {

        return graph.DoSomething();
    }
}
