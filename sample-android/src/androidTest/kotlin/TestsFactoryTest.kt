import dev.michallaskowski.kuiks.sample.sharedTests.TestExample
import junit.framework.Test
import junit.framework.TestResult
import junit.framework.TestSuite
import org.junit.runner.RunWith
import org.junit.runners.AllTests

@RunWith(AllTests::class)
class TestsFactoryTests {

    // based on https://stackoverflow.com/questions/18559831/how-can-i-run-a-junit-testsuite-created-by-a-factory-method

    companion object {

        @JvmStatic
        fun suite(): TestSuite {
            val testSuite = TestSuite()
            testSuite.addTest(TestFactoryExample())
            return testSuite
        }
    }
}

class TestFactoryExample: Test {
    override fun run(result: TestResult?) {
        result?.startTest(this)
        TestExample().testOne()
        result?.endTest(this)
    }

    override fun countTestCases(): Int {
        return 1
    }

}