package io.xianzhi.common.mybatis.plus.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * ID实体
 *
 * @author Max
 * @since 1.0.0
 */
@Data
public class IdDO {
    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
}
