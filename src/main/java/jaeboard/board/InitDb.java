package jaeboard.board;

import jaeboard.board.entity.*;
import jaeboard.board.entity.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {  // public이 아닌 static 클래스이다.

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("김재령", "대전", "1", "1234");
            em.persist(member);

            Book book1 = createBook("JPA1", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("김희주", "도마동", "2", "3456");
            em.persist(member);

            Book book1 = createBook("SPRING1", 30000, 150);
            em.persist(book1);

            Book book2 = createBook("SPRING2", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 5);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
        private static Delivery createDelivery(Member member) { // static 메서드이다.
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
        private static Book createBook(String name, int price, int quantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(quantity);
            return book;
        }
        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setUsername(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}
