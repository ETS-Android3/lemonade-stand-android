package com.example.a219_lemonade_stand;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SingleplayerView1 extends View {

    private Bitmap seller;
    private Bitmap unscaledseller;
    private int profileX , profileY , profileWidth = 300, profileHeight = 300;
    private String lemonstock = "lemons: ";
    private String waterstock = "water: ";
    private String sugarstock = "sugar: ";

    private String s_Revenue = "Revenue: ";
    private String s_Overhead = "Overhead: ";
    private String s_Profit = "Profit: ";

    private Paint scorePaint = new Paint();


    private Bitmap locationImage;
    private Bitmap unscaledlLcationImage;
    private int locationImageX , locationImageY , locationImageWidth = 500, locationImageHeight = 300;

    private Bitmap recipepricingButton;
    private Bitmap unscaledrecipepricing;
    private int rpX , rpY , rpWidth = 300, rpHeight = 300;


    private Bitmap unscaledknife;
    private Bitmap knife;
    private int knifeX , knifeY , knifeWidth = 200, knifeHeight = 200;


    private Bitmap slot3;
    private int slot3X , slot3Y , slot3Width = 200, slot3Height = 200;


    private Bitmap unscaledcooler;
    private Bitmap cooler;
    private int coolerX , coolerY , coolerWidth = 200, coolerHeight = 200;

    private int arrowX , arrowY , arrowWidth = 300, arrowHeight = 300;
    private Bitmap unscaledarrow;
    private Bitmap goarrow;
    private boolean arrowClicked= false;
    public int prevmoney;

    public boolean getarrowClicked() { return arrowClicked; };

    private Bitmap homeButton;
    private Bitmap unscaledhome;
    private int homeButtonX = 20, homeButtonY = 20, homeButtonWidth = 200, homeButtonHeight = 200;




    private boolean touch = false;

    public SingleplayerView1(Context context) {
        super(context);


        unscaledhome = BitmapFactory.decodeResource(getResources(), R.drawable.lemonlogo);
        homeButton = Bitmap.createScaledBitmap(unscaledhome, homeButtonWidth, homeButtonHeight, false);

        unscaledrecipepricing = BitmapFactory.decodeResource(getResources(), R.drawable.recipeicon);
        recipepricingButton = Bitmap.createScaledBitmap(unscaledrecipepricing, rpWidth, rpHeight, false);


        unscaledknife = BitmapFactory.decodeResource(getResources(), R.drawable.knife);
        knife = Bitmap.createScaledBitmap(unscaledknife, knifeWidth, knifeHeight, false);

        slot3 = Bitmap.createScaledBitmap(unscaledknife, slot3Width, slot3Height, false);

        unscaledcooler = BitmapFactory.decodeResource(getResources(), R.drawable.icecooler);
        cooler = Bitmap.createScaledBitmap(unscaledcooler, coolerWidth, coolerHeight, false);


        unscaledseller = BitmapFactory.decodeResource(getResources(), R.drawable.sellerpic);
        seller = Bitmap.createScaledBitmap(unscaledseller, profileWidth, profileHeight, false);


        unscaledarrow = BitmapFactory.decodeResource(getResources(), R.drawable.goarrow);
        goarrow = Bitmap.createScaledBitmap(unscaledarrow, arrowWidth, arrowHeight, false);



        unscaledlLcationImage = BitmapFactory.decodeResource(getResources(), R.drawable.location1);
        locationImage = Bitmap.createScaledBitmap(unscaledlLcationImage,  locationImageWidth, locationImageHeight, false);



        scorePaint.setColor(Color.MAGENTA);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);





    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        profileX = canvas.getWidth() - seller.getWidth() - 20;
        profileY = 20;

        canvas.drawBitmap(seller, profileX, profileY, null);


        locationImageX = (canvas.getWidth() /2 ) - (locationImageWidth/2);
        locationImageY = 20;
        canvas.drawBitmap(locationImage, locationImageX, locationImageY, null);


        coolerX = (canvas.getWidth() /2 ) + (coolerWidth/2) + 100;
        coolerY = 450;

        knifeX = (canvas.getWidth() /2 ) - (knifeWidth/2);
        knifeY = 450;

        slot3X = (canvas.getWidth() /2 ) - (slot3Width/2) - 300;
        slot3Y = 450;

        canvas.drawBitmap(slot3, slot3X, slot3Y, null);
        canvas.drawBitmap(cooler, coolerX, coolerY, null);
        canvas.drawBitmap(knife, knifeX, knifeY, null);


        canvas.drawText("PREVIOUS RETURN", 40, 750, scorePaint);
        canvas.drawText(s_Revenue, 40, 850, scorePaint);
        canvas.drawText(s_Overhead, 40, 950, scorePaint);
        canvas.drawText(s_Profit, 40, 1050, scorePaint);

        canvas.drawText("CURRENT STOCK", 40, 1200, scorePaint);
        canvas.drawText(lemonstock, 40, 1300, scorePaint);
        canvas.drawText(waterstock, 40, 1400, scorePaint);
        canvas.drawText(sugarstock, 40, 1500, scorePaint);
        canvas.drawText("Recipe(s): ", 40, 1600, scorePaint);



        arrowX = canvas.getWidth() - goarrow.getWidth() - 20;
        arrowY = canvas.getHeight() - goarrow.getHeight() - 20;
        canvas.drawBitmap(goarrow, arrowX, arrowY, null);

        rpX = 20;
        rpY = canvas.getHeight() - recipepricingButton.getHeight() - 20;
        canvas.drawBitmap(recipepricingButton, rpX, rpY, null);

        canvas.drawBitmap(homeButton, homeButtonX, homeButtonY, null);

        if(touch)  {


        }
        else {



        }





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                if( x > rpX && x < rpX + rpWidth && y > rpY && y < rpY + rpHeight )  {

                    Toast.makeText(getContext(), "Go to Recipe/Pricing State", Toast.LENGTH_SHORT).show();
                    Intent intent_RP = new Intent(getContext(), RecipePricingActivity.class);
                    intent_RP.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent_RP);

                }

                if( x > arrowX && x < arrowX + arrowWidth && y > arrowY && y < arrowY + arrowHeight ) {
                    arrowClicked = true;

                    Toast.makeText(getContext(), "Start of the day", Toast.LENGTH_SHORT).show();
                    Intent intent_StartDay = new Intent(getContext(), SingleplayerActivity2.class);
                    intent_StartDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent_StartDay);

                }
                if( x > homeButtonX && x < homeButtonY + homeButtonWidth && y > homeButtonY && y < homeButtonY + homeButtonHeight ) {

                    Toast.makeText(getContext(), "Return to MainMenu", Toast.LENGTH_SHORT).show();
                    Intent intent_EndDay = new Intent(getContext(), MainMenuActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }
                return true;
        }





        return false;
    }

}
