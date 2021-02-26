package eu.grsu.platform.api;

public interface WaterWorkable {
	int giveWater();//delete water
	int checkWater();//doesn't delete water
	void restoreWater();
}
