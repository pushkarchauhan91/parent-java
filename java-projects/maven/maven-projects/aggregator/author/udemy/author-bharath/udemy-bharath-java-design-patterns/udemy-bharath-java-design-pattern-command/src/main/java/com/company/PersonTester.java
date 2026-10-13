package com.company;

public class PersonTester {

	public static void main(String[] args) {
		
		Television television = new Television();
		
		RemoteControl remoteControl = new RemoteControl();
	
		OnCommand onCommand = new OnCommand(television);
		remoteControl.setCommand(onCommand);
		remoteControl.pressButton();
		
		OffCommand offCommand = new OffCommand(television);
		remoteControl.setCommand(offCommand);
	}

}
