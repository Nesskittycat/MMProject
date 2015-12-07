package com.money.mmproject;

import com.parse.Parse;

public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //This will only be called once in your app's entire lifecycle.
        Parse.initialize(this,
                "sb86WYyd0l1igfZY77sTtEWb0TVn3g067JORyVT6",
                "uQTRuM7tHPVzgctBOYQO6LmFpSBCNbAKYaFS8OmA");
    }
}