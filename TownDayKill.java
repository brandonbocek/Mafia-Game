package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Game;
import com.objects.Player;

public class TownDayKill {

	Game theGame;
	List<Player> thePlayers;
	
	public TownDayKill(Game theGame){
		this.theGame=theGame;
	}
	
	public Game killPlayerDead(String name){
		queryPlayers();
		
		for(Player p : thePlayers){
			if(p.getFirstName().equals(name)){
				killPlayerInDatabase(p.getId());
				theGame.killAPlayer(p.getId());
				System.out.println("Admin is officially killing " + name);
				break;
			}
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
