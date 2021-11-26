package common.rsc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : Pan Yingting
 * @date : 2021/4/25 12:04 下午
 */
public class EulerFunction {
    public static void main(String[] args) {
        int n = 65538;
        //由于double运算的精度很高（64位存储），所以误差很小，导致小数部分很小，可以直接舍弃小数部分
        int temp =(int)EulerGenerate(n);
        System.out.println(temp);
    }

    private static double EulerGenerate(int n) {
        if(n==1) {
            return 1.0;
        }
        int eNum = -1;
        String[] dc = Discompose(n).split(" ");
        //键表示素因数，值表示该素因数的次方数
        Map<Integer,Integer> map=new HashMap<>();
        for (String child : dc) {
            //将每个素因数转化为整型（Discompose方法返回格式固定，略去异常处理）
            int num=Integer.parseInt(child);
            if(map.containsKey(num)) {
                //把次方数加1
                map.put(num, map.get(num)+1);
            }else {
                map.put(num, 1);
            }
        }
        //System.out.println(map);
        Set<Integer> set = map.keySet();
        double temp=1.0;
        for (Integer p : set) {
            //直接使用double代替分数近似运算，在主函数中采用舍弃法处理误差
            temp=temp*(1-(1.0/p));
        }
        temp*=n;
        return temp;
    }
    //对n进行素因式分解
    private static String Discompose(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sb.append(i + " ");
                n = n/i;
                i--;
            }

        }
        sb.append(n+" ");
        return sb.toString();
    }
}
