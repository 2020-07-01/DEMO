package rpc;

/**
 * @author :qiang
 * @date :2019/11/8 下午4:13
 * @description :
 * @other :
 */
public class CalculateServiceImpl implements CalculateService {

    @Override
    public String Calculate(People people) {
        int res = people.getA() + people.getB();
        return "计算结果 " + res;
    }
}
