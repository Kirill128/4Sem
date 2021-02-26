package eu.grsu.platform.containers;

import eu.grsu.platform.api.WaterWorkable;

public class BigContainer extends AContainer implements WaterWorkable{
	public static final int STANDART_BIG_CONTAINER_VOLUME=1000;
	
	public BigContainer(int waterVolume) {
		this.setWaterVolume(waterVolume);
	}

	@Override
	public void setWaterVolume(int waterVolume) {
		if(waterVolume<=1000 && waterVolume>=0)this.waterVolume=waterVolume;
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
