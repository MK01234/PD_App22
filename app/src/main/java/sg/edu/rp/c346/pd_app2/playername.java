package sg.edu.rp.c346.pd_app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class playername extends AppCompatActivity {
    EditText etp1, etp2;
    Button btngame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playername);
        btngame = findViewById(R.id.buttongame);
        etp1 = findViewById(R.id.editTextp1);
        etp2 = findViewById(R.id.editText2);

        btngame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etp1.getText().toString().equals("")){

                    Toast.makeText(playername.this,"Name cannot be empty",Toast.LENGTH_LONG).show();
                }
                else if (etp2.getText().toString().equals("")){
                    Toast.makeText(playername.this,"Name cannot be empty",Toast.LENGTH_LONG).show();
                }

                else{
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    String name = etp1.getText().toString();
                    intent.putExtra("Player 1: 0", name);
                    String name2 = etp2.getText().toString();
                    intent.putExtra("Player 2: 0", name2);
                    startActivity(intent);
                }

            }
        });
    }
}
