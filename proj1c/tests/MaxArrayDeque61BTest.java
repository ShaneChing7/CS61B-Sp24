

import static com.google.common.truth.Truth.assertThat;

import deque.MaxArrayDeque61B;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDeque61BTest {

    @Test
    public void testMaxWithNaturalOrderComparator() {
        // 自然顺序比较器（升序）
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();

        MaxArrayDeque61B<Integer> deque = new MaxArrayDeque61B<>(naturalOrder);
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        // 默认 max 使用 naturalOrder 比较器
        assertThat(deque.max()).isEqualTo(30);

        // 使用同样的比较器，调用 max(Comparator<T> c)
        assertThat(deque.max(naturalOrder)).isEqualTo(30);
    }

    @Test
    public void testMaxWithReverseOrderComparator() {
        // 逆序比较器（降序）
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();

        MaxArrayDeque61B<Integer> deque = new MaxArrayDeque61B<>(reverseOrder);
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        // 默认 max 使用 reverseOrder 比较器
        assertThat(deque.max()).isEqualTo(10);

        // 使用 naturalOrder 比较器进行测试
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        assertThat(deque.max(naturalOrder)).isEqualTo(30);
    }

    @Test
    public void testMaxWithStringLengthComparator() {
        // 自定义比较器：按字符串长度排序，长度相同时按字典顺序
        Comparator<String> stringLengthComparator = Comparator
                .comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder());

        MaxArrayDeque61B<String> deque = new MaxArrayDeque61B<>(stringLengthComparator);
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("cherry");

        // 按长度比较，最大值应该是 "banana"
        assertThat(deque.max()).isEqualTo("cherry");

        // 使用字典顺序比较器
        Comparator<String> alphabeticalOrder = Comparator.naturalOrder();
        assertThat(deque.max(alphabeticalOrder)).isEqualTo("cherry");
    }



    @Test
    public void testMaxWithCustomAbsoluteValueComparator() {
        // 自定义比较器：按绝对值排序
        Comparator<Integer> absoluteValueComparator = (a, b) -> Integer.compare(Math.abs(a), Math.abs(b));

        MaxArrayDeque61B<Integer> deque = new MaxArrayDeque61B<>(absoluteValueComparator);
        deque.addLast(-10);
        deque.addLast(20);
        deque.addLast(-30);

        // 默认 max 使用 absoluteValueComparator
        assertThat(deque.max()).isEqualTo(-30);

        // 使用自然顺序比较器
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        assertThat(deque.max(naturalOrder)).isEqualTo(20);
    }

    @Test
    public void testMaxWithEmptyDeque() {
        // 自然顺序比较器（升序）
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();

        MaxArrayDeque61B<Integer> deque = new MaxArrayDeque61B<>(naturalOrder);

        // 空队列调用 max 方法应返回 null
        assertThat(deque.max()).isNull();
        assertThat(deque.max(naturalOrder)).isNull();
    }
}
