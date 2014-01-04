package com.anygine.common;

public class Money implements Comparable<Money> {
	
  private static final Currency DEFAULT_CURRENCY = Currency.EURO;

  public static final Object ZERO = new Money();
  
	private final float amount;
	private final Currency currency;
	
	public Money(float amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Money(float amount) {
	  this.amount = amount;
	  this.currency = DEFAULT_CURRENCY;
	}
	
	public Money() {
	  this.amount = 0.0f;
	  this.currency = DEFAULT_CURRENCY;
	}

  public float getAmount() {
		return amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Money add(Money other) {
		if (other.getCurrency() != currency) {
			throw new IllegalArgumentException(
					"No support for currency conversion (yet)");
		}
		return new Money(other.getAmount() + amount, currency);
	}
	
  public Money subtract(Money other) {
    if (other.getCurrency() != currency) {
      throw new IllegalArgumentException(
          "No support for currency conversion (yet)");
    }
    if (amount < other.getAmount()) {
      throw new IllegalArgumentException(
          "Subtracting " + other + " from " + this 
          + " would result in a negative amount");
    }
    return new Money(amount - other.getAmount(), currency);
  }
  
	@Override
	public String toString() {
	  return String.format("%.2f", amount) + " " + currency.toString();
	}

  @Override
  public int compareTo(Money other) {
    if (currency != other.getCurrency()) {
      throw new UnsupportedOperationException(
          "No support for currency conversion (yet)");
    }
    if (amount == other.getAmount()) {
      return 0;
    } 
    return (amount > other.getAmount() ? 1 : -1);
  }
  
  @Override
  public boolean equals(Object other) {
    if (!other.getClass().equals(this.getClass())) {
      return false;
    }
    Money otherMoney = (Money) other;
    if (currency != otherMoney.getCurrency()) {
      return false;
    }
    return amount == otherMoney.getAmount();
  }

  public Money times(float multiplier) {
    return new Money(amount * multiplier, currency);
  }
}
