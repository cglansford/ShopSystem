package DAOs;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public Customer getSingleCustomer(int customerID) {
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer managedEntity = em.createQuery("SELECT c FROM Customer c WHERE c.customerID = :customerID",
        					Customer.class).setParameter("customerID", customerID).getSingleResult();
        em.flush();
        em.getTransaction().commit();
       
        em.close();
        return managedEntity;
	}
	
	public Customer getRecentCustomer(String firstName, String surname) {
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer managedEntity = em.createQuery("SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.surname =:surname",
        					Customer.class).setParameter("firstName", firstName).setParameter("surname", surname).getSingleResult();
        em.flush();
        em.getTransaction().commit();
       
        em.close();
        return managedEntity;
		
	}
}
