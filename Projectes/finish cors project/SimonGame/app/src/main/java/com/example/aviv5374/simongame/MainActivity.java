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
    private ArrayList<Button> buttonsSequence = new ArrayList<Button>();
    private int indexOfRelevantSequenceButton = 0;
    private boolean isGameRunning = false;
    private boolean isClickable = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0] = (Button) findViewById(R.id.redButton);
        buttons[1] = (Button) findViewById(R.id.blueButton);
        buttons[2] = (Button) findViewById(R.id.greenButton);
        buttons[3] = (Button) findViewById(R.id.yellowButton);

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


    private void startGame() {
        if (!isGameRunning) {
            buttonsSequence.clear();
            indexOfRelevantSequenceButton = 0;
            chagnButtonsClickableState(false);
            //isGameRunning = true;
            manageButtonsSequence();
        } else {
            Toast toast = Toast.makeText(this, R.string.game_is_running_error_text, Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    private void manageButtonsSequence() {
        chagnButtonsClickableState(false);//TO CHECK: maybe not relevent
        for (int i =0; i<4; i++) {
            addToSequence();
            playSequence();
        }
        //chagnButtonsClickableState(true);
    }

    private void chagnButtonsClickableState(boolean state) {
        for (int i = 0; i < buttons.length; i++)
            buttons[i].setClickable(state);
        isClickable = state;
    }

    private void addToSequence() {
        Random rand = new Random();
        int chosenIndex = rand.nextInt(buttons.length);
        if (buttonsSequence.size()>=2 && buttons[chosenIndex] == buttonsSequence.get(buttonsSequence.size() - 1) && buttons[chosenIndex] == buttonsSequence.get(buttonsSequence.size() - 2)) {
            addToSequence();
        } else {
            buttonsSequence.add(buttons[chosenIndex]);
        }

    }

    private void playSequence() {
        Toast toast;
        for (int i = 0; i < buttonsSequence.size(); i++) {
            switch (buttonsSequence.get(i).getId()) {
                case R.id.redButton:
                    toast = Toast.makeText(this, "red button", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.blueButton:
                    toast = Toast.makeText(this, "blue button", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.greenButton:
                    toast = Toast.makeText(this, "green button", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                case R.id.yellowButton:
                    toast = Toast.makeText(this, "yellow button", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                default:
                    toast = Toast.makeText(this, "the button I get is wrong. Please try again.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
            }

            /*
            buttonsSequence.get(i).setPressed(true);
            buttonsSequence.get(i).invalidate();
            */
            playButton(buttonsSequence.get(i).getId());
            /*
            buttonsSequence.get(i).setPressed(false);
            buttonsSequence.get(i).invalidate();
            */
        }
    }

    public void onButtonClick(View v) {
        Button relevantButton = (Button) findViewById(v.getId());
        chagnButtonsClickableState(false);//?
        playButton(relevantButton.getId());

/*
if(isGameRuning && comperButtons(relevantButton): boolean){
prepareTheNextTry(): void
}
else{
endGame(): void
}
*/
        chagnButtonsClickableState(true);//?


    }

    //work here is done
    private void playButton(int buttonId) {
        //https://www.youtube.com/watch?v=whFVhvM-J0U
        MediaPlayer mp = null;
        switch (buttonId) {
            case R.id.redButton:
                mp = MediaPlayer.create(this, R.raw.red_sound);
                break;
            case R.id.blueButton:
                mp = MediaPlayer.create(this, R.raw.blue_sound);
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
        //// TODO: twice the last two lanes later
        //busy waiting
        while (mp != null && mp.getCurrentPosition() < mp.getDuration() / 4) ;
        mp.stop();
    }

    private boolean comperButtons(Button button) {
        return buttonsSequence.get(indexOfRelevantSequenceButton).equals(button);
    }

    private void prepareTheNextTry() {
        indexOfRelevantSequenceButton++;
        if (indexOfRelevantSequenceButton >= buttonsSequence.size()) {
            if (buttonsSequence.size() < 16) {
                indexOfRelevantSequenceButton = 0;
                manageButtonsSequence();
            } else {
                endGame();
            }
        }
        //?
        else {
            chagnButtonsClickableState(true);
        }
    }

    private void endGame() {
        Toast toast = Toast.makeText(this, String.valueOf(buttonsSequence.size()), Toast.LENGTH_SHORT);
        toast.show();
        isGameRunning = false;
        chagnButtonsClickableState(true);

    }

}
