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
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;



public class MonitoringParameters extends Object implements Structure, Cloneable {
	
	public static final NodeId ID = Identifiers.MonitoringParameters;
	public static final NodeId BINARY = Identifiers.MonitoringParameters_Encoding_DefaultBinary;
	public static final NodeId XML = Identifiers.MonitoringParameters_Encoding_DefaultXml;	
	
    protected UnsignedInteger ClientHandle;
    protected Double SamplingInterval;
    protected ExtensionObject Filter;
    protected UnsignedInteger QueueSize;
    protected Boolean DiscardOldest;
    
    public MonitoringParameters() {}
    
    public MonitoringParameters(UnsignedInteger ClientHandle, Double SamplingInterval, ExtensionObject Filter, UnsignedInteger QueueSize, Boolean DiscardOldest)
    {
        this.ClientHandle = ClientHandle;
        this.SamplingInterval = SamplingInterval;
        this.Filter = Filter;
        this.QueueSize = QueueSize;
        this.DiscardOldest = DiscardOldest;
    }
    
    public UnsignedInteger getClientHandle()
    {
        return ClientHandle;
    }
    
    public void setClientHandle(UnsignedInteger ClientHandle)
    {
        this.ClientHandle = ClientHandle;
    }
    
    public Double getSamplingInterval()
    {
        return SamplingInterval;
    }
    
    public void setSamplingInterval(Double SamplingInterval)
    {
        this.SamplingInterval = SamplingInterval;
    }
    
    public ExtensionObject getFilter()
    {
        return Filter;
    }
    
    public void setFilter(ExtensionObject Filter)
    {
        this.Filter = Filter;
    }
    
    public UnsignedInteger getQueueSize()
    {
        return QueueSize;
    }
    
    public void setQueueSize(UnsignedInteger QueueSize)
    {
        this.QueueSize = QueueSize;
    }
    
    public Boolean getDiscardOldest()
    {
        return DiscardOldest;
    }
    
    public void setDiscardOldest(Boolean DiscardOldest)
    {
        this.DiscardOldest = DiscardOldest;
    }
    
    /**
      * Deep clone
      *
      * @return cloned MonitoringParameters
      */
    public MonitoringParameters clone()
    {
        MonitoringParameters result = new MonitoringParameters();
        result.ClientHandle = ClientHandle;
        result.SamplingInterval = SamplingInterval;
        result.Filter = Filter;
        result.QueueSize = QueueSize;
        result.DiscardOldest = DiscardOldest;
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
		return "MonitoringParameters: "+ObjectUtils.printFieldsDeep(this);
	}

}
