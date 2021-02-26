package eu.grsu.platform.containers;

import eu.grsu.platform.api.WaterWorkable;

public class LittleContainer extends AContainer implements WaterWorkable {
	public static final int STANDART_LITTLE_CONTAINER_VOLUME=400;
	public LittleContainer(int waterVolume) {
		this.setWaterVolume(waterVolume);
	}
	@Override
	public void setWaterVolume(int waterVolume) {
		if(waterVolume<=400  && waterVolume>=0)this.waterVolume=waterVolume;
	}
	public int giveWater() {
		int a=this.waterVolume;
		this.waterVolume=0;
		return a;
	}
	
	public int checkWater() {
		return this.waterVolume;
	}
	@Override
	public void restoreWater() {
		// TODO Auto-generated method stub
		
	}
	
}
