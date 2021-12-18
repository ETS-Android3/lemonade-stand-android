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


public class MainMenuView extends View {

    private int canvasWidth, canvasHeight;

    private Bitmap bg, ubg;
    private int bgX = 0, bgY = 0, bgW = 1100, bgH = 800;

    private Bitmap singleplayer, usp;
    private int spX = 100, spY = 800, spWidth = 400, spHeight = 300;

    private Bitmap multiplayer, ump;
    private int mpX = 700, mpY = 800, mpWidth = 300, mpHeight = 300;

    private Bitmap market, umm;
    private int marketX = 700, marketY = 1600, marketWidth = 300, marketHeight = 300;

    private Bitmap store, ust;
    private int storeX = 200, storeY = 1600, storeWidth = 300, storeHeight = 300;

    private Bitmap settings, usx;
    private int settingsX = 900, settingsY = 50, settingsWidth = 100, settingsHeight = 100;

    private Bitmap button_Inventory, uiv;
    private int button_InventoryX = 700, button_InventoryY = 1200, button_InventoryWidth = 300, button_InventoryHeight = 300;

    private Bitmap button_RecipePricing, urp;
    private int button_RecipePricingX = 200, button_RecipePricingY = 1200, button_RecipePricingWidth = 300, button_RecipePricingHeight = 300;


    private String s_Player_Name = "Player-name-placeholder";
    private String s_Player_Balance = "Balance: $ null";

    //goes to profile?
    private Bitmap uav;
    private Bitmap avatar;
    private int avatarX = 550, avatarY = 300, avatarWidth = 100, avatarHeight = 200;


    private Bitmap ull;
    private Bitmap stall;
    private int stallX = 350, stallY = 300, stallWidth = 100, stallHeight = 200;



    private Bitmap homeButton;
    private Bitmap unscaledhome;
    private int homeButtonX = 20, homeButtonY = 20, homeButtonWidth = 200, homeButtonHeight = 200;


    private Paint scorePaint = new Paint();


    GameObject mmGameObject = new GameObject();
    Player mmPlayer = new Player();

    public MainMenuView(Context context) {
        super(context);

        ubg = BitmapFactory.decodeResource(getResources(), R.drawable.location1);
        bg = Bitmap.createScaledBitmap(ubg, bgW, bgH, false);

        unscaledhome = BitmapFactory.decodeResource(getResources(), R.drawable.lemonlogo);
        homeButton = Bitmap.createScaledBitmap(unscaledhome, homeButtonWidth, homeButtonHeight, false);

        usp = BitmapFactory.decodeResource(getResources(), R.drawable.campaignbutton);
        singleplayer = Bitmap.createScaledBitmap(usp, spWidth, spHeight, false);


        ump = BitmapFactory.decodeResource(getResources(), R.drawable.multiplayerbutton);
        multiplayer = Bitmap.createScaledBitmap(ump, mpWidth, mpHeight, false);


        umm = BitmapFactory.decodeResource(getResources(), R.drawable.marketbutton);
        market = Bitmap.createScaledBitmap(umm, marketWidth, marketHeight, false);


        ust = BitmapFactory.decodeResource(getResources(), R.drawable.store_icon);
        store = Bitmap.createScaledBitmap(ust, storeWidth, storeHeight, false);



        usx = BitmapFactory.decodeResource(getResources(), R.drawable.settings);
        settings = Bitmap.createScaledBitmap(usx, settingsWidth, settingsHeight, false);


        uiv = BitmapFactory.decodeResource(getResources(), R.drawable.lemons);
        button_Inventory = Bitmap.createScaledBitmap(uiv, button_InventoryWidth, button_InventoryHeight, false);


        urp = BitmapFactory.decodeResource(getResources(), R.drawable.mixture);
        button_RecipePricing = Bitmap.createScaledBitmap(urp, button_RecipePricingWidth, button_RecipePricingHeight, false);


        uav = BitmapFactory.decodeResource(getResources(), R.drawable.sellerpic);
        avatar = Bitmap.createScaledBitmap(uav, avatarWidth, avatarHeight, false);


        ull = BitmapFactory.decodeResource(getResources(), R.drawable.lemonadestandstall);
        stall = Bitmap.createScaledBitmap(ull, stallWidth, stallHeight, false);



        mmPlayer.readFromFile(context);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bg, bgX, bgY, null);

        s_Player_Name = "" + mmPlayer.getS_PString();
        s_Player_Balance = "Bal: $" + mmGameObject.getBalance();


        canvas.drawText(s_Player_Name, 300, 100, scorePaint);
        canvas.drawText(s_Player_Balance, 300, 200, scorePaint);


        canvas.drawBitmap(singleplayer, spX, spY, null);
        canvas.drawBitmap(multiplayer, mpX, mpY, null);

        canvas.drawBitmap(market, marketX, marketY, null);
        canvas.drawText("Market", marketX, marketY, scorePaint);

        canvas.drawBitmap(store, storeX, storeY, null);
        canvas.drawText("Store", storeX, storeY, scorePaint);

        canvas.drawBitmap(settings, settingsX, settingsY, null);

        canvas.drawBitmap(button_Inventory, button_InventoryX, button_InventoryY, null);
        canvas.drawText("Inventory", button_InventoryX, button_InventoryY, scorePaint);

        canvas.drawBitmap(button_RecipePricing, button_RecipePricingX, button_RecipePricingY, null);
        canvas.drawText("Recipe/Pricing", button_RecipePricingX, button_RecipePricingY, scorePaint);

        canvas.drawBitmap(avatar, avatarX, avatarY, null);
        canvas.drawBitmap(stall, stallX, stallY, null);



        canvas.drawBitmap(homeButton, homeButtonX, homeButtonY, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if( x > spX && x < spX + spWidth && y > spY && y < spY + spHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), SingleplayerActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > marketX && x < marketX + marketWidth && y > marketY && y < marketY + marketHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), MarketActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > storeX && x < storeX + storeWidth && y > storeY && y < storeY + storeHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), StoreActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > settingsX && x < settingsX + settingsWidth && y > settingsY && y < settingsY + settingsHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), SettingsActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > button_InventoryX && x < button_InventoryX + button_InventoryWidth && y > button_InventoryY && y < button_InventoryY + button_InventoryHeight ) {

                    //inventory activity
                    Intent intent_EndDay = new Intent(getContext(), MainMenuActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                if( x > button_RecipePricingX && x < button_RecipePricingX + button_RecipePricingWidth && y > button_RecipePricingY && y < button_RecipePricingY + button_RecipePricingHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), RecipePricingActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }

                //player activity??
                if( x > avatarX && x < avatarX + avatarWidth && y > avatarY && y < avatarY + avatarHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), MainMenuActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }







                if( x > mpX && x < mpX + mpWidth && y > mpY && y < mpY + mpHeight ) {

                    Intent intent_EndDay = new Intent(getContext(), SingleplayerActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }






                if( x > homeButtonX && x < homeButtonX + homeButtonWidth && y > homeButtonY && y < homeButtonY + homeButtonHeight ) {

                    Toast.makeText(getContext(), "Return to MainMenu", Toast.LENGTH_SHORT).show();
                    Intent intent_EndDay = new Intent(getContext(), MainMenuActivity.class);
                    intent_EndDay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    getContext().startActivity(intent_EndDay);

                }


        }
        return false;
    }
}
