package jaeboard.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<>();
}
