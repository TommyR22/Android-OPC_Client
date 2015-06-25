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
import org.opcfoundation.ua.core.ContentFilterResult;
import org.opcfoundation.ua.core.MonitoringFilterResult;



public class EventFilterResult extends MonitoringFilterResult implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.EventFilterResult;
	public static final NodeId BINARY = Identifiers.EventFilterResult_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.EventFilterResult_Encoding_DefaultXml;	
	
    protected StatusCode[] SelectClauseResults;
    protected DiagnosticInfo[] SelectClauseDiagnosticInfos;
    protected ContentFilterResult WhereClauseResult;
    
    public EventFilterResult() {}
    
    public EventFilterResult(StatusCode[] SelectClauseResults, DiagnosticInfo[] SelectClauseDiagnosticInfos, ContentFilterResult WhereClauseResult)
    {
        this.SelectClauseResults = SelectClauseResults;
        this.SelectClauseDiagnosticInfos = SelectClauseDiagnosticInfos;
        this.WhereClauseResult = WhereClauseResult;
    }
    
    public StatusCode[] getSelectClauseResults()
    {
        return SelectClauseResults;
    }
    
    public void setSelectClauseResults(StatusCode[] SelectClauseResults)
    {
        this.SelectClauseResults = SelectClauseResults;
    }
    
    public DiagnosticInfo[] getSelectClauseDiagnosticInfos()
    {
        return SelectClauseDiagnosticInfos;
    }
    
    public void setSelectClauseDiagnosticInfos(DiagnosticInfo[] SelectClauseDiagnosticInfos)
    {
        this.SelectClauseDiagnosticInfos = SelectClauseDiagnosticInfos;
    }
    
    public ContentFilterResult getWhereClauseResult()
    {
        return WhereClauseResult;
    }
    
    public void setWhereClauseResult(ContentFilterResult WhereClauseResult)
    {
        this.WhereClauseResult = WhereClauseResult;
    }
    
    /**
      * Deep clone
      *
      * @return cloned EventFilterResult
      */
    public EventFilterResult clone()
    {
        EventFilterResult result = new EventFilterResult();
        result.SelectClauseResults = SelectClauseResults==null ? null : SelectClauseResults.clone();
        result.SelectClauseDiagnosticInfos = SelectClauseDiagnosticInfos==null ? null : SelectClauseDiagnosticInfos.clone();
        result.WhereClauseResult = WhereClauseResult==null ? null : WhereClauseResult.clone();
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
		return "EventFilterResult: "+ObjectUtils.printFieldsDeep(this);
	}

}
