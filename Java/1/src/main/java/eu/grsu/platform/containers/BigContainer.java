package eu.grsu.platform.containers;

import eu.grsu.platform.api.WaterWorkable;

public class BigContainer extends AContainer implements WaterWorkable{
	public BigContainer(int waterVolume) {
		this.setWaterVolume(waterVolume);
	}

	@Override
	public void setWaterVolume(int waterVolume) {
		if(waterVolume==1000)this.waterVolume=waterVolume;
	}
	
	public int giveWater() {
		int a=this.waterVolume;
		this.waterVolume=0;
		return a;
	}
}
