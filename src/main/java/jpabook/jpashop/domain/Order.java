package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long Id;

    //  Order의 입장에서는 N:1 ( 다 대 일 )
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    //  orderItems , order 중 값이 변경되는 테이블이 있으면 양쪽 값을 다 각각 persist를 해줘야 하는데
    //  cascade를 사용하면 일괄 적용된다.
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //  1:1 관계에서는 FK를 어디에 둬도 상관 없다, 주로 Access 많이 하는 곳에 쓰는 게 좋다.
    //  현 상황에서는 주문 쪽을 확인하면서도 배달을 확인 하는 경우는 있지만,
    //  배달을 확인 하면서 주문 쪽을 살필 경우는 잘 없기 떄문에 Order쪽에 선언 하는게 좋다.
    //  모든 연관 관계는 지연상태로 설정 하는게 좋다.(OneToOne,ManyToOne)  fetch = FetchType.LAZY
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    //JoinColumn    -> 연관 관계를 잡아줌
    private Delivery delivery;

    private LocalDateTime orderDate;    //  주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus Status;
    //  주문 상태 : ORDER,CANCEL

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


}
