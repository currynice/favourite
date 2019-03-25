package com.cxy.favourite.resolver;


import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.annotation.CurrentUser;
import com.cxy.favourite.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserRepository userRepository;
    //参数时User类，而且有@CurrentUser注解
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)&&parameter.getParameterType().isAssignableFrom(User.class);
    }

    /**
     * 返回Controller的形参对象
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

//        //请求参数类型为其他(自己组装的实体)
//        Type paramType = parameter.getGenericParameterType();
//        Object result = JSON.parseObject(jsonBody, paramType);
//        return result;
        Long userId = new Long(request.getParameter("userId"));
        //查数据库,这里比较简单
        //参考https://blog.csdn.net/a18716374124/article/details/79208990

        return userRepository.getByUserId(userId);

    }

}


