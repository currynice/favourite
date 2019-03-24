package com.cxy.favourite.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;

/**
 * jpa父接口
 * @NoRepositoryBean指明当前这个接口不作为一个Repository创建代理实现类。
 * 用来配置父接口不参与jpa的代理
 *
 *
 *UserRepository
 * 继承了JpaRepository接口（SpringDataJPA提供的简单数据操作接口）、
 * JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口，未选择）
 * Serializable（序列化接口）。
 */

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>
        ,JpaSpecificationExecutor<T>,QuerydslPredicateExecutor<T>
{
}
