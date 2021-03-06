package nblott.org.flung.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;

import nblott.org.flung.R;

public class MainActivity extends AppCompatActivity {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        start = (Button) findViewById(R.id.start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                Intent playIntent = new Intent(getApplicationContext(), GameActivity.class);
                playIntent.putExtra(GameActivity.KEY_LEVEL_NAME, "DEBUG");
                startActivity(playIntent);
                System.out.println("click");
            }
        });
    }
}
