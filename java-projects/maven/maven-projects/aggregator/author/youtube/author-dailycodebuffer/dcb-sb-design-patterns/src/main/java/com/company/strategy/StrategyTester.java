package com.company.strategy;

import com.company.strategy.impl.AdditionStrategy;
import com.company.strategy.impl.MultiplicationStrategy;
import com.company.strategy.impl.SubtractionStrategy;

public class StrategyTester {

    public static void main(String[] args) {
        System.out.println(new Context(new AdditionStrategy()).executeStrategy(10, 5));
        System.out.println(new Context(new SubtractionStrategy()).executeStrategy(10, 5));
        System.out.println(new Context(new MultiplicationStrategy()).executeStrategy(10, 5));
    }
}
