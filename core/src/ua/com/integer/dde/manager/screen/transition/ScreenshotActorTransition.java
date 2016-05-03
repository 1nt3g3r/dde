package ua.com.integer.dde.manager.screen.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.integer.dde.manager.screen.StageScreen;
import ua.com.integer.dde.util.ScreenshotUtils;

public abstract class ScreenshotActorTransition extends ScreenshotTransition {
	protected Group background;
	
	@Override
	public void transit(Class<? extends Screen> next) {
		Image b = new Image(ScreenshotUtils.getScreenshot());
		b.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		b.setOrigin(Align.center);
		b.setScaleY(-1f);
		
		background = new Group();
		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setOrigin(Align.center);
		background.addActor(b);
		
		super.transit(next);
	}
	
	@Override
	public void beforeTransaction() {
		((StageScreen) getNextScreen()).addActor(background);
	}
}
