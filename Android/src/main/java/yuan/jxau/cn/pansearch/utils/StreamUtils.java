package yuan.jxau.cn.pansearch.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 编程只服JAVA on 2017.03.12.
 */

public class StreamUtils {
    /**
     * 将输入流转化为字符串
     * @param inputStream
     * @return string
     */
    public static String streamToString(InputStream inputStream) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*100];
        int temp = -1;

        try {
            while ((temp = inputStream.read(buffer))!=-1){
                bos.write(buffer , 0, temp);
            }
            //返回读取数据
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
