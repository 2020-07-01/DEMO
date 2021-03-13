package com.rabbitmq.plan1;

import lombok.Data;

/**
 * @ClassName : MessageContent
 * @Author : yq
 * @Date: 2021-03-13
 * @Description : 自定义消息内容
 */
@Data
public class MessageContent<T> {


    /**
     * 消息内容
     */
    private T content;

    /**
     * 时间戳
     */
    private Long timeStamp;

    /**
     * 消息附带信息
     */
    private ExtraInfo extraInfo;

    private static final class ExtraInfo {

        /**
         * 消息来源
         */
        private String source;
    }


}
