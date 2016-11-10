# retest
ReTest is a JUnit extension perform Randomic Test with ease and simplicity. It supports method parameters with Test annotation and much more.

## 1.	Introduction
ReTest is an extension of JUnit that enable to application of the TDD in algorithms with non-deterministic characteristics.
The ReTest framework to makes possible the repetition of the same test scenario, with different seeds for random data generation, to increase test coverage. From the result of these repetitions, the framework stores the seeds that have generated failures and uses them in future tests, ensuring that a scenario where an error was detected in the past is always executed again.

## 2.	API Information for to use

The first step is to add the library file in your project. The library can be downloaded through the URL: https://github.com/andreivo/retest/blob/master/binaries/ReTest-0.1.0-SNAPSHOT.jar

To use ReTest simply create a test project using JUnit 4, and include the @RunWith annotation with ReTestRunner.class argument in the test class.
In the test methods, you need include configuration annotations on the method and on the parameters that you want to be generated and managed by the tool. These parameters are then used as input data in the tests. Bellow is a simple example of use.

```java
@RunWith(ReTestRunner.class)
public class TestClazz {
	@Test
	@ReTest(10)
	@SaveBrokenTestDataFiles(filePath = "/tmp/file1.csv")
	@LoadTestFromDataFiles(filePath = "/tmp/file1.csv")
	public void testMethod(@RandomParam Random random){
		Object result = nonDeterministicAlgorithm(random);
		assertResult(result);
	}
}
```





### [MIT Licensed](LICENSE)
