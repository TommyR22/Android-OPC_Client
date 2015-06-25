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

package _PackageName_;

import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.Variant;
_imports_

@Description("_description_")
public class _ClassName_ extends _SuperType_ implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers._ClassName_;
	public static final NodeId BINARY = Identifiers._ClassName__Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers._ClassName__Encoding_DefaultXml;	
	
_Content_ 

	public boolean supportsAttribute(UnsignedInteger attributeId) {
    	
    	if (attributeId.equals(Attributes.IsAbstract) ||
    			attributeId.equals(Attributes.Symmetric) ||
    			attributeId.equals(Attributes.InverseName)) {
    			
    			return true;
    	}
    		
    	return super.supportsAttribute(attributeId);
	}

	public Variant read(UnsignedInteger attributeId) {
		if (attributeId.equals(Attributes.IsAbstract))                
			return new Variant(IsAbstract);
		
		if (attributeId.equals(Attributes.Symmetric))             
			return new Variant(Symmetric);
		
		if (attributeId.equals(Attributes.InverseName))         
			return new Variant(InverseName);
		
		return super.read(attributeId);
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
		return "_ClassName_: "+ObjectUtils.printFieldsDeep(this);
	}

}
