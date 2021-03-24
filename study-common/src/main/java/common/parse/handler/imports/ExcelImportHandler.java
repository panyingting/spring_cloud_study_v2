package common.parse.handler.imports;


import common.parse.context.ExcelParseContext;

/**
 * handler 接口
 * Created by panyingting
 */
public interface ExcelImportHandler<T> {

    /**
     * 操作方法
     *
     * @param context 上下文信息
     * @throws Exception 抛出异常
     */
    void handle(ExcelParseContext<T> context) throws Exception;

}
