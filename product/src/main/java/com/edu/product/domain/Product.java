package com.edu.product.domain;

import com.edu.product.otherdomain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    User seller;
    @NotEmpty
    String productName;
    @NotEmpty
    String productContent;
    @NotEmpty
    String productImgUrl;
    @NotNull
    Long price;

}
