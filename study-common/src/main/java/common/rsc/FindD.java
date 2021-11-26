package common.rsc;

import org.junit.jupiter.api.Test;

/**
 * @author : Pan Yingting
 * @date : 2021/4/25 12:29 下午
 */
public class FindD {

    @Test
    public void test(){
        System.out.println(findD(24, 23));
    }
    public int findD(int r, int e){
        for (int d=1; d<1000; d++) {
            if((e*d-1)%r == 0) {
                System.out.println(e*d-1);
                System.out.println((e*d-1)/r);
                return d;
            }
        }
        return 0;
    }

    @Test
    public void test2(){
        double d = Math.pow(125, 7);
        // 5
        System.out.println(d%143);
        System.out.println(Math.pow(67, 103));
        System.out.println(Math.pow(67, 103)%143);
    }
}
