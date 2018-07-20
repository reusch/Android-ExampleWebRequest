package de.supergeilesteil.lightswitch;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import de.supergeilesteil.lightswitch.webrequests.AsyncWebTask;
import org.apmem.tools.layouts.FlowLayout;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        fill();
    }

    /**
     * Fill view with content
     */
    private void fill() {
        // sizes
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        int ibheight = (int) (100 * scale + 0.5f); // 100dp
        int ibwidth = ibheight;
        int padding = (int) (10 * scale + 0.5f);
        for(int i=1; i<=9; i++) {
            ImageButton ib = new ImageButton(getApplicationContext());
            byte[] imageAsBytes = Base64.decode(
                    "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcU"
                            + "FhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgo"
                            + "KCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCABQADwDASIA"
                            + "AhEBAxEB/8QAHAAAAQUBAQEAAAAAAAAAAAAAAAIDBAUHBggB/8QANhAAAQQBAgQCBggHAAAAAAAA"
                            + "AQACAwQRBQYHEiExQVETFCIyQpFDYWJxg6Gx01KBk6LB0fD/xAAZAQEAAwEBAAAAAAAAAAAAAAAA"
                            + "AQMEAgX/xAAfEQEAAgICAgMAAAAAAAAAAAAAAQIDBBExEkEFISL/2gAMAwEAAhEDEQA/APVKZtu5"
                            + "a0p+yQnlGunLI2eL5Gj88n8gUDvLiMAeATRYpGMhJIQMtZgr5MMcjvJwUgAJFhmYXgd8dEDqEljg"
                            + "5jXDsRkJSAVPuHUq2ltoz3JhDD6ctLiCfo5DjA6nsrhZ7xy0t+pbAnmhiMsunWIdREeM87Yngvbj"
                            + "xyzn6KJ6THa8O8KDmh1eG5NH/H6MRA/1C0pB3nTz1qWh+JB+4s3MGnQbu1SsIgyjO6G5TbBI6Joh"
                            + "kiZ7vIR052yLrKWlaOY+kEhz19qzK79XLz8nyNMVppaOl9de1o5hejeVPPSnaP4kH7iTJvfSa8Rd"
                            + "e9bqMx78kBe0fe5nMB/MrmtQ0fRYY3OEVjPl67Pj5c+F574rUTuHiBtzbWisLH2X4mEbjlzHOA9o"
                            + "+OA1x6+a7wb1M9/CsSi+C1K+UvZtUh1aItOQWDB8+ieTNSEQVYYW9RGwMB+4YTy3KAkPaHsLXAFp"
                            + "GCCMghLQgw7iLojttCnYiJ9RrEw1ZHfDCTkVyfNhzyZ95p5RlwHNJ2br9PU6gkhsxys6jLXZwR3B"
                            + "8j9S17UaNbUqE9K/BHYqTsLJYpG5a9p7ghYTb4Zw7D1me5oLLVmncaSInTdYyMnlxj2gAehzleft"
                            + "6UZv1XtoxZvD6npK31uaho1R8t60yJnZoJ9p58mjuSlcCdhWxq1rfe5oDFqN5vLRqv71oD2J+0Rj"
                            + "/ipHD7hNp9vWo957kYbV2cCWpUkeJI4G/C7xDjjBAHQfX4bYBhWampXBHPuXObLOSePQQhC2KQhC"
                            + "EAue3k0CnDLj3C8fON3+l0KpN3N5tHkx4cx/scgkbZh9W23pMB+iqQs+TAFZqNp3ShVA7eib+gUl"
                            + "AIQhAIQhAKt1+J02lzMaMuIIA8+hVkqnXLza0bGDq9xPQDPYIJ9JhZTgY7u2NoPyT6rdEvNvUmOH"
                            + "SRg5XtIwQR/gqyQCEIQf/9k="
                    , Base64.DEFAULT);

            Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            ib.setImageBitmap(b);
            ib.setPadding(padding, padding, padding, padding);
            FlowLayout fl = findViewById(R.id.ExampleFlowLayout);
            ib.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ib.setMaxWidth(150);
            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(ibwidth, ibheight);
            ib.setLayoutParams(lp);
            ib.setTag("Testtag"+i);
            ib.setOnClickListener(getButtonOnClickListener(ib, "test"+i
            ));
            fl.addView(ib);
        }

    }

    private void showToast(Object res) {
        showToast(res, false);
    }

    private void showToast(Object res, boolean longduration) {
        Toast t;
        String msg;
        if(res instanceof Integer) {
            msg = getString((Integer) res);
        }
        else if(res instanceof String) {
            msg = (String) res;
        }
        else return;

        int shlen;
        if(longduration) {
            shlen = Toast.LENGTH_LONG;
        }
        else {
            shlen = Toast.LENGTH_SHORT;
        }
        t = Toast.makeText(this, msg, shlen);
        ViewGroup group = (ViewGroup) t.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    private View.OnClickListener getButtonOnClickListener(final View button, final String req)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                showToast("Button-Tag: "+button.getTag()+", Requested: "+req);
                AsyncWebTask a = new AsyncWebTask();
                a.execute("http://brett.fritz.box:8000/lampe/"+req);
            }
        };
    }
}
