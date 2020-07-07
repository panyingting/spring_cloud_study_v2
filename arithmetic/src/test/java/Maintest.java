import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Maintest {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(2019, Calendar.DECEMBER, 31);

        Date strDate = calendar.getTime();

        DateFormat formatUpperCase = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("2019-12-31 to yyyy-MM-dd: " + formatUpperCase.format(strDate));

        formatUpperCase = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-12-31 to YYYY/MM/dd: " + formatUpperCase.format(strDate));


        System.out.println("{\"hour\":15, \"minute\":0, \"channel\":1}");


        System.out.println("date:"+new Date(1296000000+System.currentTimeMillis()));


        System.out.println(1296000001/TimeUnit.DAYS.toMillis(1));
        System.out.println(1296000001%TimeUnit.DAYS.toMillis(1));
        System.out.println(TimeUnit.MILLISECONDS.toDays(111296000001L)/365);

        System.out.println((1593853084389L - System.currentTimeMillis()));
        System.out.println(TimeUnit.MILLISECONDS.toHours(1593853084389L - System.currentTimeMillis()));
        int hour;
        int minute;
        int channel;
        Map<String, Integer> map = JSON.parseObject("{\"hour\":15, \"minute\":0, \"channel\":1}", new TypeReference<Map<String, Integer>>(){});
        hour = map.get("hour");
        minute = map.get("minute");
        channel = map.get("channel");

        System.out.println(hour + " " + minute + " " + channel);
    }
}

class One{
    private boolean belongGiftPlan;

    public boolean isBelongGiftPlan() {
        return belongGiftPlan;
    }

    public void setBelongGiftPlan(boolean belongGiftPlan) {
        this.belongGiftPlan = belongGiftPlan;
    }
}