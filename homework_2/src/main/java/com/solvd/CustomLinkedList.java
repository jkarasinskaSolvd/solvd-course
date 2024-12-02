package com.solvd;

public class CustomLinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public T getFirst() {
        return head.data;
    }

    public T getLast() {
        return tail.data;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.previous = tail;
        tail.next = newNode;
        tail = newNode;

        size++;
    }


    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            addLast(data);
        }
    }

    public void addInPosition(int index, T data) {
        checkIndex(index);

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            addFirst(data);
        } else {
            Node<T> prev = getNode(index - 1);
            if (index == size - 1) {
                addLast(data);
            } else {
                newNode.next = prev.next;
                prev.next = newNode;
                newNode.previous = prev;
                newNode.next.previous = newNode;
                size++;
            }
        }

    }

    public void removeFirst() {
        Node<T> next = getNode(1);

        if (head == null) {
            throw new IndexOutOfBoundsException("You can't remove from empty list");
        }else{
            next.previous = null;
            head.next = null;
            head = next;
        }

        size--;
    }

    public void removeLast() {
        Node<T> prev = getNode(size - 2);

        prev.next = null;
        tail.previous = null;
        tail = prev;
    }

    public void remove(int index) {
        checkIndex(index);

        Node<T> prev = getNode(index - 1);
        Node<T> next = getNode(index + 1);
        Node<T> thisNode = getNode(index);
        if (index == 0) {
            removeFirst();
        } else {
            if (index == size - 1) {
                removeLast();
            } else {
                prev.next = next;
                next.previous = prev;
                thisNode.next = null;
                thisNode.previous = null;
                size--;
            }
        }

    }

    public void remove(T data) {
        for (int i = 0; i < size; i++) {
            if(getNode(i).data.equals(data)) {
                remove(i);
            }
        }
    }

    private Node<T> getNode(int index) {
        checkIndex(index);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid Index (index must be between 0 and " + (size - 1) + ")");
        }
    }



}
