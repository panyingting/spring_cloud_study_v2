package common;

/**
 * @author : Pan Yingting
 * @date : 2021/6/21 12:51 下午
 */
public class TestGCTime {

    public static void main(String[] args) {
        // jstat -gc 1 1000    复制一行数据
        String gc = "39296.0 39296.0  0.0   1490.6 314624.0 133358.2  655360.0   333069.9  99328.0 94506.5 12032.0 11085.8  24133   86.298   4      0.250   86.548";
        // ps -p 1 -o etime    复制结果
        double time = getTime("2-18:17:09");

        String[] s = gc.split("\\s+");
        System.out.printf("          吞吐量：%.5f%%%n", (1 - Double.parseDouble(s[s.length - 1]) / time) * 100);
        System.out.printf("young GC 平均时间：%.2fms%n", (Double.parseDouble(s[s.length - 4]) / Integer.parseInt(s[s.length - 5])) * 1000);
        System.out.printf("young GC 平均间隔：%.2fs%n", time / Integer.parseInt(s[s.length - 5]));
        System.out.printf("  old GC 平均时间：%.2fms%n", (Double.parseDouble(s[s.length - 2]) / Integer.parseInt(s[s.length - 3])) * 1000);
        System.out.printf("  old GC 平均间隔：%.2fs%n", time / Integer.parseInt(s[s.length - 3]));
    }

    private static double getTime(String time) {
        int t = 0;
        if (time.contains("-")) {
            t += Integer.parseInt(time.split("-")[0]) * 24 * 3600;
            time = time.split("-")[1];
        }
        String[] ts = time.split(":");
        return t + Integer.parseInt(ts[0]) * 3600 + Integer.parseInt(ts[1]) * 60 + Integer.parseInt(ts[2]);
    }
}
