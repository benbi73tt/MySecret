package ru.home.mysecrets.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.home.mysecrets.R
import ru.home.mysecrets.data.BuildConfig
import ru.home.mysecrets.extensions.launchMain


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: TokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        //устанавливаем нашу основную тему после срабатывания splash screen
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        lifecycleScope.launchMain(
            safeAction = {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    //Приостанавливает выполнение сопрограммы до завершения задания.
                    viewModel.checkAuth().join()
                    viewModel.token.collect {
                        startNavigation(it)
                    }
                }
            }, onError = {
                if (BuildConfig.DEBUG) {
                    Toast.makeText(
                        this,
                        "MainActivity ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    startNavigation(String())
                }
            })
    }

    private fun startNavigation(startScreen: String) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_main)

        if (startScreen.isBlank()) {
            navGraph.setStartDestination(R.id.authorization_main)
        } else {
            //Проверить на подлинность токена
            navGraph.setStartDestination(R.id.first_fragment)
        }
        navController.graph = navGraph
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onSupportNavigateUp()
    }
}
