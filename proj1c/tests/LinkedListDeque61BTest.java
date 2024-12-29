import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;


public class LinkedListDeque61BTest {


    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testIteratorEmptyDeque() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        Iterator<Integer> it = deque.iterator();

        // 空队列，hasNext 应该返回 false
        assertFalse(it.hasNext());

        // 调用 next() 时应该抛出 NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    @Test
    public void testIteratorSingleElementDeque() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        Iterator<Integer> it = deque.iterator();

        // 队列有一个元素，hasNext 应该返回 true
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(10), it.next());

        // 没有更多元素了，hasNext 应该返回 false
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorMultipleElements() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> it = deque.iterator();

        // 按顺序返回元素 1, 2, 3
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());

        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());

        // 没有更多元素了，hasNext 应该返回 false
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorAddFirstAndLast() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(25);

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
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 移除第一个元素
        deque.removeFirst();

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
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();

        // 添加多个元素，确保迭代器正确遍历
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }

        Iterator<Integer> it = deque.iterator();

        // 确保元素按顺序遍历
        for (int i = 0; i < 20; i++) {
            assertTrue(it.hasNext());
            assertEquals(Integer.valueOf(i), it.next());
        }

        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorAfterRemovingAll() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 清空队列
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        // 此时队列为空，迭代器应不再有元素
        Iterator<Integer> it = deque.iterator();

        assertFalse(it.hasNext());

        // 调用 next() 时应抛出 NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> it.next());
    }

    //toString Test
    @Test
    public void testEmptyDequeToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        assertThat(deque.toString()).isEqualTo("[]");
    }

    @Test
    public void testAddFirstToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(10);
        deque.addFirst(20);
        assertThat(deque.toString()).isEqualTo("[20, 10]");
    }

    @Test
    public void testAddLastToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        assertThat(deque.toString()).isEqualTo("[10, 20]");
    }

    @Test
    public void testAddFirstAndLastToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(30);
        deque.addFirst(20);
        deque.addLast(40);
        deque.addLast(50);
        assertThat(deque.toString()).isEqualTo("[20, 30, 40, 50]");
    }

    @Test
    public void testRemoveFirstToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.removeFirst();
        assertThat(deque.toString()).isEqualTo("[20, 30]");
    }

    @Test
    public void testRemoveLastToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.removeLast();
        assertThat(deque.toString()).isEqualTo("[10, 20]");
    }

    @Test
    public void testClearDequeToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        while (!deque.isEmpty()) {
            deque.removeFirst();
        }
        assertThat(deque.toString()).isEqualTo("[]");
    }

    @Test
    public void testMixedOperationsToString() {
        LinkedListDeque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.removeFirst();
        deque.addFirst(5);
        deque.addLast(40);
        assertThat(deque.toString()).isEqualTo("[5, 20, 30, 40]");
    }
}
