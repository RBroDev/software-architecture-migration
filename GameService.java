package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A singleton service for the game engine
 * 
 * @author coce@snhu.edu
 */
public class GameService {

;
    
	/**
	 * A list of the active games
	 */
	private static List<Game> games = new ArrayList<Game>();

	/*
	 * Holds the next game identifier
	 */
	private static long nextGameId = 1;
	/*Notice the static variables holding the next identifier
	 to be assigned for game id, team id, and player id */
    private static long nextTeamId = 1; 
    private static long nextPlayerId = 1;

	private static GameService instance; // creates container to hold service
	
	private GameService() {} // no other class can create GameService()

	/*
	 * Singleton Pattern Purpose:
	 * The Singleton pattern ensures that only one instance of the GameService exists 
	 * in memory at any time. This provides a global point of access to the game 
	 * management logic and prevents data conflicts by ensuring all parts of the 
	 * application share the same list of games and the same ID counter.
	 */
	
	public static GameService getInstance() {   //only authorized access to instance
		if (instance == null) {
			instance = new GameService();
	    }
	    return instance;
	}
	
	/**
	 * Construct a new game instance
	 * 
	 * @param name the unique name of the game
	 * @return the game instance (new or existing)
	 */
	public Game addGame(String name) {

		// a local game instance
		Game game = getGame(name); //Use existing iterator

		/*
		 * Iterator Pattern Purpose:
		 * The Iterator pattern provides a standard way to traverse through the collection 
		 * of games without exposing the underlying structure of the ArrayList. 
		 * In this application, it allows us to safely check for existing game names or IDs 
		 * to prevent duplicates, ensuring each game instance remains unique.
		 */
		
		// look for existing game with same name
		Iterator<Game> gamesIterator = games.iterator();
		
		while (gamesIterator.hasNext()) {
			Game tempGame = gamesIterator.next();
			if (tempGame.getName().equalsIgnoreCase(name)) {
				return tempGame; //return existing instance
			} 
		}

		// if not found, make a new game instance and add to list of games
		if (game == null) {
			game = new Game(nextGameId++, name);
			games.add(game);
		}

		// return the new/existing game instance to the caller
		return game;
	}

	/**
	 * Returns the game instance at the specified index.
	 * <p>
	 * Scope is package/local for testing purposes.
	 * </p>
	 * @param index index position in the list to return
	 * @return requested game instance
	 */
	Game getGame(int index) {
		return games.get(index);
	}
	
	/**
	 * Returns the game instance with the specified id.
	 * 
	 * @param id unique identifier of game to search for
	 * @return requested game instance
	 */
	public Game getGame(long id) {

		// a local game instance
		Game game = null;

		// Iterator logic for ID
		Iterator<Game> gamesIterator = games.iterator();
		while (gamesIterator.hasNext()) {
			Game gameInstance = gamesIterator.next();
			if (gameInstance.getId() == id) {
				game = gameInstance;
				break;
			}
		}

		return game;
	}

	/**
	 * Returns the game instance with the specified name.
	 * 
	 * @param name unique name of game to search for
	 * @return requested game instance
	 */
	public Game getGame(String name) {

		// a local game instance
		Game game = null;
		
		// Use iterator to look for existing game with same id
	    Iterator<Game> gamesIterator = games.iterator();

	    while (gamesIterator.hasNext()) {
	        // Get the next game in the list
	        Game gameInstance = gamesIterator.next();
	        
	        // If the name matches, assign it to our local variable and stop looking
	        if (gameInstance.getName().equalsIgnoreCase(name)) {
	            game = gameInstance;
	            break; 
	        }
	    }

		return game;
	}

	/**
	 * Returns the number of games currently active
	 * 
	 * @return the number of games currently active
	 */
	public int getGameCount() {
		return games.size();
	}
	
	//getters
	public long getNextTeamId() {
        return nextTeamId++;
    }

    public long getNextPlayerId() {
        return nextPlayerId++;
    }
}
