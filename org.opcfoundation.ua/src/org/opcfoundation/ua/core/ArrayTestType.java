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
import java.util.UUID;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.UnsignedLong;
import org.opcfoundation.ua.builtintypes.UnsignedShort;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.builtintypes.XmlElement;
import org.opcfoundation.ua.core.EnumeratedTestType;



public class ArrayTestType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ArrayTestType;
	public static final NodeId BINARY = Identifiers.ArrayTestType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ArrayTestType_Encoding_DefaultXml;	
	
    protected Boolean[] Booleans;
    protected Byte[] SBytes;
    protected Short[] Int16s;
    protected UnsignedShort[] UInt16s;
    protected Integer[] Int32s;
    protected UnsignedInteger[] UInt32s;
    protected Long[] Int64s;
    protected UnsignedLong[] UInt64s;
    protected Float[] Floats;
    protected Double[] Doubles;
    protected String[] Strings;
    protected DateTime[] DateTimes;
    protected UUID[] Guids;
    protected byte[][] ByteStrings;
    protected XmlElement[] XmlElements;
    protected NodeId[] NodeIds;
    protected ExpandedNodeId[] ExpandedNodeIds;
    protected StatusCode[] StatusCodes;
    protected DiagnosticInfo[] DiagnosticInfos;
    protected QualifiedName[] QualifiedNames;
    protected LocalizedText[] LocalizedTexts;
    protected ExtensionObject[] ExtensionObjects;
    protected DataValue[] DataValues;
    protected Variant[] Variants;
    protected EnumeratedTestType[] EnumeratedValues;
    
    public ArrayTestType() {}
    
    public ArrayTestType(Boolean[] Booleans, Byte[] SBytes, Short[] Int16s, UnsignedShort[] UInt16s, Integer[] Int32s, UnsignedInteger[] UInt32s, Long[] Int64s, UnsignedLong[] UInt64s, Float[] Floats, Double[] Doubles, String[] Strings, DateTime[] DateTimes, UUID[] Guids, byte[][] ByteStrings, XmlElement[] XmlElements, NodeId[] NodeIds, ExpandedNodeId[] ExpandedNodeIds, StatusCode[] StatusCodes, DiagnosticInfo[] DiagnosticInfos, QualifiedName[] QualifiedNames, LocalizedText[] LocalizedTexts, ExtensionObject[] ExtensionObjects, DataValue[] DataValues, Variant[] Variants, EnumeratedTestType[] EnumeratedValues)
    {
        this.Booleans = Booleans;
        this.SBytes = SBytes;
        this.Int16s = Int16s;
        this.UInt16s = UInt16s;
        this.Int32s = Int32s;
        this.UInt32s = UInt32s;
        this.Int64s = Int64s;
        this.UInt64s = UInt64s;
        this.Floats = Floats;
        this.Doubles = Doubles;
        this.Strings = Strings;
        this.DateTimes = DateTimes;
        this.Guids = Guids;
        this.ByteStrings = ByteStrings;
        this.XmlElements = XmlElements;
        this.NodeIds = NodeIds;
        this.ExpandedNodeIds = ExpandedNodeIds;
        this.StatusCodes = StatusCodes;
        this.DiagnosticInfos = DiagnosticInfos;
        this.QualifiedNames = QualifiedNames;
        this.LocalizedTexts = LocalizedTexts;
        this.ExtensionObjects = ExtensionObjects;
        this.DataValues = DataValues;
        this.Variants = Variants;
        this.EnumeratedValues = EnumeratedValues;
    }
    
    public Boolean[] getBooleans()
    {
        return Booleans;
    }
    
    public void setBooleans(Boolean[] Booleans)
    {
        this.Booleans = Booleans;
    }
    
    public Byte[] getSBytes()
    {
        return SBytes;
    }
    
    public void setSBytes(Byte[] SBytes)
    {
        this.SBytes = SBytes;
    }
    
    public Short[] getInt16s()
    {
        return Int16s;
    }
    
    public void setInt16s(Short[] Int16s)
    {
        this.Int16s = Int16s;
    }
    
    public UnsignedShort[] getUInt16s()
    {
        return UInt16s;
    }
    
    public void setUInt16s(UnsignedShort[] UInt16s)
    {
        this.UInt16s = UInt16s;
    }
    
    public Integer[] getInt32s()
    {
        return Int32s;
    }
    
    public void setInt32s(Integer[] Int32s)
    {
        this.Int32s = Int32s;
    }
    
    public UnsignedInteger[] getUInt32s()
    {
        return UInt32s;
    }
    
    public void setUInt32s(UnsignedInteger[] UInt32s)
    {
        this.UInt32s = UInt32s;
    }
    
    public Long[] getInt64s()
    {
        return Int64s;
    }
    
    public void setInt64s(Long[] Int64s)
    {
        this.Int64s = Int64s;
    }
    
    public UnsignedLong[] getUInt64s()
    {
        return UInt64s;
    }
    
    public void setUInt64s(UnsignedLong[] UInt64s)
    {
        this.UInt64s = UInt64s;
    }
    
    public Float[] getFloats()
    {
        return Floats;
    }
    
    public void setFloats(Float[] Floats)
    {
        this.Floats = Floats;
    }
    
    public Double[] getDoubles()
    {
        return Doubles;
    }
    
    public void setDoubles(Double[] Doubles)
    {
        this.Doubles = Doubles;
    }
    
    public String[] getStrings()
    {
        return Strings;
    }
    
    public void setStrings(String[] Strings)
    {
        this.Strings = Strings;
    }
    
    public DateTime[] getDateTimes()
    {
        return DateTimes;
    }
    
    public void setDateTimes(DateTime[] DateTimes)
    {
        this.DateTimes = DateTimes;
    }
    
    public UUID[] getGuids()
    {
        return Guids;
    }
    
    public void setGuids(UUID[] Guids)
    {
        this.Guids = Guids;
    }
    
    public byte[][] getByteStrings()
    {
        return ByteStrings;
    }
    
    public void setByteStrings(byte[][] ByteStrings)
    {
        this.ByteStrings = ByteStrings;
    }
    
    public XmlElement[] getXmlElements()
    {
        return XmlElements;
    }
    
    public void setXmlElements(XmlElement[] XmlElements)
    {
        this.XmlElements = XmlElements;
    }
    
    public NodeId[] getNodeIds()
    {
        return NodeIds;
    }
    
    public void setNodeIds(NodeId[] NodeIds)
    {
        this.NodeIds = NodeIds;
    }
    
    public ExpandedNodeId[] getExpandedNodeIds()
    {
        return ExpandedNodeIds;
    }
    
    public void setExpandedNodeIds(ExpandedNodeId[] ExpandedNodeIds)
    {
        this.ExpandedNodeIds = ExpandedNodeIds;
    }
    
    public StatusCode[] getStatusCodes()
    {
        return StatusCodes;
    }
    
    public void setStatusCodes(StatusCode[] StatusCodes)
    {
        this.StatusCodes = StatusCodes;
    }
    
    public DiagnosticInfo[] getDiagnosticInfos()
    {
        return DiagnosticInfos;
    }
    
    public void setDiagnosticInfos(DiagnosticInfo[] DiagnosticInfos)
    {
        this.DiagnosticInfos = DiagnosticInfos;
    }
    
    public QualifiedName[] getQualifiedNames()
    {
        return QualifiedNames;
    }
    
    public void setQualifiedNames(QualifiedName[] QualifiedNames)
    {
        this.QualifiedNames = QualifiedNames;
    }
    
    public LocalizedText[] getLocalizedTexts()
    {
        return LocalizedTexts;
    }
    
    public void setLocalizedTexts(LocalizedText[] LocalizedTexts)
    {
        this.LocalizedTexts = LocalizedTexts;
    }
    
    public ExtensionObject[] getExtensionObjects()
    {
        return ExtensionObjects;
    }
    
    public void setExtensionObjects(ExtensionObject[] ExtensionObjects)
    {
        this.ExtensionObjects = ExtensionObjects;
    }
    
    public DataValue[] getDataValues()
    {
        return DataValues;
    }
    
    public void setDataValues(DataValue[] DataValues)
    {
        this.DataValues = DataValues;
    }
    
    public Variant[] getVariants()
    {
        return Variants;
    }
    
    public void setVariants(Variant[] Variants)
    {
        this.Variants = Variants;
    }
    
    public EnumeratedTestType[] getEnumeratedValues()
    {
        return EnumeratedValues;
    }
    
    public void setEnumeratedValues(EnumeratedTestType[] EnumeratedValues)
    {
        this.EnumeratedValues = EnumeratedValues;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ArrayTestType
      */
    public ArrayTestType clone()
    {
        ArrayTestType result = new ArrayTestType();
        result.Booleans = Booleans==null ? null : Booleans.clone();
        result.SBytes = SBytes==null ? null : SBytes.clone();
        result.Int16s = Int16s==null ? null : Int16s.clone();
        result.UInt16s = UInt16s==null ? null : UInt16s.clone();
        result.Int32s = Int32s==null ? null : Int32s.clone();
        result.UInt32s = UInt32s==null ? null : UInt32s.clone();
        result.Int64s = Int64s==null ? null : Int64s.clone();
        result.UInt64s = UInt64s==null ? null : UInt64s.clone();
        result.Floats = Floats==null ? null : Floats.clone();
        result.Doubles = Doubles==null ? null : Doubles.clone();
        result.Strings = Strings==null ? null : Strings.clone();
        result.DateTimes = DateTimes==null ? null : DateTimes.clone();
        result.Guids = Guids==null ? null : Guids.clone();
        result.ByteStrings = ByteStrings==null ? null : ByteStrings.clone();
        result.XmlElements = XmlElements==null ? null : XmlElements.clone();
        result.NodeIds = NodeIds==null ? null : NodeIds.clone();
        result.ExpandedNodeIds = ExpandedNodeIds==null ? null : ExpandedNodeIds.clone();
        result.StatusCodes = StatusCodes==null ? null : StatusCodes.clone();
        result.DiagnosticInfos = DiagnosticInfos==null ? null : DiagnosticInfos.clone();
        result.QualifiedNames = QualifiedNames==null ? null : QualifiedNames.clone();
        result.LocalizedTexts = LocalizedTexts==null ? null : LocalizedTexts.clone();
        result.ExtensionObjects = ExtensionObjects==null ? null : ExtensionObjects.clone();
        result.DataValues = DataValues==null ? null : DataValues.clone();
        result.Variants = Variants==null ? null : Variants.clone();
        result.EnumeratedValues = EnumeratedValues==null ? null : EnumeratedValues.clone();
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
		return "ArrayTestType: "+ObjectUtils.printFieldsDeep(this);
	}

}
