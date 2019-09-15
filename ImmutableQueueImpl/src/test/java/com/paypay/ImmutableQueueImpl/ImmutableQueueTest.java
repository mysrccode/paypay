package com.paypay.ImmutableQueueImpl;


import java.util.NoSuchElementException;

import org.junit.Test;

import junit.framework.Assert;

public class ImmutableQueueTest {

    @Test
    public void shouldBeAbleToCreateEmptyQueue(){
        final Queue<String> queue = new ImmutableQueue<>();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenCallHeadOnEmptyQueue(){
        final Queue<String> queue = new ImmutableQueue<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertNull(queue.head());
    }

    @Test
    public void shouldBeAbleToEnqueueElementAndGetNewQueueCreated(){
        final Queue<String> queue = new ImmutableQueue<>();
        final Queue<String> newQueue = queue.enQueue("Test");
        Assert.assertTrue(newQueue.head().equals("Test"));
        Assert.assertTrue(queue.isEmpty());
        Assert.assertTrue(!newQueue.isEmpty());
    }

    @Test
    public void shouldBeAbleToDeQueueElementAndGetNewQueueCreated(){
        final Queue<String> queue = new ImmutableQueue<>();
        final Queue<String> newQueue1 = queue.enQueue("Test");
        Assert.assertTrue(queue.isEmpty());
        Assert.assertTrue(!newQueue1.isEmpty());
        Assert.assertTrue(newQueue1.head().equals("Test"));
        final Queue<String> newQueue2 = newQueue1.deQueue();
        Assert.assertTrue(newQueue1.head().equals("Test"));
        Assert.assertTrue(!newQueue1.isEmpty());
        Assert.assertTrue(newQueue2.isEmpty());
    }

}