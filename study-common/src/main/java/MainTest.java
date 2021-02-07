import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author : Pan Yingting
 * @date : 2020/12/10 5:12 下午
 */
public class MainTest {

    public static void main(String[] args) throws ParseException {


        String str = "|@|32|4|4|4";

        String[] strs = str.split("\\|");
        System.out.println(Lists.newArrayList(strs));


    }
}
