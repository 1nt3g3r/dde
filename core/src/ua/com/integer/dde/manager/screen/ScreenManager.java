package ua.com.integer.dde.manager.screen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.dde.DDECore;
import ua.com.integer.dde.manager.LoadManager;

/**
 * Screen manager. Contains game screens. Can contain only one instance 
 * of each screen - as example, if you create TestScreen class, you 
 * can't put it twice to this manager.
 * 
 * @author 1nt3g3r
 *
 */
public class ScreenManager implements Disposable, LoadManager {
	private ObjectMap<Class<? extends Screen>, Screen> screens;
	
	/**
	 * Creates screen manager. For future use you should call setGame() method
	 */
	public ScreenManager() {
		screens = new ObjectMap<Class<? extends Screen>, Screen>();
	}
	
	/**
	 * Adds AbstractScreen instance object
	 */
	public void addScreen(Screen screen) {
		screens.put(screen.getClass(), screen);
	}

	/**
	 * Clears this ScreenManager and call dispose() 
	 * for every screen. Also disposes AbstractScreen.batch
	 */
	@Override
	public void dispose() {
		for(Screen screen : screens.values()) {
			screen.dispose();
		}
		screens.clear();
	}
	
	/**
	 * Returns screen by his class
	 * @param type class of the screen
	 */
	@SuppressWarnings("unchecked")
	public <T extends Screen> T getScreen(Class<T> type) {
		for (Screen screen : screens.values()) {
			if (screen.getClass() == type) {
				return (T) screen;
			}
		}

		Screen toReturn = null;
		try {
			toReturn = (Screen) type.newInstance();
			screens.put(type, toReturn);
			return (T) toReturn;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		throw new IllegalArgumentException("No screen for this class!");
	}
	
	/**
	 * Shows screen by his class
	 */
	public <T extends Screen> void showScreen(Class<T> type) {
		Screen screenToShow = getScreen(type);
		if (screenToShow == null) {
			try {
				screenToShow = (Screen) type.newInstance();
				screens.put(type, screenToShow);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		DDECore.getInstance().setScreen(screens.get(type));
	}

	@Override
	public void loadAll() {
	}

	@Override
	public boolean loadStep() {
		return true;
	}

	@Override
	public float getLoadPercent() {
		return 1;
	}

	@Override
	public int getAssetCount() {
		return 0;
	}

	@Override
	public int getLoadedAssetCount() {
		return 0;
	}
	
	public void disposeScreen(Class<? extends Screen> screen) {
		if (isLoaded(screen)) {
			Screen screenToRemove = getScreen(screen);
			screenToRemove.dispose();
			screens.remove(screen);
		}
	}
	
	public boolean isLoaded(Class<? extends Screen> screen) {
		return screens.containsKey(screen);
	}

	@Override
	public boolean isLoaded(String name) {
		return true;
	}
}
