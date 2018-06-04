package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Player;

public class RoleGetter {

	private String name;
	List<Player> player;
	
	public RoleGetter(String name){
		this.name = name;
	}
	
	public String getPlayerRole(){
		queryPlayers();
		for(Player p: player){
			if(p.getFirstName().equals(name)){
				switch(p.getTeamName()){
				case 1:
					return "Mafia";
				case 2:
					return "Cop";
				case 3:
					return "Nurse";
				case 4:
					return "Lawyer";
				case 5:
					return "Grandma with a knife";
				case 6:
					return "Undercover Cop";
				default:
					return "Civilian";
				}
			}
		}
		return "Civilian";
	}
	
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
			player = session.createQuery("from Player").list();
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
	
	public ArrayList<Player> getOtherPlayers(){
		queryPlayers();
		ArrayList<Player> toReturn = new ArrayList<Player>();
		for(Player p: player){
			if(!p.getFirstName().equals(name) && p.getAliveOrDead()){
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	
}
