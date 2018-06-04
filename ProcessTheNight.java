package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Player;
import com.objects.Game;

public class ProcessTheNight {

	List<Player> thePlayers;
	Game theGame;
	
	public ProcessTheNight(Game theGame){
		this.theGame=theGame;
	}
	
	public Game killPlayer(){
		queryPlayers();
		int playerIdToDie = getPlayerMafiaChose();
		System.out.println(playerIdToDie + " is the index of the player to die");
		String playerName = "";
		if(playerIdToDie != -1){
			playerName = killPlayerInDatabase(playerIdToDie);
			
			theGame.killAPlayer(playerIdToDie);
		}
		
		return theGame;
		
	}
	
	private void queryPlayers(){
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Player.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {			
					
			// start a transaction
			session.beginTransaction();
					
			// query students
			thePlayers = session.createQuery("from Player").list();
						
			// commit transaction
			session.getTransaction().commit();
					
		}finally {
			factory.close();
		}
	}
	
	private int getPlayerMafiaChose(){
		ArrayList<String> people = new ArrayList<String>();
		Player nurse = null, gma = null, cop = null, lawyer = null;
		
		for(Player p : thePlayers){
			if(p.getAliveOrDead()){
				if(p.getTeamName() == 1){
					people.add(p.getPlayerChosenToActOn());
					System.out.println("Added " + p.getPlayerChosenToActOn() + " to possible death list");
				}else if(p.getTeamName() == 3){
					nurse = p;
				}else if(p.getTeamName() == 5){
					gma = p;
				}else if(p.getTeamName() == 2){
					cop = p;
				}else if(p.getTeamName() == 4){
					lawyer = p;
				}
			}
		}
		
		//grandma killed non-mafia
		if(nurse != null && gma != null && nurse.getPlayerChosenToActOn().equals(gma.getFirstName())){
			return nurse.getId();
		}else if(cop != null && gma != null && cop.getPlayerChosenToActOn().equals(gma.getFirstName())){
			return cop.getId();
		}else if(lawyer != null && gma != null && lawyer.getPlayerChosenToActOn().equals(gma.getFirstName())){
			return lawyer.getId();
		}
		
		//figuring out who the mafia votes to kill
		int votes[] = new int[people.size()];
		for(int i=0; i<people.size(); i++){
			votes[i] = 1;
		}
		
		for(int i=0; i<people.size(); i++){
			for(int j=0; j<people.size(); j++){
				if(i!=j && people.get(i).equals(people.get(j))){
					votes[i]++;
					System.out.println("Added a vote to " + people.get(i));
				}
			}
		}
		int maxSize = 0, maxIndex = -1;
		for(int i=0; i<people.size(); i++){
			if(maxSize < votes[i]){
				maxSize = votes[i];
				maxIndex = i;
			}
		}
		
		Player markedForDeath;
		//mafia failed to kill someone
		if(maxIndex == -1){
			return -1;
			
		//mafia chose someone to kill
		}else if(people.size() > 0){
			int index = 0;
			for(Player x : thePlayers){
				if(x.getFirstName().equals(people.get(maxIndex))){
					maxIndex = index;
					break;
				}
				index++;
			}
			markedForDeath = thePlayers.get(maxIndex);
			
			//mafia chose to kill the grandma so someone from the mafia will die
			if(gma != null && markedForDeath.getFirstName().equals(gma.getFirstName())){
				for(Player x : thePlayers){
					if(x.getAliveOrDead() && (x.getTeamName() == 1 || x.getTeamName() == 5))
						if(x.getPlayerChosenToActOn().equals(gma.getFirstName()))
							return x.getId();
						
				}
				return -1;
				
			//mafia didn't choose the grandma
			}else{
				//the nurse saved the mafia's victim
				if(nurse != null && nurse.getPlayerChosenToActOn().equals(markedForDeath.getFirstName())){
					return -1;
				//mafia's victim dies
				}else{
					
					return markedForDeath.getId();
				}
			}
		}return -1;
	}
	
	public String killPlayerInDatabase(int id){
		String playerName = "";
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Player.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		try {								
					
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
					
			Player me = session.get(Player.class, id);
			playerName = me.getFirstName();	
			System.out.println("Killing " + me.getFirstName() + " in the database");
			me.setAliveOrDead(0);
			
			// commit the transaction
			session.getTransaction().commit();
		}finally{
			factory.close();
		}
		return playerName;
	}
	
}
