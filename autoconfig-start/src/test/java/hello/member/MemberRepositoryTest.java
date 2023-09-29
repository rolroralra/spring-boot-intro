package hello.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.config.DbConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = {MemberRepository.class, DbConfig.class})
@Transactional
@Rollback
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void memberTest() {
        Member member = new Member("member1", "회원1");
        memberRepository.initTable();
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.memberId());

        assertThat(findMember.memberId()).isEqualTo(member.memberId());
        assertThat(findMember.name()).isEqualTo(member.name());
    }
}
