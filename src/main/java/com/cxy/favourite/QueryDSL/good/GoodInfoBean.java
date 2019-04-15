//package com.cxy.favourite.QueryDSL.good;
//
//import lombok.Data;
//import org.hibernate.annotations.GeneratorType;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "good_infos")
//@Data
//public class GoodInfoBean
//        implements Serializable
//{
//    private static final long serialVersionUID = 1L;
//    //主键
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "tg_id")
//    private Long id;
//    //商品标题
//    @Column(name = "tg_title")
//    private String title;
//    //商品价格
//    @Column(name = "tg_price")
//    private double price;
//    //商品单位
//    @Column(name = "tg_unit")
//    private String unit;
//    //商品排序
//    @Column(name = "tg_order")
//    private int order;
//    //类型外键
//    @Column(name = "tg_type_id")
//    private Long typeId;
//}