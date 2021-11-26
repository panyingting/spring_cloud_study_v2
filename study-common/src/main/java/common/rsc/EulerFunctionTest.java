package common.rsc;

import org.junit.jupiter.api.Test;

/**
 * @author : Pan Yingting
 * @date : 2021/4/26 2:03 下午
 */
public class EulerFunctionTest {


    @Test
    public void main() {
        // 如果两个正整数a和n互质，那么一定可以找到整数b，使得 ab-1 被n整除，或者说ab被n除的余数是1。
        System.out.println((Math.pow(11, 6))%7);
        System.out.println((Math.pow(11, 6)-1)/7);
        System.out.println((Math.pow(11, 5)));
    }
    @Test
    public void main2() {

        // 1771561
        System.out.println((Math.pow(11, 6)));
        System.out.println((Math.pow(11, 5) * 11 ));
        // 161051.0
        System.out.println((Math.pow(11, 5)  ));
    }

}
