package common.study.main;

import org.junit.jupiter.api.Test;

/**
 * @author : Pan Yingting
 * @date : 2021/2/7 8:30 下午
 */
class SuerClass<T> {

    public SuerClass() {
    }
}

public class GenericTest extends SuerClass<MainDemo> {


    @Test
    public void main() {

        GenericTest test = new GenericTest();
    }
}
