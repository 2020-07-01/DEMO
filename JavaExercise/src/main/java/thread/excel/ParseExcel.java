package thread.excel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName : ParseExcel
 * @Author : yq
 * @Date: 2021-03-13
 * @Description :
 */
public class ParseExcel {

    private static List<Object> globalList = new CopyOnWriteArrayList();

    /**
     * 解析Excel
     * 1.分页解析
     */

    public void acceptStream(FileInputStream fileInputStream) throws Exception {

        Workbook workbook = WorkbookFactory.create(fileInputStream);

        Sheet sheet;

        List<Sheet> sheetList = new LinkedList<>();
        int sheetIndex = 0;
        boolean flag = true;
        while (flag) {
            sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                flag = false;
            } else {
                sheetList.add(sheet);
            }
            sheetIndex++;
        }

        int processors = Runtime.getRuntime().availableProcessors();
        int count = sheetList.size() % processors == 0 ? sheetList.size() % processors : sheetList.size() % processors + 1;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        BlockingQueue queue = new DelayQueue();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.SECONDS, queue, threadFactory);

        for (int i = 0; i < processors; i++) {
            int start = count * i;
            int end = (count * (i + 1)) > sheetList.size() ? sheetList.size() : count * (i + 1);
            threadPoolExecutor.submit(new Parse(cyclicBarrier, sheetList, start, end));
        }

        boolean isFinished = false;
        while (isFinished) {
            if (cyclicBarrier.getNumberWaiting() == 0) {
                isFinished = true;
            }
        }
        System.out.println("线程执行结束!");


    }

    private static class Parse implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private List<Sheet> sheetList;

        private Integer start;

        private Integer end;


        Parse(CyclicBarrier cyclicBarrier, List<Sheet> sheetList, Integer start, Integer end) {
            this.cyclicBarrier = cyclicBarrier;
            this.sheetList = sheetList;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                List<Object> list = new LinkedList<>();
                List<Sheet> sheets = sheetList.subList(start, end);

                for (Sheet sheet : sheets) {

                    int rows = sheet.getLastRowNum();

                    for (int i = 0; i < rows; i++) {
                        //解析
                    }

                }

                globalList.addAll(list);
                cyclicBarrier.await();
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {

    }
}
