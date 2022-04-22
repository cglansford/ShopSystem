package DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.StockItem;

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
	
	public ArrayList<StockItem> getAllItems(){
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ArrayList<StockItem> list = new ArrayList<StockItem>();
        list = (ArrayList<StockItem>) em.createQuery("FROM StockItem").getResultList();
        em.flush();
        em.getTransaction().commit();
        em.close();
        return list;
		
	}
	
	public void updateItem(int stockItemID, String name, int price, String category, String manufacturer) {
		EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        StockItem managedEntity = em.createQuery("SELECT s FROM StockItem s WHERE s.stockItemID = :stockItemID",
        					StockItem.class).setParameter("stockItemID", stockItemID).getSingleResult();
        managedEntity.setTitle(name);
        managedEntity.setPrice(price);
        managedEntity.setCategory(category);
        managedEntity.setManufacturer(manufacturer);
        em.flush();
        em.getTransaction().commit();
        em.close();

	}
}
