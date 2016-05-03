package ua.com.integer.dde.desktop.screen.atlases;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;

public class AtlasItem extends Table {
	public AtlasItem(TextureRegion region, String title) {
		Image image = new Image(region);
		image.setScaling(Scaling.fit);
		image.debug();
		add(image).expand();
	}
}
