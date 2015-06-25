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
import org.opcfoundation.ua.core.Argument;
import org.opcfoundation.ua.core.StatusResult;



public class ProgramDiagnosticDataType extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ProgramDiagnosticDataType;
	public static final NodeId BINARY = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ProgramDiagnosticDataType_Encoding_DefaultXml;	
	
    protected NodeId CreateSessionId;
    protected String CreateClientName;
    protected DateTime InvocationCreationTime;
    protected DateTime LastTransitionTime;
    protected String LastMethodCall;
    protected NodeId LastMethodSessionId;
    protected Argument[] LastMethodInputArguments;
    protected Argument[] LastMethodOutputArguments;
    protected DateTime LastMethodCallTime;
    protected StatusResult LastMethodReturnStatus;
    
    public ProgramDiagnosticDataType() {}
    
    public ProgramDiagnosticDataType(NodeId CreateSessionId, String CreateClientName, DateTime InvocationCreationTime, DateTime LastTransitionTime, String LastMethodCall, NodeId LastMethodSessionId, Argument[] LastMethodInputArguments, Argument[] LastMethodOutputArguments, DateTime LastMethodCallTime, StatusResult LastMethodReturnStatus)
    {
        this.CreateSessionId = CreateSessionId;
        this.CreateClientName = CreateClientName;
        this.InvocationCreationTime = InvocationCreationTime;
        this.LastTransitionTime = LastTransitionTime;
        this.LastMethodCall = LastMethodCall;
        this.LastMethodSessionId = LastMethodSessionId;
        this.LastMethodInputArguments = LastMethodInputArguments;
        this.LastMethodOutputArguments = LastMethodOutputArguments;
        this.LastMethodCallTime = LastMethodCallTime;
        this.LastMethodReturnStatus = LastMethodReturnStatus;
    }
    
    public NodeId getCreateSessionId()
    {
        return CreateSessionId;
    }
    
    public void setCreateSessionId(NodeId CreateSessionId)
    {
        this.CreateSessionId = CreateSessionId;
    }
    
    public String getCreateClientName()
    {
        return CreateClientName;
    }
    
    public void setCreateClientName(String CreateClientName)
    {
        this.CreateClientName = CreateClientName;
    }
    
    public DateTime getInvocationCreationTime()
    {
        return InvocationCreationTime;
    }
    
    public void setInvocationCreationTime(DateTime InvocationCreationTime)
    {
        this.InvocationCreationTime = InvocationCreationTime;
    }
    
    public DateTime getLastTransitionTime()
    {
        return LastTransitionTime;
    }
    
    public void setLastTransitionTime(DateTime LastTransitionTime)
    {
        this.LastTransitionTime = LastTransitionTime;
    }
    
    public String getLastMethodCall()
    {
        return LastMethodCall;
    }
    
    public void setLastMethodCall(String LastMethodCall)
    {
        this.LastMethodCall = LastMethodCall;
    }
    
    public NodeId getLastMethodSessionId()
    {
        return LastMethodSessionId;
    }
    
    public void setLastMethodSessionId(NodeId LastMethodSessionId)
    {
        this.LastMethodSessionId = LastMethodSessionId;
    }
    
    public Argument[] getLastMethodInputArguments()
    {
        return LastMethodInputArguments;
    }
    
    public void setLastMethodInputArguments(Argument[] LastMethodInputArguments)
    {
        this.LastMethodInputArguments = LastMethodInputArguments;
    }
    
    public Argument[] getLastMethodOutputArguments()
    {
        return LastMethodOutputArguments;
    }
    
    public void setLastMethodOutputArguments(Argument[] LastMethodOutputArguments)
    {
        this.LastMethodOutputArguments = LastMethodOutputArguments;
    }
    
    public DateTime getLastMethodCallTime()
    {
        return LastMethodCallTime;
    }
    
    public void setLastMethodCallTime(DateTime LastMethodCallTime)
    {
        this.LastMethodCallTime = LastMethodCallTime;
    }
    
    public StatusResult getLastMethodReturnStatus()
    {
        return LastMethodReturnStatus;
    }
    
    public void setLastMethodReturnStatus(StatusResult LastMethodReturnStatus)
    {
        this.LastMethodReturnStatus = LastMethodReturnStatus;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ProgramDiagnosticDataType
      */
    public ProgramDiagnosticDataType clone()
    {
        ProgramDiagnosticDataType result = new ProgramDiagnosticDataType();
        result.CreateSessionId = CreateSessionId;
        result.CreateClientName = CreateClientName;
        result.InvocationCreationTime = InvocationCreationTime;
        result.LastTransitionTime = LastTransitionTime;
        result.LastMethodCall = LastMethodCall;
        result.LastMethodSessionId = LastMethodSessionId;
        if (LastMethodInputArguments!=null) {
            result.LastMethodInputArguments = new Argument[LastMethodInputArguments.length];
            for (int i=0; i<LastMethodInputArguments.length; i++)
                result.LastMethodInputArguments[i] = LastMethodInputArguments[i].clone();
        }
        if (LastMethodOutputArguments!=null) {
            result.LastMethodOutputArguments = new Argument[LastMethodOutputArguments.length];
            for (int i=0; i<LastMethodOutputArguments.length; i++)
                result.LastMethodOutputArguments[i] = LastMethodOutputArguments[i].clone();
        }
        result.LastMethodCallTime = LastMethodCallTime;
        result.LastMethodReturnStatus = LastMethodReturnStatus==null ? null : LastMethodReturnStatus.clone();
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
		return "ProgramDiagnosticDataType: "+ObjectUtils.printFieldsDeep(this);
	}

}
