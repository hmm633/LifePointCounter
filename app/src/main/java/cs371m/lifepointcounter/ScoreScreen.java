package cs371m.lifepointcounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreScreen extends AppCompatActivity {

    private static final String TAG = "ScoreScreen";
    private static final String DEBUG_TAG = "onTouch";

    private static int yDown = 0;
    private static int yUp = 0;

    public LifeFrames Game;

    // Custom views (the black rectangles)
    private GraphicsView frame1;
    private GraphicsView frame2;
    private GraphicsView frame3;
    private GraphicsView frame4;
    private GraphicsView frame5;
    private GraphicsView frame6;
    private GraphicsView frame7;
    private GraphicsView frame8;

    // Buttons
    private Button coinTossButton;
    private Button dieRollButton;
    private Button newGameButton;
    private Button addPlayerButton;
    private Button removePlayerButton;

    // Textviews on the lifeframes
    private TextView lifeText1;
    private TextView lifeText2;
    private TextView lifeText3;
    private TextView lifeText4;
    private TextView lifeText5;
    private TextView lifeText6;
    private TextView lifeText7;
    private TextView lifeText8;

    // Icons
    private Bitmap coinBitmap;
    private Bitmap dieBitmap;
    private Bitmap newGameBitmap;

    //Checkbox
    private CheckBox tutorialCheckBox;
    private boolean skipTutorial;

    private Dialog tutDialog;

    public void initialize(){
        coinBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.money);
        dieBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon165);
        newGameBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrows);
    }

    // First Player Frame
    private View.OnTouchListener TouchListener1 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame1.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    // Only get the first finger's info
//                    if(event.getPointerId() == 0)
                        yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    // Only get the first finger's info
//                    if(event.getDeviceId() == 0)
                        yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(0);
                        createRecord(0, 0);
