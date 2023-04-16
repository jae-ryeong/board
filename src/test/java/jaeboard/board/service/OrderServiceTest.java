package jaeboard.board.service;

import jaeboard.board.entity.*;
import jaeboard.board.entity.item.Book;
import jaeboard.board.entity.item.Item;
import jaeboard.board.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext private EntityManager em;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderService orderService;

    @Test
    public void orderSave() throws Exception{
        //given
        Member member = createMember();

        Item item = createBook("JPA", 10000, 10); // 이름, 가격, 재고
        int quantity = 2;
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), quantity);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(item.getStockQuantity()).isEqualTo(8);
        assertThat(getOrder.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);   // 주문한 상품의 종류 수
        assertThat(getOrder.getTotalPrice()).isEqualTo(20000);
    }

    @Test
    public void 상품재고수량초과() throws Exception{
        //given
        Member member = createMember();

        Item item = createBook("JPA", 10000, 10); // 이름, 가격, 재고
        int quantity = 11;

        assertThrows(IllegalArgumentException.class, () -> orderService.order(member.getId(), item.getId(), quantity));
    }

    @Test
    public void cancel() throws Exception{
        //given
        Member member = createMember();

        Item item = createBook("JPA", 10000, 10); // 이름, 가격, 재고
        int quantity = 2;
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), quantity);

        //then
        Order order = orderRepository.findOne(orderId);
        orderService.cancel(orderId);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("김재령");
        member.setAddress(new Address("대전", "동산초교로", "12345"));
        em.persist(member);
        return member;
    }

    private Book createBook(String book_name, int price, int quantity) {
        Book book = new Book();

        book.setName(book_name);
        book.setPrice(price);
        book.setStockQuantity(quantity);

        em.persist(book);
        return book;
    }


























}