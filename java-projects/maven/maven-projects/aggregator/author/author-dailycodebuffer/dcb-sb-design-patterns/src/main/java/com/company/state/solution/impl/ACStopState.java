package com.company.state.solution.impl;

public class ACStopState implements State {

    @Override
    public void doAction() {
        System.out.println("AC is OFF");
    }

}
