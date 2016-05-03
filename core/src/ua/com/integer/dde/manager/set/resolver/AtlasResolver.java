package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.atlas.AtlasManager;

public class AtlasResolver implements ResourceResolver {
	@Override
	public PathDescriptorLoadManager resolve(String resName) {
		if (resName.equals("atlases")) {
			return new AtlasManager();
		}
		
		return null;
	}
}
