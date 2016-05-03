package ua.com.integer.dde.manager.atlas;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ua.com.integer.dde.manager.PathDescriptorLoadManager;
import ua.com.integer.dde.manager.descriptor.PathDescriptor;

public class AtlasManager extends PathDescriptorLoadManager {
	public AtlasManager() {
		this(null);
	}
	
	public AtlasManager(PathDescriptor descriptor) {
		setDescriptor(descriptor);
		
		addExtension("pack");
		addExtension("atlas");
		addExtension("etc1");
	}
	
	public TextureAtlas getAtlas(String name) {
		return (TextureAtlas) get(name);
	}

	@Override
	protected Object createItem(FileHandle handle) {
		return new TextureAtlas(handle);
	}
}
