/**
 * @projectName answer-cloud
 * @package com.answer.user.model
 * @className com.answer.user.model.SysUserExcel
 */
package com.answer.user.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.answer.common.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * SysUserExcel
 * @description 用户导出实例
 * @author answer_wx
 * @date 2024/1/23 21:17
 * @version 1.0
 */
@Data
public class SysUserExcel implements Serializable {
    private static final long serialVersionUID = -5886012896705137070L;

    @Excel(name = "用户姓名", height = 20, width = 30, isImportField = "true_st" )
    private String username;

    @Excel(name = "用户昵称", height = 20, width = 30, isImportField = "true_st" )
    private String nickname;

    @Excel(name = "手机号码", height = 20, width = 30, isImportField = "true_st" )
    private String mobile;

    @Excel(name = "性别", replace = {"男_0", "女_1"}, isImportField = "true_st" )
    private Integer sex;

    @Excel(name = "创建时间", format = CommonConstant.DATETIME_FORMAT, isImportField = "true_st", width = 20)
    private Date createTime;

    @Excel(name = "修改时间", format = CommonConstant.DATETIME_FORMAT, isImportField = "true_st", width = 20)
    private Date updateTime;
}