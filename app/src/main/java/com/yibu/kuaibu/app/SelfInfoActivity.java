package com.yibu.kuaibu.app;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.http.HttpRequest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class SelfInfoActivity extends Activity implements OnClickListener {

    private TextView name, sex;
    private ImageView changeimage;
    private ImageView changename;
    private ImageView changesex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_info);

        initinfo();
        initv();


    }

    //请求个人资料列表
    private void initinfo() {
//		HttpRequest.getAllInfo(new RequestCallBack<String>() {
//			
//			@Override
//			public void onSuccess(ResponseInfo<String> responseInfo) {
//				try 
//				{
//					handleResultOfAllinfo(responseInfo);
//				} 
//				catch (JSONException e)
//				{
//					e.printStackTrace();
//				}
//				
//			}
//			
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

    }

    /**
     * 处理 请求个人列表数据的结果
     *
     * @param responseInfo
     * @throws JSONException
     */
    protected void handleResultOfAllinfo(ResponseInfo<String> responseInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseInfo.result);
        int RESPCODE = jsonObject.getInt("RESPCODE");
        String RESPMSG = jsonObject.getString("RESPMSG");
        switch (RESPCODE) {
            case 201://查询成功

                break;
            default:
                break;
        }
    }

    private void initv() {
        name = (TextView) findViewById(R.id.info_name);
        sex = (TextView) findViewById(R.id.info_sex);
        changeimage = (ImageView) findViewById(R.id.info_changeimage);
        changename = (ImageView) findViewById(R.id.info_changename);
        changesex = (ImageView) findViewById(R.id.info_changesex);
        changeimage.setOnClickListener(this);
        changesex.setOnClickListener(this);
        changeimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_changeimage:

                break;
            case R.id.info_changename:

                break;
            case R.id.info_changesex:

                break;

            default:
                break;
        }

    }


}
