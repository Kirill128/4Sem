package eu.grsu.platform.containers;

import eu.grsu.platform.api.WaterWorkable;

public class LittleContainer extends AContainer implements WaterWorkable {
	public LittleContainer(int waterVolume) {
		this.setWaterVolume(waterVolume);
	}
	@Override
	public void setWaterVolume(int waterVolume) {
		if(waterVolume==400)this.waterVolume=waterVolume;
	}
	public int giveWater() {
		int a=this.waterVolume;
		this.waterVolume=0;
		return a;
	}
}
