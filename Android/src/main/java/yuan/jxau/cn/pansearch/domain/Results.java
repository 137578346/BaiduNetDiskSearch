package yuan.jxau.cn.pansearch.domain;

import java.util.List;

/**
 * Created by 编程只服JAVA on 2017.03.12.
 */

public class Results {
    public List<Result> results;

    public class Result{
        public String titleNoFormatting;
        public String unescapedUrl;
    }
}
