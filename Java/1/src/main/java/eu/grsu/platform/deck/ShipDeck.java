package eu.grsu.platform.deck;

import java.util.List;

import eu.grsu.platform.api.WaterWorkable;
import eu.grsu.platform.containers.BigContainer;
import eu.grsu.platform.containers.LittleContainer;

public class ShipDeck implements WaterWorkable {
	
	List<WaterWorkable> containers;
	
	public ShipDeck(BigContainer container) {
		this.containers.add(container);
	}
	public ShipDeck(LittleContainer container) {
		this.containers.add(container);
	}
	public ShipDeck(LittleContainer container1,LittleContainer container2) {
		this.containers.add(container1);
		this.containers.add(container2);
	}
	
	public int giveWater() {
		int res=0;
		for(WaterWorkable w : containers) {
			res+=w.giveWater();
		}
		return res;
	}
	public int checkWater() {
		int res=0;
		for(WaterWorkable w : containers) {
			res+=w.checkWater();
		}
		return res;
	}
	@Override
	public void restoreWater() {
		// TODO Auto-generated method stub
		
	}
	
	
}
