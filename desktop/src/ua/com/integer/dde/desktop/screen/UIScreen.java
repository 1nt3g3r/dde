package ua.com.integer.dde.desktop.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import ua.com.integer.dde.desktop.DesktopLauncher;
import ua.com.integer.dde.manager.screen.ScreenEvent;
import ua.com.integer.dde.manager.screen.StageScreen;
import ua.com.integer.dde.manager.screen.transition.imp.FadeOutTransition;

public class UIScreen extends StageScreen {
	private Table fullScreenTable;

	public UIScreen() {
		fullScreenTable = fullscreenTable();
	}
	
	public DesktopLauncher core() {
		return DesktopLauncher.getInstance();
	}
	
	public void slideToScreen(Class<? extends Screen> screenClass) {
		FadeOutTransition.fadeOut().transit(screenClass);
	}
	
	public Skin skin() {
		return DesktopLauncher.getInstance().getUISkin();
	}
	
	public Table fullscreenTable() {
		Table result = new Table();
		result.setFillParent(true);
		addActor(result);
		return result;
	}
	
	public void addTitle(String title) {
		Label titleLabel = percentWidthText(title, 1f, 0.1f);
		titleLabel.setAlignment(Align.center);
		titleLabel.setTouchable(Touchable.disabled);
		fullscreenTable().add(titleLabel).expand().fillX().top();
	}
	
	public Label percentWidthText(String text, float percentWidth, float percentHeight) {
		float width = (float) Gdx.graphics.getWidth() * percentWidth;
		float height = (float) Gdx.graphics.getWidth() * percentHeight;
		
		Label result = new Label(text, skin());
		result.setSize(width, height);
		return result;
	}
	
	public TextButton percentWidthButton(String text, float percentWidth, float percentHeight) {
		float width = (float) Gdx.graphics.getWidth() * percentWidth;
		float height = (float) Gdx.graphics.getWidth() * percentHeight;
		
		TextButton result = new TextButton(text, skin());
		result.setSize(width, height);
		return result;
	}
	
	public TextButton smallRectangleButton(String text) {
		TextButton result = percentWidthButton(text, 0.1f, 0.05f);
		return result;
	}
	
	public TextButton createBackButton() {
		TextButton backButton = percentWidthButton("<", 0.05f, 0.04f);
		backButton.setColor(Color.GREEN);
		backButton.addListener(new BackListener());
		return backButton;
	}
	
	public Table createTopPanel(String title) {
		Table topPanel = new Table(skin()).left();
		topPanel.setBackground(skin().get(ScrollPaneStyle.class).background);
		topPanel.setHeight(Gdx.graphics.getHeight() * 0.07f);

		TextButton backButton = createBackButton();
		topPanel.add(backButton).size(backButton.getWidth(), backButton.getHeight()).pad(3f);
		
		topPanel.add(new Label(title, skin())).expand();
		
		fullScreenTable.add(topPanel).height(topPanel.getHeight()).fillX().top();
		
		return topPanel;
	}
	
	public Table createContentPanel() {
		Table contentPanel = new Table();
		fullScreenTable.row();
		fullScreenTable.add(contentPanel).fill().expand();
		return contentPanel;
	}
	
	public class BackListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			notifyAboutEvent(ScreenEvent.BACK_OR_ESCAPE_PRESSED);
		}
	}
	
	public class GotoScreenListener extends ClickListener {
		private Class<? extends Screen> screen;
		
		public GotoScreenListener(Class<? extends Screen> screen) {
			this.screen = screen;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y) {
			slideToScreen(screen);
		}
	}
}
