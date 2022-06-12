package mobi.arazzguliyeff.taskagitmakas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GirisPanel extends AppCompatActivity {

    Button basla;
    EditText editText;
    String goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_panel);

        basla = findViewById(R.id.button);

        editText = (EditText) findViewById(R.id.goal);


        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goal = editText.getText().toString();
                if(editText.getText().toString().equals("")){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Lütfen Bir Değer Girin", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent i = new Intent(GirisPanel.this,MainActivity.class);
                    i.putExtra("goal", goal);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}