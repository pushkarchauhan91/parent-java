package com.company.state;

import com.company.state.solution.Context;
import com.company.state.solution.impl.ACStartState;
import com.company.state.solution.impl.ACStopState;

public class StateTester {

    public static void main(String[] args) {
        ACStartState acStartState = new ACStartState();
        Context context = new Context(acStartState);
        context.setState(acStartState);
        context.doAction();

        System.out.println("------------------------");

        ACStopState acStopState = new ACStopState();
        context.setState(acStopState);
        context.doAction();
    }
}
