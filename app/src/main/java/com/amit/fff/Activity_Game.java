package com.amit.fff;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    int currentLevel = 0;
    private boolean goodToGo = true;
    int[] steps = {1, 1, 1, 2, 2, 2, 3, 3, 3};
    int[]arrowsDir = {0,1,2,3};


    //30286325
    // 3



    /* access modifiers changed from: protected */
    @Override
    // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String id = getIntent().getStringExtra(EXTRA_ID);
        if (id.length() == this.steps.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.steps;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = Integer.valueOf(String.valueOf(id.charAt(i))).intValue() % 4;
                i++;
            }
        }
        for (int step :
                steps) {
            Log.d("game:", step + "");
        }
        findViews();
        initViews();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    //0-left
    //1-right
    //2-up
    //3-down
    private void arrowClicked(int direction) {
        Log.d("game:",  "~~~~~~~~~~~~~~");
        Log.d("game:",  "direction: "+direction);
        Log.d("game:",  "step: "+this.steps[this.currentLevel]);
        if (this.goodToGo && direction != this.steps[this.currentLevel]) {
            this.goodToGo = false;
            Log.d("game:",  "shit");
        }
        int i = this.currentLevel + 1;
        this.currentLevel = i;
        //this.currentLevel ++
        if (i >= this.steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);
        if (this.goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Failed ", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    private void initViews() {
        int i = 0;
        while (true) {
            ImageButton[] imageButtonArr = this.arrows;
            if (i < imageButtonArr.length) {
                imageButtonArr[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.d("game:",  "~~~>"+Arrays.asList(arrows).indexOf(v));
                        Activity_Game.this.arrowClicked(Arrays.asList(arrows).indexOf(v));
                    }
                });
                i++;
            } else {
                return;
            }
        }
    }

    private void findViews() {
        this.arrows = new ImageButton[]{(ImageButton) findViewById(R.id.game_BTN_left), (ImageButton) findViewById(R.id.game_BTN_right), (ImageButton) findViewById(R.id.game_BTN_up), (ImageButton) findViewById(R.id.game_BTN_down)};
    }
}
