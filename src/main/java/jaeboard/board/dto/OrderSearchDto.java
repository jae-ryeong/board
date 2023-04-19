package jaeboard.board.dto;

import jaeboard.board.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearchDto {

    private String memberName;
    private OrderStatus orderStatus;
}
