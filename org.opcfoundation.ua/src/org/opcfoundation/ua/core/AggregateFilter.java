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
import org.opcfoundation.ua.core.MonitoringFilter;



public class AggregateFilter extends MonitoringFilter implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.AggregateFilter;
	public static final NodeId BINARY = Identifiers.AggregateFilter_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.AggregateFilter_Encoding_DefaultXml;	
	
    protected DateTime StartTime;
    protected NodeId AggregateType;
    protected Double ProcessingInterval;
    protected AggregateConfiguration AggregateConfiguration;
    
    public AggregateFilter() {}
    
    public AggregateFilter(DateTime StartTime, NodeId AggregateType, Double ProcessingInterval, AggregateConfiguration AggregateConfiguration)
    {
        this.StartTime = StartTime;
        this.AggregateType = AggregateType;
        this.ProcessingInterval = ProcessingInterval;
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
    
    public NodeId getAggregateType()
    {
        return AggregateType;
    }
    
    public void setAggregateType(NodeId AggregateType)
    {
        this.AggregateType = AggregateType;
    }
    
    public Double getProcessingInterval()
    {
        return ProcessingInterval;
    }
    
    public void setProcessingInterval(Double ProcessingInterval)
    {
        this.ProcessingInterval = ProcessingInterval;
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
      * @return cloned AggregateFilter
      */
    public AggregateFilter clone()
    {
        AggregateFilter result = new AggregateFilter();
        result.StartTime = StartTime;
        result.AggregateType = AggregateType;
        result.ProcessingInterval = ProcessingInterval;
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
		return "AggregateFilter: "+ObjectUtils.printFieldsDeep(this);
	}

}
