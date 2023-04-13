package jaeboard.board.dto;

import jaeboard.board.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class orderSearchDto {

    private String memberName;
    private OrderStatus orderStatus;
}
