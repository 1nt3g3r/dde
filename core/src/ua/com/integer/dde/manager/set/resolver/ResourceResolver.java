package ua.com.integer.dde.manager.set.resolver;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;

public interface ResourceResolver {
	public PathDescriptorLoadManager resolve(String resName);
}
