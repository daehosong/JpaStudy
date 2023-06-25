package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ExMemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberEx memberEx){
        em.persist(memberEx);
        return memberEx.getId();
    }
    public MemberEx find(Long id){
        return em.find(MemberEx.class,id);
    }
}
