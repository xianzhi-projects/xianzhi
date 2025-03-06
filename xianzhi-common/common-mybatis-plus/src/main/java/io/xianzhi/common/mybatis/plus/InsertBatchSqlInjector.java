package io.xianzhi.common.mybatis.plus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * 批量插入
 *
 * @author Max
 * @since 1.0.0
 */
public class InsertBatchSqlInjector extends DefaultSqlInjector {

    /**
     * 添加批量插入
     *
     * @param configuration 配置对象
     * @param mapperClass   当前mapper
     * @param tableInfo     表信息
     * @return 方法
     */
    @Override
    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(configuration, mapperClass, tableInfo);
        // 添加自定义方法：批量插入，方法名为 insertBatchSomeColumn
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}