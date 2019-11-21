package common.study.set;

import common.study.entity.One;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tree Set 学习
 * Created by panyingting on 2018/12/3.
 */
public class TreeSetTest {


    public static void main(String[] args) throws ParseException {
        TreeSet treeSet = new TreeSet();

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-01").getTime());
    }
}
