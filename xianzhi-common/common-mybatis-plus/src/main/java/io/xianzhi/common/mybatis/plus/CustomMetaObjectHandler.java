package io.xianzhi.common.mybatis.plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.xianzhi.core.content.ContextHolder;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义数据填充
 *
 * @author Max
 * @since 1.0.0
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        String currentUserId = ContextHolder.getContextOrThrow().getUniqueKey();
        this.setFieldValByName(MyBatisConstant.CREATE_BY, currentUserId, metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_BY, currentUserId, metaObject);
        this.setFieldValByName(MyBatisConstant.CREATE_AT, now, metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_AT, now, metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(MyBatisConstant.UPDATE_BY, ContextHolder.getContextOrThrow().getUniqueKey(), metaObject);
        this.setFieldValByName(MyBatisConstant.UPDATE_AT, new Date(), metaObject);

    }
}
