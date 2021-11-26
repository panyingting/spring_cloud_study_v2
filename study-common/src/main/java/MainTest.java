import com.google.common.collect.Lists;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Pan Yingting
 * @date : 2020/12/10 5:12 下午
 */
public class MainTest {

    public static void main(String[] args) throws ParseException {


        String str = "3.4dfs5";

        String[] strs = str.split("\\D+");
        System.out.println(Lists.newArrayList(strs));

        System.out.println(TimeUnit.SECONDS.toDays(1625068800- 1624464000));

    }
}
