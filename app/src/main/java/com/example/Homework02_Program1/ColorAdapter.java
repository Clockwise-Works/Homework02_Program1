package com.example.Homework02_Program1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorAdapter extends ArrayAdapter<CustomColorInfo>
{
    public ColorAdapter(Context context, ArrayList<CustomColorInfo> colors)
    {
        super(context, 0, colors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_color, parent, false);
        }

        TextView rgbValueTextView = convertView.findViewById(R.id.tv_list_rgbValue);
        TextView hexValueTextView = convertView.findViewById(R.id.tv_list_hexValue);

        CustomColorInfo color = getItem(position);

        if (color != null)
        {
            //Formatting the RGB values
            String rgbColor = "Red: " + color.getRed() + "   Green: " + color.getGreen() + "   Blue: " + color.getBlue();

            //Formatting the hex value
            String hexColor = color.getHex();
            String hexDisplay = "Hex: " + hexColor;

            //Set the formatted text to the textviews
            rgbValueTextView.setText(rgbColor);
            hexValueTextView.setText(hexDisplay);

            convertView.setBackgroundColor(Color.parseColor(hexColor));

            updateTextColor(color.getRed(), color.getGreen(), color.getBlue(), rgbValueTextView, hexValueTextView);
        }

        return convertView;
    }

    private void updateTextColor(int red, int green, int blue, TextView rgbTextView, TextView hexTextView)
    {
        //check if the color is dark (less than or equal to 100)
        if (red <= 100 && green <= 100 && blue <= 100)
        {
            rgbTextView.setTextColor(Color.parseColor("#FFFFFF"));
            hexTextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            rgbTextView.setTextColor(Color.parseColor("#000000"));
            hexTextView.setTextColor(Color.parseColor("#000000"));
        }
    }
}
