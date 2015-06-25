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
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.StatusCode;



public class HistoryUpdateResult extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.HistoryUpdateResult;
	public static final NodeId BINARY = Identifiers.HistoryUpdateResult_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.HistoryUpdateResult_Encoding_DefaultXml;	
	
    protected StatusCode StatusCode;
    protected StatusCode[] OperationResults;
    protected DiagnosticInfo[] DiagnosticInfos;
    
    public HistoryUpdateResult() {}
    
    public HistoryUpdateResult(StatusCode StatusCode, StatusCode[] OperationResults, DiagnosticInfo[] DiagnosticInfos)
    {
        this.StatusCode = StatusCode;
        this.OperationResults = OperationResults;
        this.DiagnosticInfos = DiagnosticInfos;
    }
    
    public StatusCode getStatusCode()
    {
        return StatusCode;
    }
    
    public void setStatusCode(StatusCode StatusCode)
    {
        this.StatusCode = StatusCode;
    }
    
    public StatusCode[] getOperationResults()
    {
        return OperationResults;
    }
    
    public void setOperationResults(StatusCode[] OperationResults)
    {
        this.OperationResults = OperationResults;
    }
    
    public DiagnosticInfo[] getDiagnosticInfos()
    {
        return DiagnosticInfos;
    }
    
    public void setDiagnosticInfos(DiagnosticInfo[] DiagnosticInfos)
    {
        this.DiagnosticInfos = DiagnosticInfos;
    }
    
    /**
      * Deep clone
      *
      * @return cloned HistoryUpdateResult
      */
    public HistoryUpdateResult clone()
    {
        HistoryUpdateResult result = new HistoryUpdateResult();
        result.StatusCode = StatusCode;
        result.OperationResults = OperationResults==null ? null : OperationResults.clone();
        result.DiagnosticInfos = DiagnosticInfos==null ? null : DiagnosticInfos.clone();
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
		return "HistoryUpdateResult: "+ObjectUtils.printFieldsDeep(this);
	}

}
