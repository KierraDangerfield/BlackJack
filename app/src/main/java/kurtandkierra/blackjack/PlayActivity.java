package kurtandkierra.blackjack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Kurt and Kierra on 1/30/2018.
 */

/*
    1=Ace
    2-10 = 2-10
    11=Jack
    12=Queen
    13=King
*/

public class PlayActivity extends AppCompatActivity {

    Integer currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        // final int currentAmount = 0;
        final int STARTING_AMOUNT = 100;


        // test data
        Button b = findViewById(R.id.win_points);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView points_tv = findViewById(R.id.player_points);
                String points_s = points_tv.getText().toString().trim();
                int points_i = Integer.parseInt(points_s);
                String temp = "" + (points_i + 1);
                points_tv.setText(temp);
            }// end onClick
        });

        b = findViewById(R.id.lose_points);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView points_tv = findViewById(R.id.player_points);
                String points_s = points_tv.getText().toString().trim();
                int points_i = Integer.parseInt(points_s);
                String temp = "" + (points_i - 1);
                points_tv.setText(temp);
            }// end onClick
        });
        // end test data

        // deal button
        b = findViewById(R.id.deal_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView, textView1, textView2;
                textView1 = findViewById(R.id.dealer_total);
                textView1.setText("0");
                textView2 = findViewById(R.id.player_total);
                textView2.setText("0");
                for (int i = 0; i < 4; i++) {
                    draw_card(i);
                }// end for

                // hide deal button
                Button options = findViewById(R.id.deal_button);
                options.setClickable(false);
                options.setVisibility(View.INVISIBLE);
                // activate skip button
                options = findViewById(R.id.skip_button);
                options.setClickable(true);
                options.setVisibility(View.VISIBLE);
                // activate draw button
                options = findViewById(R.id.draw_button);
                options.setClickable(true);
                options.setVisibility(View.VISIBLE);


            }// end onClick
        });// end Listener

        //bet button
        b = findViewById(R.id.bet_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check et and get bet amount
                EditText bet_et = findViewById(R.id.betAmount_editText);
                String betStr = bet_et.getText().toString();
                //validate
                if (betStr.isEmpty()) {
                    bet_et.setText("Need Valid Bet Amount");
                    return;
                }

                Integer bet;
                bet = Integer.valueOf(betStr);
                if (bet > STARTING_AMOUNT) {
                    bet_et.setText("Need Valid Bet Amount");
                    return;
                }

                currentNumber = STARTING_AMOUNT - bet;

                TextView moneyTV = findViewById(R.id.money_textview);
                moneyTV.setText("$" + currentNumber);
            }
        });//end of Listener
    }// end onCreate

    public void draw_card(int place){
            TextView textView;
            Random ran = new Random();
            int card_num = ran.nextInt(13) + 1;//13 is max num 1 is min
            String num;

            if (card_num == 11) {
                num = "J";
            } else if (card_num == 12) {
                num = "Q";
            } else if (card_num == 13) {
                num = "K";
            } else if (card_num == 1) {
                num = "A";
            } else {
                num = card_num + "";
            }
            if (card_num > 10) {
                card_num = 10;
            }
            if (place == 0) { // dealer card 1
                textView = findViewById(R.id.dealer_c1);
                textView.setText(num);

                textView = findViewById(R.id.dealer_total);
                String d_total = textView.getText().toString().trim();
                int d_total_int = Integer.parseInt(d_total) + card_num;
                d_total = "" + d_total_int;
                textView.setText(d_total);
            }  else if (place == 1) { // player card 1
                textView = findViewById(R.id.player_c1);
                textView.setText(num);

                textView = findViewById(R.id.player_total);
                String p_total = textView.getText().toString().trim();
                int p_total_int = Integer.parseInt(p_total) + card_num;
                p_total = "" + p_total_int;
                textView.setText(p_total);
            } else if (place == 2) { // player card 2
                textView = findViewById(R.id.player_c2);
                textView.setText(num);

                textView = findViewById(R.id.player_total);
                String p_total = textView.getText().toString().trim();
                int p_total_int = Integer.parseInt(p_total) + card_num;
                p_total = "" + p_total_int;
                textView.setText(p_total);
            }else if (place == 2) { // player optional card 3
                textView = findViewById(R.id.player_c2);
                textView.setText(num);

                textView = findViewById(R.id.player_total);
                String p_total = textView.getText().toString().trim();
                int p_total_int = Integer.parseInt(p_total) + card_num;
                p_total = "" + p_total_int;
                textView.setText(p_total);
            }else if (place == 3) { // dealer card 2
                textView = findViewById(R.id.dealer_c2);
                textView.setText(num);

                textView = findViewById(R.id.dealer_total);
                String d_total = textView.getText().toString().trim();
                int d_total_int = Integer.parseInt(d_total) + card_num;
                d_total = "" + d_total_int;
                textView.setText(d_total);
            } else { // if ERROR
                Toast.makeText(
                        PlayActivity.this,
                        "Something went wrong",
                        Toast.LENGTH_LONG
                ).show();
            }// end if
        } // end draw_card


}


