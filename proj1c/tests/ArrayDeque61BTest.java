import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

public class ArrayDeque61BTest {

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }
    @Test
    public void testIteratorEmptyDeque() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        Iterator<Integer> it = deque.iterator();
        // 队列为空，hasNext 应该返回 false
        assertFalse(it.hasNext());
        // 调用 next() 时应该抛出 NoSuchElementException
        try {
            it.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // 测试通过
        }
    }

    @Test
    public void testIteratorSingleElementDeque() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(10);
        Iterator<Integer> it = deque.iterator();

        // 应该有一个元素
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(10), it.next());
        // 没有更多元素了
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorMultipleElements() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> it = deque.iterator();

        // 依次返回元素 1, 2, 3
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());

        // 没有更多元素了
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorAddFirstAndLast() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(25);

        // 创建迭代器
        Iterator<Integer> it = deque.iterator();

        // 预期输出 5, 10, 20, 25
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(5), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(10), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(20), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(25), it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorRemoveFirstAndLast() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 先移除一个元素
        deque.removeFirst();

        // 创建迭代器
        Iterator<Integer> it = deque.iterator();

        // 预期输出 2, 3
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorWithResize() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        // Add elements to force a resize
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        Iterator<Integer> it = deque.iterator();

        // Ensure elements are correctly iterated after resizing
        for (int i = 0; i < 20; i++) {
            assertTrue(it.hasNext());
            assertEquals(Integer.valueOf(i), it.next());
        }

        assertFalse(it.hasNext());
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    //toString Test

    @Test
    public void testEmptyDequeToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.toString()).isEqualTo("[]");
    }

    @Test
    public void testAddFirstToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        deque.addFirst(20);
        assertThat(deque.toString()).isEqualTo("[20, 10]");
    }

    @Test
    public void testAddLastToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        assertThat(deque.toString()).isEqualTo("[10, 20]");
    }

    @Test
    public void testAddAndRemoveToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addLast(30);
        assertThat(deque.toString()).isEqualTo("[10, 20, 30]");

        deque.removeFirst();
        assertThat(deque.toString()).isEqualTo("[20, 30]");

        deque.removeLast();
        assertThat(deque.toString()).isEqualTo("[20]");
    }

    @Test
    public void testResizeToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 1; i <= 10; i++) {
            deque.addLast(i * 10);
        }
        assertThat(deque.toString()).isEqualTo("[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]");
    }

    @Test
    public void testClearDequeToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        while (!deque.isEmpty()) {
            deque.removeFirst();
        }
        assertThat(deque.toString()).isEqualTo("[]");
    }
}
