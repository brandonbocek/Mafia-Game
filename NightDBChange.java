package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Player;

public class NightDBChange {

	String name;
	String playerChosen;
	int correctId = 0;
	List<Player> thePlayers;
	
	public NightDBChange(){
	
	}
	
	public NightDBChange(String name, String playerChosen){
		this.name=name;
		this.playerChosen=playerChosen;
		setCorrectPlayerId();
	}
	
	public String getAnothersRole(){
		for(Player p: thePlayers){
			if(p.getFirstName().equals(playerChosen)){
				if(p.getTeamName() == 1){
					return "Mafia";
				}else{
					return "Not Mafia";
				}
			}
		}
		return "Not Mafia";
	}
	
	public void updateChosenPlayer(){
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
					
			Player me = session.get(Player.class, correctId);
					
			me.setPlayerChosenToActOn(playerChosen);
			
			// commit the transaction
			session.getTransaction().commit();
		}finally{
			factory.close();
		}
	}
	
	private void setCorrectPlayerId(){
		queryPlayers();
		for(Player p : thePlayers){
			System.out.println(p.getFirstName() + " ? "+ name);
			if(p.getFirstName().equals(name)){
				correctId = p.getId();
			}
		}
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
	
	public boolean playerHasPermissionToView(){
		boolean hasPerm = false;
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
					
			Player me = session.get(Player.class, correctId);
			
			if(me.getAllowedToGo() == 1)
				hasPerm = true;
			
		}finally{
			factory.close();
		}
		return hasPerm;
	}
	
	public void allowPlayersToTakeTheirAction(){
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
			
			// set permissions to 1
			session.createQuery("update Player set allowed_to_go=1")
				.executeUpdate();
						
			// commit the transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}
	
	public void removePlayersChoosePermission(){
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
					
			Player me = session.get(Player.class, correctId);
					
			me.setAllowedToGo(0);
			
			// commit the transaction
			session.getTransaction().commit();
		}finally{
			factory.close();
		}
	}
}
