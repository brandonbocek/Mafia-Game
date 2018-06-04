package com.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mafGame")
public class Player {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Name")
	private String firstName;
	
	@Column(name="team_name")
	private int teamName;
	
	@Column(name="alive_or_dead")
	private int aliveOrDead;
	
	@Column(name="player_chosen_q")
	private String playerChosenToQ;
	
	@Column(name="player_chosen_act")
	private String playerChosenToActOn;

	@Column(name="allowed_to_go")
	private int allowedToGo;

	public Player() {
		
	}

	public Player(String firstName, int aliveOrDead, int allowedToGo) {
		this.firstName = firstName;
		this.aliveOrDead = aliveOrDead;
		this.allowedToGo = allowedToGo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public int getTeamName() {
		return teamName;
	}

	public void setTeamName(int teamName) {
		this.teamName = teamName;
	}
	
	public boolean getAliveOrDead(){
		if(aliveOrDead == 1)
			return true;
		else
			return false;
	}
	
	public String getPlayerChosenToActOn() {
		return playerChosenToActOn;
	}

	public void setPlayerChosenToActOn(String playerChosenToActOn) {
		this.playerChosenToActOn = playerChosenToActOn;
	}
	
	public String getLifeStatus(){
		if(aliveOrDead == 1)
			return "Alive";
		else
			return "Dead";
	}
	
	public int getAllowedToGo() {
		return allowedToGo;
	}

	public void setAllowedToGo(int allowedToGo) {
		this.allowedToGo = allowedToGo;
	}
	
	public void setAliveOrDead(int aliveOrDead){
		this.aliveOrDead=aliveOrDead;
	}
	
}
