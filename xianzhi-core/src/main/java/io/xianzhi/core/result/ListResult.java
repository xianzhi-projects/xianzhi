package io.xianzhi.core.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 列表返回结果
 *
 * @author Max
 * @since 1.0.0
 */

@Data
@AllArgsConstructor
public class ListResult<E> implements Serializable {

    /**
     * 查询的数据
     */
    private List<E> list;

    /**
     * 查询的总数
     */
    private long total;

    /**
     * 返回空结果
     *
     * @param <E> 泛型
     * @return 返回空结果
     */
    public static <E> ListResult<E> empty() {
        return new ListResult<>(List.of(), 0);
    }

    /**
     * 构建返回结果
     *
     * @param list  查询的列表
     * @param total 查询的总数
     * @param <E>   返回的数据泛型
     * @return 列表结果
     */

    public static <E> ListResult<E> of(List<E> list, long total) {
        return new ListResult<>(list, total);
    }
}