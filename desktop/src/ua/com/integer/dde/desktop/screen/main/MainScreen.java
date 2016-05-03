package ua.com.integer.dde.desktop.screen.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ua.com.integer.dde.desktop.DesktopLauncher;
import ua.com.integer.dde.desktop.screen.UIScreen;
import ua.com.integer.dde.desktop.screen.atlases.AtlasesScreen;

public class MainScreen extends UIScreen {
	@Override
	public void firstTimeShown() {
		setBackgroundColor(Color.DARK_GRAY);
		
		addTitle("MAIN");
		addLaunchButton();
		initChoosePanel();
	}
	
	private void initChoosePanel() {
		Table choosePanel = new Table().top();
		choosePanel.setBackground(skin().get(ScrollPaneStyle.class).background);
		choosePanel.setSize(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight());
		fullscreenTable().add(choosePanel).width(choosePanel.getWidth()).fillY().expand().left();
		
		TextButton atlasesButton = percentWidthButton("Atlases", 0.18f, 0.05f);
		atlasesButton.addListener(new GotoScreenListener(AtlasesScreen.class));
		choosePanel.add(atlasesButton).size(atlasesButton.getWidth(), atlasesButton.getHeight()).pad(20);
	}
	
	private void addLaunchButton() {
		TextButton launchButton = percentWidthButton("Launch", 0.2f, 0.07f);
		launchButton.addListener(new LaunchGameListener());
		fullscreenTable().add(launchButton).size(launchButton.getWidth(), launchButton.getHeight()).expand().bottom().right().pad(20);
	}
	
	class LaunchGameListener extends ClickListener {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			DesktopLauncher.getInstance().setGameRunning(true);
		}
	}
}
