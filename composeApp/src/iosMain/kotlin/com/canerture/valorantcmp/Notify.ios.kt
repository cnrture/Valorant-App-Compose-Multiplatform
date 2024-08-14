package com.canerture.valorantcmp

import androidx.compose.runtime.Composable
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UINavigationController
import platform.UIKit.UITabBarController
import platform.UIKit.UIViewController

@Composable
internal actual fun Notify(message: String, onDismiss: () -> Unit) {
    val topViewController = getTopViewController()

    topViewController?.let { viewController ->
        val alert = UIAlertController.alertControllerWithTitle(
            "Something went wrong!",
            message,
            UIAlertControllerStyleAlert,
        )
        alert.addAction(
            UIAlertAction.actionWithTitle(
                "OK",
                UIAlertActionStyleDefault,
                handler = { onDismiss() },
            )
        )
        viewController.presentViewController(alert, animated = true, completion = null)
    }
}

fun getTopViewController(rootViewController: UIViewController? = null): UIViewController? {
    val rootVC = rootViewController ?: UIApplication.sharedApplication.keyWindow?.rootViewController
    return when {
        rootVC == null -> null
        rootVC.presentedViewController != null -> getTopViewController(rootVC.presentedViewController)
        rootVC is UINavigationController -> rootVC.visibleViewController
        rootVC is UITabBarController -> rootVC.selectedViewController
        else -> rootVC
    }
}