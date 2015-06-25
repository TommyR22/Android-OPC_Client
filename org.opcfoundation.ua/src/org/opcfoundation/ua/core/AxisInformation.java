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
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.core.AxisScaleEnumeration;
import org.opcfoundation.ua.core.EUInformation;
import org.opcfoundation.ua.core.Range;



public class AxisInformation extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.AxisInformation;
	public static final NodeId BINARY = Identifiers.AxisInformation_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.AxisInformation_Encoding_DefaultXml;	
	
    protected EUInformation EngineeringUnits;
    protected Range EURange;
    protected LocalizedText Title;
    protected AxisScaleEnumeration AxisScaleType;
    protected Double[] AxisSteps;
    
    public AxisInformation() {}
    
    public AxisInformation(EUInformation EngineeringUnits, Range EURange, LocalizedText Title, AxisScaleEnumeration AxisScaleType, Double[] AxisSteps)
    {
        this.EngineeringUnits = EngineeringUnits;
        this.EURange = EURange;
        this.Title = Title;
        this.AxisScaleType = AxisScaleType;
        this.AxisSteps = AxisSteps;
    }
    
    public EUInformation getEngineeringUnits()
    {
        return EngineeringUnits;
    }
    
    public void setEngineeringUnits(EUInformation EngineeringUnits)
    {
        this.EngineeringUnits = EngineeringUnits;
    }
    
    public Range getEURange()
    {
        return EURange;
    }
    
    public void setEURange(Range EURange)
    {
        this.EURange = EURange;
    }
    
    public LocalizedText getTitle()
    {
        return Title;
    }
    
    public void setTitle(LocalizedText Title)
    {
        this.Title = Title;
    }
    
    public AxisScaleEnumeration getAxisScaleType()
    {
        return AxisScaleType;
    }
    
    public void setAxisScaleType(AxisScaleEnumeration AxisScaleType)
    {
        this.AxisScaleType = AxisScaleType;
    }
    
    public Double[] getAxisSteps()
    {
        return AxisSteps;
    }
    
    public void setAxisSteps(Double[] AxisSteps)
    {
        this.AxisSteps = AxisSteps;
    }
    
    /**
      * Deep clone
      *
      * @return cloned AxisInformation
      */
    public AxisInformation clone()
    {
        AxisInformation result = new AxisInformation();
        result.EngineeringUnits = EngineeringUnits==null ? null : EngineeringUnits.clone();
        result.EURange = EURange==null ? null : EURange.clone();
        result.Title = Title;
        result.AxisScaleType = AxisScaleType;
        result.AxisSteps = AxisSteps==null ? null : AxisSteps.clone();
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
		return "AxisInformation: "+ObjectUtils.printFieldsDeep(this);
	}

}
