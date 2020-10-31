package me.rajatdhamija.rsod

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_red_screen_of_death.*
import timber.log.Timber

@SuppressLint("SetTextI18n")
internal class RedScreenOfDeathActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_red_screen_of_death)

        supportActionBar?.hide()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.holo_red_light)
            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.holo_red_light)
        }

        val threadName = requireNotNull(intent.getStringExtra(THREAD))
        val throwable = intent.getSerializableExtra(THROWABLE) as Throwable

        renderException(threadName, throwable)
        setupShareButton(threadName, throwable)
        logException(throwable)
    }

    private fun renderException(threadName: String, throwable: Throwable) {
        textThreadName.text = "App crashed in $threadName thread"
        textException.text = throwable.javaClass.simpleName
        textStackTrace.text = throwable.stackTraceToString()
        textStackTrace.movementMethod = ScrollingMovementMethod()
    }

    private fun setupShareButton(threadName: String, throwable: Throwable) {
        val appDate = requireNotNull(intent.getParcelableExtra<AppData>(APP_DATA))

        shareButton.setOnClickListener {
            val sendIntent = Intent().apply {
                action = ACTION_SEND
                putExtra(
                    EXTRA_TEXT,
                    Utils.prepareShareData(appDate, threadName, throwable)
                )
                type = "text/plain"
            }
            val shareIntent = createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    private fun logException(throwable: Throwable) {
        Timber.e("-----------Exception caught by Red Screen Of Death -----------")
        Timber.e(throwable, throwable.javaClass.simpleName)
        Timber.e("--------------------------------------------------------------")
    }

    companion object {
        private const val APP_DATA = "me.rajatdhamija.redscreenofdeath.APP_DATA"
        private const val THROWABLE = "me.rajatdhamija.redscreenofdeath.THROWABLE"
        private const val THREAD = "me.rajatdhamija.redscreenofdeath.THREAD"

        fun newIntent(
            context: Context,
            threadName: String,
            throwable: Throwable,
            appData: AppData,
        ) = Intent(context, RedScreenOfDeathActivity::class.java)
            .apply {
                putExtra(THREAD, threadName)
                putExtra(THROWABLE, throwable)
                putExtra(APP_DATA, appData)
                flags =
                    FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NO_ANIMATION
            }
    }
}
