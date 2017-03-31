package yuan.jxau.cn.pansearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import yuan.jxau.cn.pansearch.domain.Results;
import yuan.jxau.cn.pansearch.utils.StreamUtils;
import yuan.jxau.cn.pansearch.utils.ToastUtils;

public class MainActivity extends Activity {
    private Button bt_search;
    private ListView mListView;
    private EditText et_key;
    private List<Results.Result> mResultList;
    private static final String TAG = "MainActivity";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mListView.setAdapter(new MyAdapter());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
    }

    /**
     * 初始化ListView的相关数据
     */
    private void initData() {

    }

    //连接网络，获取json数据
    private void getData(final String keyWord) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1.封装url地址
                    URL url = new URL("http://10.0.2.2:8080/json/json.json");
                    //2.开启连接
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    //3.设置相关请求头
                    urlConnection.setConnectTimeout(5000);//请求超时
                    urlConnection.setReadTimeout(5000);//读取超时
                    //4.获取请求成功响应码
                    if (urlConnection.getResponseCode() == 200){
                        Log.d(TAG, "Has got responseCode!!!");
                        //5.以流的形式将数据获取下来
                        InputStream inputStream = urlConnection.getInputStream();
                        //6.将流转化为字符串
                        String json = StreamUtils.streamToString(inputStream);
                        System.out.println("查询到的json："+json);

                        parseJson(json);
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showShort(getApplicationContext(),"网络状态不佳，请重新尝试搜索");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 解析json
     * @param json
     */
    private void parseJson(String json) {
        Gson gson = new Gson();
        Results resultList = gson.fromJson(json, Results.class);
        mResultList = resultList.results;
        Message message = Message.obtain();
        if (mResultList != null)
            mHandler.sendEmptyMessage(0);
    }

    private void initUI() {
        bt_search = (Button) findViewById(R.id.bt_search);
        et_key = (EditText) findViewById(R.id.et_key);

        //设置点击事件
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = et_key.getText().toString().trim();
                if (TextUtils.isEmpty(keyWord)){
                    ToastUtils.showShort(getApplicationContext(),"请输入关键字@_@");
                }else{
                    Log.d(TAG,keyWord);
                    //连接网络获取数据
                    getData(keyWord);
                }
            }
        });

        mListView = (ListView) findViewById(R.id.ll_context);


        //为listview的条目设置点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mUrl = mResultList.get(position).unescapedUrl;
                Intent intent = new Intent(getApplication() , ResultDetailActivity.class);
                intent.putExtra("mUrl" , mUrl);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        ViewHolder viewHolder;

        @Override
        public int getCount() {
            return mResultList.size();
        }

        @Override
        public Results.Result getItem(int position) {
            return mResultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView = (View) View.inflate(getApplication(),R.layout.item_listview , null);
                convertView.setTag(viewHolder);
                viewHolder.mTv = (TextView) convertView.findViewById(R.id.tv_item);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mTv.setText(getItem(position).titleNoFormatting);
            return convertView;
        }
    }

    class ViewHolder {
        public TextView mTv;
    }
}
