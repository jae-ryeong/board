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
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "orderprice")
    private int orderPrice;
    private int count;
}
