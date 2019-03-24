package com.cxy.favourite.QueryDSL;

import com.cxy.favourite.QueryDSL.good.GoodDTO;
import com.cxy.favourite.QueryDSL.good.GoodInfoBean;
import com.cxy.favourite.QueryDSL.good.QGoodInfoBean;
import com.cxy.favourite.QueryDSL.good.QGoodTypeBean;
import com.cxy.favourite.common.aop.LogManage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.select.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2019/3/19/019.
 */

@RestController
@RequestMapping(value = "/dsl")
@Slf4j

public class TestQueryDSLController  {

    @Autowired
    private TUserRepository tUserRepository;

    //new
    //Entity管理者
    @Autowired
    private EntityManager entityManager;

    //Jpa查询工厂
    private JPAQueryFactory jpaQueryFactory;

    //只执行一次
    @PostConstruct
    public void initFactory(){
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        log.info("jpaQueryFactory init successfully");
    }



    @RequestMapping(value = "/getAll")
    @LogManage(description = "dsl查询全部,id desc")
    public List<TUserBean> getAll(){
        //使用queryDSL
        QTUserBean tUser = QTUserBean.tUserBean;
        //查询并返回结果,tUser查询源，
        return jpaQueryFactory.selectFrom(tUser).orderBy(tUser.id.asc()).fetch();
    }

    @RequestMapping(value = "/detail/{id}")
    @LogManage(description = "查询单条")
    public TUserBean detail(@PathVariable(value = "id")Integer id){
        //使用queryDSL
        QTUserBean tUser = QTUserBean.tUserBean;
        return jpaQueryFactory.selectFrom(tUser).where(tUser.id.eq(id)).fetchOne();
    }


    @RequestMapping(value = "/detail2/{id}")
    @LogManage(description = "查询单条2")
    public TUserBean detail2(@PathVariable(value = "id")Integer id){
        //SpringDataJpa整合QueryDSL
        QTUserBean tUser = QTUserBean.tUserBean;
        return tUserRepository.findOne(tUser.id.eq(id)).get();
    }

    @RequestMapping(value = "/like")
    @LogManage(description = "模糊查询")
    public List<TUserBean> like(@RequestParam(value = "param",required = true)String name){
            //QueryDSL方式
        QTUserBean tUser = QTUserBean.tUserBean;
        return jpaQueryFactory.selectFrom(tUser).where(tUser.name.like(name)).fetch();
    }

    @RequestMapping(value = "/jpaupdate")
    @LogManage(description = "jpa更新")
    public String update(TUserBean tUserBean){
        this.tUserRepository.save(tUserBean);
       return "success";
    }


    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping(value = "/update")
    @LogManage(description = "DSL更新")
    public String update2(TUserBean tUserBean){
       QTUserBean tUser = QTUserBean.tUserBean;
       jpaQueryFactory.update(tUser).set(tUser.id,tUserBean.getId()).set(tUser.name,tUserBean.getName()).set(tUser.age,tUserBean.getAge())
               .set(tUser.addreee,tUserBean.getAddreee()).set(tUser.pwd,tUserBean.getPwd()).where(tUser.id.eq(tUserBean.getId())).execute();
        return "success";

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @RequestMapping(value = "/delete")
    @LogManage(description = "DSL删除")
    public String delete(){
        QTUserBean tUser = QTUserBean.tUserBean;
        jpaQueryFactory.delete(tUser).where(tUser.addreee.eq("nj").and(tUser.age.gt(20))).execute();
        return "delete success";

    }

    @RequestMapping(value = "selectByType/{typeId}")
    @LogManage(description = "联表查询返回单表数据")
    public List<GoodInfoBean> selectByType(@PathVariable(value = "typeId")Long id){
        //查询实体1
        QGoodInfoBean good = QGoodInfoBean.goodInfoBean;

        //查询实体2
        QGoodTypeBean type = QGoodTypeBean.goodTypeBean;
        //多表查询用selectfrom()会报错
        return jpaQueryFactory.select(good).from(good,type).where(good.typeId.eq(type.id).and(good.typeId.eq(id))).orderBy(good.id.desc()).fetch();

    }

    @RequestMapping(value = "/selectDTO")
    @LogManage(description = "联表查询返回自定义对象")
    public List<GoodDTO> selectByType(){
        //查询实体1
        QGoodInfoBean good = QGoodInfoBean.goodInfoBean;

        //查询实体2
        QGoodTypeBean type = QGoodTypeBean.goodTypeBean;
        //多表查询用selectfrom()会报错
        return jpaQueryFactory.select(
                Projections.bean(GoodDTO.class,
                        good.id,
                        good.title,
                        good.price,
                        good.unit,
                        type.name.as("typeName")

                        )
        ).from(good,type)//构建两表笛卡尔集
                .where(good.typeId.eq(type.id)).orderBy(good.id.desc()).fetch();

    }

    @RequestMapping(value = "/selectDTO2")
    @LogManage(description = "联表查询返回自定义对象2")
    public List<GoodDTO> selectDTO2(){
        //查询实体1
        QGoodInfoBean good = QGoodInfoBean.goodInfoBean;

        //查询实体2
        QGoodTypeBean type = QGoodTypeBean.goodTypeBean;
        //多表查询用selectfrom()会报错
        return jpaQueryFactory.select(
                        good.id,
                        good.title,
                        good.price,
                        good.unit,
                        type.name).from(good,type)
                .where(good.typeId.eq(type.id)).orderBy(good.id.desc()).fetch()
                .stream().map(tuple -> {
                    GoodDTO dto = new GoodDTO();
                    dto.setId(tuple.get(good.id));
                    dto.setPrice(tuple.get(good.price));
                    dto.setTitle(tuple.get(good.title));
                    dto.setUnit(tuple.get(good.unit));
                    dto.setTypeName(tuple.get(type.name));
                    return dto;
                    //返回集合并且转换为List<GoodDTO>
                }).collect(Collectors.toList());

    }

    @RequestMapping(value = "selectCount")
    @LogManage(description = "使用函数")
    public Long selectCount(){
        //查询实体1
        QGoodInfoBean good = QGoodInfoBean.goodInfoBean;

        //查询id条数
        //根据主键来进行聚合，因为主键默认有索引，效率会更高。
        //fetch OR fetchOne genju select里面判断
        return jpaQueryFactory.select(good.id.count()).from(good).fetchOne();

    }

    /**
     * group by & having聚合函数
     * 根据addreee(写错了)进行分组并且仅查询年龄大于22岁的数据
     * 使用groupBy,having分组
     * @return
     */
    @RequestMapping(value = "/groupByExample")
    public List<TUserBean> groupByExample()
    {
        //用户查询实体
        QTUserBean user = QTUserBean.tUserBean;
        return jpaQueryFactory
                .selectFrom(user)
                .groupBy(user.addreee)//根据地区分组
                .having(user.age.gt(22))//并且年龄大于22岁
                .fetch();//返回用户列表
    }

    /**
     * 子查询  通过JpaExpression
     * 价格最高的商品列表
     * @return
     */
    @RequestMapping(value = "/sub")
    public List<GoodInfoBean> list(){
        QGoodInfoBean good = QGoodInfoBean.goodInfoBean;
        return jpaQueryFactory.selectFrom(good).where(good.price.eq(JPAExpressions.select(good.price.max()).from(good))).fetch();
    }


}
