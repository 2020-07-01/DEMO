package test3;

import java.nio.channels.Channel;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 此类为发布订阅的监听类
 * 此类需要在Spring容器中进行配置
 * 在配置文件中还需要配置监听容器
 * 
 * 过程实现：订阅发布机制包括三个部分：订阅者、发布者、channel(通道)，发布者和订阅者都是Redis的客户端
 * @author qiang
 *
 */
public class RedisMessageListener implements MessageListener {
	
	private RedisTemplate redisTemplate;
	
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void onMessage(Message message, byte[] bytes) {
		//获取消息
		byte[] body = message.getBody();
		
		//使用值序列转化器
		String msgBody = (String) redisTemplate.getValueSerializer().deserialize(body);
		System.err.print(msgBody);
		
		//获取channel
		byte[] Channel = message.getChannel();
		//使用字符串序列化器转化
		String channelStr = (String) redisTemplate.getStringSerializer().deserialize(Channel);
		System.err.print(channelStr);
		//渠道名称转换
		String byteStr = new String(bytes);
		System.err.println(byteStr);
		
	}

}
