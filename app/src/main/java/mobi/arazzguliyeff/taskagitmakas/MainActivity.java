package mobi.arazzguliyeff.taskagitmakas;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView resim;
    Button buton;
    TextView youText,aiText;

    int count_tas = 0,count_kagit = 0,count_makas = 0,scoreYou = 0,scoreAi = 0;
    ImageView benim_tas, benim_kagit, benim_makas;
    Random rand;
    Integer[] images = {
            R.drawable.makas,
            R.drawable.tas,
            R.drawable.kagit
    };
    int picImaged = 0, lastPicked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int goal = Integer.parseInt(intent.getStringExtra("goal"));
        String gameOver = intent.getStringExtra("GameOver");
        String win = intent.getStringExtra("Win");

        resim = (ImageView) findViewById(R.id.Resim);

        buton = (Button) findViewById(R.id.buton); //buton

        benim_kagit = (ImageView) findViewById(R.id.benim_kagit); //asagi kagit
        benim_makas = (ImageView) findViewById(R.id.benim_makas); //asagi makas
        benim_tas = (ImageView) findViewById(R.id.benim_tas); //asagi tas

        final MediaPlayer winSound = MediaPlayer.create(this, R.raw.win1);
        final MediaPlayer loseSound = MediaPlayer.create(this, R.raw.lose);

        youText =  findViewById(R.id.youText);
        aiText = findViewById(R.id.aiText);

        rand = new Random();

        SecilenTas(benim_tas);
        SecilenKagit(benim_kagit);
        SecilenMakas(benim_makas);

        buton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                do {

                    picImaged = rand.nextInt(images.length);
                    resim.setImageResource(images[picImaged]);

                    if(images[picImaged] == R.drawable.tas && count_makas%2==1){
                        scoreAi+=1;
                        loseSound.start();
                        //scoreAiTxt = String.valueOf(scoreAi);
                        aiText.setText("AI: "+scoreAi);
                    }

                    else if(images[picImaged]== R.drawable.tas && count_kagit%2==1){
                        scoreYou+=1;
                        winSound.start();
                        //scoreYouTxt = String.valueOf(scoreYou);
                        youText.setText("You: "+scoreYou);
                    }

                    else if(images[picImaged]== R.drawable.makas && count_kagit%2==1){
                        scoreAi+=1;
                        loseSound.start();
                        //scoreAiTxt = String.valueOf(scoreAi);
                        aiText.setText("AI: "+scoreAi);
                    }

                    else if(images[picImaged]==R.drawable.makas && count_tas%2==1){
                        scoreYou+=1;
                        winSound.start();
                        //scoreYouTxt = String.valueOf(scoreYou);
                        youText.setText("You: "+scoreYou);
                    }

                    else if(images[picImaged] == R.drawable.kagit && count_tas%2==1){
                        scoreAi+=1;
                        loseSound.start();
                        //scoreAiTxt = String.valueOf(scoreAi);
                        aiText.setText("AI: "+scoreAi);
                    }

                    else if(images[picImaged]==R.drawable.kagit && count_makas%2==1){
                        scoreYou+=1;
                        winSound.start();
                        //scoreYouTxt = String.valueOf(scoreYou);
                        youText.setText("You: "+scoreYou);
                    }
                    count_makas = 0;
                    count_kagit = 0;
                    count_tas = 0;

                    if(goal==scoreAi){
                        scoreYou =0;
                        scoreAi=0;
                        Intent i = new Intent(MainActivity.this,GameOver.class);
                        startActivity(i);
                    }

                    else if(goal==scoreYou){
                        scoreYou =0;
                        scoreAi=0;
                        Intent i = new Intent(MainActivity.this,Win.class);
                        startActivity(i);
                    }

                    benim_tas.setImageResource(R.drawable.tas);
                    benim_makas.setImageResource(R.drawable.makas);
                    benim_kagit.setImageResource(R.drawable.kagit);

                }while(picImaged == lastPicked);
                lastPicked = picImaged;
                resim.setImageResource(images[picImaged]);
                lastPicked = picImaged;
            }
        });
    }

    //Tas
    private void SecilenTas(View view){
        benim_tas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_tas+=1;
                if(count_tas % 2 == 1){
                    benim_tas.setImageResource(R.drawable.tas_piced);
                    count_makas=0;
                    benim_makas.setImageResource(R.drawable.makas);
                    count_kagit =0;
                    benim_kagit.setImageResource(R.drawable.kagit);

                }else if (count_tas %2==0){
                    count_tas=0;
                    benim_tas.setImageResource(R.drawable.tas);
                }
            }
        });
    }
    //Kagit
    private void SecilenKagit(View view){
        benim_kagit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_kagit+=1;
                if(count_kagit % 2 ==1){
                    benim_kagit.setImageResource(R.drawable.kagit_piced);
                    count_makas=0;
                    benim_makas.setImageResource(R.drawable.makas);
                    count_tas=0;
                    benim_tas.setImageResource(R.drawable.tas);
                }else if(count_kagit %2==0){
                    count_kagit=0;
                    benim_kagit.setImageResource(R.drawable.kagit);
                }
            }
        });
    }

    private void SecilenMakas(View view){
        benim_makas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_makas+=1;
                if(count_makas % 2 ==1){
                    benim_makas.setImageResource(R.drawable.makas_piced);
                    count_tas=0;
                    benim_tas.setImageResource(R.drawable.tas);
                    count_kagit=0;
                    benim_kagit.setImageResource(R.drawable.kagit);
                }else if(count_makas%2 == 0){
                    count_makas=0;
                    benim_makas.setImageResource(R.drawable.makas);
                }
            }
        });
    }

}