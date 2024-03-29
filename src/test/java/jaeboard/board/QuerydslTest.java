package jaeboard.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jaeboard.board.entity.Member;

import jaeboard.board.entity.QMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslTest {
    
    @PersistenceContext
    EntityManager em;

    @Test
    void querydslTest() {

        Member member = new Member();
        member.setUsername("ABC");
        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = QMember.member;

        List<Member> result = query
                .selectFrom(qMember)
                .fetch();

        //assertThat(result).isEqualTo(member);
        // lombok 동작 확인 (hello.getId())
        //assertThat(result.getId()).isEqualTo(member.getId());
    }
}
