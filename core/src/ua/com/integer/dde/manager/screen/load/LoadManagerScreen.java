package ua.com.integer.dde.manager.screen.load;

import ua.com.integer.dde.manager.LoadManager;
import ua.com.integer.dde.manager.screen.StageScreen;
import ua.com.integer.dde.manager.screen.ScreenEvent;
import ua.com.integer.dde.manager.screen.ScreenListener;
import ua.com.integer.dde.manager.screen.transition.ScreenTransition;
import ua.com.integer.dde.manager.screen.transition.imp.SideOutTransition;

public class LoadManagerScreen extends StageScreen {
	private LoadManager loadManager;
	private Class<? extends StageScreen> nextScreen;
	private float lastPercent;
	private LoadListener loadListener;
	private ScreenTransition screenTransition = SideOutTransition.right();
	
	private boolean firstFrameRendered;
	
	public LoadManagerScreen() {
		initScreenEventListener();
	}
	
	private void initScreenEventListener() {
		addScreenEventListener(new ScreenListener() {
			@Override
			public void eventHappened(StageScreen screen, ScreenEvent event) {
				switch(event) {
				case SHOW:
					lastPercent = 0f;
					if (loadListener != null) {
						loadListener.loadPercentChanged(0f);
					}
					break;
				default:
					break;
				}
			}
		});
	}
	
	public void setLoadManager(LoadManager loadManager) {
		this.loadManager = loadManager;
		firstFrameRendered = false;
	}
	
	public void setNextScreen(Class<? extends StageScreen> nextScreen) {
		this.nextScreen = nextScreen;
		firstFrameRendered = false;
	}
	
	public void setScreenTransition(ScreenTransition screenTransition) {
		this.screenTransition = screenTransition;
		firstFrameRendered = false;
	}
	
	public void setLoadListener(LoadListener loadListener) {
		this.loadListener = loadListener;
		firstFrameRendered = false;
	}
	
	public void init(LoadManager loadManager, Class<? extends StageScreen> nextScreen, LoadListener loadListener) {
		this.loadManager = loadManager;
		this.loadListener = loadListener;
		this.nextScreen = nextScreen;
		firstFrameRendered = false;
	}
	
	public void init(LoadManager loadManager, Class<? extends StageScreen> nextScreen) {
		init(loadManager, nextScreen, null);
		firstFrameRendered = false;
	}
	
	@Override
	public void render(float delta) {
		if (!firstFrameRendered) {
			super.render(delta);
			firstFrameRendered = true;
			return;
		}
		
		if (loadManager == null) {
			super.render(delta);
			return;
		}
		
		if (loadManager.loadStep()) {
			if (loadListener != null) {
				loadListener.loadPercentChanged(1f);
				loadListener.finished();
			}
			
			if (nextScreen != null) {
				screenTransition.create().transit(nextScreen);
			}
		} else {
			float currentPercent = loadManager.getLoadPercent();
			if (loadListener != null) {
				loadListener.loadStep(currentPercent);
			}

			if (Math.abs(currentPercent - lastPercent) >= 0.01f) {
				lastPercent = currentPercent;
				if (loadListener != null) {
					loadListener.loadPercentChanged(currentPercent);
				}
			}
		}
		
		super.render(delta);
	}
	
	public LoadManager getLoadManager() {
		return loadManager;
	}
	
	public LoadListener getLoadListener() {
		return loadListener;
	}
	
	public float getPercent() {
		return lastPercent;
	}
	
	public Class<? extends StageScreen> getNextScreen() {
		return nextScreen;
	}
}
