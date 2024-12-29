package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn ;


    public HyponymsHandler(WordNet wn) {
        super();
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        return wn.doSomething();
    }
}
