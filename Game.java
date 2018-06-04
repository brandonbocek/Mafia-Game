package com.objects;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Player;

public class Game {

	private int numOfMafia;
	private boolean copPlays = false;
	private boolean nursePlays = false;
	private boolean lawyerPlays = false;
	private boolean gmaPlays = false;
	private boolean underCoverCopPlays = false;
	private int numPlayersNeeded = 0;
	private int numOfPlayersPlaying = 0;
	private List<Player> thePlayers;
	
	public Game(){
		
	}
	
	public Game(int numOfMafia, String copIsPlay, String nurseIsPlay, String lawyerIsPlay, String gmaIsPlay, String underCopIsPlay){
		makeEveryPlayerAlive();
		queryPlayers();
		this.numOfMafia = numPlayersNeeded = numOfMafia;
		if(copIsPlay.equals("yes")){
			copPlays = true;
			numPlayersNeeded++;
		}
		if(nurseIsPlay.equals("yes")){
			nursePlays = true;
			numPlayersNeeded++;
		}
		if(lawyerIsPlay.equals("yes")){
			lawyerPlays = true;
			numPlayersNeeded++;
		}
		if(gmaIsPlay.equals("yes")){
			gmaPlays = true;
			numPlayersNeeded++;
		}
		if(underCopIsPlay.equals("yes")){
			underCoverCopPlays = true;
			numPlayersNeeded++;
		}
	}
	
	private void makeEveryPlayerAlive(){
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Player.class)
										.buildSessionFactory();
				
		Session session = factory.getCurrentSession();
				
		try {		
			session = factory.getCurrentSession();
			session.beginTransaction();
		
			// set team name to 0
			session.createQuery("update Player set alive_or_dead=1")
				.executeUpdate();
					
			// commit the transaction
			session.getTransaction().commit();
		}finally{
			factory.close();
		}
	}
	
	private void clearAllRoles(){
		for(Player p : thePlayers){
			p.setTeamName(0);
		}
	}
	
	public boolean optionsAreAppropriate(){
		if(thePlayers.size() < numPlayersNeeded){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void queryPlayers() {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Player.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {			
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			thePlayers = session.createQuery("from Player").list();
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
	
	private void giveMafiaRoles(){
		
		Random rand = new Random();
		int numMafiaAssigned = 0;
		int i;
		
		do{
			i = rand.nextInt(thePlayers.size());
			
			if(thePlayers.get(i).getTeamName() == 0){
				thePlayers.get(i).setTeamName(1);
				numMafiaAssigned++;
			}
			
		}while(numMafiaAssigned < numOfMafia);
	}
	
	private void assignSpecialNonMafiaRole(int codeNum){
		Random rand = new Random();
		int i;
		boolean roleAssigned = false;
		do{
			i = rand.nextInt(thePlayers.size());
			if(thePlayers.get(i).getTeamName() == 0){
				thePlayers.get(i).setTeamName(codeNum);
				roleAssigned = true;
			}
		}while(!roleAssigned);
	}
	
	public void assignAllRoles(){
		setNumOfPlayersPlaying(thePlayers.size());
		clearAllRoles();
		giveMafiaRoles();
		if(copPlays)
			assignSpecialNonMafiaRole(2);
		if(nursePlays)
			assignSpecialNonMafiaRole(3);
		if(lawyerPlays)
			assignSpecialNonMafiaRole(4);
		if(gmaPlays)
			assignSpecialNonMafiaRole(5);
		if(underCoverCopPlays)
			assignSpecialNonMafiaRole(6);
		//System.out.println("About to update database");
		changeDataBaseValues();
	}
	
	private void changeDataBaseValues(){
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Player.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try {								
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// set team name to 0
			session.createQuery("update Player set team_name=0")
				.executeUpdate();
						
			// commit the transaction
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// set permissions to 1
			session.createQuery("update Player set allowed_to_go=0")
				.executeUpdate();
						
			// commit the transaction
			session.getTransaction().commit();
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			for(int i=0; i<thePlayers.size(); i++){
				Player p = session.get(Player.class, thePlayers.get(i).getId());
				p.setTeamName(thePlayers.get(i).getTeamName());
			}
			
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done Updating!");
		}
		finally {
			factory.close();
		}
	}
	
	public void killAPlayer(int id){
		for(Player p : thePlayers){
			if(p.getId() == id){
				p.setAliveOrDead(0);
			}
		}
	}

	public int getNumOfPlayersPlaying() {
		return numOfPlayersPlaying;
	}

	public void setNumOfPlayersPlaying(int numOfPlayersPlaying) {
		this.numOfPlayersPlaying = numOfPlayersPlaying;
	}
	
	public boolean getPlayerLife(int i){
		return thePlayers.get(i).getAliveOrDead();
	}
	
	public String getPlayerName(int i){
		return thePlayers.get(i).getFirstName();
	}
	
	public List<Player> getThePlayers(){
		return thePlayers;
	}
	
	public String getWinner(){
		int numGood = 0, numBad = 0, numMafia=0;
		for(Player p : thePlayers){
			if(p.getAliveOrDead()){
				if(p.getTeamName() == 1){
					numMafia++;
					numBad++;
				}else if(p.getTeamName() == 4){
					numBad++;
				}else{
					numGood++;
				}
			}
		}
		if(numBad >= numGood){
			return "Mafia wins";
		}else if(numMafia <= 0){
			return "The Town wins";
		}else{
			return "The game is not over";
		}
		
	}
	
}
