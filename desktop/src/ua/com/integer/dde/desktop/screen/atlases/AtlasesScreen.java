package ua.com.integer.dde.desktop.screen.atlases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import ua.com.integer.dde.desktop.screen.UIScreen;
import ua.com.integer.dde.desktop.screen.main.MainScreen;
import ua.com.integer.dde.manager.screen.ScreenEvent;
import ua.com.integer.dde.manager.screen.ScreenListener;
import ua.com.integer.dde.manager.screen.StageScreen;

public class AtlasesScreen extends UIScreen implements ScreenListener {
	private AtlasesModel model = new AtlasesModel();
	
	public AtlasesScreen() {
		addScreenEventListener(this);
	}
	
	@Override
	public void eventHappened(StageScreen screen, ScreenEvent event) {
		switch(event) {
		case BACK_OR_ESCAPE_PRESSED:
			gotoMenuScreen();
			break;
		case FIRST_TIME_SHOWN:
			initUI();
			break;
		default:
			break;
		}
	}
	
	private void gotoMenuScreen() {
		slideToScreen(MainScreen.class);
	}
	
	private void initUI() {
		setBackgroundColor(Color.NAVY);
		createTopPanel("ATLASES");
		
		setupAtlasesLayout();
	}
	
	private List<String> atlasList;
	
	private void setupAtlasesLayout() {
		Table fullScreenTable = createContentPanel();
		
		Table contentTable = new Table();
		fullScreenTable.add(contentTable).expand().fill().pad(Gdx.graphics.getWidth() * 0.02f);
		
		Window atlasListWindow = new Window("Atlases", skin());
		atlasListWindow.setMovable(false);
		
		atlasList = new List<String>(skin());
		ScrollPane atlasListScroll = new ScrollPane(atlasList);
		atlasListScroll.setFadeScrollBars(true);
		atlasList.setItems(model.getAtlasNames());
		atlasListWindow.add(atlasListScroll).expand().fill();
		contentTable.add(atlasListWindow).expand().fill();
		
		Window regionListWindow = new Window("Regions in atlas", skin());
		regionListWindow.setMovable(false);
		contentTable.add(regionListWindow).expand().fill();
	}
	
}
