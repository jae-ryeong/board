package jaeboard.board.entity.item;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Music extends Item {

    private String artist;
    private String etc;
}
