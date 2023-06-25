package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

//  @Embeddable = 어딘가에 내장 될 수 있다. -> 해당 되는 곳은 @Embedded
//  값 타입은 @Setter를 제공 하지 않게 하고 , 생성자에서 값을 모두 초기화 해서 변경 불가능한 클래스를 만든다.
//  jpa 스펙상 자바 기본 생성자를 public이나 protected로 해야 하는데
//  public으로 설정하면 어디에서든 불러올 수 있기 때문에, protected로 설정하는 게 좋다.
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    //  스프링 부트 신규 설정 (엔티티(필드) 테이블(컬럼))
    //  1. 카멜 케이스 언더스코어(memberPoint member_point)
    //  2. .(점) _(언더스코어)
    //  3. 대문자 소문자
    //  아래 집코드는 zip_Code로 생성 됨.
    //  //////////////////////////////////////////////////////////////////////////////////////////
    //  1. 논리명 생성: 명시적으로 컬럼, 테이블명을 직접 적지 않으면 ImplicitNamingStrategy 사용
    //  spring.jpa.hibernate.naming.implicit-strategy : 테이블이나, 컬럼명을 명시하지 않을 때 논리명 적용,
    //  2. 물리명 적용:
    //  spring.jpa.hibernate.naming.physical-strategy : 모든 논리명에 적용됨, 실제 테이블에 적용
    //  (username usernm 등으로 회사 룰로 바꿀 수 있음

    private String zipCode;

    //  기본 생성자까지 만들어 줘야 함.
    protected Address() {
    }

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
