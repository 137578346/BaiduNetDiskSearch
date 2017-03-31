<%--
  Created by IntelliJ IDEA.
  User: 编程只服JAVA
  Date: 2016.09.03
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>百度网盘百度云网盘搜索引擎</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="网盘搜索|百度网盘搜索|百度云搜索" />
    <meta name="description" content="百度云搜索，搜索百度云资源，无论是java、javascript、android、视频教程" />
    <link href="css/base2.css" rel="stylesheet" type="text/css" />
    <link rel="shortcut  icon" type="image/x-icon" href="a.ico" media="screen"  />
    <script>
        function checkForm(){
            var q=document.getElementById("q");
            if(q.value==null || q.value==""){
                alert("~=(๑•́ ₃ •̀๑) 请输入需要搜索的信息~=(๑•́ ₃ •̀๑) ");
                return false;
            }
            return true;
        }
        /**
         *百度分享接口
         */
        window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"无论是各类热门无损音乐、小说、java、javascript、android视频教程、还是各类精彩的电影，一搜必达，努力做最简洁干净绿色的网盘搜索引擎","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"3","bdPos":"right","bdTop":"100"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
    </script>


</head>
<body>
<div style="display: none;" class="tip" id="topTipText"></div>
<div class="nav center">
    <div class="box_outer">
        <div class="box_body">
            <div align="center" style="padding-top: 100px;">
                <img src="images/logo1.png" alt="百度网盘搜索引擎|搜盘|盘搜"/>
            </div>
            <table class="sch">
                <tbody>
                <tr id='test'>
                    <td class="first_td"></td>
                    <td width="460px"></td>
                    <form id="cse-search-box" name="f"  action="${pageContext.request.contextPath}/baiduServlet" method="GET" target="_blank"  onsubmit="return checkForm()">

                        <td width="373px">
                            <br/>
                            <input type="hidden" name="wp" id="wp" value="0" />
                            <input type="hidden" name="op" id="op" value="0" />
                            <input type="hidden" name="ty" id="ty" value="gn" />
                            <div id="sugOut"><input name="q" value="" maxlength=100 id="q" autocomplete="off">
                                <br>
                                <div id="sug"></div>
                            </div>
                        </td>
                        <td>
                            <br/>
                            <button type="submit">网盘一下</button>
                        </td>
                    </form>
                    <td align=right id="hao_search_content" width="360px"></td>
                    <td class="last_td"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="box_b"><span class="box_p"></span></div>
    </div>
</div>


<div>
    <p align="center" style="padding-top: 300px;">
        做最简洁干净无弹窗稳定绿色的百度云网盘搜索引擎<br/>
        无论是java视频、nba、欢乐颂。。。你都可以一手获得  <br/>
        如果有侵犯的地方，联系我们及时整改。站长：YuanYuan（邮箱：137578346@qq.com）<br />
        免责声明：本百度云搜索引擎搜索结果来自Google自定义搜索，不存储任何网盘内容，只提供信息检索服务。<br />
        百度云搜索：本百度云搜索引擎非人工检索方式，不代表本站立场，本站不对其真实合法性负责，亦不承担任何法律责任。<br/>
        Copyright&copy;2014-2015  百度云百度网盘资源搜索引擎<br />
    </p>
</div>
</body>
</html>
<script type="text/javascript" src="js/base.js"></script>