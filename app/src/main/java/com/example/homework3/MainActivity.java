package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[] mGameButtons = new Button[9];
    Button btn_newGame;
    TextView mGameStatus;
    boolean userWon, computerWon = false;

    //initialize board
    char[] mBoard = new char[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameButtons[0] = findViewById(R.id.button1);
        mGameButtons[1] = findViewById(R.id.button2);
        mGameButtons[2] = findViewById(R.id.button3);
        mGameButtons[3] = findViewById(R.id.button4);
        mGameButtons[4] = findViewById(R.id.button5);
        mGameButtons[5] = findViewById(R.id.button6);
        mGameButtons[6] = findViewById(R.id.button7);
        mGameButtons[7] = findViewById(R.id.button8);
        mGameButtons[8] = findViewById(R.id.button9);

        mGameStatus = findViewById(R.id.textView);
        btn_newGame = findViewById(R.id.button14);

        //init game status
        mGameStatus.setText("Game Status");

        for (int i = 0; i < 9; i++) {
            mGameButtons[i].setOnClickListener(this);
        }

        btn_newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 9; i++) {
                    mGameButtons[i].setText(""); //reset gameboard
                    mGameStatus.setText("Game Status"); //reset status
                }
            }
        });
    }

    /*
    * The onClick method places the user piece,
    * places the computer piece, and checks for a winner in one large
    * method.
    * */
    @Override
    public void onClick(View v) {

        //check if game is finished
        if (checkForWinner() == 1 || checkForWinner() == -1){
            return; //no pieces can be placed
        }

        //users move
        Button selectedButton = (Button) v;
        if (!(selectedButton.getText().toString() == "X") && !(selectedButton.getText().toString() == "O")) { //is move legal?
            selectedButton.setText("X");
            selectedButton.setTextColor(getResources().getColor(R.color.myRed)); //extra credit
        }
        else{
            return; //reject move
        }

        //did player 1 make a winning move
        if (checkForWinner() == 1){
            mGameStatus.setText("User Wins!");
            return; //end game
        }

        //the computer makes a move
        setComputerMove(mGameButtons);

        //did the computer make a winning move
        if (checkForWinner() == -1){
            mGameStatus.setText("Computer Wins!");
            return; //end game
        }

        //check for draw
        if (makeSureSpaceIsAvailable() == false) {
            mGameStatus.setText("Draw!");
            return; //end game
        }
    }

    //a computer will place a random tile in the game
    void setComputerMove(Button[] mGameButtons){
        boolean computerFoundPlace = false;
        Random rn = new Random();
        int value = rn.nextInt(8 + 1);

        //make sure there is actually a space for the computer
        if (!makeSureSpaceIsAvailable()){
            return;
        }

        //find a place for the computer to set piece
        while (!computerFoundPlace){
            if (!(mGameButtons[value].getText().toString() == "X") && !(mGameButtons[value].getText().toString() == "O")){
                mGameButtons[value].setText("O");
                mGameButtons[value].setTextColor(getResources().getColor(R.color.myBlue)); //extra credit
                computerFoundPlace = true;
            }
            else{
                //reset value until its one that works
                value = rn.nextInt(8 + 1); //get a new random int
            }
        }
    }

    //this method sees if anymore moves are legal to be made in the game
    boolean makeSureSpaceIsAvailable(){
        for (int i = 0; i < 8; i++){
            if (mGameButtons[i].getText().toString() == "X" || (mGameButtons[i].getText().toString() == "O")){
                //do nothing
            }
            else {
                return true;
            }
        }
        return false; //it failed the test
    }

    //returns 1 if user wins, returns -1 if computer wins, returns 0 if no winner
    int checkForWinner(){

        //check all user wins
        if (mGameButtons[0].getText().toString() == "X" &&
            mGameButtons[1].getText().toString() == "X" &&
            mGameButtons[2].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[3].getText().toString() == "X" &&
            mGameButtons[4].getText().toString() == "X" &&
            mGameButtons[5].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[6].getText().toString() == "X" &&
            mGameButtons[7].getText().toString() == "X" &&
            mGameButtons[8].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[0].getText().toString() == "X" &&
            mGameButtons[3].getText().toString() == "X" &&
            mGameButtons[6].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[1].getText().toString() == "X" &&
            mGameButtons[4].getText().toString() == "X" &&
            mGameButtons[7].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[2].getText().toString() == "X" &&
            mGameButtons[5].getText().toString() == "X" &&
            mGameButtons[8].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[0].getText().toString() == "X" &&
            mGameButtons[4].getText().toString() == "X" &&
            mGameButtons[8].getText().toString() == "X"){
            return 1;
        }

        else if (mGameButtons[2].getText().toString() == "X" &&
            mGameButtons[4].getText().toString() == "X" &&
            mGameButtons[6].getText().toString() == "X"){
            return 1;
        }

        //check all computer wins
        else if (mGameButtons[0].getText().toString() == "O" &&
                mGameButtons[1].getText().toString() == "O" &&
                mGameButtons[2].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[3].getText().toString() == "O" &&
                mGameButtons[4].getText().toString() == "O" &&
                mGameButtons[5].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[6].getText().toString() == "O" &&
                mGameButtons[7].getText().toString() == "O" &&
                mGameButtons[8].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[0].getText().toString() == "O" &&
                mGameButtons[3].getText().toString() == "O" &&
                mGameButtons[6].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[1].getText().toString() == "O" &&
                mGameButtons[4].getText().toString() == "O" &&
                mGameButtons[7].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[2].getText().toString() == "O" &&
                mGameButtons[5].getText().toString() == "O" &&
                mGameButtons[8].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[0].getText().toString() == "O" &&
                mGameButtons[4].getText().toString() == "O" &&
                mGameButtons[8].getText().toString() == "O"){
            return -1;
        }

        else if (mGameButtons[2].getText().toString() == "O" &&
                mGameButtons[4].getText().toString() == "O" &&
                mGameButtons[6].getText().toString() == "O"){
            return -1;
        }
        else{ //nobody has won
            return 0;
        }
    }
}
