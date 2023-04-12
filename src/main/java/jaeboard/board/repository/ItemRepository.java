package jaeboard.board.repository;

import jaeboard.board.entity.item.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void Save(Item item) {
        if(item.getId() == null){
            em.persist(item);
        } else{
            em.merge(item);
        }
    }

    public List<Item> findAll() {
        return  em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
}
