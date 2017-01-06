# ReTest
ReTest is a JUnit extension perform Randomic Test with ease and simplicity. It supports method parameters with Test annotation and much more.

## 1.	Introduction
ReTest is an extension of JUnit that enable to application of the TDD in algorithms with non-deterministic characteristics.
The ReTest framework to makes possible the repetition of the same test scenario, with different seeds for random data generation, to increase test coverage. From the result of these repetitions, the framework stores the seeds that have generated failures and uses them in future tests, ensuring that a scenario where an error was detected in the past is always executed again.

## 2.	API Information

The first step is to add the library file in your project.

### If you use Maven:
Add in your pom.xml file
```xml
<dependency>
  <groupId>com.github.andreivo</groupId>
  <artifactId>retest</artifactId>
  <version>1.0</version>
</dependency>
```
### If you prefer download of file:
The library can be downloaded in [ReTest](https://oss.sonatype.org/content/groups/staging/com/github/andreivo/retest/1.0/).

### 2.1 To use:
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
In the above code, the test method is marked with the annotation @ReTest(10) that indicate this method will be run 10 times. 

At each execution, the framework will initialize the Random parameter, received by the test method, with a different seed. Note that this object is send to the method being tested, in this case nonDeterministicAlgorithm(), which will use it internally to generate its random numbers and, consequently, as a basis for its non-deterministic decisions. 

The assertResult() method checks whether the return of the algorithm is considered valid. Running the test multiple times with Random initialized with different seeds will allow for validation in a larger number of scenarios.

The seeds used in the tests that fail will be stored in the file "tmp / file1.csv", because the test method is marked with the @SaveBrokenTestDataFiles annotation.

When executed again, in addition to the ten repetitions configured by the @ReTest annotation, the test method will also run with the seeds stored in the "tmp / file1.csv" file, which is configured by the @LoadTestFromDataFiles annotation. Running the fail tests again, you can check that the error has been corrected, in addition to maintaining a set of regression tests.

The idea is that, like in TDD, the tests are executed frequently, and at all the cicle of development the executions of test achieve good code coverage. This is reinforced by the fact that the tests that have failed previously are always executed again.

## 3.	Features
The ReTest framework has a very intuitive API that allows you to:

1. generate randomic data to be applied to the tests;
2. create custom randomizers for data in the application domain;
3. save the data from failed tests;
4. save test data that has been successfully executed;
5. save the return of the test method to generate a set of data based on random inputs and expected outputs;
6. load test data from external files or sources;
7. create custom mechanisms for handling external sources, both for saving and loading test data.

### 3.1.	ReTest Annotation Set

In addition to the common JUnit annotations, the ReTest framework has a set of 4 annotations for the test methods and 4 annotations for the method parameters.

The annotations for the methods are:

1. **@ReTest:** This annotation is responsible for performing the test repetition. In this annotation it is possible to indicate how many times the test should be repeated;
2. **@SaveBrokenTestDataFiles:** When you mark a method with this annotation, the input data will be saved to the file when the test fails;
3. **@SaveSuccessTestDataFiles:** When you mark a method with this annotation, the input data will be saved to file when the test passes successfully;
4. **@LoadTestFromDataFiles:** When you mark a method with this annotation, the input data will be loaded from this file.

The annotations for the method parameters are:

1. **@IntegerParam:** Annotation indicates that the ReTest framework return in parameter a random integer;
2. **@RandomParam:** This annotation indicates that the framework must pass an instance of an object of type Random, with a known seed, so that it can be stored and retrieved from files, making it possible to reconstruct the same test scenario;
3. **@SecureRandomParam:** This annotation indicates that the framework must pass an instance of an object of type SecureRandom, with a known seed, so that it can be stored and retrieved from files, making it possible to reconstruct the same test scenario;
4. **@Param:** This annotation allows to indicate custom randomizers for the specific data in the application domain, allowing the extension of the framework for random generation of several types of data.


### [MIT Licensed](LICENSE)
