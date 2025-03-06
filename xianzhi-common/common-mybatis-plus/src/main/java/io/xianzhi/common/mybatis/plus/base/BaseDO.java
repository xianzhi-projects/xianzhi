package io.xianzhi.common.mybatis.plus.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.xianzhi.common.mybatis.plus.MyBatisConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 基础实体
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDO extends IdDO {

    /**
     * 新增用户
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 新增时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    /**
     * 修改用户
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;

    /**
     * 删除标识
     */
    @TableField(value = MyBatisConstant.DELETED_FLAG)
    private Boolean deletedFlag;
}
