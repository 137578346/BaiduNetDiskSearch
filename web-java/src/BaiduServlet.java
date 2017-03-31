import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by 编程只服JAVA on 2016.09.03.
 */
public class BaiduServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html,charset=utf-8");
        /**
         * 得到谷歌自定义搜索需要的参数
         */
        String start = req.getParameter("start");//设置搜索结果的起始页面
        String q=URLEncoder.encode(req.getParameter("q"),"utf-8");//搜索内容
        /**
         * 和google建立连接，并且得到返回的数据
         */
        String urlStr="https://www.googleapis.com/customsearch/v1element?key=AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY&rsz=filtered_cse&num=10&hl=zh_CN&prettyPrint=false&source=gcsc&gss=.com&sig=8bdfc79787aa2b2b1ac464140255872c&start="+start+"&cx=016351911466409552003:emnpyw-pq98&q="+q+"&sort=&googlehost=https://www.google.com.hk/";
        URL url=new URL(urlStr);//得的url
        InputStream inputStream =url.openStream();//得到输入流（google已经将结果发送过来了）
        /**
         * 优化输入流的读取速度，进行层层包装
         *      1.先将其包装为缓冲字节数据流
         *      2.再包装为字符流
         *      3.——>包装字符流
         */
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
        //BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        /**
         * 将数据流转化为字符串
         */
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        bufferedReader.close();
        /**
         * 将字符串转化为json
         */
        JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());

        /**
         * 将字符串发往客户端
         */
        PrintWriter out = resp.getWriter();
        out.println(stringBuilder);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
