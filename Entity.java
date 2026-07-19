package com.gamingroom;

/**
 * A base class to hold common attributes and behaviors (id and name)
 * * @author Rebecca Scranton
 */

public class Entity {
	private long id;
	private String name;

	// Default constructor (protected so only subclasses can see it)
	private Entity() {
	}

	// Constructor with id and name
	public Entity(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + "]";
	}
}