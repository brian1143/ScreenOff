package app.phhuang.screenoff

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast

class AdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        Log.i("debug", "on enabled")
        val devicePolicyManager = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        devicePolicyManager.lockNow()
    }

    override fun onDisabled(context: Context, intent: Intent) {
        Log.i("debug", "on disabled")
        val uninstallIntent = Intent(Intent.ACTION_DELETE, Uri.parse("package:${context.packageName}"))
        try {
            context.startActivity(uninstallIntent)
        } catch (e: Exception) {
            Toast.makeText(context, R.string.toast_ready_to_remove, Toast.LENGTH_SHORT).show()
        }
    }
}