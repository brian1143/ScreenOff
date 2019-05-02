package app.phhuang.screenoff

import android.app.admin.DevicePolicyManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun onButtonClicked(view: View) {
        when (view.id) {
            R.id.btn_uninstall -> {
                Log.i("debug", "on button uninstall clicked")
                val devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
                val componentName = ComponentName(this, AdminReceiver::class.java)
                if (devicePolicyManager.isAdminActive(componentName)) {
                    Log.i("debug", "admin is active")
                    devicePolicyManager.removeActiveAdmin(componentName)
                } else {
                    Log.i("debug", "admin is inactive")
                    val intent = Intent(Intent.ACTION_DELETE, Uri.parse("package:$packageName"))
                    startActivity(intent)
                }
                finish()
            }
            R.id.btn_rate -> {
                val uri = Uri.parse("market://details?id=$packageName")
                val goToMarketIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                    val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                    } else {
                        Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                    }
                    addFlags(flags)
                }
                try {
                    startActivity(goToMarketIntent)
                } catch (e: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }
            }
            R.id.btnMore -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Pai-Hsiang,+Huang"))
                startActivity(browserIntent)
            }
        }
    }
}