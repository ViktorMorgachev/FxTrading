package com.learning.lessons.android.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import com.learning.navigation.*
import com.learning.navigation.params.action.ActionParams
import com.learning.navigation.params.screens.ScreenParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouterImpl @Inject constructor(
    private val screenResolver: ScreenResolver,
    private val actionResolver: ActionResolver,
    private val resultContainer: ResultContainer,
    private val context: Context
) : Router {

    private var navController: NavController? = null
    private var navControllerInside: NavController? = null
    private var activity: Activity? = null

    override fun onCreate(activity: ComponentActivity) {
        actionResolver.registerResultListener(activity)
    }

    override fun bind(activity: Activity, containerID: Int) {
        this.navController = activity.findNavController(containerID)
        this.activity = activity
    }

    override fun bind(controller: NavHostController, activity: Activity) {
        this.navController = controller
        this.activity = activity
    }

    override fun navigate(data: ScreenParams, sharedElements: Map<Any, String>?) {
        screenResolver.navigate(this.navController, data, sharedElements)
    }

    override fun navigate(idContainer: Int, fragment: Fragment, data: ScreenParams, sharedElements: Map<Any, String>?) {

    }

    override fun execute(data: ActionParams) {
        actionResolver.execute(activity, data)
    }

    override fun setResultListener(key: String, listener: ResultListener) {
        resultContainer.setResultListener(key, listener)
    }

    override fun sendResult(key: String, data: Any?) {
        resultContainer.sendResult(key, data)
    }

    override fun back() {
        navController?.navigateUp()
    }

    override fun getMainStartIntent(): Intent {
        TODO("Not yet implemented")
    }

}