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

package org.opcfoundation.ua.core;

import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.core.ArrayTestType;
import org.opcfoundation.ua.core.ScalarTestType;



public class CompositeTestType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.CompositeTestType;
	public static final NodeId BINARY = Identifiers.CompositeTestType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.CompositeTestType_Encoding_DefaultXml;	
	
    protected ScalarTestType Field1;
    protected ArrayTestType Field2;
    
    public CompositeTestType() {}
    
    public CompositeTestType(ScalarTestType Field1, ArrayTestType Field2)
    {
        this.Field1 = Field1;
        this.Field2 = Field2;
    }
    
    public ScalarTestType getField1()
    {
        return Field1;
    }
    
    public void setField1(ScalarTestType Field1)
    {
        this.Field1 = Field1;
    }
    
    public ArrayTestType getField2()
    {
        return Field2;
    }
    
    public void setField2(ArrayTestType Field2)
    {
        this.Field2 = Field2;
    }
    
    /**
      * Deep clone
      *
      * @return cloned CompositeTestType
      */
    public CompositeTestType clone()
    {
        CompositeTestType result = new CompositeTestType();
        result.Field1 = Field1==null ? null : Field1.clone();
        result.Field2 = Field2==null ? null : Field2.clone();
        return result;
    }
    


	public NodeId getTypeId() {
		return ID;
	}

	public NodeId getXmlEncodeId() {
		return XML;
	}

	public NodeId getBinaryEncodeId() {
		return BINARY;
	}
	
	public String toString() {
		return "CompositeTestType: "+ObjectUtils.printFieldsDeep(this);
	}

}
