package com.example.desugar_issues_sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {
    private lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        splitInstallManager = SplitInstallManagerFactory.create(this)
        startFeatureModuleActivity()
    }

    private fun startFeatureModuleActivity() {
        val intent = Intent("android.intent.action.VIEW")
        intent.setClassName(this, "com.example.dynamicfeature.FeatureActivity")
        if (isDynamicFeatureInstalled("dynamicfeature")) {
            startActivity(intent)
        } else {
            triggerDynamicFeatureInstall("dynamicfeature")
            waitAndStartActivity(intent)
        }
    }

    private fun isDynamicFeatureInstalled(featureName: String): Boolean {
        return splitInstallManager.installedModules.contains(featureName)
    }

    private fun triggerDynamicFeatureInstall(featureName: String) {
        val request = SplitInstallRequest.newBuilder()
            .addModule(featureName)
            .build()

        splitInstallManager.startInstall(request)
    }

    private fun waitAndStartActivity(intent: Intent) {
        splitInstallManager.registerListener { session ->
            if (session.status() == SplitInstallSessionStatus.INSTALLED) {
                startActivity(intent)
            }
        }
    }
}