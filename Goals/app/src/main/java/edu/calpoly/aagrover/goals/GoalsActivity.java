package edu.calpoly.aagrover.goals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GoalsActivity extends AppCompatActivity {

    String FILENAME = "GoalDate.txt";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        // Will likely use OpenGL ES, this is too simple/boring. Does not get main point across.
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#D3D3D3"));
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.drawRect(50, 50, 200, 200, paint);
        LinearLayout ll = (LinearLayout) findViewById(R.id.draw_area);
        ll.setBackground(new BitmapDrawable(bg));

        File goalsFile = new File(FILENAME);

        // Used to store the goal name and date to achieve by.
        String goal = "", date = "";

        if (goalsFile.exists()) {
            try {
                InputStream is = getAssets().open(FILENAME);
                int value = is.read();

                // Testing to make sure the the file is being read.
                Log.d("aagrover", "" + value);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // The file doesn't exist. Eventually add text, telling the user they should start a goal. :)
        }

        final Button bRegister = (Button)findViewById(R.id.bStartNewGoal);

        assert bRegister != null;
        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GoalsActivity.this, StartNewGoalActivity.class);
                GoalsActivity.this.startActivity(intent);

            }
        });
    }
}
