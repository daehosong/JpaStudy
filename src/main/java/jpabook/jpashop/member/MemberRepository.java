package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //  JPA가 제공하는 표준 어노테이션
    //  em에 인젝션 주입  , EntityManager은 @Autowired로는 안되고 @PersistenceContext 표준 어노테이션이 있어야 하지만
    //  스프링 데이터 JPA가 있으면 지원해준다.
    //  persist는 DB에 insert 쿼리가 날라가게 된다. 영속성 컨텐츠에 멤버 객체를 올려줌
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    //  단건 조회 ,      .find(type,PK)
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    //  sql은 테이블을 대상으로 쿼리문을 만드는데, qlString은 엔티티 객체<<를 대상으로 쿼리를 만든다고 보면 된다.
    //  from 뒤의 대상이 테이블이 아니라 엔티티가 된다.
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //  이름을 통해서 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name", Member.class)
                .setParameter("name",name).getResultList();
    }

}
