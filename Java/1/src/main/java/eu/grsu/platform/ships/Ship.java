package eu.grsu.platform.ships;

import java.util.ArrayList;
import java.util.List;

import eu.grsu.platform.api.WaterWorkable;
import eu.grsu.platform.deck.ShipDeck;
import lombok.Getter;
import lombok.Setter;

public class Ship implements WaterWorkable {
	
	private List<WaterWorkable> shipDecks;
	@Getter
	@Setter
	private String name;
	
	public Ship(ShipDeck desk1,ShipDeck desk2,String name) {
		this.shipDecks=new ArrayList<>();
		this.shipDecks.add(desk1);
		this.shipDecks.add(desk2);
		this.name=name;
	}

	@Override
	public int giveWater() {
		int res=0;
		for(WaterWorkable w : this.shipDecks) {
			res+=w.giveWater();
		}
		return res;
	}
	@Override
	public int checkWater() {
		int res=0;
		for(WaterWorkable w : this.shipDecks) {
			res+=w.checkWater();
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "Ship Name:"+this.name;
	}


	@Override
	public void restoreWater() {
		// TODO Auto-generated method stub
		
	}
}
