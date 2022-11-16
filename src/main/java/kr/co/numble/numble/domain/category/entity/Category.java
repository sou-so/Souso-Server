package kr.co.numble.numble.domain.category.entity;

import kr.co.numble.numble.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_category")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 10)
    private String categoryName;

    @Column(nullable = false)
    private String categoryImageUrl;

    @Builder
    public Category(String categoryName, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
    }
}
