/*
* @JT Kelly 
* <p> Ratio Class Tester
* <p> Project 4
* <p> Tests ratios
* 
*/
import java.util.ArrayList;

public class EntryPoint
{
	private static final int CONSTRUCTOR = 1;
	private static final int CONSTRUCTOR_LONG = 2;
	private static final int CONSTRUCTOR_LONG_LONG = 3;
	private static final int GET_DENOMINATOR = 4;
	private static final int GET_NUMERATOR = 5;
	private static final int ADD = 6;
	private static final int SUBTRACT = 7;
	private static final int MULTIPLY = 8;
	private static final int DIVIDE = 9;

	private static final int TEST_MIN = CONSTRUCTOR;
	private static final int TEST_MAX = DIVIDE;
	private static final int MAX_NUM_TESTS = TEST_MAX + 1;
	private static int TEST = 0;
	private static int TEST_CASE = 0;
	private static int TEST_CASES_FAILED = 0;
	private static boolean TEST_PASSED = false;

	private static ArrayList parseCommandLine(String[] args)
	{
		ArrayList tests = new ArrayList();
		boolean perform_all = false;

		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals("all"))
			{
				perform_all = true;
			}
		}

		if (perform_all)
		{
			System.out.println("All tests specified to be executed.");

			for (int i = TEST_MIN; i <= TEST_MAX; i++)
			{
				tests.add(new Integer(i));
			}
		}
		else
		{
			System.out.println(args.length + " command line arguments specified to be executed.");

			for (int i = 0; i < args.length; i++)
			{
				int test = new Integer(args[i]).intValue();

				System.out.print(test + " ");

				if (test >= TEST_MIN && test <= TEST_MAX)
				{
					tests.add(new Integer(test));
				}
			}
			System.out.println();
		}

		System.out.println(tests.size() + " valid tests specified to be executed.");

		return tests;
	}

	private static void dump_error_message(String message)
	{
		System.out.println("<" + TEST + "," + TEST_CASE + "> " + message);
		TEST_PASSED = false;
		TEST_CASES_FAILED++;
	}

	private static void checkContents(String contents, String test_contents)
	{
		if (!contents.equals(test_contents))
		{
			dump_error_message(" expected [" + test_contents + "] found [" + contents + "]");
		}
		TEST_CASE++;
	}

	public static void main(String args[])
	{
		// ArrayList tests = parseCommandLine(args[]);
		ArrayList tests = parseCommandLine(new String[]
		{ "all" });
		boolean[] TestResults = new boolean[MAX_NUM_TESTS];

		// initialize test results array
		for (int i = 0; i <= TEST_MAX; i++)
		{
			TestResults[i] = false;
		}

		int total_test_cases = 0;
		int total_failed_test_cases = 0;

		for (int i = TEST_MIN; i <= TEST_MAX; i++)
		{
			TEST_PASSED = true;
			TEST_CASES_FAILED = 0;

			if (tests.contains(new Integer(i)))
			{
				TEST = i;
				TEST_CASE = 0;

				System.out.println("\nTest " + TEST + " started");

				switch (i)
				{
				case CONSTRUCTOR:
					System.out.println("CONSTRUCTOR");
					testConstructor();
					break;

				case CONSTRUCTOR_LONG:
					System.out.println("CONSTRUCTOR_LONG");
					testConstructorLong();
					break;

				case CONSTRUCTOR_LONG_LONG:
					System.out.println("CONSTRUCTOR_LONG_LONG");
					testConstructorLongLong();
					break;

				case GET_DENOMINATOR:
				{
					System.out.println("GET_DENOMINATOR");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG };
					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}
					testGetDenominator();
				}
					break;

				case GET_NUMERATOR:
				{
					System.out.println("GET_NUMERATOR");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG, GET_DENOMINATOR };
					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}

					testGetNumerator();
				}
					break;

				case ADD:
				{
					System.out.println("ADD");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG };

					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}
					testAdd();
				}
					break;

				case SUBTRACT:
				{
					System.out.println("SUBTRACT");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG };
					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}
					testSubtract();
				}
					break;

				case MULTIPLY:
				{
					System.out.println("MULTIPLY");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG };

					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}
					testMultiply();
				}
					break;

				case DIVIDE:
				{
					System.out.println("DIVIDE");
					int[] requiredPassedTests =
					{ CONSTRUCTOR, CONSTRUCTOR_LONG, CONSTRUCTOR_LONG_LONG };

					for (int rpt = TEST_MIN; rpt < requiredPassedTests.length; rpt++)
					{
						if (!TestResults[rpt])
						{
							dump_error_message("Cannot perform this test since test " + rpt + " failed.");
							break;
						}
					}
					testDivide();
				}
					break;

				default:
					System.out.println("Unrecognized test " + i);
				}

				System.out.println("Test " + TEST + " " + (TEST_PASSED ? "passed" : "failed"));

				TestResults[TEST] = TEST_PASSED;

				System.out.println(
						"Test " + TEST + " " + "Failed (" + TEST_CASES_FAILED + " / " + TEST_CASE + ") test cases");
				total_test_cases += TEST_CASE;
				total_failed_test_cases += TEST_CASES_FAILED;

				TEST_CASES_FAILED = 0;
				TEST_CASE = 0;

				System.out.println("Test " + TEST + " ended");
			}
		}

		int failed_count = 0;
		int passed_count = 0;
		for (int i = TEST_MIN; i <= TEST_MAX && i < TestResults.length; i++)
		{
			if (tests.contains(new Integer(i)))
			{
				if (TestResults[i])
				{
					passed_count++;
				}
				else
				{
					failed_count++;
				}
			}
		}

		System.out.println("\nFailed " + total_failed_test_cases + " / " + total_test_cases + " test cases");
		System.out.println("Passed: " + passed_count + " / " + tests.size() + " tests");
		System.out.println("Failed: " + failed_count + " / " + tests.size() + " tests");
	}

	private static void testConstructor()
	{
		Ratio test_Fraction = new Ratio();
		checkContents(test_Fraction.toString(), "0");
	}

	private static void testConstructorLong()
	{
		for (int i = 0; i < 100; i++)
		{
			Ratio test_Fraction = new Ratio(i);
			checkContents(test_Fraction.toString(), (new Integer(i)).toString());
		}
	}


	private static void testConstructorLongLong()
	{
		for (int i = 1; i < 100; i++)
		{
			Ratio test_Fraction = new Ratio(i, i);
			checkContents(test_Fraction.toString(), "1");
		}

		for (int i = 1; i < 100; i += 2)
		{
			Ratio test_Fraction = new Ratio(i, i * 2);
			checkContents(test_Fraction.toString(), "1/2");
		}

		for (int i = 1; i < 100; i += 2)
		{
			Ratio test_Fraction = new Ratio(2 * i, i * 3);
			checkContents(test_Fraction.toString(), "2/3");
		}

		for (int i = 1; i < 100; i++)
		{
			Ratio test_Fraction = new Ratio(i, -1);
			checkContents(test_Fraction.toString(), new Integer(-i).toString());
		}
	}

	private static void testGetDenominator()
	{
		for (int i = 1; i < 100; i++)
		{
			Ratio test_Fraction = new Ratio(1, i);
			long denomResult = test_Fraction.getDivisor();
			if (denomResult != i)
			{
				dump_error_message("Expected denominator " + i + "; found " + denomResult);
				TEST_CASES_FAILED++;
			}
			TEST_CASE++;
		}
	}

	private static void testGetNumerator()
	{
		for (int i = 1; i < 100; i++)
		{
			Ratio test_Fraction = new Ratio(i);
			long numerResult = test_Fraction.getDividend();
			if (numerResult != i)
			{
				dump_error_message("Expected numerator " + i + "; found " + numerResult);
				TEST_CASES_FAILED++;
			}
			TEST_CASE++;
		}
	}

	private static void testAdd()
	{
		Ratio r1 = new Ratio(1, 2);
		Ratio result = r1.addRatio(r1);
		checkContents(result.toString(), "1");
		//
		r1 = new Ratio(3, 4);
		Ratio r2 = new Ratio(1, 2);
		result = r1.addRatio(r2);
		checkContents(result.toString(), "5/4");
	}

	private static void testSubtract()
	{
		for (int i = 1; i < 100; i++)
		{
			Ratio test_Fraction1 = new Ratio(-i);
			Ratio test_Fraction2 = new Ratio(i);
			Ratio difference = test_Fraction1.subtractRatio(test_Fraction2);
			checkContents(difference.toString(), new Integer(-2 * i).toString());
		}

		Ratio r1 = new Ratio(1, 2);
		Ratio result = r1.subtractRatio(r1);
		checkContents(result.toString(), "0");
		//
		r1 = new Ratio(2, 3);
		Ratio r2 = new Ratio(1, 6);
		result = r1.subtractRatio(r2);
		checkContents(result.toString(), "1/2");

	}

	private static void testMultiply()
	{
		for (int i = 2; i < 100; i++)
		{
			Ratio test_Fraction1 = new Ratio(i);
			Ratio test_Fraction2 = new Ratio(1, i);
			Ratio product = test_Fraction1.multiplyRatio(test_Fraction2);
			checkContents(product.toString(), "1");
		}
		//
		Ratio r1 = new Ratio(1, 2);
		Ratio result = r1.multiplyRatio(r1);
		checkContents(result.toString(), "1/4");
		//
	}

	private static void testDivide()
	{
		for (int i = 2; i < 100; i++)
		{
			Ratio test_Fraction1 = new Ratio(i);
			Ratio test_Fraction2 = new Ratio(1, i);
			Ratio quotient = test_Fraction1.divideRatio(test_Fraction2);
			checkContents(quotient.toString(), new Integer(i * i).toString());
		}
		//
		// Add tests here
		//
	}
}
