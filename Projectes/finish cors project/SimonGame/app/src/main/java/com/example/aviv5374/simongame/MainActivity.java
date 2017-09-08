package com.example.aviv5374.simongame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[4];
    private ArrayList<Button> buttonsSequence;
    private int indexOfRelevantSequenceButton;
    private boolean isGameRunning;
    private int triesCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0] = (Button) findViewById(R.id.redButton);
        buttons[1] = (Button) findViewById(R.id.blueButton);
        buttons[2] = (Button) findViewById(R.id.greenButton);
        buttons[3] = (Button) findViewById(R.id.yellowButton);
        buttonsSequence = new ArrayList<Button>();
        indexOfRelevantSequenceButton = 0;
        isGameRunning = false;
        triesCounter =0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_anew_game_op:
                startGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //setup the relevant verbals and start a new game
    private void startGame() {
        if (!isGameRunning) {
            buttonsSequence.clear();
            indexOfRelevantSequenceButton = 0;
            changeButtonsClickableState(false);
            isGameRunning = true;
            manageButtonsSequence();
        } else {
            Toast toast = Toast.makeText(this, R.string.game_is_running_error_text, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    //add a new random button to the sequence and play it
    private void manageButtonsSequence() {
        changeButtonsClickableState(false);
        addToSequence();
        playSequence();
        changeButtonsClickableState(true);
    }

    //change the cliclable state of the buttons in the array by state given
    private void changeButtonsClickableState(boolean state) {
        for (int i = 0; i < buttons.length; i++)
            buttons[i].setClickable(state);
    }

    //dd a new random button to the sequence
    private void addToSequence() {
        Random rand = new Random();
        int chosenIndex = rand.nextInt(buttons.length);
        //if the chosen button is the same as the two before it, restart the method and chose a new button
        if (buttonsSequence.size() >= 2 && buttons[chosenIndex] == buttonsSequence.get(buttonsSequence.size() - 1) && buttons[chosenIndex] == buttonsSequence.get(buttonsSequence.size() - 2)) {
            addToSequence();
        } else {
            buttonsSequence.add(buttons[chosenIndex]);
        }
    }

    //play the the button sequence
    private void playSequence() {
        Toast toast;
        for (int i = 0; i < buttonsSequence.size(); i++) {
            //show a toast with the relevant button name
            switch (buttonsSequence.get(i).getId()) {
                case R.id.redButton:
                    toast = Toast.makeText(this, R.string.red_button, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.blueButton:
                    toast = Toast.makeText(this, R.string.blue_button, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.greenButton:
                    toast = Toast.makeText(this, R.string.green_button, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.yellowButton:
                    toast = Toast.makeText(this, R.string.yellow_button, Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                default:
                    toast = Toast.makeText(this, "the button I get is wrong. Please try again", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
            }

            /*
            //!!! the original code to change background to relevant state for each button !!!
            //!!! after a lot of effort i did manage to make that work !!!
            //!!! the toats here is a compromise !!!
            buttonsSequence.get(i).setPressed(true);
            buttonsSequence.get(i).invalidate();
            */

            //play the relevant sound for each button
            playButton(buttonsSequence.get(i).getId());

            /*
            //!!! the original code to change background to relevant state for each button !!!
            //!!! after a lot of effort i did manage to make that work !!!
            //!!! the toats here is a compromise !!!
            buttonsSequence.get(i).setPressed(false);
            buttonsSequence.get(i).invalidate();
            */
        }
    }

    public void onButtonClick(View v) {
        Button relevantButton = (Button) findViewById(v.getId());
        //play the relevant sound for this button
        playButton(relevantButton.getId());
        if (isGameRunning) {
            //compare this button to the relevant button on the sequence
            compareButtons(relevantButton);
        }
    }

    ////play the relevant sound of a button
    private void playButton(int buttonId) {
        MediaPlayer mp = null;
        //set the relevant sound for the given button id
        switch (buttonId) {
            case R.id.redButton:
                mp = MediaPlayer.create(this, R.raw.redsound);
                break;
            case R.id.blueButton:
                mp = MediaPlayer.create(this, R.raw.bluesound);
                break;
            case R.id.greenButton:
                mp = MediaPlayer.create(this, R.raw.green_sound);
                break;
            case R.id.yellowButton:
                mp = MediaPlayer.create(this, R.raw.yellow_sound);
                break;
            default:
                Toast toast = Toast.makeText(this, "the button I get is wrong. Please try again.", Toast.LENGTH_SHORT);
                toast.show();
                return;

        }
        mp.setVolume(1.0f, 1.0f);
        mp.start();
        //busy waiting
        while (mp != null && mp.getCurrentPosition() < mp.getDuration() / 6) ;
        mp.stop();
        mp.release();
    }

    //compare button given to the relevant button on the sequence
    private void compareButtons(Button button) {
        if (buttonsSequence.get(indexOfRelevantSequenceButton).equals(button)){
            triesCounter ++;
            if(triesCounter >= buttonsSequence.size()) {
                Toast toast = Toast.makeText(this, R.string.player_copy_the_Sequence_right, Toast.LENGTH_SHORT);
                toast.show();
                triesCounter = 0;
            }
            prepareTheNextTry();
        } else {
            Toast toast = Toast.makeText(this, R.string.player_make_mistake_error, Toast.LENGTH_SHORT);
            toast.show();
            endGame();
        }
    }

    private void prepareTheNextTry() {
        indexOfRelevantSequenceButton++;
        if (indexOfRelevantSequenceButton >= buttonsSequence.size()) {
            if (buttonsSequence.size() < 16) {
                indexOfRelevantSequenceButton = 0;
                //start the next sequence
                manageButtonsSequence();
            } else {
                Toast toast = Toast.makeText(this, R.string.end_game, Toast.LENGTH_SHORT);
                toast.show();
                endGame();
            }
        }
    }

    private void endGame() {
        Toast toast = Toast.makeText(this, String.valueOf(buttonsSequence.size()), Toast.LENGTH_SHORT);
        toast.show();
        isGameRunning = false;
        changeButtonsClickableState(true);

    }

}
