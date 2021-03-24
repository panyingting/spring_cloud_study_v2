package common.parse.context;


import common.parse.entity.bean.ExcelUserEntity;
import common.study.option.ServiceResponse;

import javax.servlet.http.Cookie;
import java.io.InputStream;

/**
 * Excel 解析上下文
 * Created by panyingting
 */
public class UserIdParseContext extends ExcelParseContext<ExcelUserEntity> {

    private ServiceResponse result;

    private Cookie[] cookies;


    public UserIdParseContext(InputStream inputStream) {
        super(inputStream);
    }


    public ServiceResponse getResult() {
        return result;
    }

    public void setResult(ServiceResponse result) {
        this.result = result;
    }

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

}
