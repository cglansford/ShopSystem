package DAOs;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.TransactionHistory;

public class TransactionHistoryDAO {

	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("CiaranPU"); 
	
	public TransactionHistoryDAO(){
		
	}
	
	public void persistObject(Object object)  {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
	}
	
	public ArrayList<TransactionHistory> getUserHistory(int userID){
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ArrayList<TransactionHistory> list = new ArrayList<TransactionHistory>();
        list = (ArrayList<TransactionHistory>) em.createQuery("SELECT u FROM TransactionHistory u WHERE u.userID = :userID",
        				TransactionHistory.class).setParameter("userID", userID).getResultList();
        em.flush();
        em.getTransaction().commit();
        em.close();
        return list;
	}
	
}
