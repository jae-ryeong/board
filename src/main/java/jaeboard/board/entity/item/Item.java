package jaeboard.board.entity.item;

import jaeboard.board.entity.Category;
import jaeboard.board.entity.ItemCategory;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategory = new ArrayList<>();

    // 비즈니스 로직 (Entity가 자체적으로 해결할 수 있는 경우 Entity안에 로직을 넣는게 좋다)
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public int removeStock(int quantity) {
        if (this.stockQuantity - quantity < 0) {
            throw new IllegalArgumentException("보유재고보다 많은 수를 주문할 수 없습니다. ");
        }
        return this.stockQuantity -= quantity;
    }


























}
