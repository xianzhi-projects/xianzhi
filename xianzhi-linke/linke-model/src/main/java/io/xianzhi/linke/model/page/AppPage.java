package io.xianzhi.linke.model.page;

import io.xianzhi.core.base.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 应用查询条件
 *
 * @author Max
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppPage extends Page implements Serializable {
}
