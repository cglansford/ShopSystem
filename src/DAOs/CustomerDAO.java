package DAOs;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Customer;

public class CustomerDAO {
	
	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("CiaranPU"); 
	
	public CustomerDAO() {
		
	}

	public void persistObject(Object object)  {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
	}
	
	public ArrayList<Customer> getAllCustomers(){
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ArrayList<Customer> list = new ArrayList<Customer>();
        list = (ArrayList<Customer>) em.createQuery("FROM Customer").getResultList();
        em.flush();
        em.getTransaction().commit();
        em.close();
        return list;
		
	}
}
