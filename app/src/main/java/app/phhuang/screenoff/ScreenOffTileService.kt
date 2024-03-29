package app.phhuang.screenoff

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import android.util.Log

@RequiresApi(Build.VERSION_CODES.N)
class ScreenOffTileService : TileService() {
    private fun updateTile() {
        val tile = qsTile
        tile?.let {
            it.state = Tile.STATE_ACTIVE
            it.label = getString(R.string.qs_tile_label)
            it.updateTile()
        }
    }

    override fun onTileAdded() {
        Log.i("debug", "on tile added")
    }

    override fun onTileRemoved() {
        Log.i("debug", "on tile removed")
    }

    override fun onStartListening() {
        Log.i("debug", "on tile start listening")

        updateTile()
    }

    override fun onStopListening() {
        Log.i("debug", "on tile stop listening")
    }

    override fun onClick() {
        Log.i("debug", "on click")

        sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

        val intentMainActivity = Intent(this, MainActivity::class.java)
        intentMainActivity.flags = FLAG_ACTIVITY_NEW_TASK
        startActivity(intentMainActivity)
    }
}