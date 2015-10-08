package com.example.gigold.gigoldandroidapp;

import android.content.Intent;
import android.os.SystemClock;
import android.test.InstrumentationTestCase;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.Assert;

/**
 * Created by xie_lp on 15/10/8.
 */
public class TestMyApp extends InstrumentationTestCase {
    MainActivity mActivity = null;
    private Button button = null;

    private TextView text = null;

    public void testSample() throws Throwable {
        Assert.assertTrue(1 + 1 == 3); // 测试一个错误的结果
    }

    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        mActivity.finish();

        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        Intent intent = new Intent();

        intent.setClassName("cn.hpc.assistant", MainActivity.class.getName());

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mActivity = (MainActivity) getInstrumentation().startActivitySync(
                intent);

        text = (TextView) mActivity.findViewById(R.id.text1);

        button = (Button) mActivity.findViewById(R.id.id_btn_fun);

    }

    /*
     *
     * 活动功能测试
     */

    public void testActivity() throws Exception {
        // 测试键壮性，连续运行某项功能100次，点击Button 100次
        for (int i = 0; i < 100; ++i) {
            getInstrumentation().runOnMainSync(new PerformClick(button));
            SystemClock.sleep(500); // 中间间隔 0.5秒
        }
        assertEquals("Android InstrumentationTestCase", text.getText().toString()); //检查运行后的输出结果

    }

    /*
     *
     * 模拟按钮点击的接口
     */

    private class PerformClick implements Runnable {

        Button btn;

        public PerformClick(Button button) {
            btn = button;
        }

        public void run() {
            btn.performClick();
        }

    }
}

