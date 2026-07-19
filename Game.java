package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A simple class to hold information about a game
 * 
 * <p>
 * Notice the overloaded constructor that requires
 * an id and name to be passed when creating.
 * Also note that no mutators (setters) defined so
 * these values cannot be changed once a game is
 * created.
 * </p>
 * 
 * @author coce@snhu.edu
 *
 */

// extends Entity to access ID and Name
public class Game extends Entity {
	//the ability to have one or more teams involved
	private List<Team> teams = new ArrayList<Team>(); 

	/**
	 * Constructor with an identifier and name
	 */
	public Game(long id, String name) {
	    super(id, name); //Entity handles id and name variables
	}

	/**
	 * Construct a new team instance
	 * * @param name the unique name of the team
	 * @return the team instance (new or existing)
	 */
	public Team addTeam(String name) {

		//a local team instance
		Team team = null;

		//Use iterator to look for existing team with same name
		//If found, assign that instance to the local variable
		Iterator<Team> teamsIterator = teams.iterator();
		
		while (teamsIterator.hasNext()) {
			Team teamInstance = teamsIterator.next();
			if (teamInstance.getName().equalsIgnoreCase(name)) {
				team = teamInstance;
				break;
			}
		}

		//If not found, make a new team instance and add to list of teams
		if (team == null) {
			// Get the singleton instance of GameService to access its ID counters
            GameService service = GameService.getInstance();
            team = new Team(service.getNextTeamId(), name);
            teams.add(team);
		}

		return team;
	}
	
	@Override
	public String toString() {
		//updated to use Entity methods
		return "Game [id=" + getId() + ", name=" + getName() + "]";
	}
}
