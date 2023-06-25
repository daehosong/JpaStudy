package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //  멤버의 입장에서는 1:N ( 일대 다 )
    //  mappedBy은 누구에 의해서 매핑이 되었는지 나타내줌.
    @OneToMany(mappedBy = "member")

    //  컬렉션은 필드에서 바로 초기화 해서 사용하는 것이 안전하다.
    //  만약 getOrders() 처럼 임의의 메서드에서 컬력션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문제가 발생할 수 있다
    private List<Order> orders = new ArrayList<>();

}
