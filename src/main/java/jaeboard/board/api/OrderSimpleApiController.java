package jaeboard.board.api;

import jaeboard.board.dto.OrderSearchDto;
import jaeboard.board.entity.Address;
import jaeboard.board.entity.Order;
import jaeboard.board.entity.OrderStatus;
import jaeboard.board.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearchDto());
        for (Order order : all) {
            order.getMember().getUsername();
            order.getDelivery().getAddress();
        }
        return all;
    }
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearchDto());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @Data
    private class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order o) {
            orderId = o.getId();
            name = o.getMember().getUsername();
            orderDate = o.getOrderDate();
            orderStatus = o.getOrderStatus();
            address = o.getDelivery().getAddress();
        }
    }
}
