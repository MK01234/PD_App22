package sg.edu.rp.c346.pd_app2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class vscomputername extends AppCompatActivity {
    EditText etp;
    Button btngame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vscomputername);
        btngame1 = findViewById(R.id.btncpu);
        etp = findViewById(R.id.editTextp);


        btngame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etp.getText().toString().equals("")){

                    Toast.makeText(vscomputername.this,"Name cannot be empty",Toast.LENGTH_LONG).show();
                }

                else{
                    Intent intent = new Intent(getBaseContext(), vsComputer.class);
                    String name = etp.getText().toString();
                    intent.putExtra("Player 1: 0", name);

                    startActivity(intent);
                }

            }
        });
    }
}

//    EditText etp;
//    Button btngamecpu;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vs_computer);
//        btngamecpu = findViewById(R.id.buttongamecpu);
//        etp = findViewById(R.id.editTextp);
//
//        btngamecpu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (etp.getText().toString().equals("")){
//
//                    Toast.makeText(vscomputername.this,"Name cannot be empty",Toast.LENGTH_LONG).show();
//                }
//
//                else {
//                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                    String name = etp.getText().toString();
//                    intent.putExtra("Player : 0", name);
//
//                    startActivity(intent);
//                }
//
//            }
//        });


