package jpabook.jpashop.service;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
//  Transaction에서 데이터 변경하는 건 트랜잭셔널 무조건 있어야 함. 스프링에서 제공하는 트랜잭셔널 사용하는 게 좋다.
//  Tarnsactional(readOnly=true)    -> 조회 같이 데이터를 읽어 올 때 최적화로 찾아옴   , 디폴트 값 false
//  단, 쓰기를 할 때에는 true로 설정하면 데이터 변경이 안되기 때문에 사용 금지
//  그리고 내부에서 Transactional 사용시 내부가 우선권을 가진다.
//  @RequiredArgsConstructor    (롬복)    ->  final이 있는 필드만 가지고 생성자를 바로 만들어줌.
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    //  Autowired 인젝션 주입
    //  이렇게 쓰면 단점은 변경이 불가능하고, 테스트 같은거 할 때 불편하다.
    //  생성자 주입 방식을 권장
    //  변경 불가능한 안전한 객체 생성 가능
    //  생성자가 하나면, @Autowired 를 생략할 수 있다.
    //  private MemberRepository memberRepository; 에서
    //  final 키워드를 추가하면 컴파일 시점에 memberRepository 를 설정하지 않는 오류를 체크할 수 있다.
    //  (보통 기본 생성자를 추가할 때 발견)
    private final MemberRepository memberRepository;
    /** 회원가입
     * @param member
     * @return
       같은 이름이면 안된다는 중복회원 검증 추가
     */
    @Transactional
    public Long join(Member member){
        validateDUplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
/*
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/
    public void validateDUplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //  회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //  회원 하나 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
