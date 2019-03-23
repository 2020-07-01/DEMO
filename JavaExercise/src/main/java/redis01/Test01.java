package redis01;
/**
 * redis与java连接：测试redis每秒的操作次数
 * 需要添加jedis.jar包
 * @author qiang
 *
 */
public class Test01 {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localHost",6379);//连接redis
		int i = 0;//记录操作次数
		try {
			//开始毫秒数
			long start = System.currentTimeMillis();
			while(true) {
				long end = System.currentTimeMillis();
				if(end - start>= 1000) {
					//当大于等于1秒时结束操作
					break;
				}
				i++;
				jedis.set("test"+i, i + "");
			}
		} finally {//关闭连接
			jedis.close();
		}
		//打印1秒内redis的操作次数
		System.out.println("redis每秒操作："+i+"次");
	}
}
