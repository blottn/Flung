package nblott.org.scrolling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
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
                startActivity(playIntent);
                System.out.println("click");
            }
        });
    }
}
