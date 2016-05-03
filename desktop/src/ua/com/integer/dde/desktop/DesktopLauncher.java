package ua.com.integer.dde.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglFrame;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import ua.com.integer.dde.DDECore;
import ua.com.integer.dde.desktop.screen.main.MainScreen;
import ua.com.integer.dde.desktop.test.TestGame;

public class DesktopLauncher extends DDECore {
	private static DesktopLauncher instance = new DesktopLauncher();
	
	private Skin uiSkin;
	private DDECore gameToLaunch;
	private boolean gameRunning;
	
	public static DesktopLauncher getInstance() {
		return instance;
	}
	
	private DesktopLauncher() {
	}
	
	@Override
	public void ready() {
		uiSkin = new Skin(Gdx.files.internal("data/skins/uiskin.json"));
		screens().showScreen(MainScreen.class);
		gameToLaunch = new TestGame();
	}
	
	public Skin getUISkin() {
		return uiSkin;
	}
	
	public void setGameToLaunch(DDECore gameToLaunch) {
		this.gameToLaunch = gameToLaunch;
	}
	
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
		if (gameRunning) {
			gameToLaunch.create();
		} else {
			gameToLaunch.dispose();
		}
	}
	
	public boolean isGameRunning() {
		return gameRunning;
	}
	
	public DDECore getGameToLaunch() {
		return gameToLaunch;
	}

	@Override
	public void dispose () {
		if (gameRunning) {
			gameToLaunch.dispose();
		} else {
			super.dispose();
		}
	}

	@Override
	public void pause () {
		if (gameRunning) {
			gameToLaunch.pause();
		} else {
			super.pause();
		}
	}

	@Override
	public void resume () {
		if (gameRunning) {
			gameToLaunch.resume();
		} else {
			super.resume();
		}
	}

	@Override
	public void render () {
		if (gameRunning) {
			checkForExit();
			gameToLaunch.render();
		} else {
			super.render();
		}
	}
	
	private void checkForExit() {
		if (Gdx.input.isKeyPressed(Keys.Q) && (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))) {
			setGameRunning(false);
		}
	}

	@Override
	public void resize (int width, int height) {
		if (gameRunning) {
			gameToLaunch.resize(width, height);
		} else {
			super.resize(width, height);
		}
	}

	public void setScreen (Screen screen) {
		if (gameRunning) {
			gameToLaunch.setScreen(screen);
		} else {
			super.setScreen(screen);
		}
	}

	public Screen getScreen () {
		if (gameRunning) {
			return gameToLaunch.getScreen();
		} else {
			return super.getScreen();
		}
	}
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.title = "Dark Dream Engine";
		new LwjglFrame(DesktopLauncher.getInstance(), config);
	}
}
