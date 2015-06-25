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
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class ResponseHeader extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ResponseHeader;
	public static final NodeId BINARY = Identifiers.ResponseHeader_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ResponseHeader_Encoding_DefaultXml;	
	
    protected DateTime Timestamp;
    protected UnsignedInteger RequestHandle;
    protected StatusCode ServiceResult;
    protected DiagnosticInfo ServiceDiagnostics;
    protected String[] StringTable;
    protected ExtensionObject AdditionalHeader;
    
    public ResponseHeader() {}
    
    public ResponseHeader(DateTime Timestamp, UnsignedInteger RequestHandle, StatusCode ServiceResult, DiagnosticInfo ServiceDiagnostics, String[] StringTable, ExtensionObject AdditionalHeader)
    {
        this.Timestamp = Timestamp;
        this.RequestHandle = RequestHandle;
        this.ServiceResult = ServiceResult;
        this.ServiceDiagnostics = ServiceDiagnostics;
        this.StringTable = StringTable;
        this.AdditionalHeader = AdditionalHeader;
    }
    
    public DateTime getTimestamp()
    {
        return Timestamp;
    }
    
    public void setTimestamp(DateTime Timestamp)
    {
        this.Timestamp = Timestamp;
    }
    
    public UnsignedInteger getRequestHandle()
    {
        return RequestHandle;
    }
    
    public void setRequestHandle(UnsignedInteger RequestHandle)
    {
        this.RequestHandle = RequestHandle;
    }
    
    public StatusCode getServiceResult()
    {
        return ServiceResult;
    }
    
    public void setServiceResult(StatusCode ServiceResult)
    {
        this.ServiceResult = ServiceResult;
    }
    
    public DiagnosticInfo getServiceDiagnostics()
    {
        return ServiceDiagnostics;
    }
    
    public void setServiceDiagnostics(DiagnosticInfo ServiceDiagnostics)
    {
        this.ServiceDiagnostics = ServiceDiagnostics;
    }
    
    public String[] getStringTable()
    {
        return StringTable;
    }
    
    public void setStringTable(String[] StringTable)
    {
        this.StringTable = StringTable;
    }
    
    public ExtensionObject getAdditionalHeader()
    {
        return AdditionalHeader;
    }
    
    public void setAdditionalHeader(ExtensionObject AdditionalHeader)
    {
        this.AdditionalHeader = AdditionalHeader;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ResponseHeader
      */
    public ResponseHeader clone()
    {
        ResponseHeader result = new ResponseHeader();
        result.Timestamp = Timestamp;
        result.RequestHandle = RequestHandle;
        result.ServiceResult = ServiceResult;
        result.ServiceDiagnostics = ServiceDiagnostics;
        result.StringTable = StringTable==null ? null : StringTable.clone();
        result.AdditionalHeader = AdditionalHeader;
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
		return "ResponseHeader: "+ObjectUtils.printFieldsDeep(this);
	}

}
