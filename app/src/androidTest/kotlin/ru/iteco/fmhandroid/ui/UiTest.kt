package ru.iteco.fmhandroid.ui

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.Until
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


const val MODEL_PACKAGE = "ru.iteco.fmhandroid"
const val MODEL_UI = "com.android.systemui"

const val TIMEOUT = 10000L
@RunWith(AndroidJUnit4::class)

class UiTest{
    private lateinit var device: UiDevice
    private lateinit var deviceObject: UiObject
    private fun waitForPackage(packageName: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
    }

    @Before
    fun beforeEachTest() {
        // Press home
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)



    }
    @After
    fun afterEachTest(){




    }
    @Test
    fun restartingTheApplicationAfterSuccessfulAuthorization(){
        val packageName = MODEL_PACKAGE
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "authorization_image_button")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "Log out")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "Login")).setText("login2")
        device.findObject(By.text( "Password")).setText("password2")
        device.findObject(By.text( "SIGN IN")).click()
        Thread.sleep(2000)
        device.pressRecentApps()
        Thread.sleep(2000)
        device.swipe(523,1401, 531, 767, 10)
        Thread.sleep(2000)
        device.pressHome()
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
        Thread.sleep(5000)
        assertTrue(device.wait(Until.hasObject(By.text("News")), 1000))
    }
    @Test
    fun loggingInToTheApplicationAfterUnlogging(){
        val packageName = MODEL_PACKAGE
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)

        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "authorization_image_button")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "Log out")).click()
        Thread.sleep(5000)
        device.pressRecentApps()
        Thread.sleep(2000)
        device.swipe(523,1401, 531, 767, 10)
        Thread.sleep(2000)
        device.pressHome()
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
        Thread.sleep(5000)
        assertTrue(device.wait(Until.hasObject(By.text("Authorization")), 1000))
        Thread.sleep(3000)
        device.findObject(By.text( "Login")).setText("login2")
        device.findObject(By.text( "Password")).setText("password2")
        device.findObject(By.text( "SIGN IN")).click()
        Thread.sleep(3000)
    }
    @Test
    fun authorizationInTheApplicationWhenThereIsNoInternetConnection(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "authorization_image_button")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "Log out")).click()
        Thread.sleep(2000)
        device.openQuickSettings()
        device.findObject(By.text( "AndroidWifi")).click()
        device.findObject(By.text( "Mobile data")).click()
        device.pressBack()
        device.pressBack()
        device.findObject(By.text( "Login")).setText("login2")
        device.findObject(By.text( "Password")).setText("password2")
        device.findObject(By.text( "SIGN IN")).click()
        Thread.sleep(5000)
        assertTrue(device.wait(Until.hasObject(By.text("Authorization")), 1000))
        device.openQuickSettings()
        device.findObject(By.text( "Wi-Fi")).click()
        device.findObject(By.text( "Mobile data")).click()
        device.pressBack()
        device.pressBack()
        Thread.sleep(7000)
        device.findObject(By.text( "SIGN IN")).click()
        Thread.sleep(7000)

    }
    @Test
    fun navigatingByNavigationArrowToTheMainPage(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.text( "ALL CLAIMS")).click()
        Thread.sleep(2000)
        device.pressBack()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.hasObject(By.text("ALL CLAIMS")), 1000))
    }

    @Test
    fun navigatingByNavigationArrowToThePageWithApplications(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.text( "ALL CLAIMS")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "Plan date")).click()
        Thread.sleep(2000)
        device.pressBack()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.hasObject(By.res(packageName, "filters_material_button")), 1000))
    }
    @Test
    fun clickOnThePrivacyPolicyLink(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "main_menu_image_button")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "About")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "https://vhospice.org/#/privacy-policy/")).click()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.hasObject(By.text("https://vhospice.org/#/privacy-policy/")), 1000))
    }
    @Test
    fun clickOnTheTermsOfUseLink(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "main_menu_image_button")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "About")).click()
        Thread.sleep(2000)
        device.findObject(By.text( "https://vhospice.org/#/terms-of-use")).click()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.hasObject(By.text("https://vhospice.org/#/terms-of-use")), 1000))
    }
    @Test
    fun newsMinimize(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.res(packageName, "view_news_item_image_view")).click()
        Thread.sleep(2000)
        device.findObject(By.res(packageName, "view_news_item_image_view")).click()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.gone(By.res(packageName, "news_item_description_text_view" )), 1000))


    }
    @Test
    fun newsMinimizeOnAllNews(){
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)
        Thread.sleep(5000)
        device.findObject(By.text( "ALL NEWS")).click()
        Thread.sleep(2000)
        device.findObject(By.res(packageName, "view_news_item_image_view")).click()
        Thread.sleep(2000)
        device.findObject(By.res(packageName, "view_news_item_image_view")).click()
        Thread.sleep(2000)
        assertTrue(device.wait(Until.gone(By.res(packageName, "news_item_description_text_view" )), 1000))
    }
}