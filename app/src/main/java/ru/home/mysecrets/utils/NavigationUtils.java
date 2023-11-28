package ru.home.mysecrets.utils;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;

public abstract class NavigationUtils {
    /**
     * This function will check navigation safety before starting navigation using direction
     *
     * @param navController NavController instance
     * @param resId     navigation operation
     */
    public static void navigateSafe(NavController navController, @IdRes int resId) {
        NavDestination currentDestination = navController.getCurrentDestination();

        if (currentDestination != null) {
            NavAction navAction = currentDestination.getAction(resId);

            if (navAction != null) {
                int destinationId = navAction.getDestinationId();

                NavGraph currentNode;
                if (currentDestination instanceof NavGraph)
                    currentNode = (NavGraph) currentDestination;
                else
                    currentNode = currentDestination.getParent();

                if (destinationId != 0 && currentNode != null && currentNode.findNode(destinationId) != null) {
                    navController.navigate(resId);
                }
            }
        }
    }


    /**
     * This function will check navigation safety before starting navigation using resId and args bundle
     *
     * @param navController NavController instance
     * @param resId         destination resource id
     * @param args          bundle args
     */
    public static void navigateSafe(NavController navController, @IdRes int resId, Bundle args) {
        NavDestination currentDestination = navController.getCurrentDestination();

        if (currentDestination != null) {
            NavAction navAction = currentDestination.getAction(resId);

            if (navAction != null) {
                int destinationId = navAction.getDestinationId();

                NavGraph currentNode;
                if (currentDestination instanceof NavGraph)
                    currentNode = (NavGraph) currentDestination;
                else
                    currentNode = currentDestination.getParent();

                if (destinationId != 0 && currentNode != null
                        && currentNode.findNode(destinationId) != null) {
                    navController.navigate(resId, args);
                }
            }
        }
    }
}
