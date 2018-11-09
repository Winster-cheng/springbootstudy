package com.mhc.bi.config;

import com.mhc.framework.support.session.auth.CurrentUserHolder;
import com.mhc.framework.support.session.auth.CurrentUserHolderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 宫本（gongben@maihaoche.com）
 * @since 2018/10/17 上午10:55
 */
@Slf4j
@Configuration
public class BeanConfig {

    @Bean
    public CurrentUserHolder currentUserHolder(){
        return  new CurrentUserHolderImpl();
    }

}
