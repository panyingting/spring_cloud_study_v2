import com.alibaba.fastjson.JSON;

public class Maintest {

    public static void main(String[] args) {

        One one = new One();

        System.out.println(JSON.toJSONString(one));
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