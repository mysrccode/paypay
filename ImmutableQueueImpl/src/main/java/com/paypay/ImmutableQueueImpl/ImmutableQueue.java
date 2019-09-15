package com.paypay.ImmutableQueueImpl;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * An immutable queue based on two stacks.
 * It is based on Eric Lippert paper - https://blogs.msdn.microsoft.com/ericlippert/2007/12/10/immutability-in-c-part-four-an-immutable-queue/
 * @param <T>
 */
public class ImmutableQueue<T> implements Queue<T> {

    private ImmutableStack front; // stack to remove elements from
    private ImmutableStack back; // stack to add elements to

    public ImmutableQueue() { // empty queue using two empty stacks
        this.front = ImmutableStack.emptyStack();
        this.back = ImmutableStack.emptyStack();
    }

    public ImmutableQueue(ImmutableStack<T> front, ImmutableStack<T> back) {
        this.front = front;
        this.back = back;
    }

    /**
     * Add given element to queue
     * @param element
     * @return immutable Queue<T>
     */
    @Override
    public Queue<T> enQueue(T element) {
        Objects.requireNonNull(element, "given element is null");
        return new ImmutableQueue<T>(this.front.push(element), this.back);
    }

    /**
     * DeQueue element from queue
     * throw NoSuchElementException if queue is empty
     * @return immutable Queue<T>
     */
    @Override
    public Queue<T> deQueue() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        if (!this.back.isEmpty()) {
            return new ImmutableQueue<T>(this.front, this.back.tail);
        } else {
            return new ImmutableQueue<T>(ImmutableStack.emptyStack(), this.front.reverse().tail);
        }
    }

    /**
     * Get the head element
     * if Object is empty throw and Exception NoSuchElementException.
     * @return head element of queue
     */
    @Override
    public T head() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        if (this.back.isEmpty())
            balanceQueue();
        return (T) this.back.head;
    }

    private void balanceQueue() {
        this.back = this.front.reverse();
        this.front = ImmutableStack.emptyStack();
    }

    @Override
    public boolean isEmpty() {
        return (this.front.size + this.back.size) ==0;
    }

    private static class ImmutableStack<T> {

        private final T head;
        private final ImmutableStack<T> tail;
        private int size;

        private ImmutableStack() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private ImmutableStack(T element, ImmutableStack<T> tail) {
            this.head = element;
            this.tail = tail;
            this.size = tail.size + 1;
        }

        public static ImmutableStack emptyStack() {
            return new ImmutableStack();
        }

        public ImmutableStack<T> pop() {
            return this.tail;
        }

        public ImmutableStack<T> push(T element) {
            return new ImmutableStack<>(element, this);
        }

        //Reverses the  stack
        public ImmutableStack<T> reverse() {
            ImmutableStack<T> stack = new ImmutableStack<T>();
            ImmutableStack<T> tail = this;
            while (!tail.isEmpty()) {
                stack = stack.push(tail.head);
                tail = tail.tail;
            }
            return stack;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }
}