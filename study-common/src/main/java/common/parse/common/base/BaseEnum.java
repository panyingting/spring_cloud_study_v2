package common.parse.common.base;

/**
 * @describe: 枚举信息定义
 * @author: pyt email:panyingting220415@credithc.com
 * @create_time: 2022/8/29 11:19
 */
public interface BaseEnum<T, K extends BaseEnum<T, K>> {

    T getCode();

    K[] getEnums();

    /**
     * 判断当前 code是否属于当前枚举实例信息
     * @param code 枚举对应的 code
     * @return code 和当前实例code相对则返回 true, code为空或者不等则返回false
     */
    default boolean isOf(T code){
        return code != null && code.equals(getCode());
    }

    /**
     * 根据 code 获取对应枚举实例信息
     * @param code 编码信息
     * @return 返枚举实例信息
     */
    default K getInstance(T code){
        if (code != null) {
            K[] arr = getEnums();
            for (K instance : arr) {
                if (instance.isOf(code)) {
                    return instance;
                }
            }
        }
        return null;
    }



}
