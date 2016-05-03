package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.music.MusicManager;

public class MusicResolver implements ResourceResolver {
	@Override
	public PathDescriptorLoadManager resolve(String resName) {
		if (resName.equals("music")) {
			return new MusicManager();
		}
		
		return null;
	}
}
