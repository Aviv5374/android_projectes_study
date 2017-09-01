package com.example.aviv5374.simongame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[4];
    private ArrayList<Button> buttonsSequence = new ArrayList<Button>();
    private int indexOfRelevantSequenceButton = 0;
    private boolean isGameRunמing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0] = (Button)findViewById(R.id.redButton);
        buttons[1] = (Button)findViewById(R.id.blueButton);
        buttons[2] = (Button)findViewById(R.id.greenButton);
        buttons[3] = (Button)findViewById(R.id.yellowButton);
        buttons[0].setBackgroundResource(R.drawable.lightred);//this ok!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.start_anew_game_op:
                startGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startGame(){
        /*
       int counter = 0;
if(!isGameRuning){
buttonsSequence.clear();
indexOfReleventSequenceButon = 0;
chagnButtonsClickableState(in boolean state =false): void
//count 3 seacnd with tosts?
new CountDownTimer(1000, 30)//?
{
public void onTick(long millisUntilFinished){
Tost tost = Toast.makeText(this,String.valueOf(counter),Toast.LENGTH_SHORT);
toast.show():
counter ++;
}
}.start();
isGameRuning = true;
manageButtonsSequence(): void
{
else{
Tost tost = Toast.makeText(this,"A game is runing right now. Please try againe later.",Toast.LENGTH_SHORT);
toast.show():
}
         */
    }

    private void manageButtonsSequence(){
        /*
        chagnButtonsClickableState(in boolean state =false): void//TO CHECK: maybe not relevent
addToSequencen(): void
playSequence(): void
chagnButtonsClickableState(in boolean state =true): void
         */
    }

    private void chagnButtonsClickableState( boolean state){
        /*
        for(buttons.Count)
{
buttons[i].setClickable(state);
)
         */
    }

    private void addToSequencen(){
        /*
        Random rand = new Random();
int chosenIndex = rand.nextInt(buttons.legth);
if(buttons[chosenIndex] == buttonsSequence[buttonsSequence.count-1] && buttons[chosenIndex] == buttonsSequence[buttonsSequence.count-2]){
addToSequencen(): void
{
 else{
buttonsSequence.Add(buttons[chosenIndex);
}
         */
    }

private void playSequence(){
    /*
    for(buttonsSequence.Count){
playButton(Button b=buttonsSequence[i]): void
}
     */
}

public void onButtonClick(View v){
    /*
    Button relventButton = (Button) v...;
chagnButtonsClickableState(in boolean state =false): void//?
playButton(relventButton): void
if(isGameRuning && comperButtons(relventButton): boolean){
prepareTheNextTry(): void
}
else{
endGame(): void
}
chagnButtonsClickableState(in boolean state =true): void//?
     */
}

private void playButton(Button button){
    /*
    https://www.youtube.com/watch?v=whFVhvM-J0U
code:
MediaPlayer mp;
int idOfReleventButtonOriginalColor;
int idOfReleventButtonLighterColor;
swich b{
case red:
idOfReleventButtonOriginalColor = red;
idOfReleventButtonLighterColor = lighterRed
mp = MediaPlayer.create(this, R.raw.redSong);
braek;
case blue ...

mp.start();
b.setColor(idOfReleventButtonLighterColor);
//busy wating
whlie(mp != null && mp.getCurrentPosition() <  mp.getDuration()){};
mp.stop();
//alwys  after stop
mp = MP.create(...);
b.setColor(idOfReleventButtonOriginalColor);
     */
}

private boolean comperButtons(Button button){
    /*
    return buttonsSequence[indexOfReleventSequenceButon].equles(b);
     */
    return true;
}

private void prepareTheNextTry(){
    /*
    indexOfReleventSequenceButon++
if(indexOfReleventSequenceButon >= buttonsSequence.count ){
if(buttonsSequence.count < 16){
indexOfReleventSequenceButon = 0;
manageButtonsSequence(): void
}
else{
endGame(): void
}
}
//?
else{
chagnButtonsClickableState(in boolean state =true): void
}
     */
}

private void endGame(){
    /*
    Tost tost = Toast.makeText(this,String.valueOf(buttonsSequence.count),Toast.LENGTH_SHORT);
toast.show();
isGameRuning = false;
chagnButtonsClickableState(in boolean state =true): void
     */
}


}
