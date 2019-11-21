package common.study.javasisst;


import org.apache.hadoop.hbase.util.Bytes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by panyingting on 2019/1/22.
 */
public class MainTest {

    public static void main(String[] args) throws ParseException {

        byte[] rowKey = rowkey(42473, "2019-01-16 17:34:11");
        System.out.println(new String(rowKey));

        System.out.println(System.currentTimeMillis());

    }

    public static byte[] rowkey(Integer id, String modifiedStr) throws ParseException {
        Date modified = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(modifiedStr);
        byte[] idKey = new byte[8];
        if (id != null) {
            idKey = Bytes.toBytes(Long.reverse(id));
        }

        byte[] timeKey = new byte[Bytes.SIZEOF_LONG];
        if (modified != null) {
            timeKey = Bytes.toBytes(modified.getTime());
        }

        byte[] rowKey = Bytes.add(idKey, timeKey);
        return rowKey;
    }
}
