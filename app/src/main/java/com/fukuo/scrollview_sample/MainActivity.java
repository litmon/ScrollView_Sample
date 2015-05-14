package com.fukuo.scrollview_sample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    CustomScrollView view;
    LinearLayout imageStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (CustomScrollView) findViewById(R.id.custom_scroll);
        imageStack = (LinearLayout) findViewById(R.id.imageStack);


        for (int i = 0; i < 50; i++)
            view.addView(initLayoutView(i));

        view.setOnScrollChangedListener(new CustomScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                System.out.print("onScrollChanged l: " + l + ", t: " + t);
                System.out.println(" getScrollY: " + view.getScrollY());

                int c = getChildTop(t);

                System.out.println("indexOfTopChild: " + c);
                System.out.println("imageStackChild: " + imageStack.getChildCount());
                while (imageStack.getChildCount() != c){
                    if(imageStack.getChildCount() > c){
                        imageStack.removeViewAt(0);
                    }else if(imageStack.getChildCount() < c){
                        imageStack.addView(initImageView());
                    }
                }
            }
        });
    }

    private int getChildTop(int t) {
        View topView = view.layout.getChildAt(0);

        for (int i = 1; i < view.layout.getChildCount(); i++) {
            View topNextView = view.layout.getChildAt(i);

            if (topNextView.getY() >= t) {
                topNextView.setBackgroundColor(Color.RED);
                return i;
            }

            topNextView.setBackgroundColor(Color.BLACK);
        }

        return 0;
    }

    public View initImageView(){
        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(R.mipmap.ic_launcher);

        return image;
    }

    public View initLayoutView(int i) {
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageView image = new ImageView(getApplicationContext());
        image.setImageResource(R.mipmap.ic_launcher);

        TextView text = new TextView(getApplicationContext());
        text.setText("index: " + (i + 1));
        text.setTextColor(Color.BLACK);

        layout.addView(image);
        layout.addView(text);

        return layout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
