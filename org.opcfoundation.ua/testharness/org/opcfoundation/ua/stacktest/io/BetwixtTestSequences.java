/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Foundation MIT License 1.00
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/MIT/1.00/
 * ======================================================================*/

package org.opcfoundation.ua.stacktest.io;

//Import log4j classes.
import org.apache.log4j.BasicConfigurator;
import org.opcfoundation.ua.stacktest.TestSequence;

/**
 * This is a test class for testing Jakarta Betwixt with the
 * TestCases.xml document.  
 * 
 * @author jouni.aro@prosys.fi
 *
 */
public class BetwixtTestSequences {
	public TestSequence testSequence;
	
    public BetwixtTestSequences() {
		super();
	}

	public static void main(String[] args) {
		// Set up a simple configuration that logs on the console.
	    BasicConfigurator.configure();

    	TestSequenceReader testSequenceReader = new TestSequenceReader();
    	System.out.println("[read()]");
    	TestSequence testSequence = testSequenceReader.read("resources/TestCases.xml");
        
    	// send bean to system out
        System.out.println("[read result]");
        System.out.println(testSequence);

        testSequence.convertToDerivedTestCases();
    	TestSequenceWriter testSequenceWriter = new TestSequenceWriter();

    	System.out.println("[write()]");
    	String writeStr = testSequenceWriter.write(testSequence);
        System.out.println("[write result]");
        System.out.println(writeStr);
    }
}
