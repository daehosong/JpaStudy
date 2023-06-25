package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.member.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  //  트랜잭셔널이 테스트에 있을 때, 기본적으로 롤백을 해버림
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void MemberSign() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);
        //then
        assertEquals(member,memberRepository.findOne(savedId));

    }
    @Test
    public void MemberException() throws Exception{
        //given

        //when

        //then

    }
}