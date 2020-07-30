import dev.michallaskowski.kuiks.sample.sharedTests.TestListBehavior
import junit.framework.JUnit4TestAdapter
import junit.framework.TestSuite
import org.junit.runner.RunWith
import org.junit.runners.AllTests

@RunWith(AllTests::class)
class TestsFactoryTests {

    // based on https://stackoverflow.com/questions/18559831/how-can-i-run-a-junit-testsuite-created-by-a-factory-method
    // and https://stackoverflow.com/questions/3257080/how-do-i-dynamically-create-a-test-suite-in-junit-4
    companion object {

        @JvmStatic
        fun suite(): TestSuite {
            val testSuite = TestSuite()
            val testClasses = listOf(TestListBehavior::class, ContributorsTests::class)
            testClasses.forEach {
                testSuite.addTest(JUnit4TestAdapter(it.java))
            }
            return testSuite
        }
    }
}