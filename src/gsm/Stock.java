package gsm;

import java.text.NumberFormat;

/**
   The Stock class holds data about a stock.
*/

public class Stock
{
	static NumberFormat nf = NumberFormat.getCurrencyInstance();
	private String symbol;     // Trading symbol of stock
	private Double sharePriceN; // Current price per share
	private String sharePriceS; //$$$
	/**
		Constructor
		@param sym The stock's trading symbol.
		@param sharePrice2 The stock's share price.
	*/

	public Stock(String sym, Double sharePrice2) {
		setSymbol(sym);
		sharePriceN = sharePrice2;
	}
   
    /**
        getSymbol method
        @return Get the stock's trading symbol.
    */
   
    public String getSymbol()
    {
        return symbol;
    }
   
    /**
        setSymbol method
        @return Set the stock's trading symbol.
    */
   
    public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

/**
      getSharePrice method
      @return The stock's share price
   */
   
   public Double getSharePriceN()
   {
      return sharePriceN;
   }
   
   public String getSharePriceS()
   {
	   sharePriceS = nf.format(sharePriceN);
	   return sharePriceS;
   }
   
   /**
       setSymbol method
       @return Set the stock's trading symbol.
   */
  
	public void setSharePrice(Double sharePrice) {
		sharePriceS = getSharePriceS();
		this.sharePriceN = sharePrice;
	}

   /**
      toString method
      @return A string indicating the object's
              trading symbol and share price.
   */
   
   public String toString()
   {
	   sharePriceS = getSharePriceS();
	   // Create a string describing the stock.
	   String str = getSymbol() + ": " + sharePriceS;
	   // Return the string.
	   return str;
   }
   public String toStringWithout$Space()
   {
	   sharePriceS = getSharePriceS();
	   // Create a string describing the stock.
	   String str = getSymbol() + ":" + sharePriceN;
	   // Return the string.
	   return str;
   }

   /**
      The equals method compares two Stock objects.
      @param object2 A Stock object to compare to the
                     calling Stock object.
      @return true if the argument object is equal to
                   the calling object.
   */

   public boolean equals(Stock object2)
   {
      boolean status;
      
      // Determine whether this object's symbol and
      // sharePrice fields are equal to object2's
      // symbol and sharePrice fields.
      if (getSymbol().equals(object2.getSymbol()) &&
          sharePriceS == object2.sharePriceS)
         status = true;  // Yes, the objects are equal.
      else
         status = false; // No, the objects are not equal.
      
      // Return the value in status.
      return status;
   }
   
   public boolean equalsN(String s) {
	   boolean status;
	   
	   if(getSymbol().equals(s)) {
		   status=true;
	   } else {
		   status=false;
	   }
	   
	   return status;
   }

   /**
      The copy method makes a copy of a Stock object.
      @return A reference to a copy of the calling object.
   */

   public Stock copy()
   {
      // Create a new Stock object and initialize it
      // with the same data held by the calling object.
      Stock copyObject = new Stock(getSymbol(), sharePriceN);
      
      // Return a reference to the new object.
      return copyObject;
   }
}
