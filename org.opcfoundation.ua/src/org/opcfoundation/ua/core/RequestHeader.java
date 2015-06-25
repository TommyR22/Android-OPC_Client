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
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class RequestHeader extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.RequestHeader;
	public static final NodeId BINARY = Identifiers.RequestHeader_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.RequestHeader_Encoding_DefaultXml;	
	
    protected NodeId AuthenticationToken;
    protected DateTime Timestamp;
    protected UnsignedInteger RequestHandle;
    protected UnsignedInteger ReturnDiagnostics;
    protected String AuditEntryId;
    protected UnsignedInteger TimeoutHint;
    protected ExtensionObject AdditionalHeader;
    
    public RequestHeader() {}
    
    public RequestHeader(NodeId AuthenticationToken, DateTime Timestamp, UnsignedInteger RequestHandle, UnsignedInteger ReturnDiagnostics, String AuditEntryId, UnsignedInteger TimeoutHint, ExtensionObject AdditionalHeader)
    {
        this.AuthenticationToken = AuthenticationToken;
        this.Timestamp = Timestamp;
        this.RequestHandle = RequestHandle;
        this.ReturnDiagnostics = ReturnDiagnostics;
        this.AuditEntryId = AuditEntryId;
        this.TimeoutHint = TimeoutHint;
        this.AdditionalHeader = AdditionalHeader;
    }
    
    public NodeId getAuthenticationToken()
    {
        return AuthenticationToken;
    }
    
    public void setAuthenticationToken(NodeId AuthenticationToken)
    {
        this.AuthenticationToken = AuthenticationToken;
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
    
    public UnsignedInteger getReturnDiagnostics()
    {
        return ReturnDiagnostics;
    }
    
    public void setReturnDiagnostics(UnsignedInteger ReturnDiagnostics)
    {
        this.ReturnDiagnostics = ReturnDiagnostics;
    }
    
    public String getAuditEntryId()
    {
        return AuditEntryId;
    }
    
    public void setAuditEntryId(String AuditEntryId)
    {
        this.AuditEntryId = AuditEntryId;
    }
    
    public UnsignedInteger getTimeoutHint()
    {
        return TimeoutHint;
    }
    
    public void setTimeoutHint(UnsignedInteger TimeoutHint)
    {
        this.TimeoutHint = TimeoutHint;
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
      * @return cloned RequestHeader
      */
    public RequestHeader clone()
    {
        RequestHeader result = new RequestHeader();
        result.AuthenticationToken = AuthenticationToken;
        result.Timestamp = Timestamp;
        result.RequestHandle = RequestHandle;
        result.ReturnDiagnostics = ReturnDiagnostics;
        result.AuditEntryId = AuditEntryId;
        result.TimeoutHint = TimeoutHint;
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
		return "RequestHeader: "+ObjectUtils.printFieldsDeep(this);
	}

}
