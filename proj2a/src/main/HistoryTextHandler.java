package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private final NGramMap ngm;
    public HistoryTextHandler(NGramMap ngm) {
        this.ngm = ngm;
    }

    //   下面的q是前端输入框中接受的请求
    //   public record NgordnetQuery(List<String> words,
    //                                int startYear,
    //                                int endYear,
    //                                int k) {
    //    }
    @Override
    public String handle(NgordnetQuery q) {
        StringBuilder sb = new StringBuilder();
        int startYear = q.startYear();
        int endYear = q.endYear();
        for (var word : q.words()) {
            sb.append(word);
            sb.append(": ");
            //在NGramMap的对象中一一找的前端输入的单词，把单词的startYear-endYear对应年份的频率一一转换成字符串进行拼接
            //cat: {2016=2.3157514119191215E-5, 2017=2.482102172595473E-5}
            //dog: {2016=4.805214145459557E-5, 2017=5.4171157785607686E-5}
            sb.append(ngm.weightHistory(word, startYear , endYear).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}