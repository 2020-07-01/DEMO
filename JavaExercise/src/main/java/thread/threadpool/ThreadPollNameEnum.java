package thread.threadpool;

/**
 * @ClassName : ThreadPollNameEnum
 * @Author : yq
 * @Date: 2021-03-14
 * @Description :
 */
public enum ThreadPollNameEnum {


    parseExcel("parseExcel", "解析Excel文件"),
    createExcel("createExcel", "生成Excel文件");

    /**
     * 线程池名称
     */
    private String threadPollName;

    /**
     * 业务描述
     */
    private String businessDesc;

    ThreadPollNameEnum(String threadPollName, String businessDesc) {
        this.threadPollName = threadPollName;
        this.businessDesc = businessDesc;
    }
}
