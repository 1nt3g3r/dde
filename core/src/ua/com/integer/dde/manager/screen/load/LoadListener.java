package ua.com.integer.dde.manager.screen.load;

public interface LoadListener {
	public void loadStep(float loadPercent);
	public void loadPercentChanged(float loadPercent);
	public void finished();
}
