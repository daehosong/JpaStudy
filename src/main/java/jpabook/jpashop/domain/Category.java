package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;


    //  다대다 관계는 실무에서는 잘 안 쓰는 방법
    @ManyToMany
    @JoinTable(name = "category_item",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name ="item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child=new ArrayList<>();

    //==양방향 구조 일 때, 연관 관계 편의 메서드 , 위치는 핵심적으로 컨트롤 하는쪽으로 작성
    //  양방향의 클래스를 원자적으로 묶는 메서드를 만드는 것임.
    //  연관 관계 메서드 내용 다시 한번 확인.
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
