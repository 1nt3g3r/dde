package ua.com.integer.dde.desktop.test;

import ua.com.integer.dde.DDECore;

public class TestGame extends DDECore {
	@Override
	public void ready() {
		System.out.println("Test game launched!");
	}
	
	@Override
	public void dispose() {
		System.out.println("Game finished");
		super.dispose();
	}
}
