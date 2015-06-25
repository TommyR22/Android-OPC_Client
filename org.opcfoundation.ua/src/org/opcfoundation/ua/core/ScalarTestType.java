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
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.UnsignedLong;
import org.opcfoundation.ua.builtintypes.UnsignedShort;
import org.opcfoundation.ua.builtintypes.XmlElement;
import org.opcfoundation.ua.core.EnumeratedTestType;



public class ScalarTestType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ScalarTestType;
	public static final NodeId BINARY = Identifiers.ScalarTestType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ScalarTestType_Encoding_DefaultXml;	
	
    protected Boolean Boolean;
    protected Byte SByte;
    protected UnsignedByte Byte;
    protected Short Int16;
    protected UnsignedShort UInt16;
    protected Integer Int32;
    protected UnsignedInteger UInt32;
    protected Long Int64;
    protected UnsignedLong UInt64;
    protected Float Float;
    protected Double Double;
    protected String String;
    protected DateTime DateTime;
    protected UUID Guid;
    protected byte[] ByteString;
    protected XmlElement XmlElement;
    protected NodeId NodeId;
    protected ExpandedNodeId ExpandedNodeId;
    protected StatusCode StatusCode;
    protected DiagnosticInfo DiagnosticInfo;
    protected QualifiedName QualifiedName;
    protected LocalizedText LocalizedText;
    protected ExtensionObject ExtensionObject;
    protected DataValue DataValue;
    protected EnumeratedTestType EnumeratedValue;
    
    public ScalarTestType() {}
    
    public ScalarTestType(Boolean Boolean, Byte SByte, UnsignedByte Byte, Short Int16, UnsignedShort UInt16, Integer Int32, UnsignedInteger UInt32, Long Int64, UnsignedLong UInt64, Float Float, Double Double, String String, DateTime DateTime, UUID Guid, byte[] ByteString, XmlElement XmlElement, NodeId NodeId, ExpandedNodeId ExpandedNodeId, StatusCode StatusCode, DiagnosticInfo DiagnosticInfo, QualifiedName QualifiedName, LocalizedText LocalizedText, ExtensionObject ExtensionObject, DataValue DataValue, EnumeratedTestType EnumeratedValue)
    {
        this.Boolean = Boolean;
        this.SByte = SByte;
        this.Byte = Byte;
        this.Int16 = Int16;
        this.UInt16 = UInt16;
        this.Int32 = Int32;
        this.UInt32 = UInt32;
        this.Int64 = Int64;
        this.UInt64 = UInt64;
        this.Float = Float;
        this.Double = Double;
        this.String = String;
        this.DateTime = DateTime;
        this.Guid = Guid;
        this.ByteString = ByteString;
        this.XmlElement = XmlElement;
        this.NodeId = NodeId;
        this.ExpandedNodeId = ExpandedNodeId;
        this.StatusCode = StatusCode;
        this.DiagnosticInfo = DiagnosticInfo;
        this.QualifiedName = QualifiedName;
        this.LocalizedText = LocalizedText;
        this.ExtensionObject = ExtensionObject;
        this.DataValue = DataValue;
        this.EnumeratedValue = EnumeratedValue;
    }
    
    public Boolean getBoolean()
    {
        return Boolean;
    }
    
    public void setBoolean(Boolean Boolean)
    {
        this.Boolean = Boolean;
    }
    
    public Byte getSByte()
    {
        return SByte;
    }
    
    public void setSByte(Byte SByte)
    {
        this.SByte = SByte;
    }
    
    public UnsignedByte getByte()
    {
        return Byte;
    }
    
    public void setByte(UnsignedByte Byte)
    {
        this.Byte = Byte;
    }
    
    public Short getInt16()
    {
        return Int16;
    }
    
    public void setInt16(Short Int16)
    {
        this.Int16 = Int16;
    }
    
    public UnsignedShort getUInt16()
    {
        return UInt16;
    }
    
    public void setUInt16(UnsignedShort UInt16)
    {
        this.UInt16 = UInt16;
    }
    
    public Integer getInt32()
    {
        return Int32;
    }
    
    public void setInt32(Integer Int32)
    {
        this.Int32 = Int32;
    }
    
    public UnsignedInteger getUInt32()
    {
        return UInt32;
    }
    
    public void setUInt32(UnsignedInteger UInt32)
    {
        this.UInt32 = UInt32;
    }
    
    public Long getInt64()
    {
        return Int64;
    }
    
    public void setInt64(Long Int64)
    {
        this.Int64 = Int64;
    }
    
    public UnsignedLong getUInt64()
    {
        return UInt64;
    }
    
    public void setUInt64(UnsignedLong UInt64)
    {
        this.UInt64 = UInt64;
    }
    
    public Float getFloat()
    {
        return Float;
    }
    
    public void setFloat(Float Float)
    {
        this.Float = Float;
    }
    
    public Double getDouble()
    {
        return Double;
    }
    
    public void setDouble(Double Double)
    {
        this.Double = Double;
    }
    
    public String getString()
    {
        return String;
    }
    
    public void setString(String String)
    {
        this.String = String;
    }
    
    public DateTime getDateTime()
    {
        return DateTime;
    }
    
    public void setDateTime(DateTime DateTime)
    {
        this.DateTime = DateTime;
    }
    
    public UUID getGuid()
    {
        return Guid;
    }
    
    public void setGuid(UUID Guid)
    {
        this.Guid = Guid;
    }
    
    public byte[] getByteString()
    {
        return ByteString;
    }
    
    public void setByteString(byte[] ByteString)
    {
        this.ByteString = ByteString;
    }
    
    public XmlElement getXmlElement()
    {
        return XmlElement;
    }
    
    public void setXmlElement(XmlElement XmlElement)
    {
        this.XmlElement = XmlElement;
    }
    
    public NodeId getNodeId()
    {
        return NodeId;
    }
    
    public void setNodeId(NodeId NodeId)
    {
        this.NodeId = NodeId;
    }
    
    public ExpandedNodeId getExpandedNodeId()
    {
        return ExpandedNodeId;
    }
    
    public void setExpandedNodeId(ExpandedNodeId ExpandedNodeId)
    {
        this.ExpandedNodeId = ExpandedNodeId;
    }
    
    public StatusCode getStatusCode()
    {
        return StatusCode;
    }
    
    public void setStatusCode(StatusCode StatusCode)
    {
        this.StatusCode = StatusCode;
    }
    
    public DiagnosticInfo getDiagnosticInfo()
    {
        return DiagnosticInfo;
    }
    
    public void setDiagnosticInfo(DiagnosticInfo DiagnosticInfo)
    {
        this.DiagnosticInfo = DiagnosticInfo;
    }
    
    public QualifiedName getQualifiedName()
    {
        return QualifiedName;
    }
    
    public void setQualifiedName(QualifiedName QualifiedName)
    {
        this.QualifiedName = QualifiedName;
    }
    
    public LocalizedText getLocalizedText()
    {
        return LocalizedText;
    }
    
    public void setLocalizedText(LocalizedText LocalizedText)
    {
        this.LocalizedText = LocalizedText;
    }
    
    public ExtensionObject getExtensionObject()
    {
        return ExtensionObject;
    }
    
    public void setExtensionObject(ExtensionObject ExtensionObject)
    {
        this.ExtensionObject = ExtensionObject;
    }
    
    public DataValue getDataValue()
    {
        return DataValue;
    }
    
    public void setDataValue(DataValue DataValue)
    {
        this.DataValue = DataValue;
    }
    
    public EnumeratedTestType getEnumeratedValue()
    {
        return EnumeratedValue;
    }
    
    public void setEnumeratedValue(EnumeratedTestType EnumeratedValue)
    {
        this.EnumeratedValue = EnumeratedValue;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ScalarTestType
      */
    public ScalarTestType clone()
    {
        ScalarTestType result = new ScalarTestType();
        result.Boolean = Boolean;
        result.SByte = SByte;
        result.Byte = Byte;
        result.Int16 = Int16;
        result.UInt16 = UInt16;
        result.Int32 = Int32;
        result.UInt32 = UInt32;
        result.Int64 = Int64;
        result.UInt64 = UInt64;
        result.Float = Float;
        result.Double = Double;
        result.String = String;
        result.DateTime = DateTime;
        result.Guid = Guid;
        result.ByteString = ByteString;
        result.XmlElement = XmlElement;
        result.NodeId = NodeId;
        result.ExpandedNodeId = ExpandedNodeId;
        result.StatusCode = StatusCode;
        result.DiagnosticInfo = DiagnosticInfo;
        result.QualifiedName = QualifiedName;
        result.LocalizedText = LocalizedText;
        result.ExtensionObject = ExtensionObject;
        result.DataValue = DataValue;
        result.EnumeratedValue = EnumeratedValue;
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
		return "ScalarTestType: "+ObjectUtils.printFieldsDeep(this);
	}

}
