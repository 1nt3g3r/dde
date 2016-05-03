package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.font.TTFFontManager;

public class TTFFontManagerResolver implements ResourceResolver {
	@Override
	public PathDescriptorLoadManager resolve(String resName) {
		if (resName.equals("fonts")) {
			return new TTFFontManager();
		}
		
		return null;
	}
}
