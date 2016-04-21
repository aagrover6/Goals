package edu.calpoly.aagrover.goals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class StartNewGoalActivity extends AppCompatActivity {

    private String FILENAME = "GoalDate.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_goal);

        final EditText etUsername = (EditText)findViewById(R.id.etUsername);
        final EditText etTypeGoal = (EditText)findViewById(R.id.etTypeGoal);
        final EditText etTypeDate = (EditText)findViewById(R.id.etTypeDate);
        final Button bSetGoal = (Button)findViewById(R.id.bSetGoal);

        assert bSetGoal != null;
        bSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert etTypeGoal != null;
                assert etTypeDate != null;
                final String goal = etTypeGoal.getText().toString();
                final String date = etTypeDate.getText().toString();

                FileOutputStream outputStream = null;
                try {
                    outputStream = openFileOutput(FILENAME, Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);
                    final PrintStream printStream = new PrintStream(outputStream);
                    printStream.print(goal + " " + date);
                    outputStream.close();
                    printStream.close();

                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }

                // Now go back to the main goals page.
                Intent intent = new Intent(StartNewGoalActivity.this, GoalsActivity.class);
                StartNewGoalActivity.this.startActivity(intent);
            }
        });
    }
}
