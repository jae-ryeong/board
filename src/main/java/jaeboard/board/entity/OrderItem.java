package jaeboard.board.entity;

import jaeboard.board.entity.item.Item;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "order_item")
public class OrderItem {   // 주문 상품

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private int orderPrice;
    private int count;
}
