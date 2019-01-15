package app.phhuang.screenoff

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class UninstallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uninstall)
    }

    fun onUninstallClicked(view: View) {
        Log.i("debug", "on uninstall clicked")
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
}