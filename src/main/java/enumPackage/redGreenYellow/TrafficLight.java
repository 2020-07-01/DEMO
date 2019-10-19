package enumPackage.redGreenYellow;

/**
 * @author :qiang
 * @date :2019/10/19 下午2:04
 * @description :
 * @other :
 */
public class TrafficLight {

    //color的值一直在改变
    Signal color = Signal.RED;

    public void change() {

        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            default:
                System.out.println("信号灯出现异常!");
        }
    }

    @Override
    public String toString() {
        return "the trafficLight is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(t);
            t.change();
        }
    }

}
