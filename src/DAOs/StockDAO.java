package DAOs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StockDAO {
	
	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("CiaranPU"); 
	
	public StockDAO() {
		
	}
	
	public void persistObject(Object object)  {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
	}
}
