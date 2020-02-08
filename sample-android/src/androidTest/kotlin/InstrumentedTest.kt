import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.michallaskowski.kuiks.sample.sharedTests.TestExample
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("dev.michallaskowski.kuiks.sample.android", appContext.packageName)
    }

    @Test
    fun runSharedTest() {
        TestExample().testOne()
    }
}