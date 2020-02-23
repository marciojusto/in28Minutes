package com.in28minutes.spring.basics.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSortAlgorithm implements SortAlgorithm {
    public int[] sort(int[] numbers, int number) {
        // Logic for Bubble Sort
        return numbers;
    }
}
