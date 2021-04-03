package com.cjs.sso.service;

import com.alibaba.fastjson.JSON;
import com.cjs.sso.domain.MyUser;
import com.cjs.sso.entity.SysPermission;
import com.cjs.sso.entity.SysUser;
import com.cjs.sso.redis.RedisService;
import com.cjs.sso.util.Constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2019-02-11
 * @author:heshengjin qq:2356899074
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getByUsername(username);
        if (null == sysUser) {
            log.warn("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }
        List<SysPermission> permissionList = permissionService.findByUserId(sysUser.getId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (SysPermission sysPermission : permissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }
        //举个例子，假设我们想增加一个字段，这里我们增加一个mobile表示手机号
        //附加的mobile参数
        MyUser myUser = new MyUser(sysUser.getUsername(), sysUser.getPassword(),Constants.MOBILE,sysUser.getId(), true, true, true, true, authorityList);

        log.info("登录成功！用户: {}", JSON.toJSONString(myUser));
        //登录就删除Redis的sso退出标识
        redisService.deleteObject(Constants.SSO_CLICENT_LOGOUT_NAME_PREFIX + sysUser.getUsername().trim());
        return myUser;
    }
}
