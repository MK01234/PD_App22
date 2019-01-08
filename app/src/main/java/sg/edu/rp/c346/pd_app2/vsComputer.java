package sg.edu.rp.c346.pd_app2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class vsComputer extends AppCompatActivity implements View.OnClickListener {
    private vsComputer activity;
    private boardcpu boardcpu;
    private Button[][] buttons = new Button[3][3];

    private boolean p1Turn = true;

    private int roundCount;

    private int p1Point, eltW, eltH;;
    private int computerpoint;

    private TextView tvPlayer1;
    private TextView computer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvPlayer1 = findViewById(R.id.text_view_p1);
        computer = findViewById(R.id.text_view_p2);
        boardcpu = new boardcpu();

        Intent intentReceived = getIntent();
        String name = intentReceived.getStringExtra("Player 1: 0");
        String name2 = intentReceived.getStringExtra("Player 2: 0");
        tvPlayer1.setText(name + ": " + p1Point);
        computer.setText(name2 + ": " + computerpoint);

        for (int i = 0; i < 3; i++) {
            for (int p = 0; p < 3; p++) {
                String buttonID = "button_" + i + p;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][p] = findViewById(resourceID);
                buttons[i][p].setOnClickListener(this);
            }
        }
        Button btnhome = findViewById(R.id.button_home);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), homepage.class);

                startActivity(intent);

            }
        });


        Button btnReset = findViewById(R.id.button_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();


            }
        });
    }




  @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (p1Turn) {
            ((Button) v).setText("X");
        } else {
            Spannable color = new SpannableString("O");
            ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
            color.setSpan(fcsRed, 0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ((Button) v).setText(color);

        }

        roundCount++;

        if (checkWin()) {
            if (p1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            p1Turn = !p1Turn;
        }

    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int p = 0; p < 3; p++) {
                field[i][p] = buttons[i][p].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        Intent intentReceived = getIntent();
        String name = intentReceived.getStringExtra("Player 1: 0");

        p1Point++;
        AlertDialog.Builder p1_builder = new AlertDialog.Builder(this);
        p1_builder.setMessage(name + " WIN!.").setCancelable(false).setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                resetBoard();
                updatePoint();
            }

        });
        AlertDialog alert = p1_builder.create();
        alert.show();

    }


    private void player2Wins() {
        Intent intentReceived = getIntent();
        String name2 = intentReceived.getStringExtra("Player 2: 0");
        computerpoint++;
        AlertDialog.Builder p1_builder = new AlertDialog.Builder(this);
        p1_builder.setMessage("Computer" + " WIN!.").setCancelable(false).setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                resetBoard();
                updatePoint();
            }

        });
        AlertDialog alert = p1_builder.create();
        alert.show();


    }

    private void updatePoint() {
        Intent intentReceived = getIntent();
        String name = intentReceived.getStringExtra("Player 1: 0");
        String name2 = intentReceived.getStringExtra("Player 2: 0");
        tvPlayer1.setText(name + ": " + p1Point);
        computer.setText("Computer"  + ": " +  computerpoint);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        p1Turn = true;

    }


    private void draw() {

        AlertDialog.Builder p1_builder = new AlertDialog.Builder(this);
        p1_builder.setMessage("DRAW!.").setCancelable(false).setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                resetBoard();

            }

        });
        AlertDialog alert = p1_builder.create();
        alert.show();

    }
    private void reset() {
        p1Point = 0;
        computerpoint = 0;
        updatePoint();
        resetBoard();
    }
    public void setMainActivity(vsComputer a) {
        activity = a;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!boardcpu.isEnded()  &&  event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX()/ eltW);
            int y = (int) (event.getY()/ eltH );
            char win = boardcpu.play(x, y);
            computer.invalidate();

            if (win != ' ') {
                activity.gameEnded(win);
            } else {
                // computer plays ...
                win = boardcpu.computer();
                computer.invalidate();

                if (win != ' ') {
                    activity.gameEnded(win);
                }
            }
        }

        return super.onTouchEvent(event);


    }

    private void gameEnded(char win) {
        String msg = (win == 'T') ? "Game Ended. Tie" : "GameEnded. " + win + " win";

        new AlertDialog.Builder(this).setTitle("Tic Tac Toe");



    }


}



