package com.example.tictactoe;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class GamePageActivity extends AppCompatActivity implements View.OnClickListener {

    public int roundCount = 0;
    public int activePlayer = 1;
    private ImageButton[][] buttons = new ImageButton[3][3];
    public int[][] gameBoard={{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};

    public int X_playerPoints = 0;
    public int O_playerPoints = 0;

    TextView X_textView;
    TextView O_textView;
    TextView turn;
    Button resetBtn;


    @Override
    public void onClick(View v) {

        if (activePlayer == 1) {
            turn.setText("O is Playing");
            ImageButton ibV = ((ImageButton) v);
            ibV.setImageResource(R.drawable.x);
            ibV.setEnabled(false);
            String a = v.getResources().getResourceEntryName(v.getId());
            int gameStatePointer = Integer.parseInt(a.substring(a.length()-2, a.length()));
            int a0 = Integer.parseInt(a.substring(a.length()-2, a.length()-1));
            int a1 = Integer.parseInt(a.substring(a.length()-1, a.length()));

            gameBoard[a0][a1] = 0;
            activePlayer-=1;
        } else {
            turn.setText("X is Playing");
            ImageButton ibV = ((ImageButton) v);
            ibV.setImageResource(R.drawable.o);
            ibV.setEnabled(false);
            String a = v.getResources().getResourceEntryName(v.getId());
            int gameStatePointer = Integer.parseInt(a.substring(a.length()-2, a.length()));
            int a0 = Integer.parseInt(a.substring(a.length()-2, a.length()-1));
            int a1 = Integer.parseInt(a.substring(a.length()-1, a.length()));

            gameBoard[a0][a1] = 1;
            activePlayer+=1;
        }

        roundCount++;

        if (winner() == 0) {
            X_playerPoints++;
            X_textView.setText("X score: " + X_playerPoints);
            winAlert(0);
            newGame();
        }
        else if (winner() == 1) {
            O_playerPoints++;
            O_textView.setText("O score: " + O_playerPoints);
            winAlert(1);
            newGame();
        }
        else if (roundCount == 9){
            draw();
            newGame();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        newGame();

        turn = findViewById(R.id.turnID);
        turn.setText("X begin");
        resetBtn = findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                X_playerPoints = 0;
                O_playerPoints = 0;
                X_textView.setText("X score: 0");
                O_textView.setText("O score: 0");
            }
        });
        X_textView = findViewById(R.id.x_scoreTv);
        O_textView = findViewById(R.id.o_scoreTv);
        X_textView.setText("X score: 0");
        O_textView.setText("O score: 0");
    }

    public void newGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setEnabled(true);
                buttons[i][j].setImageResource(0);
                gameBoard[i][j] = -1;
                roundCount = 0;
            }
        }
    }

    private void draw() {
        AlertDialog.Builder a = new AlertDialog.Builder(GamePageActivity.this);
        a.setTitle("No winner!");
        a.setMessage("Play again");
        a.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = a.create();
        dialog.show();
    }


    public void winAlert(int winner){
        AlertDialog.Builder a = new AlertDialog.Builder(GamePageActivity.this);
        a.setTitle("Congratulations!");
        if (winner == 0){
            a.setMessage("X wins");
        }
        else{
            a.setMessage("O wins");
        }

        a.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = a.create();
        dialog.show();
    }



    public int winner(){

        // rows

        if(this.gameBoard[0][0]==0&&this.gameBoard[0][1]==0&&this.gameBoard[0][2]==0){
            return 0;
        }

        if(this.gameBoard[0][0]==1&&this.gameBoard[0][1]==1&&this.gameBoard[0][2]==1){
            return 1;
        }

        if(this.gameBoard[1][0]==0&&this.gameBoard[1][1]==0&&this.gameBoard[1][2]==0){
            return 0;
        }

        if(this.gameBoard[1][0]==1&&this.gameBoard[1][1]==1&&this.gameBoard[1][2]==1){
            return 1;
        }

        if(this.gameBoard[2][0]==0&&this.gameBoard[2][1]==0&&this.gameBoard[2][2]==0){
            return 0;
        }

        if(this.gameBoard[2][0]==1&&this.gameBoard[2][1]==1&&this.gameBoard[2][2]==1){
            return 1;
        }


        // columns

        if(this.gameBoard[0][0]==0&&this.gameBoard[1][0]==0&&this.gameBoard[2][0]==0){
            return 0;
        }

        if(this.gameBoard[0][0]==1&&this.gameBoard[1][0]==1&&this.gameBoard[2][0]==1){
            return 1;
        }

        if(this.gameBoard[0][1]==0&&this.gameBoard[1][1]==0&&this.gameBoard[2][1]==0){
            return 0;
        }

        if(this.gameBoard[0][1]==1&&this.gameBoard[1][1]==1&&this.gameBoard[2][1]==1){
            return 1;
        }

        if(this.gameBoard[0][2]==0&&this.gameBoard[1][2]==0&&this.gameBoard[2][2]==0){
            return 0;
        }

        if(this.gameBoard[0][2]==1&&this.gameBoard[1][2]==1&&this.gameBoard[2][2]==1){
            return 1;
        }



        //  cross
        if(this.gameBoard[0][0]==0&&this.gameBoard[1][1]==0&&this.gameBoard[2][2]==0){
            return 0;
        }

        if(this.gameBoard[0][0]==1&&this.gameBoard[1][1]==1&&this.gameBoard[2][2]==1){
            return 1;
        }

        if(this.gameBoard[2][0]==0&&this.gameBoard[1][1]==0&&this.gameBoard[0][2]==0){
            return 0;
        }

        if(this.gameBoard[2][0]==1&&this.gameBoard[1][1]==1&&this.gameBoard[0][2]==1){
            return 1;
        }

        return -1;
    }
}