//                        cs371m.lifepointcounter.Log.removeAt(0);
                        lifeText1.setText(String.valueOf(Game.getPlayerLP(0)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(0);
                        createRecord(0, 1);
                        lifeText1.setText(String.valueOf(Game.getPlayerLP(0)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(0);
                        // Testing
//                        Record.entryList.removeAt(0);
//                        Entry entry = new Entry("hope", "this", "actually", "works");
//                        Record.entryList.add(entry);
                        createRecord(0, 3);
                        lifeText1.setText(String.valueOf(Game.getPlayerLP(0)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        Game.largeIncUp(0);
                        createRecord(0, 2);
                        lifeText1.setText(String.valueOf(Game.getPlayerLP(0)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };

    // Second Player Frame
    private View.OnTouchListener TouchListener2 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame2.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(1);
                        createRecord(1, 0);
                        lifeText2.setText(String.valueOf(Game.getPlayerLP(1)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(1);
                        createRecord(1, 1);
                        lifeText2.setText(String.valueOf(Game.getPlayerLP(1)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(1);
                        createRecord(1, 3);
                        lifeText2.setText(String.valueOf(Game.getPlayerLP(1)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        Game.largeIncUp(1);
                        createRecord(1, 2);
                        lifeText2.setText(String.valueOf(Game.getPlayerLP(1)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };

    // Third player frame
    private View.OnTouchListener TouchListener3 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame3.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(2);
                        createRecord(2, 0);
                        lifeText3.setText(String.valueOf(Game.getPlayerLP(2)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(2);
                        createRecord(2, 1);
                        lifeText3.setText(String.valueOf(Game.getPlayerLP(2)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(2);
                        createRecord(2, 3);
                        lifeText3.setText(String.valueOf(Game.getPlayerLP(2)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        Game.largeIncUp(2);
                        createRecord(2, 3);
                        lifeText3.setText(String.valueOf(Game.getPlayerLP(2)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };

    private View.OnTouchListener TouchListener4 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame4.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(3);
                        createRecord(3, 0);
                        lifeText4.setText(String.valueOf(Game.getPlayerLP(3)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(3);
                        createRecord(3, 1);
                        lifeText4.setText(String.valueOf(Game.getPlayerLP(3)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(3);
                        createRecord(3, 3);
                        lifeText4.setText(String.valueOf(Game.getPlayerLP(3)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        createRecord(3, 2);
                        Game.largeIncUp(3);
                        lifeText4.setText(String.valueOf(Game.getPlayerLP(3)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };

    private View.OnTouchListener TouchListener5 = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame5.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(4);
                        createRecord(4, 0);
                        lifeText5.setText(String.valueOf(Game.getPlayerLP(4)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(4);
                        createRecord(4, 1);
                        lifeText5.setText(String.valueOf(Game.getPlayerLP(4)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(4);
                        createRecord(4, 3);
                        lifeText5.setText(String.valueOf(Game.getPlayerLP(4)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        Game.largeIncUp(4);
                        createRecord(4, 2);
                        lifeText5.setText(String.valueOf(Game.getPlayerLP(4)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };

    private View.OnTouchListener TouchListener6 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int action = MotionEventCompat.getActionMasked(event);

            int height = frame6.getHeight();
            int boundary = height/2;

            switch(action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(DEBUG_TAG, "Action was DOWN");
//                    xDown = (int) event.getX();
                    yDown = (int) event.getY();
                    return true;
//                case (MotionEvent.ACTION_MOVE):
//                    Log.d(DEBUG_TAG, "Action was MOVE");
//                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.d(DEBUG_TAG, "Action was UP");
//                    xUp = (int) event.getX();
                    yUp = (int) event.getY();

                    // Top tap
                    if(yDown < boundary && yUp < boundary){
                        Game.smallIncUp(5);
                        createRecord(5, 0);
                        lifeText6.setText(String.valueOf(Game.getPlayerLP(5)));
                    }
                    // Bottom tap
                    if(yDown >= boundary && yUp >= boundary){
                        Game.smallIncDown(5);
                        createRecord(5, 1);
                        lifeText6.setText(String.valueOf(Game.getPlayerLP(5)));
                    }
                    // Swipe down
                    if(yDown < boundary && yUp >= boundary){
                        Game.largeIncDown(5);
                        createRecord(5, 3);
                        lifeText6.setText(String.valueOf(Game.getPlayerLP(5)));
                    }
                    // Swipe up
                    if(yDown >= boundary && yUp < boundary){
                        Game.largeIncUp(5);
                        createRecord(5, 2);
                        lifeText6.setText(String.valueOf(Game.getPlayerLP(5)));
                    }
                    return true;
                default:
                    // Could be incorrect
                    return onTouchEvent(event);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tutDialog = new Dialog(this);
        tutorialBox2();

        // Will eventually have to start at other screens, may need case statements
        // for that too
        setContentView(R.layout.scorescreen);

        Log.d(TAG, "in onCreate Method");

        // Initialize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing buttons
        coinTossButton = (Button) findViewById(R.id.coin_button);
        dieRollButton = (Button) findViewById(R.id.die_button);
        newGameButton = (Button) findViewById(R.id.new_game_button);
        addPlayerButton = (Button) findViewById(R.id.add_player);
        removePlayerButton = (Button) findViewById(R.id.remove_player);

        // Text inside life frames
        lifeText1 = (TextView) findViewById(R.id.player1LifeText);
        lifeText2 = (TextView) findViewById(R.id.player2LifeText);
//        lifeText3 = (TextView) findViewById(R.id.player3LifeText);

        // Black rectangle frames
        frame1 = (GraphicsView) findViewById(R.id.graphicsView);
        frame2 = (GraphicsView) findViewById(R.id.graphicsView2);
//        frame3 = (GraphicsView) findViewById(R.id.graphicsView3);
        frame1.setOnTouchListener(TouchListener1);
        frame2.setOnTouchListener(TouchListener2);
//        frame3.setOnTouchListener(TouchListener3);

        // Initializing game
        Game = new LifeFrames();

        //////////////////////////
        final SharedPreferences mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        //starting_life
        String newLifeNum = mprefs.getString("starting_life", getResources().getString(R.string.starting_life_total));
        Game.setStartingLifeTotal(Integer.parseInt(newLifeNum));

        //tap_life_change
        String newTapChange = mprefs.getString("tap_life_change", getResources().getString(R.string.tap_life_change));
        Game.setSmallInc(Integer.parseInt(newTapChange));

        //swipe_life_change
        String newSwipeChange = mprefs.getString("swipe_life_change", getResources().getString(R.string.swipe_life_change));
        Game.setLargeInc(Integer.parseInt(newSwipeChange));
        /////////////////////////

        //////////////////////////////////////////////////////ALEX ADDED THIS 5-4-16
        Game.setAllPlayerLp(Integer.parseInt(newLifeNum));
        //////////////////////////////////////////////////////ALEX ADDED THIS 5-4-16

//        Game.newGame(); // Also new

        displayNewLife();

    } // END OF ON CREATE

    public void onCheckBoxClicked(View view){
        Log.d("In OnCheckBoxClicked", "hello");
        String result = "NC";
        if(((CheckBox)view).isChecked()){
            result = "checked";
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ScoreScreen.this);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("hide", result);
        ed.commit();
    }

    public void okay_Tutorial(View view){
        tutDialog.dismiss();
    }

    public void tutorialBox2(){

//        final Dialog dieDialog = new Dialog(this);
        tutDialog.setContentView(R.layout.tutorialdialoguebox);
        tutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        tutDialog.show();

        tutorialCheckBox = (CheckBox) findViewById(R.id.tutorial_dialogue_box);
        tutDialog.setTitle("Life Point Counter");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ScoreScreen.this);
        String skipMsg = prefs.getString("hide", "NC");
        if(!skipMsg.equals("checked"))
            tutDialog.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "in onResume Method");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "in onRestart Method");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "in onPause Method");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "in onStop Method");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "in onDestroy");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ScoreScreen.this, Settings.class);
            //ScoreScreen.this.startActivity(intent);
            ScoreScreen.this.startActivityForResult(intent, 0);
            //ScoreScreen.this.finish();
            return true;
        }
        if (id == R.id.log) {
            Intent mainIntent = new Intent(ScoreScreen.this, cs371m.lifepointcounter.Record.class);
            ScoreScreen.this.startActivity(mainIntent);
//            ScoreScreen.this.finish();
//            Intent logIntent = new Intent(this, Log.class);
//            startActivity(logIntent);
            return true;
        }
//        return super.onOptionsItemSelected(item);
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == RESULT_CANCELED){
            //Apply potentially new settings
            Log.d(TAG, "In onActivityResult");
            SharedPreferences mprefs = PreferenceManager.getDefaultSharedPreferences(this);
            //starting_life
            String newLifeNum = mprefs.getString("starting_life", "20");
            Game.setStartingLifeTotal(Integer.parseInt(newLifeNum));
            Log.d(TAG, "Starting life is now: " + String.valueOf(Game.startingLifeTotal));
            //tap_life_change
            String newTapChange = mprefs.getString("tap_life_change", "1");
            Game.setSmallInc(Integer.parseInt(newTapChange));
            //swipe_life_change
            String newSwipeChange = mprefs.getString("swipe_life_change", "5");
            Game.setLargeInc(Integer.parseInt(newSwipeChange));
            Game.newGame();
            displayNewLife();
        }
    }

    // May cause problems
    private void displayNewLife(){

        switch(Game.numOfPlayers()){

            case 8:
                lifeText8.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 8");

            case 7:
                lifeText7.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 7");

            case 6:
                lifeText6.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 6");

            case 5:
                lifeText5.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 5");

            case 4:
                lifeText4.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 4");

            case 3:
                lifeText3.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 3");

            case 2:
                lifeText2.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 2");

            case 1:
                lifeText1.setText(Integer.toString(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 1");
        }

//        lifeText1.setText(Integer.toString(Game.startingLifeTotal));
//        lifeText2.setText(Integer.toString(Game.startingLifeTotal));
//        lifeText3.setText(Integer.toString(Game.startingLifeTotal));
//        lifeText4.setText(Integer.toString(Game.startingLifeTotal));
//        lifeText5.setText(Integer.toString(Game.startingLifeTotal));
//        lifeText6.setText(Integer.toString(Game.startingLifeTotal));
    }

    // Everything that needs to happen for a new game to start
    // May need to change for different # of players
    public void startNewGame(View newGameButton){

        // Reset all players' points to 'Starting Life Total'
        Game.newGame();
        // Reset our log screen
        Record.entryList.clear();

        switch(Game.numOfPlayers()){

            case 8:
                lifeText8.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 8");

            case 7:
                lifeText7.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 7");

            case 6:
                lifeText6.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 6");

            case 5:
                lifeText5.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 5");

            case 4:
                lifeText4.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 4");

            case 3:
                lifeText3.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 3");

            case 2:
                lifeText2.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 2");

            case 1:
                lifeText1.setText(String.valueOf(Game.startingLifeTotal));
                Log.d(TAG, "In startNewGame(): Case 1");
        }

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "New Game Started",
                Toast.LENGTH_SHORT);
        toast.show();

        Log.d(TAG, "At end of startNewGame()");
    }

    public void tossCoin(View coinTossButton) {

        Context context = getApplicationContext();
        CharSequence heads = "Coin landed on Heads!";
        CharSequence tails = "Coin landed on Tails!";
        int duration = Toast.LENGTH_SHORT;

        if (Game.tossCoin()) {
            Toast toast = Toast.makeText(context, heads, duration);
            toast.show();
        } else {
            Toast toast = Toast.makeText(context, tails, duration);
            toast.show();
        }
    }

    public void rollDie(View dieRollButton){

        Context context = getApplicationContext();
        // For now, the default will be to roll a six sided die
        int roll = Game.rollDie(6);

        dieBox(roll);
    }

    public void dieBox(int roll){
        AlertDialog.Builder tutorial = new AlertDialog.Builder(this);

        TextView dieTitle = new TextView(this);
        dieTitle.setText("Result");
        dieTitle.setPadding(10, 15, 10, 25);
        dieTitle.setGravity(Gravity.CENTER);
        dieTitle.setTextSize(30);

        tutorial.setCustomTitle(dieTitle);
        tutorial.setMessage(("\nYou Rolled a:\n\n" + roll + " !\n"));
        tutorial.setPositiveButton("Exit", null);

        AlertDialog die = tutorial.show();

        TextView msgText = (TextView) die.findViewById(android.R.id.message);
        msgText.setGravity(Gravity.CENTER);
        msgText.setTextSize(40);
        die.show();
    }

    public void addPlayer(View addPlayerButton){

        // Only add player when at most two players
        if(Game.numOfPlayers() <= 5){
            Game.addPlayer();
            Record.entryList.clear();

            Log.d(TAG, "Num of players after add: " + Game.numOfPlayers());

            // Change the ContentView, This may have to happen first
            switch(Game.numOfPlayers()){

                case 1:
                    setContentView(R.layout.scorescreen1);
                    Log.d(TAG, "In remove player: Content, case1");
                    break;

                case 2:
                    setContentView(R.layout.scorescreen);
                    Log.d(TAG, "In remove player: Content, case2");
                    break;

                case 3:
                    setContentView(R.layout.scorescreen3);
                    Log.d(TAG, "In remove player: Content, case3");
                    break;

                case 4:
                    setContentView(R.layout.scorescreen4);
                    Log.d(TAG, "In remove player: Content, case4");
                    break;

                case 5:
                    setContentView(R.layout.scorescreen5);
                    Log.d(TAG, "In remove player: Content, case5");
                    break;

                case 6:
                    setContentView(R.layout.scorescreen6);
                    Log.d(TAG, "In remove player: Content, case6");
                    break;

                default:
                    Log.d(TAG, "In remove player: Content, default");

            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            switch(Game.getNumOfPlayers()){

                case 6:
                    frame6 = (GraphicsView) findViewById(R.id.graphicsView6);
                    lifeText6 = (TextView) findViewById(R.id.player6LifeText);
                    frame6.setOnTouchListener(TouchListener6);
                    Log.d(TAG, "In remove player: Resources, Case 6");

                case 5:
                    frame5 = (GraphicsView) findViewById(R.id.graphicsView5);
                    lifeText5 = (TextView) findViewById(R.id.player5LifeText);
                    frame5.setOnTouchListener(TouchListener5);
                    Log.d(TAG, "In remove player: Resources, Case 5");


                case 4:
                    frame4 = (GraphicsView) findViewById(R.id.graphicsView4);
                    lifeText4 = (TextView) findViewById(R.id.player4LifeText);
                    frame4.setOnTouchListener(TouchListener4);
                    Log.d(TAG, "In remove player: Resources, Case 4");

                case 3:
                    frame3 = (GraphicsView) findViewById(R.id.graphicsView3);
                    lifeText3 = (TextView) findViewById(R.id.player3LifeText);
                    frame3.setOnTouchListener(TouchListener3);
                    Log.d(TAG, "In remove player: Resources, Case 3");

                case 2:
                    frame2 = (GraphicsView) findViewById(R.id.graphicsView2);
                    lifeText2 = (TextView) findViewById(R.id.player2LifeText);
                    frame2.setOnTouchListener(TouchListener2);
                    Log.d(TAG, "In remove player: Resources, Case 2");

                case 1:
                    frame1 = (GraphicsView) findViewById(R.id.graphicsView);
                    lifeText1 = (TextView) findViewById(R.id.player1LifeText);
                    frame1.setOnTouchListener(TouchListener1);
                    Log.d(TAG, "In remove player: Resources, Case 1");

                default:
                    Log.d(TAG, "In remove player, default");
            }

            // Reset all scores properly!
            Game.newGame();
            displayNewLife();

        }
        else{
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Maximum supported players: 6",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void removePlayer(View removePlayerButton){

        // Only do this when we have 3 or more players for the moment
        // Won't remove player once at only 2 players
        if(Game.numOfPlayers() >= 2){
            Game.removePlayer();
            Record.entryList.clear();

            Log.d(TAG, "Num of players after remove: " + Game.numOfPlayers());

            // Change the ContentView, This may have to happen first
            switch(Game.numOfPlayers()){

                case 1:
                    setContentView(R.layout.scorescreen1);
                    Log.d(TAG, "In remove plyaer: Content, case2");
                    break;

                case 2:
                    setContentView(R.layout.scorescreen);
                    Log.d(TAG, "In remove player: Content, case2");
                    break;

                case 3:
                    setContentView(R.layout.scorescreen3);
                    Log.d(TAG, "In remove player: Content, case3");
                    break;

                case 4:
                    setContentView(R.layout.scorescreen4);
                    Log.d(TAG, "In remove player: Content, case4");
                    break;

                case 5:
                    setContentView(R.layout.scorescreen5);
                    Log.d(TAG, "In remove player: Content, case5");
                    break;

                default:
                    Log.d(TAG, "In remove player: Content, default");

            }

            // Initialize toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            // Setup appropriate frames, ontouchlisteners, and textviews for each applicable player
            switch(Game.getNumOfPlayers()){

                case 5:
                    frame5 = (GraphicsView) findViewById(R.id.graphicsView5);
                    lifeText5 = (TextView) findViewById(R.id.player5LifeText);
                    frame5.setOnTouchListener(TouchListener5);
                    Log.d(TAG, "In remove player: Resources, Case 5");

                case 4:
                    frame4 = (GraphicsView) findViewById(R.id.graphicsView4);
                    lifeText4 = (TextView) findViewById(R.id.player4LifeText);
                    frame4.setOnTouchListener(TouchListener4);
                    Log.d(TAG, "In remove player: Resources, Case 4");

                case 3:
                    frame3 = (GraphicsView) findViewById(R.id.graphicsView3);
                    lifeText3 = (TextView) findViewById(R.id.player3LifeText);
                    frame3.setOnTouchListener(TouchListener3);
                    Log.d(TAG, "In remove player: Resources, Case 3");

                case 2:
                    frame2 = (GraphicsView) findViewById(R.id.graphicsView2);
                    lifeText2 = (TextView) findViewById(R.id.player2LifeText);
                    frame2.setOnTouchListener(TouchListener2);
                    Log.d(TAG, "In remove player: Resources, Case 2");

                case 1:
                    frame1 = (GraphicsView) findViewById(R.id.graphicsView);
                    lifeText1 = (TextView) findViewById(R.id.player1LifeText);
                    frame1.setOnTouchListener(TouchListener1);
                    Log.d(TAG, "In remove player: Resources, Case 1");

                default:
                    Log.d(TAG, "In remove player, default");
            }

            // Reset all scores properly!
            Game.newGame();
            displayNewLife();

        }
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Minimum supported players: 1",
                    Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    // Function that creates records, call after the LP change has been made
    /*
     * MODE:
     * tap up = 0
     * tap down = 1
     * swipe up = 2
     * swipe down = 3
     * no change = 4
      * */
    public void createRecord(int player, int mode){

        String playerName;
        String amount;
        String currentLife;


        switch (player){
            case 0: playerName = "Player1";
                break;
            case 1: playerName = "Player2";
                break;
            case 2: playerName = "Player3";
                break;
            case 3: playerName = "Player4";
                break;
            case 4: playerName = "Player5";
                break;
            case 5: playerName = "Player6";
                break;
            default: playerName = "Unknown";
        }

        switch (mode){
            case 0: amount = Integer.toString(Game.smallInc);
                break;
            case 1: amount = "-" + Integer.toString(Game.smallInc);
                break;
            case 2: amount = Integer.toString(Game.largeInc);
                break;
            case 3: amount = "-" + Integer.toString(Game.largeInc);
                break;
            case 4: amount = "0";
                break;
            default: amount = "Unknown";
        }

        currentLife = Integer.toString(Game.getPlayerLP(player));

        Entry entry = new Entry(playerName, amount, currentLife, "0");
        Record.entryList.add(entry);
    }

}
