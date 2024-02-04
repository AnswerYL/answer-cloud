/**
 * @projectName answer-cloud
 * @package com.answer.file.model
 * @className com.answer.file.model.FileInfo
 */
package com.answer.file.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * FileInfo
 * @description 文件实体类
 * @author answer_wx
 * @date 2024/1/23 19:59
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("file_info")
public class FileInfo extends Model<FileInfo> {
    private static final long serialVersionUID = -1438078028040922174L;

    @TableId
    private String id;
    /**
     * 原始文件名
     */
    private String name;
    /**
     * 是否图片
     */
    private Boolean isImg;
    /**
     * 上传文件类型
     */
    private String contentType;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 冗余字段
     */
    private String path;
    /**
     * oss访问路径 oss需要设置公共读
     */
    private String url;
    /**
     * FileType字段
     */
    private String source;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}