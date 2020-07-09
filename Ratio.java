/*
* @JT Kelly
* <p> Ratio
* <p> Project 4
* <p> 
* 
*/

public class Ratio implements Comparable
{
	// class level variables
	private long _dividend;
	private long _divisor;
	private long _ratio;

	// Constructor with no variables provided
	public Ratio()
	{
		_dividend = 0;
		_divisor = 1;
	}

	// Constructor with 1 variable provided
	public Ratio(long a)
	{
		_dividend = a;
		_divisor = 1;
		_ratio = (a / 1);
	}

	// Constructor with both variables provided
	public Ratio(long a, long b) throws ArithmeticException
	{
		_dividend = a;
		_divisor = b;
		_ratio = (a / b);
		simplify();
	}

	// Accessor to return dividend value
	public long getDividend()
	{
		return _dividend;
	}

	// Accessor to return divisor value
	public long getDivisor()
	{
		return _divisor;
	}

	// Method to add two ratios
	public Ratio addRatio(Ratio r)
	{
		long den;
		long num;
		long gcd;
		// Check if divisors are equal
		if (this._divisor == r._divisor)
		{
			den = this._divisor;
			num = this._dividend + r._dividend;
		} else
		// if they aren't, find gcd
		{
			gcd = greatestCommonDivisor(this._divisor, r._divisor);
			num = gcd;

			// Use gcd to make both sides equal to each other
			if (Math.abs(this._divisor) == gcd)
			{
				long divide = (r._divisor) /(this._divisor);
				num = (this._dividend * divide) + r._dividend;
				den = r._divisor;
			} else if (Math.abs(r._divisor) == gcd)
			{
				long divide = (this._divisor) / (r._divisor);
				num = (r._dividend * divide) + this._dividend;
				den = this._divisor;
			} else if ((this._divisor) > (r._divisor))
			{
				num = this._dividend + (r._dividend * gcd);
				den = this._divisor;
			} else
			{
				num = (this._dividend * gcd) + r._dividend;
				den = r._divisor;
			}
		}

		// Create new ratio to be returned
		Ratio addRatio = new Ratio(num, den);
		addRatio.simplify();
		return addRatio;

	}

	// Method to subtract two ratios
	public Ratio subtractRatio(Ratio r)
	{
		long den;
		long num;
		long gcd;
		// Check if divisors are equal
		if (this._divisor == r._divisor)
		{
			den = this._divisor;
			num = this._dividend - r._dividend;
		} else
		// if they aren't, find gcd
		{
			gcd = greatestCommonDivisor(this._divisor, r._divisor);
			num = gcd;

			// Use gcd to make both sides equal to each other
			if (Math.abs(this._divisor) == gcd)
			{
				long divide =(r._divisor) / (this._divisor);
				num = (this._dividend * divide) - r._dividend;
				den = r._divisor;
			} else if ((r._divisor) == gcd)
			{
				long divide = (this._divisor) / (r._divisor);
				num = (r._dividend * divide) - this._dividend;
				den = this._divisor;
			} else if ((this._divisor) > (r._divisor))
			{
				num = this._dividend - (r._dividend * gcd);
				den = this._divisor;
			} else
			{
				num = (this._dividend * gcd) - r._dividend;
				den = r._divisor;
			}
		}

		// Create new ratio to be returned
		Ratio subRatio = new Ratio(num, den);
		subRatio.simplify();
		return subRatio;
	}

	// Method to multiply two ratios
	public Ratio multiplyRatio(Ratio r)
	{
		long num = r._dividend * this._dividend;
		long den = r._divisor * this._divisor;
		Ratio mulRatio = new Ratio(num, den);
		mulRatio.simplify();
		return mulRatio;
	}

	// Method to divide two ratios
	public Ratio divideRatio(Ratio r) throws ArithmeticException
	{
		long num = r._divisor * this._dividend;
		long den = r._dividend * this._divisor;
		Ratio divRatio = new Ratio(num, den);
		divRatio.simplify();
		return divRatio;
	}

	// ***************************** Private Methods

	// ***************************** Provided Code
	//
	// Find the Greatest Common Divisor of Two Integers
	//

	private long greatestCommonDivisor(long num1, long num2)
	{
		// base case
		if (num2 == 0)
		{
			return num1;
		}
		return greatestCommonDivisor(num2, num1 % num2);
	}

	// ********************************************************************************
	private void simplify()
	{
		// this will simplify the Fraction number
		long currentGCD = greatestCommonDivisor(_dividend, _divisor);
		_dividend /= currentGCD;
		_divisor /= currentGCD;

	}

	// **********************************************************************************
	public String toString()
	{
		if (_divisor == 1)
			return "" + _dividend;

		return _dividend + "/" + _divisor;
	}

	public int compareTo(Object obj)
	{
		if (obj == null)
			return -1;

		if (!(obj instanceof Ratio))
			return 1;

		if (this.equals((Ratio) (obj)))
			return 0;

		return (this.subtractRatio((Ratio) (obj)).getDividend() > 0 ? 1 : -1);
	}

	// Returns true if and only if obj are the same number or reference the same
	// object
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (this.subtractRatio((Ratio) obj).getDividend() == 0)
			return true;

		return false;
	}
}
