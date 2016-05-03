package ua.com.integer.dde.manager.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import ua.com.integer.dde.DDECore;

public class StageScreen implements Screen {
	private Stage stage;
	private Array<ScreenListener> screenListeners;

	private Color backgroundColor = Color.BLACK;
	private boolean firstTimeShown;
	
	public class BackPressListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
				notifyAboutEvent(ScreenEvent.BACK_OR_ESCAPE_PRESSED);
			}
			return super.keyDown(event, keycode);
		}
	}
	
	public StageScreen() {
		initialize();
	}

	private void initialize() {
		screenListeners = new Array<ScreenListener>();
		stage = new Stage(new ScreenViewport(), DDECore.getInstance().getSpriteBatch());
		stage.getRoot().setSize(stage.getWidth(), stage.getHeight());
		stage.addListener(new BackPressListener());
	}
	
	public void addScreenEventListener(ScreenListener listener) {
		screenListeners.add(listener);
	}
	
	public void removeScreenEventListener(ScreenListener listener) {
		screenListeners.removeValue(listener, true);
	}
	
	public void clearScreenListeners() {
		screenListeners.clear();
	}
	
	public Array<ScreenListener> getScreenListeners() {
		return screenListeners;
	}

	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Add an actor to this screen
	 */
	public void addActor(Actor actor) {
		stage.addActor(actor);
	}
	
	/**
	 * Finds and returns an actor with selected name. If no actor found null will be returned. 
	 * Method searches in root group.
	 */
	public <T extends Actor> T findByName(String name) {
		if (name == null) {
			return null;
		}
		
		return stage.getRoot().findActor(name);
	}
	
	/**
	 * Removes actor by his name
	 */
	public void removeActor(String name) {
		Actor actor = findByName(name);
		if (actor != null) {
			actor.remove();
		}
	}
	
	/**
	 * Draws background image or fills screen background color if it need. After it renders stage. 
	 * You can override it and disable draw background if you want increase performance.
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);

		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		stage.getRoot().setSize(stage.getWidth(), stage.getHeight());
		
		notifyAboutEvent(ScreenEvent.RESIZE);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		notifyAboutEvent(ScreenEvent.SHOW);
		
		if (!firstTimeShown) {
			firstTimeShown = true;
			firstTimeShown();
			notifyAboutEvent(ScreenEvent.FIRST_TIME_SHOWN);
		}
	}

	@Override
	public void hide() {
		notifyAboutEvent(ScreenEvent.HIDE);
	}

	@Override
	public void pause() {
		notifyAboutEvent(ScreenEvent.PAUSE);
	}

	@Override
	public void resume() {
		notifyAboutEvent(ScreenEvent.RESUME);
	}

	@Override
	public void dispose() {
		disposeActors(getStage().getRoot());
		notifyAboutEvent(ScreenEvent.DISPOSE);
	}
	
	private void disposeActors(Actor actor) {
		if (actor instanceof Disposable) {
			((Disposable) actor).dispose();
		}
		
		if (actor instanceof Group) {
			for(Actor child : ((Group) actor).getChildren()) {
				disposeActors(child);
			}
		}
	}
	
	public void notifyAboutEvent(ScreenEvent event) {
		for(int i = 0; i < screenListeners.size; i++) {
			screenListeners.get(i).eventHappened(this, event);
		}
	}
	
	/**
	 * Исполняет указанную задачу через указанный промежуток времени. 
	 * Особенность - если экран будет спрятан до начала времени выполнения задачи, 
	 * то задача не будет выполнена
	 * @param delay задержка в секундах перед выполнением задания
	 * @param task задача
	 */
	public void postTask(float delay, Runnable task) {
		getStage().addAction(Actions.sequence(
					Actions.delay(delay), 
					Actions.run(task)
				));
	}
	
	/**
	 * Циклично исполняет переданную задачу
	 * @param interval
	 * @param task
	 */
	public void repeatTask(float interval, Runnable task) {
		getStage().addAction(Actions.forever(
				Actions.sequence(
						Actions.delay(interval),
						Actions.run(task))
				));
	}
	
	public void clear() {
		getStage().clear();
		
		screenListeners.clear();
		
		stage.getRoot().setSize(stage.getWidth(), stage.getHeight());
		stage.addListener(new BackPressListener());
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public void firstTimeShown() {
	}
}
