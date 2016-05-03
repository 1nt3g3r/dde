package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.sound.SoundManager;

public class SoundResolver implements ResourceResolver {
	@Override
	public PathDescriptorLoadManager resolve(String resName) {
		if (resName.equals("sounds")) {
			return new SoundManager();
		}
		
		return null;
	}
}
