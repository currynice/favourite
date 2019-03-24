package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.UrlLibrary;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Administrator on 2019/3/6/006.
 */
public interface UrlLibraryRepository extends BaseRepository<UrlLibrary,Long>{
    //List<UrlLibrary> findByCountLessThanAndLogoUrl(int count, String str);

    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update UrlLibrary u set u.count=u.count+1 where u.id =:id ")
    int increaseCountById(@Param("id") Long id);


    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update UrlLibrary u set u.logoUrl = ?2 where u.id = ?1")
    int updateLogoUrlById(Long id,String logoUrl);
}
