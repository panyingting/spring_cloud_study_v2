package common.parse.common;

import common.parse.common.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @describe: 申请金额配置表数据类型信息
 * @author: pyt email:panyingting220415@credithc.com
 * @create_time: 2022/8/29 11:16
 */
@AllArgsConstructor
@Getter
public enum AppApplyAmountConfigTypeEnum implements BaseEnum<String, AppApplyAmountConfigTypeEnum> {

    DEFAULT("0", "默认数据"),
    AB_TEST_AMOUNT("1", "AB测试金额区间数据"),
    ;

    private final String code;

    private final String name;

    @Override
    public AppApplyAmountConfigTypeEnum[] getEnums() {
        return AppApplyAmountConfigTypeEnum.values();
    }

}
