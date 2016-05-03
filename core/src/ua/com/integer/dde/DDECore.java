package ua.com.integer.dde;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ua.com.integer.dde.interaction.InteractionInterface;
import ua.com.integer.dde.manager.CompositeLoadManager;
import ua.com.integer.dde.manager.atlas.AtlasManager;
import ua.com.integer.dde.manager.descriptor.PathDescriptor;
import ua.com.integer.dde.manager.font.TTFFontManager;
import ua.com.integer.dde.manager.music.MusicManager;
import ua.com.integer.dde.manager.screen.ScreenManager;
import ua.com.integer.dde.manager.sound.SoundManager;
import ua.com.integer.dde.manager.texture.TextureManager;
import ua.com.integer.dde.util.Settings;

public class DDECore extends Game {
	private boolean created;
	
	private CompositeLoadManager resources;
	
	private InteractionInterface platformInteraction;
	
	private Settings sets;
	private SpriteBatch spriteBatch;
	
	private static DDECore instance;
	
	public static DDECore getInstance() {
		return instance;
	}
	
	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		
		sets = new Settings(getClass().getName());
		spriteBatch = new SpriteBatch();
		Texture.setAssetManager(null);

		resources = new CompositeLoadManager();
		resources.addManager(new ScreenManager());
		resources.addManager(new TextureManager(PathDescriptor.internal("data/textures")));
		resources.addManager(new AtlasManager(PathDescriptor.internal("data/atlases")));
		resources.addManager(new TTFFontManager(PathDescriptor.internal("data/fonts")));
		resources.addManager(new SoundManager(PathDescriptor.internal("data/sounds")));
		resources.addManager(new MusicManager(PathDescriptor.internal("data/music")));
		
		instance = this;
		created = true;
		
		ready();
	}
	
	public void ready() {
		
	}
	
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
	public boolean isCreated() {
		return created;
	}
	
	public void setNativeInteraction(InteractionInterface platformInteraction) {
		this.platformInteraction = platformInteraction;
	}
	
	public InteractionInterface nativeInteraction() {
		return platformInteraction;
	}
	
	public Settings settings() {
		return sets;
	}
	
	public CompositeLoadManager resources() {
		return resources;
	}
	
	public ScreenManager screens() {
		return resources.getManager(ScreenManager.class);
	}
	
	public TextureManager textures() {
		return resources.getManager(TextureManager.class);
	}

	public AtlasManager atlases() {
		return resources.getManager(AtlasManager.class);
	}
	
	public TTFFontManager fonts() {
		return resources.getManager(TTFFontManager.class);
	}

	public SoundManager sounds() {
		return resources.getManager(SoundManager.class);
	}
	
	public MusicManager music() {
		return resources.getManager(MusicManager.class);
	}
}
