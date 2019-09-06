package transformImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author :qiang
 * @date :2019/7/25 下午11:13
 * @description :
 * @other :
 */
public class ConverterUtil {

    // JGP格式
    public static final String JPG = "jpeg";
    // GIF格式
    public static final String GIF = "gif";
    // PNG格式
    public static final String PNG = "png";
    // BMP格式
    public static final String BMP = "bmp";


    public static void converter(File imgFile, String format, File formatFile)
            throws IOException {

        BufferedImage bIMG = ImageIO.read(imgFile);

        ImageIO.write(bIMG, format, formatFile);
    }

    public static void main(String[] args) {

        try {
            // 转换为JGP
            ConverterUtil.converter(new File("/home/qiang/图片/1.png"), JPG, new File("/home/qiang/图片"));
            /* // 转换为GIF
            ConverterUtil.converter(new File("XXX"), GIF, new File("XXX"));
            // 转换为PNG
            ConverterUtil.converter(new File("XXX"), PNG, new File("XXX"));
            // 转换为BMP
            ConverterUtil.converter(new File("XXX"), BMP, new File("XXX"));*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
