import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

    /**
     * 測試 addFirst 方法的基本功能。
     */
    @Test
    public void addFirstTestBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("back");
        assertThat(ad.toList()).containsExactly("back").inOrder();
        ad.addFirst("middle");
        assertThat(ad.toList()).containsExactly("middle", "back").inOrder();
        ad.addFirst("front");
        assertThat(ad.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    /**
     * 測試 addLast 方法的基本功能。
     */
    @Test
    public void addLastTestBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    /**
     * 測試 isEmpty 方法。
     */
    @Test
    public void isEmptyTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        assertThat(ad.isEmpty()).isTrue();
        ad.addFirst("front");
        assertThat(ad.isEmpty()).isFalse();
    }

    /**
     * 測試 size 方法。
     */
    @Test
    public void sizeTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        assertThat(ad.size()).isEqualTo(0);
        ad.addFirst("front");
        assertThat(ad.size()).isEqualTo(1);
        ad.addLast("back");
        assertThat(ad.size()).isEqualTo(2);
    }

    /**
     * 測試 get 方法。
     */
    @Test
    public void getTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.get(0)).isEqualTo("front");
        assertThat(ad.get(1)).isEqualTo("middle");
        assertThat(ad.get(2)).isEqualTo("back");
        assertThat(ad.get(3)).isNull();
        assertThat(ad.get(-1)).isNull();
    }

    /**
     * 測試 getRecursive 方法。
     */
    @Test
    public void getRecursiveTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.getRecursive(0)).isEqualTo("front");
        assertThat(ad.getRecursive(1)).isEqualTo("middle");
        assertThat(ad.getRecursive(2)).isEqualTo("back");
        assertThat(ad.getRecursive(3)).isNull();
        assertThat(ad.getRecursive(-1)).isNull();
    }

    /**
     * 測試 removeFirst 方法。
     */
    @Test
    public void removeFirstTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeFirst()).isEqualTo("front");
        assertThat(ad.toList()).containsExactly("middle", "back").inOrder();
        assertThat(ad.removeFirst()).isEqualTo("middle");
        assertThat(ad.toList()).containsExactly("back").inOrder();
        assertThat(ad.removeFirst()).isEqualTo("back");
        assertThat(ad.toList()).isEmpty();
    }

    /**
     * 測試 removeLast 方法。
     */
    @Test
    public void removeLastTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeLast()).isEqualTo("back");
        assertThat(ad.toList()).containsExactly("front", "middle").inOrder();
        assertThat(ad.removeLast()).isEqualTo("middle");
        assertThat(ad.toList()).containsExactly("front").inOrder();
        assertThat(ad.removeLast()).isEqualTo("front");
        assertThat(ad.toList()).isEmpty();
    }

    /**
     * 測試在移除所有元素後再次添加元素。
     */
    @Test
    public void addAfterRemoveTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeFirst()).isEqualTo("front");
        assertThat(ad.removeFirst()).isEqualTo("middle");
        assertThat(ad.removeFirst()).isEqualTo("back");
        assertThat(ad.isEmpty()).isTrue();

        ad.addFirst("newFront");
        assertThat(ad.toList()).containsExactly("newFront").inOrder();
        ad.addLast("newBack");
        assertThat(ad.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    /**
     * 測試 addFirst 和 addLast 方法的混合使用。
     */
    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(10);
        ad.addLast(20);
        ad.addFirst(5);
        ad.addLast(30);
        assertThat(ad.toList()).containsExactly(5, 10, 20, 30).inOrder();
    }

    /**
     * 測試多種操作的組合。
     */
    @Test
    public void mixedOperationsTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("first");
        ad.addLast("last");
        assertThat(ad.size()).isEqualTo(2);
        assertThat(ad.removeFirst()).isEqualTo("first");
        assertThat(ad.removeLast()).isEqualTo("last");
        assertThat(ad.size()).isEqualTo(0);
    }

    /**
     * 測試多次移除操作。
     */
    @Test
    public void multipleRemovalsTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("1");
        ad.addFirst("2");
        ad.addFirst("3");
        assertThat(ad.removeFirst()).isEqualTo("3");
        assertThat(ad.removeFirst()).isEqualTo("2");
        assertThat(ad.removeFirst()).isEqualTo("1");
        assertThat(ad.isEmpty()).isTrue();
    }

    /**
     * 測試添加大量元素和移除操作。
     */
    @Test
    public void addTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i++) {
            ad.addLast(i);
        }
        assertThat(ad.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(ad.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 20; i++) {
            ad.removeFirst();
        }
        for (int i = 0; i < 20; i++) {
            ad.addFirst(i);
        }
        assertThat(ad.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(ad.get(i)).isEqualTo(19 - i);
        }
    }

    /**
     * 測試陣列擴展和縮小的功能。
     */
    @Test
    public void advancedResizeTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        for (int i = 0; i < 100; i++) {
            ad.addLast(i);
        }
        assertThat(ad.size()).isEqualTo(100);
        for (int i = 0; i < 100; i++) {
            assertThat(ad.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 75; i++) {
            ad.removeFirst();
        }
        assertThat(ad.size()).isEqualTo(25);
        for (int i = 0; i < 25; i++) {
            assertThat(ad.get(i)).isEqualTo(i + 75);
        }
        for (int i = 0; i < 25; i++) {
            ad.addFirst(i);
        }
        assertThat(ad.size()).isEqualTo(50);
        for (int i = 0; i < 25; i++) {
            assertThat(ad.get(i)).isEqualTo(24 - i);
        }
        for (int i = 25; i < 50; i++) {
            assertThat(ad.get(i)).isEqualTo(i + 50);
        }
    }

    /**
     * 測試在移除所有元素後再次添加元素。
     */
    @Test
    public void addAfterRemoveToEmptyTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeFirst()).isEqualTo("front");
        assertThat(ad.removeFirst()).isEqualTo("middle");
        assertThat(ad.removeFirst()).isEqualTo("back");
        assertThat(ad.isEmpty()).isTrue();

        ad.addFirst("newFront");
        assertThat(ad.toList()).containsExactly("newFront").inOrder();
        ad.addLast("newBack");
        assertThat(ad.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    /**
     * 測試多種情景的添加和移除操作。
     */
    @Test
    public void addAfterRemoveComprehensiveTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("first");
        ad.addLast("second");
        ad.addLast("third");
        assertThat(ad.removeFirst()).isEqualTo("first");
        assertThat(ad.removeFirst()).isEqualTo("second");
        assertThat(ad.removeFirst()).isEqualTo("third");
        assertThat(ad.isEmpty()).isTrue();

        ad.addLast("newFirst");
        assertThat(ad.toList()).containsExactly("newFirst").inOrder();
        ad.addFirst("newSecond");
        assertThat(ad.toList()).containsExactly("newSecond", "newFirst").inOrder();
    }
}