package ngrams;

import edu.princeton.cs.algs4.In;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    HashMap<String, TimeSeries> wordsMap = new HashMap<>();
    TimeSeries countsTs = new TimeSeries();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {

        In wordsIn = new In(wordsFilename);
        In countsIn = new In(countsFilename);

        while (wordsIn.hasNextLine()) {
            String nextLine = wordsIn.readLine();
            String[] splitLine = nextLine.split("\t");//splitLine: [word,year,timesOfTheWord,...]
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            double counts = Double.parseDouble(splitLine[2]);

            if (wordsMap.containsKey(word)) {
                TimeSeries ts = wordsMap.get(word);
                ts.put(year, counts);
            } else {
                TimeSeries ts = new TimeSeries();
                ts.put(year, counts);
                wordsMap.put(word, ts);
            }

        }

        while (countsIn.hasNextLine()) {
            String nextLine = countsIn.readLine();
            String[] splitLine = nextLine.split(",");//splitLineArray: [year,total number of words recorded from all texts that year,...]
            int year = Integer.parseInt(splitLine[0]);
            double numberOfYear = Double.parseDouble(splitLine[1]);

            countsTs.put(year, numberOfYear);
        }

    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries countSeries = new TimeSeries();
        if (wordsMap.containsKey(word)) {
            TimeSeries wordTs = wordsMap.get(word);
            for (int i = startYear; i <= endYear; i++) {
                Double data = wordTs.get(i);
                if (data != null) {
                    countSeries.put(i, data);
                }
            }
        }
        return countSeries;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries countSeries = new TimeSeries();
        if (wordsMap.containsKey(word)) {
            TimeSeries wordTs = wordsMap.get(word);
            for (int year : wordTs.years()) {
                Double data = wordTs.get(year);
                countSeries.put(year, data);
            }
        }
        return countSeries;

        //用TimeSeries的构造方法更简便
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {

        return new TimeSeries(countsTs, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries weight = new TimeSeries();

        // 检查 wordsMap 中是否包含该词
        if (wordsMap.containsKey(word)) {
            TimeSeries ts = countHistory(word, startYear, endYear);

            // 遍历年份
            for (int year : ts.years()) {
                Double wordCount = ts.get(year); // 获取词频数据
                Double totalCount = countsTs.get(year); // 获取总词频数据

                // 如果 wordCount 或 totalCount 为 null，设置默认值为 0
                if (wordCount == null) {
                    wordCount = 0.0;
                }
                if (totalCount == null || totalCount == 0) {
                    totalCount = 1.0; // 防止除以 0，设置为 1
                }

                // 计算相对频率并存入结果中，但只有当 wordCount 非 0 时才添加
                if (wordCount > 0) {
                    weight.put(year, wordCount / totalCount);
                }
            }
        }

        return weight;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries weight = new TimeSeries();
        if (wordsMap.containsKey(word)) {
            TimeSeries ts = countHistory(word);
            for (int year : ts.years()) {
                double WordCount = ts.get(year);
                double TotalWordCount = countsTs.get(year);
                weight.put(year, WordCount / TotalWordCount);
            }
        }
        return weight;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries summedWeight = new TimeSeries();
            for (String word : words) {
                if(wordsMap.containsKey(word)) {
                    TimeSeries A = this.weightHistory(word, startYear, endYear);//指定年份的频率
                    summedWeight = summedWeight.plus(A);
                }
            }
        return summedWeight;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries summedWeight = new TimeSeries();
        for (String word : words) {
            if(wordsMap.containsKey(word)) {
                TimeSeries A = this.weightHistory(word);//所有年份的频率
                summedWeight = summedWeight.plus(A);
            }
        }
        return summedWeight;
    }

}
