import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MainTest {

    public static void main(String[] args) {

        String TABLE_NAME = "jsd_loan_order";

        Pattern pattern = Pattern.compile("\\W+"+TABLE_NAME+"\\W+", Pattern.CASE_INSENSITIVE);

        String sql = "select * from jsd_loan_oRDER where";
        System.out.println(pattern.matcher(sql).find());
        System.out.println(pattern.matcher(sql));


    }



}


