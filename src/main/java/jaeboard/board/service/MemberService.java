package jaeboard.board.service;

import jaeboard.board.entity.Member;
import jaeboard.board.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long Join(Member member) {
        validateDuplicateMember(member);    // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUsername());

        if(!findMembers.isEmpty()){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    public Member findOne(Long memberId) {
        Member member = memberRepository.findOne(memberId);
        return member;
    }

    @Transactional
    public void update(Long id, String username) {
        Member member = memberRepository.findOne(id);
        member.setUsername(username);
    }
}
