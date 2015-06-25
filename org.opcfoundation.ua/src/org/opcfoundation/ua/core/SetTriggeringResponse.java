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

import org.opcfoundation.ua.builtintypes.ServiceResponse;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.core.Identifiers;
import org.opcfoundation.ua.utils.ObjectUtils;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.core.ResponseHeader;


public class SetTriggeringResponse extends Object implements ServiceResponse {

	public static final NodeId ID = Identifiers.SetTriggeringResponse;
	public static final NodeId BINARY = Identifiers.SetTriggeringResponse_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.SetTriggeringResponse_Encoding_DefaultXml;	
	
    protected ResponseHeader ResponseHeader;
    protected StatusCode[] AddResults;
    protected DiagnosticInfo[] AddDiagnosticInfos;
    protected StatusCode[] RemoveResults;
    protected DiagnosticInfo[] RemoveDiagnosticInfos;
    
    public SetTriggeringResponse() {}
    
    public SetTriggeringResponse(ResponseHeader ResponseHeader, StatusCode[] AddResults, DiagnosticInfo[] AddDiagnosticInfos, StatusCode[] RemoveResults, DiagnosticInfo[] RemoveDiagnosticInfos)
    {
        this.ResponseHeader = ResponseHeader;
        this.AddResults = AddResults;
        this.AddDiagnosticInfos = AddDiagnosticInfos;
        this.RemoveResults = RemoveResults;
        this.RemoveDiagnosticInfos = RemoveDiagnosticInfos;
    }
    
    public ResponseHeader getResponseHeader()
    {
        return ResponseHeader;
    }
    
    public void setResponseHeader(ResponseHeader ResponseHeader)
    {
        this.ResponseHeader = ResponseHeader;
    }
    
    public StatusCode[] getAddResults()
    {
        return AddResults;
    }
    
    public void setAddResults(StatusCode[] AddResults)
    {
        this.AddResults = AddResults;
    }
    
    public DiagnosticInfo[] getAddDiagnosticInfos()
    {
        return AddDiagnosticInfos;
    }
    
    public void setAddDiagnosticInfos(DiagnosticInfo[] AddDiagnosticInfos)
    {
        this.AddDiagnosticInfos = AddDiagnosticInfos;
    }
    
    public StatusCode[] getRemoveResults()
    {
        return RemoveResults;
    }
    
    public void setRemoveResults(StatusCode[] RemoveResults)
    {
        this.RemoveResults = RemoveResults;
    }
    
    public DiagnosticInfo[] getRemoveDiagnosticInfos()
    {
        return RemoveDiagnosticInfos;
    }
    
    public void setRemoveDiagnosticInfos(DiagnosticInfo[] RemoveDiagnosticInfos)
    {
        this.RemoveDiagnosticInfos = RemoveDiagnosticInfos;
    }
    
    /**
      * Deep clone
      *
      * @return cloned SetTriggeringResponse
      */
    public SetTriggeringResponse clone()
    {
        SetTriggeringResponse result = new SetTriggeringResponse();
        result.ResponseHeader = ResponseHeader==null ? null : ResponseHeader.clone();
        result.AddResults = AddResults==null ? null : AddResults.clone();
        result.AddDiagnosticInfos = AddDiagnosticInfos==null ? null : AddDiagnosticInfos.clone();
        result.RemoveResults = RemoveResults==null ? null : RemoveResults.clone();
        result.RemoveDiagnosticInfos = RemoveDiagnosticInfos==null ? null : RemoveDiagnosticInfos.clone();
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
		return ObjectUtils.printFieldsDeep(this);
	}
	
}
