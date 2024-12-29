import static com.google.common.truth.Truth.assertThat;

import ngrams.TimeSeries;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

public class TimeSeries_dividedTest {

    private TimeSeries ts1;
    private TimeSeries ts2;

    @Before
    public void setUp() {
        // 初始化两个 TimeSeries 实例
        ts1 = new TimeSeries();
        ts2 = new TimeSeries();

        // 填充 ts1 和 ts2 的数据
        ts1.put(2000, 10.0);
        ts1.put(2005, 15.0);
        ts1.put(2010, 20.0);

        ts2.put(2000, 2.0);
        ts2.put(2005, 5.0);
        ts2.put(2010, 4.0);
    }

    @Test
    public void testDividedBy_withCommonYears() {
        // 执行 dividedBy 操作
        TimeSeries result = ts1.dividedBy(ts2);

        // 断言结果
        assertThat(result).containsExactly(
                2000, 5.0,   // 10.0 / 2.0
                2005, 3.0,   // 15.0 / 5.0
                2010, 5.0    // 20.0 / 4.0
        );
    }

    @Test
    public void testDividedBy_withMissingYearInTs2() {
        // 给 ts2 去掉某个年份的数据
        ts2.remove(2005); // 移除 2005 年的数据

        // 执行 dividedBy 操作
        try {
            ts1.dividedBy(ts2);
        } catch (IllegalArgumentException e) {
            // 预期抛出 IllegalArgumentException
            assertThat(e).hasMessageThat().contains("Missing year in the second TimeSeries");
        }
    }

    @Test
    public void testDividedBy_withExtraYearInTs2() {
        // 给 ts2 添加额外的年份
        ts2.put(2015, 1.0); // 添加 2015 年的数据

        // 执行 dividedBy 操作
        TimeSeries result = ts1.dividedBy(ts2);

        // 断言结果应该忽略 ts2 中额外的 2015 年
        assertThat(result).containsExactly(
                2000, 5.0,
                2005, 3.0,
                2010, 5.0
        );
    }

    @Test
    public void testDividedBy_withEmptyTs() {
        // 创建空的 TimeSeries 对象
        TimeSeries emptyTs = new TimeSeries();

        // 执行 dividedBy 操作
        TimeSeries result = ts1.dividedBy(emptyTs);

        // 结果应该为空
        assertThat(result).isEmpty();
    }
}
