/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Reciprocal Community License ("RCL") Version 1.00
 * 
 * Unless explicitly acquired and licensed from Licensor under another 
 * license, the contents of this file are subject to the Reciprocal 
 * Community License ("RCL") Version 1.00, or subsequent versions as 
 * allowed by the RCL, and You may not copy or use this file in either 
 * source code or executable form, except in compliance with the terms and 
 * conditions of the RCL.
 * 
 * All software distributed under the RCL is provided strictly on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, 
 * AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES, INCLUDING WITHOUT 
 * LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RCL for specific 
 * language governing rights and limitations under the RCL.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/RCL/1.00/
 * ======================================================================*/

package org.opcfoundation.ua.utils;

import java.lang.reflect.Array;
import java.util.Collection;

import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.StatusCodes;

public class NumericRange {

	private int begin_;
	private int end_;

	// constructors
	public NumericRange() {
		//initialize with default values
		begin_ = -1;
		end_ = -1;

	}

	/**
	 * Initializes the object with a begin index.
	 * @param begin
	 */
	public NumericRange(int begin) {
		if (begin < -1) {
			throw new IllegalArgumentException("begin");
		}

		this.begin_ = -1;
		this.end_ = -1;

		this.setBegin(begin);
	}
	/**
	 * Initializes the object with a begin and end index.
	 */
	public NumericRange(int begin, int end) {
		begin_ = -1;
		end_ = -1;

		this.setBegin(begin);
		this.setEnd(end);

	}

	//	public methods
	
	/**
	 * Ensures the bounds are valid values for the object passed in.<p>
	 * Returns false if the object is not indexable or if the numeric range is out of bounds.
	 */
	public boolean ensureValid(Object value) {
		int count = -1;
		
		// Check for collections and lists.
		try {
			Collection<?> collection = (Collection<?>) value;
			count = collection.size();
		} catch (Exception e) {
			try 
			{
				count = Array.getLength(value);
			}
			catch (IllegalArgumentException e2)
			{
				
			}
		}
		
		// Ensure bounds are less than count.
		return ensureValid(count);
	}
	
	/**
	 * Tests the bounds are valid values for a collection with the specified length.<p>
	 * Returns false if the numeric range is out of bounds.
	 * 
	 * @param count
	 * @return true if valid  
	 */
	public boolean ensureValid(int count) {
		
		// Object not indexable
		if (count == -1) {
			return false;
		}
		
		// Check bounds.
		if (begin_ > count || end_ >= count) {
			return false;
		}
		
		// Set begin.
		if (begin_ < 0) {
			begin_ = 0;
		}
		
		// Set end 
		if (end_ < 0) {
			end_ = count;
		}
		
		return true;
	}
	
	public int getBegin() {
		return begin_;
	}


	public int getEnd() {
		return end_;
	}


	public void setBegin(int value) {
		if (value < -1) {
			throw new IllegalArgumentException("Begin");
		}

		if (end_ != -1 && (begin_ > end_ || begin_ < 0)) {
			throw new IllegalArgumentException("Begin < End");
		}

		this.begin_ = value;
	}


	public void setEnd(int value) {
		if (value < -1) {
			throw new IllegalArgumentException("End");
		}

		if (end_ != -1 && (begin_ > end_ || begin_ < 0)) {
			throw new IllegalArgumentException("Begin > End");
		}

		this.end_ = value;
	}


	// static members
	private static NumericRange empty_ = new NumericRange(-1,-1);

	public static NumericRange getEmpty() {
		return empty_;
	}

	/**
	 * Parses a string representing a numeric range.
	 * 
	 * @param textToParse
	 * @return numeric range
	 * @throws ServiceResultException in case the range is not in proper format
	 */
	public static NumericRange parse(String textToParse) 
	throws ServiceResultException {
		if (textToParse == null || textToParse.length() == 0) {
			return NumericRange.getEmpty();
		}

		NumericRange range = new NumericRange(-1,-1);

		try {
			int index = textToParse.indexOf(":");

			if (index != -1) {
				range.setBegin(Integer.parseInt(textToParse.substring(0, index)));
				range.setEnd(Integer.parseInt(textToParse.substring(index + 1)));

				
				
				if (range.getEnd() < 0 ) throw new IllegalArgumentException("End");
				
				if (range.getBegin() > range.getEnd()) throw new IllegalArgumentException("Begin > End");
				
				if (range.getBegin() == range.getEnd()) throw new IllegalArgumentException("Begin = End");
				
			} else {
				range.setBegin(Integer.parseInt(textToParse));
				range.setEnd(-1);
			}
			if (range.getBegin() < 0 ) throw new IllegalArgumentException("Begin");

		} catch (Exception e) {
			throw new ServiceResultException(StatusCodes.Bad_IndexRangeInvalid,
					"Cannot parse numeric range: " + textToParse + ".");
		}

		return range;
	}

	@Override
	public String toString() {
		return String.format("%d:%d", getBegin(), getEnd());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NumericRange))
			return false;
		else
			
			return begin_ == ((NumericRange)obj).begin_ && end_ == ((NumericRange)obj).end_;
	}
}
