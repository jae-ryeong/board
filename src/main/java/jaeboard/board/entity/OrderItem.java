package jaeboard.board.entity;

import jaeboard.board.entity.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
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

    // 생성 메서드
    public void createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        orderItem.setItem(item);

        item.removeStock(count);
    }
    
    // 비즈니스 로직
    public void cancel() {
        getItem().addStock(count);
    }

    // 조회 로직
    public int getToTalPrice() {
        return orderPrice * count;
    }
}
