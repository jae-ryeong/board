package jaeboard.board.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne
    private Delivery delivery;

}
