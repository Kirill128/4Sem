package eu.grsu.platform.containers;

import lombok.Getter;

@Getter
public abstract class AContainer {
	protected int waterVolume;

	public void setWaterVolume(int waterVolume) {this.waterVolume=waterVolume;}
}
