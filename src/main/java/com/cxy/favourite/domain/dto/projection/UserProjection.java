package com.cxy.favourite.domain.dto.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * 仅查询User的userName和email,还有借助@Value注解做聚合展示 得到Information().
 * 可以使用get
 */
public interface UserProjection {
    @Value("#{target.userName + ' ' + target.email}")
    String getInformation();

    String getUserName();

    String getEmail();
}
