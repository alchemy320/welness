package com.example.swala_wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        loadInterstitialAd()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val healthy_tips = findViewById<Button>(R.id.healthy_tips)
        val nutrition_advice = findViewById<Button>(R.id.nutition_advice)
        val medication = findViewById<Button>(R.id.meditation)
        val hydrationAlert=findViewById<Button>(R.id.hydration_alert)
        val startExercise=findViewById<Button>(R.id.start_exercise)
        val dailyMotivation=findViewById<Button>(R.id.daily_motivation)
        val weeklyGoals=findViewById<Button>(R.id.weekly_goals)
        val checkProgress=findViewById<Button>(R.id.wcheck_progress)

        healthy_tips.setOnClickListener {
            val intent = Intent(applicationContext, Healthy_Tips::class.java)
            startActivity(intent)
            showInterstitialAd()
        }
        nutrition_advice.setOnClickListener {
            val intent = Intent(applicationContext, Nutrition_Advice::class.java)
            startActivity(intent)
            showInterstitialAd()
        }
        medication.setOnClickListener {
            val intent = Intent(applicationContext,medication::class.java)
            startActivity(intent)
        }
        hydrationAlert.setOnClickListener {
            val intent = Intent(applicationContext,hydrationAlert::class.java)
            startActivity(intent)
        }
        startExercise.setOnClickListener {
            val intent= Intent(applicationContext,startExercise::class.java)
            startActivity(intent)
        }
        dailyMotivation.setOnClickListener {
            val intent= Intent(applicationContext,dailyMotivation::class.java)
            startActivity(intent)
        }
        weeklyGoals.setOnClickListener {
            val intent= Intent(applicationContext,weeklyGoals::class.java)
            startActivity(intent)
        }
        checkProgress.setOnClickListener {
            val intent= Intent(applicationContext,checkProgress::class.java)
            startActivity(intent)
        }

    }


    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Function checks if ad already running not to run anothet one and overlap - which is wrong
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}