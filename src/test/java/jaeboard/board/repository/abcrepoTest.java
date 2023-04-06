package jaeboard.board.repository;

import jaeboard.board.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class abcrepoTest {

    @Autowired abcrepo abcrepo;

    @Test
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        Long savedId = abcrepo.save(member);
        //when
        Member findMember = abcrepo.find(savedId);

        //then
        assertThat(findMember).isEqualTo(member);
    }

}