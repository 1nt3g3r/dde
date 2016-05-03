package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.texture.TextureManager;

public class TextureResolver implements ResourceResolver {
	@Override
	public PathDescriptorLoadManager resolve(String resName) {
		if (resName.equals("textures")) {
			return new TextureManager();
		}
		
		return null;
	}
}
