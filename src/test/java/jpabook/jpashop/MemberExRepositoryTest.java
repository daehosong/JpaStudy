package jpabook.jpashop;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberExRepositoryTest {
    @Autowired
    ExMemberRepository exMemberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        MemberEx memberEx = new MemberEx();
        memberEx.setUsername("memberA");
        Long savedId = exMemberRepository.save(memberEx);
        MemberEx findMemberEx = exMemberRepository.find(savedId);
        Assertions.assertThat(findMemberEx.getId()).isEqualTo(memberEx.getId());

        Assertions.assertThat(findMemberEx.getUsername()).isEqualTo(memberEx.getUsername())
        ;
        Assertions.assertThat(findMemberEx).isEqualTo(memberEx); //JPA 엔티티 동일성 보장
    }
}