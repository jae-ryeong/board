package jaeboard.board.service;

import jaeboard.board.entity.*;
import jaeboard.board.entity.item.Item;
import jaeboard.board.repository.ItemRepository;
import jaeboard.board.repository.MemberRepository;
import jaeboard.board.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor    // final인 애들의 생성자를 만들어준다.
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // member id를 받고, item의 id를 받고, orderitem의 수량정보를 받아서, 실제 주문 엔티티 생성

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setDeliveryStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);   // createOrderItem()을 static 메서드로 생성함으로서 여기서 사용 할 수 있게된다.

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        // 주문 식별자를 받아 주문 엔티티 조회 후 주문 취소 요청
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
