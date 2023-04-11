package jaeboard.board.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // order 클래스 내에 member 라는이름의 변수에 의해
    private List<Order> Orders = new ArrayList<>(); // Many는 많으니까 List로 받는다.

}
