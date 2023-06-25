package jpabook.jpashop.domain.Item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//  추상 클래스 why? 구현체를 가지고 하기 떄문에
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
//  전략
//  Joined 가장 정교화된 스타일, Single_Table 한군데 몰아넣기 , Table_per_class은 book album movie 처럼 나눠서
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
