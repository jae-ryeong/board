package jaeboard.board.service;

import jaeboard.board.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberService memberService;

    @Test
    void join() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("김재령");
        member2.setName("김재령");

        Long member1Id = memberService.Join(member1);
        // Long member2Id = memberService.Join(member2); 중복회원 에러 발생유도

        assertThat(member1Id).isEqualTo(member1.getId());
    }
}