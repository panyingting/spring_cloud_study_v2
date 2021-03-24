package common.parse.entity.bean.annotation;

import java.lang.annotation.*;

/**
 * 标记Excel列号
 * Created by panyingting
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnIndex {

    int value();
}
