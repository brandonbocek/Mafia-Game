package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.objects.Player;


public class AddNewPlayerToDB {
	
	List<Player> thePlayers;
	
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

	private boolean playerDoesNotExistInDB(String newName) {
		
		for (Player p : thePlayers){
			if(p.getFirstName().equals(newName)){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean didAddPlayer(String newName){
		queryPlayers();
		boolean addedStudent = false;
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Player.class)
				.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
		
		try{
			if(playerDoesNotExistInDB(newName)){
				Player tempPlayer = new Player(newName, 1, 0);
				session.beginTransaction();
				session.save(tempPlayer);
				session.getTransaction().commit();
				addedStudent = true;
			}
			
		}finally{
			factory.close();
			return addedStudent;
		}
	}

}





