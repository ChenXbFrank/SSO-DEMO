package com.cjs.sso.vo;

import com.cjs.sso.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 * @author:heshengjin qq:2356899074
 */
@Data
public class SysUserVO extends SysUser {

    /**
     * ๆ้ๅ่กจ
     */
    private List<String> authorityList;

}
