package com.example.stickynoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button increaseSizeBtn, decreaseSizeBtn, BoldBtn, underlineBtn, italicBtn;
    private EditText noteEdt;
    private TextView size;
    StickyNote note=new StickyNote();
    float currentSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEdt = findViewById(R.id.idEdTV);
        size = findViewById(R.id.idTVSize);
        increaseSizeBtn = findViewById(R.id.idBtnIncrease);
        decreaseSizeBtn = findViewById(R.id.idBtnReduce);
        underlineBtn = findViewById(R.id.idBtnUnderline);
        BoldBtn = findViewById(R.id.idBtnBold);
        italicBtn = findViewById(R.id.idBtnItalic);
        currentSize=noteEdt.getTextSize();
            size.setText(""+currentSize);


        increaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size.setText(""+currentSize);
                currentSize++;
                noteEdt.setTextSize(currentSize);

            }
        });

        decreaseSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size.setText(""+currentSize);
                currentSize--;
                noteEdt.setTextSize(currentSize);

            }
        });
        BoldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isBold())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    BoldBtn.setTextColor(getResources().getColor(R.color.white));
                    BoldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));

                }
                else
                {
                    BoldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    BoldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }

            }
        });
        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoldBtn.setTextColor(getResources().getColor(R.color.white));
                BoldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if(noteEdt.getTypeface().isItalic())
                {
                    noteEdt.setTypeface(Typeface.DEFAULT);
                    italicBtn.setTextColor(getResources().getColor(R.color.white));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));

                }
                else
                {
                    italicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }

            }
        });

        underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteEdt.getPaintFlags()==8)
                {
                    underlineBtn.setTextColor(getResources().getColor(R.color.white));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEdt.setPaintFlags(noteEdt.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
//                    noteEdt.getPaintFlags();
                }
                else
                {
                    underlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEdt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG );
                }

            }
        });

    }

    public void saveButton(View view) {
        note.setStick(noteEdt.getText().toString(),this);
        updateWidget();
        Toast.makeText(this,"NOte has been updated",Toast.LENGTH_LONG).show();
    }
    private void  updateWidget()
    {
        AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(this);
        RemoteViews  remoteViews=new RemoteViews(this.getPackageName(),R.layout.widgetlayout);
        ComponentName thisWidget=new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.idTVwidget,noteEdt.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);

    }
}