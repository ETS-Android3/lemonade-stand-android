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
import android.widget.Toast;

public class RecipePricingView extends View {



    private Bitmap homeButton;
    private Bitmap unscaledhome;
    private int homeButtonX = 20, homeButtonY = 20, homeButtonWidth = 200, homeButtonHeight = 200;

    private int canvasWidth, canvasHeight;

    private int decrementX = 100;
    private int decrementY = 0;

    private int incrementX = (canvasWidth/2) + 900;
    private int incrementY = 0;

    private int changebuttonheight = 100;
    private int changebuttonwidth = 100;



    private Bitmap decrementButton;
    private Bitmap unscaledDecrement;

    private Bitmap incrementButton;
    private Bitmap unscaledIncrement;

    private String s_currentBalance = "";
    private String s_PricingPerCup = "";

    private int pricingpercup_x = 100;
    private int pricingpercup_y =300;



    private String s_profitwrecipe = "";
    private String s_recipecost = "";
    private double playerbalance = 0;
    private double pricingpcup = 0;
    private double profitwrecip = 0;
    private double recipecost = 0;



    private String s_lemonsPerCup = "";
    private String s_sugarPerCup = "";
    private String s_waterPerCup = "";
    private int lemonspcup = 0;
    private int sugarpercup = 0;
    private int waterpercup = 0;

    private String s_flavours = "";
    private String s_effects = "";

    private Paint scorePaint = new Paint();

    GameObject recipePricingGameObject = new GameObject();

    public RecipePricingView(Context context) {
        super(context);

        unscaledhome = BitmapFactory.decodeResource(getResources(), R.drawable.lemonlogo);
        homeButton = Bitmap.createScaledBitmap(unscaledhome, homeButtonWidth, homeButtonHeight, false);

        unscaledDecrement = BitmapFactory.decodeResource(getResources(), R.drawable.minus_icon);
        decrementButton = Bitmap.createScaledBitmap(unscaledDecrement, 100, 100, false);

        unscaledIncrement = BitmapFactory.decodeResource(getResources(), R.drawable.plus_icon);
        incrementButton = Bitmap.createScaledBitmap(unscaledIncrement, 100, 100, false);

        s_currentBalance = "" + recipePricingGameObject.getBalance();

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);




    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        playerbalance = recipePricingGameObject.getBalance();
        s_currentBalance = " " + playerbalance;

        canvas.drawText("Balance: $" + s_currentBalance, (canvasWidth/2) -200, 100, scorePaint);


        canvas.drawText("PRICING",(canvasWidth/2), 200, scorePaint);

        pricingpcup = recipePricingGameObject.getPPC();
        s_PricingPerCup = "$" + pricingpcup + " per cup:: ";


        canvas.drawText(s_PricingPerCup, (canvasWidth/2) -250, 400, scorePaint);


        profitwrecip = recipePricingGameObject.getrecipeprofit();
        s_profitwrecipe = "Profit w/ Recipe: $" + profitwrecip;
        canvas.drawText(s_profitwrecipe, (canvasWidth/2) -400, 600, scorePaint);


        recipecost = recipePricingGameObject.getrecipecost();
        s_recipecost = "Recipe cost per cup: $" + recipecost;
        canvas.drawText(s_recipecost, (canvasWidth/2) -400, 800, scorePaint);


        lemonspcup = recipePricingGameObject.getUseLemons();
        s_lemonsPerCup = lemonspcup + " Lemons Per Cup";

        sugarpercup = recipePricingGameObject.getUseSugar();
        s_sugarPerCup = sugarpercup + "g sugar per cup";

        waterpercup = recipePricingGameObject.getUseWater();
        s_waterPerCup = waterpercup + "ml water per cup";


        canvas.drawText("RECIPE",(canvasWidth/2), 1000, scorePaint);

        canvas.drawText(s_lemonsPerCup, (canvasWidth/2) -250, 1100, scorePaint);
        canvas.drawText(s_sugarPerCup, (canvasWidth/2) -250, 1300, scorePaint);
        canvas.drawText(s_waterPerCup, (canvasWidth/2) -250, 1500, scorePaint);


        s_flavours = "Flavour: sweet";
        s_effects = "Effect: happy";
        canvas.drawText(s_flavours, (canvasWidth/2) -450, 1800, scorePaint);
        canvas.drawText(s_effects, (canvasWidth/2) +50, 1800, scorePaint);






        decrementY = 400 - 100;
        incrementY = 400 - 100;

        int a = incrementX;
        int b = incrementY;

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(a);
        System.out.println(b);


        canvas.drawBitmap(decrementButton, pricingpercup_x, pricingpercup_y, null);
        canvas.drawBitmap(incrementButton,  incrementX, incrementY, null);



        decrementY = 1100 - 100;
        incrementY = 1100 - 100;
        canvas.drawBitmap(decrementButton, decrementX, decrementY, null);
        canvas.drawBitmap(incrementButton, incrementX, incrementY, null);

        decrementY = 1300 - 100;
        incrementY = 1300 - 100;
        canvas.drawBitmap(decrementButton, decrementX, decrementY, null);
        canvas.drawBitmap(incrementButton, incrementX, incrementY, null);

        decrementY = 1500 - 100;
        incrementY = 1500 - 100;
        canvas.drawBitmap(decrementButton, decrementX, decrementY, null);
        canvas.drawBitmap(incrementButton, incrementX, incrementY, null);


        canvas.drawBitmap(homeButton, homeButtonX, homeButtonY, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                if( x > homeButtonX && x < homeButtonX + homeButtonWidth && y > homeButtonY && y < homeButtonY + homeButtonHeight ) {

                    Toast.makeText(getContext(), "Return to MainMenu", Toast.LENGTH_SHORT).show();
                    Intent intent_EndDay = new Intent(getContext(), MainMenuActivity.class);
                    //intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > pricingpercup_x && x < pricingpercup_x + changebuttonwidth && y > pricingpercup_y && y < pricingpercup_y + changebuttonheight ) {

                    recipePricingGameObject.dodecrement();

                }





                return true;
        }





        return false;
    }


}
