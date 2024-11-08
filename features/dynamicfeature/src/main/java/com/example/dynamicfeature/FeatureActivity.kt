package com.example.dynamicfeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.apache.avro.Schema

class FeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Schema.Parser().parse("{\"type\":\"record\"}")
    }
}
