package jaeboard.board.dto;

import jaeboard.board.entity.Address;
import jaeboard.board.entity.Order;
import jaeboard.board.entity.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    private Long orderId;
    private String userName;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        userName = order.getMember().getUsername();
        orderDate = order.getCreateDate();
        orderStatus = order.getOrderStatus();
        address = order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream()
                .map(o -> new OrderItemDto(o)).collect(Collectors.toList());
    }
}
