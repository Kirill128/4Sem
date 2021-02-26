package eu.grsu.platform.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import eu.grsu.platform.containers.BigContainer;
import eu.grsu.platform.containers.LittleContainer;
import eu.grsu.platform.deck.ShipDeck;
import eu.grsu.platform.ports.Port;
import eu.grsu.platform.ships.Ship;

public class Application {
	
	public static void main(String[] args) {
		workLoop(new Port(makeSomeShips()),new ArrayList<Ship>());
	}
	public static void workLoop(Port port,List<Ship> freeShips) {
		boolean wantToWork=true;
		while(wantToWork) {
			System.out.println("Choose your way:"
					+ "\n1 - Посмотреть сколько воды в порту"
					+ "\n2 - Посмотреть список кораблей в порту"
					+ "\n3 - Удалить корабль из порта (вода остаётся в порту)"
					+ "\n4 - Создать корабль -> наполнить корабль контейнерами с водой"
					+ "\n5 - Посмотреть список кораблей, ожидающих прибытия в порт -> посмотреть инормацию по конкретному кораблю"
					+ "\n6 - Загрузить корабль в порт"
					+ "\n7 - Выйти из программы");
			
			switch(inputInt()) {
				case 1:{
					System.out.printf("Water Volume: "+port.getWaterInPort());
				}
				break;
				case 2:{
					System.out.println("Ships names:");
					port.getShipNamesInPort().stream().forEach(System.out::println);
				}
				break;
				case 3:{
					System.out.println("Input ship name:");
					port.delete(inputString());
				}
				break;
				case 4:{
					freeShips.add(inputShip());
				}
				break;
				case 5:{
					freeShips.stream().forEach(System.out::println);
				}
				break;
				case 6:{
					System.out.print("Input ship name:");
					String name=inputString();
					for(Ship s : freeShips)
						if(s.getName().equals(name))
						{
							if(!port.add(s))
								System.out.println("Doesn't have plase for new Ship!");
							break;
						}
				}
				break;
				case 7:{
					wantToWork=false;
				}
				break;
				default:
					System.out.println("Wrong Input !");
				break;
			}
		}
	}
	public static List<Ship> makeSomeShips(){
		List<Ship> ships=new ArrayList<>();
		
			ShipDeck deck1=new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME),
					  					new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME));
			ShipDeck deck2=new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME),
  										new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME));
			ships.add(new Ship(deck1,deck2,"First"));

			ShipDeck deck3=new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME));
			ShipDeck deck4=new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME));
			ships.add(new Ship(deck3,deck4,"Second"));

			ShipDeck deck5=new ShipDeck(new BigContainer(BigContainer.STANDART_BIG_CONTAINER_VOLUME));
			ShipDeck deck6=new ShipDeck(new BigContainer(BigContainer.STANDART_BIG_CONTAINER_VOLUME));
			ships.add(new Ship(deck5,deck6,"Third"));
		
		return ships;
	}
	public static Ship inputShip() {
		System.out.printf("Input ship name:");
		String name=inputString();
		List<ShipDeck> decks=new ArrayList<>();
		for(int i=1;i<=2;i++) {
			System.out.printf("Input containers for deck "+i+":"
					+ "\n1 - 1 Big Container"
					+ "\n2 - 2 Small Containers"
					+ "\n3 - 1 Small Container");
			switch(inputInt()) {
				case 1:{
					decks.add(new ShipDeck(new BigContainer(BigContainer.STANDART_BIG_CONTAINER_VOLUME)));
				}
				break;
				case 2:{
					decks.add(new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME),
										  new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME)));
				}
				break;
				case 3:{
					decks.add(new ShipDeck(new LittleContainer(LittleContainer.STANDART_LITTLE_CONTAINER_VOLUME)));
				}
				break;			
			}
		}
		return new Ship(decks.get(0),decks.get(1),name);
	}
	
	public static int inputInt() {
		while(true) {
			try(Scanner scan = new Scanner(System.in)){
				int a=scan.nextInt();
				return a;
			}
			catch(Exception ex) {
				System.out.println("Input again:");
			}
		}
	}
	public static String inputString() {
		try(Scanner scan = new Scanner(System.in)){
			return scan.next();
		}
	}
}
