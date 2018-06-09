package com.example.hp.mn1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyTitleView.IncanInterFace{

    private MyTitleView mytitle;
    private FlowLayout flow;
    private Button button;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytitle = findViewById(R.id.mytitle);
        flow = findViewById(R.id.flow);
        mytitle.getIncanInterFace(this);

    }
    @Override
    public void getLiftBtn() {
        Toast.makeText(MainActivity.this,"退后一步",Toast.LENGTH_SHORT).show();
            flow.removeViewAt(0);
            if (flow.getChildAt(0)==null){
                finish();
                Toast.makeText(MainActivity.this,"空了，我要走了",Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void getCenterBtn() {
        Toast.makeText(MainActivity.this,"清空所有",Toast.LENGTH_SHORT).show();
        flow.removeAllViews();
    }

    @Override
    public void getRightBtn() {
        Toast.makeText(MainActivity.this,"又向前走了一步",Toast.LENGTH_SHORT).show();
        button =new Button(MainActivity.this);
        button.setWidth(flow.getWidth()/2);
        button.setHeight(80);
         button.setTextSize(30);
        button.setText("第[ "+i+++" ]步");
        button.setBackgroundColor(Color.YELLOW);
        flow.addView(button);


    }
}
