package jaeboard.board.entity.item;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Movie extends Item {

    private String director;
    private String actor;
}
