package app.phhuang.screenoff

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class AdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context?, intent: Intent?) {
        Log.i("debug", "on enabled")
        val devicePolicyManager = context?.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        devicePolicyManager.lockNow()
    }

    override fun onDisabled(context: Context?, intent: Intent?) {
        Log.i("debug", "on disabled")
        val uninstallIntent = Intent(Intent.ACTION_DELETE, Uri.parse("package:${context?.packageName}"))
        context?.startActivity(uninstallIntent)
    }
}