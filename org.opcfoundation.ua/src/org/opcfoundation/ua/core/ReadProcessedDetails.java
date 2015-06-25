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
import org.opcfoundation.ua.core.AggregateConfiguration;
import org.opcfoundation.ua.core.HistoryReadDetails;



public class ReadProcessedDetails extends HistoryReadDetails implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.ReadProcessedDetails;
	public static final NodeId BINARY = Identifiers.ReadProcessedDetails_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.ReadProcessedDetails_Encoding_DefaultXml;	
	
    protected DateTime StartTime;
    protected DateTime EndTime;
    protected Double ProcessingInterval;
    protected NodeId[] AggregateType;
    protected AggregateConfiguration AggregateConfiguration;
    
    public ReadProcessedDetails() {}
    
    public ReadProcessedDetails(DateTime StartTime, DateTime EndTime, Double ProcessingInterval, NodeId[] AggregateType, AggregateConfiguration AggregateConfiguration)
    {
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.ProcessingInterval = ProcessingInterval;
        this.AggregateType = AggregateType;
        this.AggregateConfiguration = AggregateConfiguration;
    }
    
    public DateTime getStartTime()
    {
        return StartTime;
    }
    
    public void setStartTime(DateTime StartTime)
    {
        this.StartTime = StartTime;
    }
    
    public DateTime getEndTime()
    {
        return EndTime;
    }
    
    public void setEndTime(DateTime EndTime)
    {
        this.EndTime = EndTime;
    }
    
    public Double getProcessingInterval()
    {
        return ProcessingInterval;
    }
    
    public void setProcessingInterval(Double ProcessingInterval)
    {
        this.ProcessingInterval = ProcessingInterval;
    }
    
    public NodeId[] getAggregateType()
    {
        return AggregateType;
    }
    
    public void setAggregateType(NodeId[] AggregateType)
    {
        this.AggregateType = AggregateType;
    }
    
    public AggregateConfiguration getAggregateConfiguration()
    {
        return AggregateConfiguration;
    }
    
    public void setAggregateConfiguration(AggregateConfiguration AggregateConfiguration)
    {
        this.AggregateConfiguration = AggregateConfiguration;
    }
    
    /**
      * Deep clone
      *
      * @return cloned ReadProcessedDetails
      */
    public ReadProcessedDetails clone()
    {
        ReadProcessedDetails result = new ReadProcessedDetails();
        result.StartTime = StartTime;
        result.EndTime = EndTime;
        result.ProcessingInterval = ProcessingInterval;
        result.AggregateType = AggregateType==null ? null : AggregateType.clone();
        result.AggregateConfiguration = AggregateConfiguration==null ? null : AggregateConfiguration.clone();
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
		return "ReadProcessedDetails: "+ObjectUtils.printFieldsDeep(this);
	}

}
