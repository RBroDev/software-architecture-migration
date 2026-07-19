package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
/**
 * A simple class to hold information about a team
 * <p>
 * Notice the overloaded constructor that requires
 * an id and name to be passed when creating.
 * Also note that no mutators (setters) defined so
 * these values cannot be changed once a team is
 * created.
 * </p>
 * @author coce@snhu.edu
 *
 */
//extends Entity to access ID and Name
public class Team extends Entity {
	//A list of the players assigned to this team
	private List<Player> players = new ArrayList<Player>();
	
	/*
	 * Constructor with an identifier and name
	 */
	public Team(long id, String name) {
		super(id, name); //Entity now hold ID and name
	}

	/**
	 * Construct a new player instance
	 * * @param name the unique name of the player
	 * @return the player instance (new or existing)
	 */
	public Player addPlayer(String name) {

		//a local player instance
		Player player = null;

		//Use iterator to look for existing player with same name
		Iterator<Player> playersIterator = players.iterator();
		
		while (playersIterator.hasNext()) {
			Player playerInstance = playersIterator.next();
			if (playerInstance.getName().equalsIgnoreCase(name)) {
				player = playerInstance;
				break;
			}
		}

		//If not found, a new player will be created
		if (player == null) {
			// Get the singleton instance of GameService to access its ID counters
			GameService service = GameService.getInstance();
            player = new Player(service.getNextPlayerId(), name);
            players.add(player);
		}

		return player;
	}
	
	@Override
	public String toString() { //use methods from Entity to access ID and Name
		return "Team [id=" + getId() + ", name=" + getName() + "]";
	}
}
