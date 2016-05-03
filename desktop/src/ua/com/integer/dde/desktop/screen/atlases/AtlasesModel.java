package ua.com.integer.dde.desktop.screen.atlases;

import java.io.File;

import com.badlogic.gdx.utils.Array;

public class AtlasesModel {
	public Array<String> getAtlasNames() {
		Array<String> result = new Array<String>(new File("../res/atlases").list());
		result.sort();
		return result;
	}
}
