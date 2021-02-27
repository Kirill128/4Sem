package eu.grsu.platform.ports;

import java.util.ArrayList;
import java.util.List;

import eu.grsu.platform.ships.Ship;
import lombok.Getter;
import lombok.Setter;

public class Port {
	public static final int MAX_SHIPS_IN_PORT=10;
	
	private List<Ship> ships;
	@Getter
	@Setter
	private int waterInPort;
	
	public Port(List<Ship> ships) {
		this.ships=new ArrayList<Ship>();
		for(int i=0;i<MAX_SHIPS_IN_PORT && i<ships.size();i++) {
			this.add(ships.get(i));
		}
	}
	public Port() {
		this.ships=new ArrayList<Ship>();
	}
	
	public boolean add(Ship ship) {
		if(this.ships.size()<=MAX_SHIPS_IN_PORT) {
			this.ships.add(ship);
			this.waterInPort+=ship.giveWater();
			return true;
		}
		return false;
	}
	
	public void delete(Ship ship) {
		this.ships.remove(ship);
	}
	
	public void delete(String name) {
		this.ships.removeIf((a)->a.getName().equals(name));
	}
	
	public int countShipsInPort() {
		return ships.size();
	}

	public List<String> getShipNamesInPort(){
		List<String> names=new ArrayList<>();
		this.ships.stream().forEach((a)->names.add(a.getName()));
		return names;
	}
	
}
