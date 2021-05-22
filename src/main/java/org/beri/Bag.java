package org.beri;

import java.util.Iterator;

public class Bag<T> {


    public Iterator<T> getAll() {
        return new Iterator() {
            Node<T> current = first;

            @Override
            public boolean hasNext() {

                return (current != null);
            }

            @Override
            public T next() {

                T data = current.data;
                current = current.next;
                return data;
            }

        };
    }


    Node<T> first;
    int count;

    public Bag() {
        first = null;
        count = 0;
    }

    public Bag(T data) {
        first = new Node<>(data);
        count++;
    }

    public void addItem(T item) {
        if (first == null) {
            first = new Node<>(item);
            count++;
        } else {
            Node previous = first;
            Node newFirst = new Node<>(item);
            newFirst.next = previous;
            first = newFirst;
            count++;
        }
    }

     class Node<T> {

        T data;
        Node<T> next;

        Node(T item) {
            data = item;
            next = null;
        }

    }
}
