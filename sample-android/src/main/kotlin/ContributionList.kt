package dev.michallaskowski.kuiks.sample.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.michallaskowski.kuiks.sample.android.api.GitHubService
import dev.michallaskowski.kuiks.sample.android.api.createService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.contribution_list.*

class ContributionList : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    private lateinit var service: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseUrl = intent.getStringExtra("contributors_url") ?: "https://api.github.com/"
        service = createService(baseUrl)

        setContentView(R.layout.contribution_list)
        setResponseText()
    }

    private fun setResponseText() {
        service
            .contributors("michallaskowski", "kuiks")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                label.text= it.joinToString(separator = ", ") { it.login }
            }, {
                label.text = "Error"
                it.printStackTrace()
            }).addTo(disposables)
    }
}