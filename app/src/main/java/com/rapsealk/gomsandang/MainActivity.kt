package com.rapsealk.gomsandang

import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapsealk.gomsandang.data.AppItem
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by rapsealk on 2020/08/02.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        private val SUSPECTED_PACKAGES = listOf(
            "com.ss.android.ugc.trill", // TikTok
            "com.tencent.mm",           // WeChat
            "com.mt.mtxx.mtxx"          // Meitu
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appItemAdapter = AppItemAdapter()
        application_list.adapter = appItemAdapter
        application_list.layoutManager = LinearLayoutManager(this)

        val installedApplications = getInstalledApplications()
        val applications = installedApplications.filter { SUSPECTED_PACKAGES.contains(it.packageName) }
        appItemAdapter.addItems(applications)
        appItemAdapter.notifyDataSetChanged()
    }

    private fun getInstalledApplications(): List<AppItem> {
        val installedPackages = packageManager.getInstalledPackages(0)
        return installedPackages.filterNot {
            it.applicationInfo.flags.and(ApplicationInfo.FLAG_SYSTEM) != 0
        }.map {
            val applicationName = it.applicationInfo.loadLabel(packageManager).toString()
            val icon = it.applicationInfo.loadIcon(packageManager)
            val packages = it.applicationInfo.packageName
            AppItem(applicationName, packages, icon)
        }
    }
}