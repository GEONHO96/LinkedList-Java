import java.util.Iterator;
import java.util.NoSuchElementException;

class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    // Node 클래스
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // MyLinkedList의 Iterator 구현
    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> current;

        MyLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    // Iterator를 반환하는 메서드
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    // MyLinkedList에 데이터 추가
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // MyLinkedList의 특정 인덱스의 데이터 반환
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // MyLinkedList의 특정 인덱스의 데이터 삭제
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    // MyLinkedList의 크기 반환
    public int size() {
        return size;
    }
}

// Queue 클래스
class Queue<T> {
    private MyLinkedList<T> list;

    Queue() {
        list = new MyLinkedList<>();
    }

    public void enqueue(T data) {
        list.add(data);
    }

    public T dequeue() {
        if (list.iterator().hasNext()) {
            T data = list.get(0);
            list.delete(0);
            return data;
        } else {
            throw new NoSuchElementException("Queue is empty");
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}

// Stack 클래스
class Stack<T> {
    private MyLinkedList<T> list;

    Stack() {
        list = new MyLinkedList<>();
    }

    public void push(T data) {
        list.add(data);
    }

    public T pop() {
        if (list.iterator().hasNext()) {
            T data = list.get(list.size() - 1);
            list.delete(list.size() - 1);
            return data;
        } else {
            throw new NoSuchElementException("Stack is empty");
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}

// 테스트용 메인 클래스
public class LinkedList {
    public static void main(String[] args) {
        // MyLinkedList 테스트
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        System.out.println("MyLinkedList:");
        for (int data : linkedList) {
            System.out.println(data);
        }

        // Queue 테스트
        Queue<String> queue = new Queue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println("\nQueue:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

        // Stack 테스트
        Stack<Character> stack = new Stack<>();
        stack.push('X');
        stack.push('Y');
        stack.push('Z');

        System.out.println("\nStack:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
