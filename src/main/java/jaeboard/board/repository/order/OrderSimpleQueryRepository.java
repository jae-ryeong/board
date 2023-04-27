package jaeboard.board.repository.order;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jaeboard.board.repository.order.OrderSimpleQueryDto(" +
                "o.id, m.username, o.createDate, o.orderStatus, d.address)" +
                " from Order o"+
                " join o.member m"+
                " join o.delivery d", OrderSimpleQueryDto.class).getResultList();
    }
}
