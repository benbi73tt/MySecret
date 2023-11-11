package ru.home.mysecrets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    private val tokenViewModel: TokenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        //устанавливаем нашу основную тему после срабатывания splash screen
//        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_main)
        navGraph.setStartDestination(R.id.authorization_main)
        navController.graph = navGraph

        //Проверяем авторизован ли пользователь,
        //в качестве кого необходимо войти и направляем на соответствующий экран
//        tokenViewModel.authUserData.observe(this) { authorisationData ->
//            authorisationData.accessToken?.let {
//                when (authorisationData.getRole()) {
//                    PARENT -> {
//                        navGraph.setStartDestination(R.id.mainFlowFragment)
//                    }
//
//                    CHILD -> {
//                        navGraph.setStartDestination(R.id.childrenFlowFragment)
//                    }
//
//                    else -> {
//                        navGraph.setStartDestination(R.id.signFlowFragment)
//                    }
//                }
//            }
//                ?: navGraph.setStartDestination(R.id.signFlowFragment)
//            navController.graph = navGraph
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onSupportNavigateUp()
    }
}