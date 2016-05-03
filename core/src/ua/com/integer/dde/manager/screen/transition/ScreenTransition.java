package ua.com.integer.dde.manager.screen.transition;

import com.badlogic.gdx.Screen;

import ua.com.integer.dde.DDECore;

public abstract class ScreenTransition {
	protected Class<? extends Screen> next;
	
	public void transit(Class<? extends Screen> next) {
		this.next = next;

		beforeTransaction();
		
		performTransition();
	};
	
	public void beforeTransaction() {
	}
	
	public abstract void performTransition();
	
	protected DDECore getCore() {
		return DDECore.getInstance();
	}
	
	protected Screen getNextScreen() {
		return getCore().screens().getScreen(next);
	}
	
	public ScreenTransition create() {
		return this;
	}
}
