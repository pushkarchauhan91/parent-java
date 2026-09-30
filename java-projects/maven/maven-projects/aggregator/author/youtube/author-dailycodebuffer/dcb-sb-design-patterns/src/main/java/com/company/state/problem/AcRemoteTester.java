package com.company.state.problem;

public class AcRemoteTester {

    private String state;

    private void setState(String state) {
        this.state = state;
    }

    private void doPerform() {
        if ("ON".equals(state)) {
            System.out.println("AC is ON");
        } else if ("OFF".equals(state)) {
            System.out.println("AC is OFF");
        }
    }

    public static void main(String[] args) {
        AcRemoteTester acRemoteTester = new AcRemoteTester();
        acRemoteTester.setState("ON");
        acRemoteTester.doPerform();

        acRemoteTester.setState("OFF");
        acRemoteTester.doPerform();
    }
}
// Client should know specific states in advance
// What if number of states increase