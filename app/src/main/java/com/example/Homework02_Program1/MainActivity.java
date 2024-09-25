//==================================================
//Hunter Markavich
//09-22-2024
//Homework02_Program1
//==================================================

package com.example.Homework02_Program1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Create Seekbars for RGB color adjustment, textviews for displaying values, the button for
    //inputing and saving the color, and the listview for displaying stored colors
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;
    TextView tv_j_red;
    TextView tv_j_green;
    TextView tv_j_blue;
    TextView tv_j_hexValue;
    Button btn_j_inputColor;
    ListView lv_j_savedColors;

    //Global variables that will track the RGB values, starting at the maximum of white
    int red = 255;
    int green = 255;
    int blue = 255;

    //String representation of the hex value for the RGB color, initially set to white
    String hex = String.format("#%02X%02X%02X", 255, 255, 255);

    //This will be used to change the background color dynamically
    View background;

    //ArrayList to store the custom colors created by the user
    ArrayList<CustomColorInfo> savedColors;
    ColorAdapter adapter;

    @Override
    protected void onCreate(Bundle saveState)
    {
        super.onCreate(saveState);
        //setting the content view to the activety layout
        setContentView(R.layout.activity_main);

        //Initialize views by finding them in the layout
        tv_j_red         = findViewById(R.id.tv_v_red);
        tv_j_green       = findViewById(R.id.tv_v_green);
        tv_j_blue        = findViewById(R.id.tv_v_blue);
        tv_j_hexValue    = findViewById(R.id.tv_v_hexValue);
        sb_j_red         = findViewById(R.id.sb_v_red);
        sb_j_green       = findViewById(R.id.sb_v_green);
        sb_j_blue        = findViewById(R.id.sb_v_blue);
        btn_j_inputColor = findViewById(R.id.btn_v_inputColor);
        lv_j_savedColors = findViewById(R.id.lv_v_savedColors);
        background       = findViewById(R.id.main);

        //setting the maximum value for each seekbar to 255
        sb_j_red.setMax(255);
        sb_j_green.setMax(255);
        sb_j_blue.setMax(255);

        //Initalizing text views to display the default RGB values and Hex representation
        tv_j_red.setText("Red: 255");
        tv_j_green.setText("Green: 255");
        tv_j_blue.setText("Blue: 255");
        tv_j_hexValue.setText("Hex Representation: " + hex);

        //Setting up event listeners for the seekbars and button
       setupRedSeekBarListener();
       setupGreenSeekBarListener();
       setupBlueSeekBarListener();
       inputColorButtonEvent();
       lvOnClickListener();

       savedColors = new ArrayList<CustomColorInfo>();

       fillListView();
    }

    private void setupRedSeekBarListener()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_red.setText("Red:" + String.valueOf(i));
                red = i;
                changeBackground();
                updateTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void setupGreenSeekBarListener()
    {
        sb_j_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_green.setText("Green: " + String.valueOf(i));
                green = i;
                changeBackground();
                updateTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void setupBlueSeekBarListener()
    {
        sb_j_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_blue.setText("Blue: " + String.valueOf(i));
                blue = i;
                changeBackground();
                updateTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    //How to change the background color based on the current RGB values and update it
    private void changeBackground()
    {
        hex = String.format("#%02X%02X%02X", red, green, blue);
        background.setBackgroundColor(Color.parseColor(hex));
        tv_j_hexValue.setText("Hex Representation: " + hex);
    }

    //how to set up the button click event for adding the selected color
    private void inputColorButtonEvent()
    {
        btn_j_inputColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addColorToList();
            }
        });
    }

    //How to add the current RGB color to the saved list
    private void addColorToList()
    {
        CustomColorInfo colorToAdd = new CustomColorInfo(red, green, blue, hex);
        savedColors.add(colorToAdd);
        Log.d("Color Input", "Color was successfully added");

        background.setBackgroundColor(Color.WHITE);

        resetSeekBarsAndText();
        adapter.notifyDataSetChanged();
    }

    //This is how we will reset the seekbars and the text above them by putting them back at the max
    private void resetSeekBarsAndText()
    {
        sb_j_red.setProgress(255);
        sb_j_green.setProgress(255);
        sb_j_blue.setProgress(255);

        tv_j_red.setText("Red: 255");
        tv_j_green.setText("Green: 255");
        tv_j_blue.setText("Blue: 255");
        tv_j_hexValue.setText("Hex Representation: " + hex);

        background.setBackgroundColor(Color.WHITE);
    }

    private void fillListView()
    {
        adapter = new ColorAdapter(this, savedColors);
        lv_j_savedColors.setAdapter(adapter);
    }

    private void updateTextColor()
    {
        if (red <= 100 && green <= 100 && blue <= 100)
        {
            tv_j_red.setTextColor(Color.parseColor("#FFFFFF"));
            tv_j_green.setTextColor(Color.parseColor("#FFFFFF"));
            tv_j_blue.setTextColor(Color.parseColor("#FFFFFF"));
            tv_j_hexValue.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            tv_j_red.setTextColor(Color.parseColor("#000000"));
            tv_j_green.setTextColor(Color.parseColor("#000000"));
            tv_j_blue.setTextColor(Color.parseColor("#000000"));
            tv_j_hexValue.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void lvOnClickListener()
    {
        lv_j_savedColors.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Log.d("onItemClick: ", "You clicked item: " + 1 + "at position " + position);
                background.setBackgroundColor(Color.parseColor(savedColors.get(position).getHex()));
                tv_j_red.setText("Red: " + String.valueOf(savedColors.get(position).getRed()));
                red = savedColors.get(position).getRed();
                sb_j_red.setProgress(red);
                tv_j_green.setText("Green: " + String.valueOf(savedColors.get(position).getGreen()));
                green = savedColors.get(position).getGreen();
                sb_j_green.setProgress(green);
                tv_j_blue.setText("Blue: " + String.valueOf(savedColors.get(position).getBlue()));
                blue = savedColors.get(position).getBlue();
                sb_j_blue.setProgress(blue);
                updateTextColor();
            }
        });
    }
}