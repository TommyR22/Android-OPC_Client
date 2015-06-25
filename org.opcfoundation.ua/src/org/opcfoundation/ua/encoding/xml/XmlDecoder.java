/* ========================================================================
 * Copyright (c) 2005-2013 The OPC Foundation, Inc. All rights reserved.
 *
 * OPC Reciprocal Community License ("RCL") Version 1.00
 * 
 * Unless explicitly acquired and licensed from Licensor under another 
 * license, the contents of this file are subject to the Reciprocal 
 * Community License ("RCL") Version 1.00, or subsequent versions as 
 * allowed by the RCL, and You may not copy or use this file in either 
 * source code or executable form, except in compliance with the terms and 
 * conditions of the RCL.
 * 
 * All software distributed under the RCL is provided strictly on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, 
 * AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES, INCLUDING WITHOUT 
 * LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE, QUIET ENJOYMENT, OR NON-INFRINGEMENT. See the RCL for specific 
 * language governing rights and limitations under the RCL.
 *
 * The complete license agreement can be found here:
 * http://opcfoundation.org/License/RCL/1.00/
 * ======================================================================*/
package org.opcfoundation.ua.encoding.xml;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.lang.reflect.Array;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.log4j.Logger;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.DateTime;
import org.opcfoundation.ua.builtintypes.DiagnosticInfo;
import org.opcfoundation.ua.builtintypes.Enumeration;
import org.opcfoundation.ua.builtintypes.ExpandedNodeId;
import org.opcfoundation.ua.builtintypes.ExtensionObject;
import org.opcfoundation.ua.builtintypes.LocalizedText;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.QualifiedName;
import org.opcfoundation.ua.builtintypes.StatusCode;
import org.opcfoundation.ua.builtintypes.Structure;
import org.opcfoundation.ua.builtintypes.UnsignedByte;
import org.opcfoundation.ua.builtintypes.UnsignedInteger;
import org.opcfoundation.ua.builtintypes.UnsignedLong;
import org.opcfoundation.ua.builtintypes.UnsignedShort;
import org.opcfoundation.ua.builtintypes.Variant;
import org.opcfoundation.ua.builtintypes.XmlElement;
import org.opcfoundation.ua.common.NamespaceTable;
import org.opcfoundation.ua.common.ServiceResultException;
import org.opcfoundation.ua.core.StatusCodes;
import org.opcfoundation.ua.encoding.DecodingException;
import org.opcfoundation.ua.encoding.EncoderContext;
import org.opcfoundation.ua.encoding.IDecoder;
import org.opcfoundation.ua.encoding.IEncodeable;

public class XmlDecoder implements IDecoder {

	public class NilException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2070286005552106815L;

	}
	private static final String XML_SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";
	private static final String EMPTY_STRING = "";
	private static final String OPC_UA_XSD_NAMESPACE = "http://opcfoundation.org/UA/2008/02/Types.xsd";

	static Logger logger = Logger.getLogger(XmlDecoder.class);

	private XMLStreamReader m_reader;

//	private Stack<String> m_namespaces;

	private EncoderContext m_context;      

	private UnsignedShort[] m_namespaceMappings;



	private UnsignedShort[] m_serverMappings;

	/// <summary>
	/// Initializes the object with a XML reader.
	/// </summary>
//	public XmlDecoder3(Class<? extends T> encodeableClass, XMLStreamReader reader, ServiceMessageContext context)
//	{
//		Initialize();
//
//		m_reader  = reader;
//		m_context = context;
//
//		String ns = null;
//		String name = null;
//
//		if (systemType != null)
//		{
//			QName typeName = EncodeableFactory.GetXmlName(systemType);
//			ns = typeName.Namespace;
//			name = typeName.Name;
//		}
//
//		if (ns == null)
//		{
//			moveToContent();
//			ns = m_reader.getNamespaceURI();//NamespaceURI;
//			name = m_reader.getName();//Name;
//		}
//
//		int index = name.indexOf(':');
//
//		if (index != -1)
//		{
//			name = name.substring(index + 1);
//		}
//
//		pushNamespace(ns);
//		BeginFieldSafe(name, false);
//	}


	/// <summary>
	/// Initializes the object with an XML element to parse.
	/// </summary>
	public XmlDecoder(XMLStreamReader reader, EncoderContext context) throws DecodingException
	{
	    if (context == null) 
	    	throw new IllegalArgumentException("context");
	    Initialize();
	    m_reader  = reader;
	    m_context = context;
	}

	/// <summary>
	/// Initializes the object with an XML element to parse.
	/// </summary>
	public XmlDecoder(XmlElement element, EncoderContext context) throws DecodingException
	{
	    if (context == null) 
	    	throw new IllegalArgumentException("context");
	    Initialize();
	    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

	    try {
			m_reader  = xmlInputFactory.createXMLStreamReader(new StringReader(element.toString()));
		} catch (XMLStreamException e) {
			throw new DecodingException(e);
		}
	    m_context = context;
	}

	/// <summary>
	/// Closes the stream used for reading.
	/// </summary>
	public void Close() throws DecodingException
	{
		try {
			m_reader.close();
		} catch (XMLStreamException e) {
			throw new DecodingException(e);
		}
	}

	/// <summary>
	/// Closes the stream used for reading.
	/// </summary>
	public void Close(boolean checkEof) throws DecodingException
	{
		if (checkEof && m_reader.getEventType() != XMLStreamConstants.END_DOCUMENT)
		{
			getEndElement();
		}

		try {
			m_reader.close();
		} catch (XMLStreamException e) {
			throw new DecodingException(e);
		}
	}

	public Object getArrayObject(String fieldName, int builtinTypeId)
	throws DecodingException	
	{
		switch (builtinTypeId) {
		case 1: return getBooleanArray(null);
		case 2: return getSByteArray(null);
		case 3: return getByteArray(null);
		case 4: return getInt16Array(null);
		case 5: return getUInt16Array(null);
		case 6: return getInt32Array(null);
		case 7: return getUInt32Array(null);
		case 8: return getInt64Array(null);
		case 9: return getUInt64Array(null);
		case 10: return getFloatArray(null);
		case 11: return getDoubleArray(null);
		case 12: return getStringArray(null);
		case 13: return getDateTimeArray(null);
		case 14: return getGuidArray(null);
		case 15: return getByteStringArray(null);
		case 16: return getXmlElementArray(null);
		case 17: return getNodeIdArray(null);
		case 18: return getExpandedNodeIdArray(null);
		case 19: return getStatusCodeArray(null);
		case 20: return getQualifiedNameArray(null);
		case 21: return getLocalizedTextArray(null);
		case 22: return getExtensionObjectArray(null);
		case 23: return getDataValueArray(null);
		case 24: return getVariantArray(null);		
		case 25: return getDiagnosticInfoArray(null);
		}
		throw new DecodingException("Cannot decode builtin type id "+builtinTypeId);
	}

	/// <summary>
	/// Reads a boolean from the stream.
	/// </summary>
	public Boolean getBoolean(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				boolean value = Boolean.parseBoolean(xml.toLowerCase());//ToLowerInvariant());
				EndField(fieldName);
				return value;
			}
		}

		return false;
	}

	/// <summary>
	/// Reads a boolean array from the stream.
	/// </summary>
	public Boolean[] getBooleanArray(String fieldName) throws DecodingException
	{           
		List<Boolean> values = new ArrayList<Boolean>();

		if (BeginFieldSafe(fieldName, true))
{
		//pushNamespace(OPC_UA_XSD_NAMESPACE);      

		while (MoveToElement("Boolean"))
		{
			values.add(getBoolean("Boolean"));
		}

		// check the length.
		if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
		{
			throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
		}

		//popNamespace();

		EndField(fieldName);

}

		return values.toArray(new Boolean[0]);
	}

	/// <summary>
	/// Reads a byte from the stream.
	/// </summary>
	public UnsignedByte getByte(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				UnsignedByte value = UnsignedByte.parseUnsignedByte(xml);
				EndField(fieldName);
				return value;
			}
		}

		return UnsignedByte.ZERO;
	}

	/// <summary>
	/// Reads a byte array from the stream.
	/// </summary>
	public UnsignedByte[] getByteArray(String fieldName) throws DecodingException
	{
		List<UnsignedByte> values = new ArrayList<UnsignedByte>();

		if (BeginFieldSafe(fieldName, true))
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Byte"))
			{
				values.add(getByte("Byte"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			////popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new UnsignedByte[0]);
	}

	/// <summary>
	/// Reads a byte String from the stream.
	/// </summary>
	public byte[] getByteString(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			byte[] value = null;

			String xml = getContentAsString();

			if (!IsNullOrEmpty(xml))
			{
				//					value = Convert.FromBase64String(xml);
				value = javax.xml.bind.DatatypeConverter.parseBase64Binary(xml);
			}
			else
			{
				value = new byte[0];
			}

			// check the length.
			if (m_context.getMaxByteStringLength() > 0 && m_context.getMaxByteStringLength() < value.length)
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			EndField(fieldName);
			return value;
		}

		return null;
	}

	/// <summary>
	/// Reads a byte String array from the stream.
	/// </summary>
	public byte[][] getByteStringArray(String fieldName) throws DecodingException
	{
		List<byte[]> values = new ArrayList<byte[]>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("ByteString"))
			{
				values.add(getByteString("ByteString"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			////popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new byte[0][]);
	}

	/// <summary>
	/// The type of encoding being used.
	/// </summary>
	//	public EncodingType EncodingType 
	//	{
	//		get { return EncodingType.Xml; }
	//	}

	/// <summary>
	/// The message context associated with the decoder.
	/// </summary>
	//	public ServiceMessageContext getContext 
	//	{
	//		get { return m_context; }
	//	}

	/// <summary>
	/// Reads an DataValue from the stream.
	/// </summary>
	public DataValue getDataValue(String fieldName) throws DecodingException
	{
		DataValue value = new DataValue();

		if (BeginFieldSafe(fieldName, true))
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			value.setValue(getVariant("Value"));
			value.setStatusCode(getStatusCode("StatusCode"));
			value.setSourceTimestamp(getDateTime("SourceTimestamp"));
			value.setSourcePicoseconds(getUInt16("SourcePicoseconds"));
			value.setServerTimestamp(getDateTime("ServerTimestamp"));
			value.setServerPicoseconds(getUInt16("ServerPicoseconds"));

			////popNamespace();

			EndField(fieldName);
		}

		return value;
	}

	/// <summary>
	/// Reads an DataValue array from the stream.
	/// </summary>
	public DataValue[] getDataValueArray(String fieldName) throws DecodingException
	{
		List<DataValue> values = new ArrayList<DataValue>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("DataValue"))
			{
				values.add(getDataValue("DataValue"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new DataValue[0]);
	}

	/// <summary>
	/// Reads a UTC date/time from the stream.
	/// </summary>
	public DateTime getDateTime(String fieldName) throws DecodingException
	{                        
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			// check the length.
			if (m_context.getMaxStringLength() > 0 && m_context.getMaxStringLength() < xml.length())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			if (!IsNullOrEmpty(xml))
			{
				DateTime value;
				try {
					value = DateTime.parseDateTime(xml);
				} catch (ParseException e) {
					throw new DecodingException(e);
				}//, XmlDateTimeSerializationMode.Utc);
				EndField(fieldName);
				return value;
			}
		}

		return DateTime.MIN_VALUE;
	}

	/// <summary>
	/// Reads a UTC date/time array from the stream.
	/// </summary>
	public DateTime[] getDateTimeArray(String fieldName) throws DecodingException
	{
		List<DateTime> values = new ArrayList<DateTime>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("DateTime"))
			{
				values.add(getDateTime("DateTime"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new DateTime[0]);
	}

	/// <summary>
	/// Reads an DiagnosticInfo from the stream.
	/// </summary>
	public DiagnosticInfo getDiagnosticInfo() throws DecodingException
	{
		DiagnosticInfo value = new DiagnosticInfo();

		if (BeginFieldSafe("SymbolicId", true))
		{
			value.setSymbolicId(getInt32(null));
			EndField("SymbolicId");
		}

		if (BeginFieldSafe("NamespaceUri", true))
		{
			value.setNamespaceUri(getInt32(null));
			EndField("NamespaceUri");
		}

		if (BeginFieldSafe("Locale", true))
		{
			value.setLocale(getInt32(null));
			EndField("Locale");
		}

		if (BeginFieldSafe("LocalizedText", true))
		{
			value.setLocalizedText(getInt32(null));
			EndField("LocalizedText");
		}

		value.setAdditionalInfo(getString("AdditionalInfo"));
		value.setInnerStatusCode(getStatusCode("InnerStatusCode"));

		if (BeginFieldSafe("InnerDiagnosticInfo", true))
		{
			value.setInnerDiagnosticInfo(getDiagnosticInfo());
			EndField("InnerDiagnosticInfo");
		}

		return value;
	}              

	/// <summary>
	/// Reads an DiagnosticInfo from the stream.
	/// </summary>
	public DiagnosticInfo getDiagnosticInfo(String fieldName) throws DecodingException
	{
		DiagnosticInfo value = null;

		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);
			value = getDiagnosticInfo();
			//popNamespace();

			EndField(fieldName);
			return value;
		}

		return value;
	}

	/// <summary>
	/// Reads an DiagnosticInfo array from the stream.
	/// </summary>
	public DiagnosticInfo[] getDiagnosticInfoArray(String fieldName) throws DecodingException
	{
		List<DiagnosticInfo> values = new ArrayList<DiagnosticInfo>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("DiagnosticInfo"))
			{
				values.add(getDiagnosticInfo("DiagnosticInfo"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new DiagnosticInfo[0]);
	}

	/// <summary>
	/// Reads a double from the stream.
	/// </summary>
	public Double getDouble(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				double value = 0;

				if (xml.length() == 3)
				{
					if (xml == "NaN")
					{
						value = Double.NaN;
					}

					if (xml == "INF")
					{
						value = Double.POSITIVE_INFINITY;
					}
				}

				if (xml.length() == 4)
				{
					if (xml == "-INF")
					{
						value = Double.NEGATIVE_INFINITY;
					}
				}

				if (value == 0)
				{
					value = Double.parseDouble(xml);
				}

				EndField(fieldName);
				return value;
			}
		}

		return (double)0;
	}

	/// <summary>
	/// Reads a double array from the stream.
	/// </summary>
	public Double[] getDoubleArray(String fieldName) throws DecodingException
	{
		List<Double> values = new ArrayList<Double>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Double"))
			{
				values.add(getDouble("Double"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Double[0]);
	}

	/// <summary>
	/// Reads an encodeable object from the stream.
	/// </summary>
	public <T extends IEncodeable> T getEncodeable(
			String      fieldName, 
			Class<? extends T> encodeableClass) throws DecodingException
	{
		@SuppressWarnings("unchecked")
		T encodeable = (T) m_context.getEncodeableSerializer().getEncodeable(encodeableClass, this);
		EndField(fieldName);
		return encodeable;
		
//		if (encodeableClass == null) throw new IllegalArgumentException("systemType");
//
//		IEncodeable value = null;// TODO construct encodeable of the right class Activator.CreateInstance(systemType) as IEncodeable;
//
//		if (value == null)
//		{               
//			throw new DecodingException(
//					StatusCodes.Bad_DecodingError,
//					"Type does not support IEncodeable interface: " + encodeableClass.getName());
//		}
//
//		if (BeginFieldSafe(fieldName, true))
//		{
//			QName xmlName = EncodeableFactory.GetXmlName(encodeableClass);
//
//			pushNamespace(xmlName.getNamespaceURI());                
//			value.Decode(this);      
//			//popNamespace();
//
//			// skip to end of encodeable object.
//			moveToContent();
//
//			while (!(m_reader.getEventType() == XmlStreamConstants.END_ELEMENT && m_reader.LocalName == fieldName && m_reader.getNamespaceURI() == m_namespaces.peek()))
//			{
//				if (m_reader.getEventType() == XmlStreamConstants.None)
//				{
//					throw new DecodingException(
//							StatusCodes.Bad_DecodingError,
//							"Unexpected end of stream decoding field '" + fieldName + "' for type '" + systemType.FullName + "'.");
//				}
//
//				skip();//m_reader.Skip();
//				moveToContent();
//			}
//
//			EndField(fieldName);
//		}
//
//		return value;
	}

	/// <summary>
	/// Reads an encodeable object array from the stream.
	/// </summary>
	@SuppressWarnings("unchecked")
	public <T extends IEncodeable> T[] getEncodeableArray(String fieldName, Class<? extends T> encodeableClass) throws DecodingException
	{
		if (encodeableClass == null) throw new IllegalArgumentException("encodeableClass");

		//				boolean isNil = false;

		List<IEncodeable> encodeables = new ArrayList<IEncodeable>();

//		try {
			if (BeginFieldSafe(fieldName, true))
			{                           
//			QName xmlName = EncodeableFactory.GetXmlName(encodeableClass);     
			String name = encodeableClass.getName(); // TODO this may not work
			//StackUtils.getDefaultSerializer().//
//			m_context.getEncodeableSerializer().
			//pushNamespace(xmlName.getNamespaceURI());

			while (MoveToElement(name))
			{
				encodeables.add(getEncodeable(name, encodeableClass));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < encodeables.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);

			// convert to an array of the specified type.
//			Array values = Array.CreateInstance(systemType, encodeables.length);
//
//			for (int ii = 0; ii < encodeables.length; ii++)
//			{
//				values.SetValue(encodeables[ii], ii);
//			}

//			return (T[]) encodeables.toArray();
		}

//		} catch (NilException ex)
//		{
//			return null;
//		}

		return (T[]) encodeables.toArray(new IEncodeable[0]);
	}

	/**
	 * @throws XMLStreamException 
	 * @throws DecodingException 
	 * 
	 */
	public void getEndElement() throws DecodingException {
//		m_reader.getEndElement();
//		boolean isEmpty = isEmptyElement(); // TODO removed isEmpty
		moveToTag();
		if(m_reader.isEndElement())
			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			}
		else
			throw new DecodingException("Not an end element");

//		if (!isEmpty)
//		{
//			moveToContent();
//		}
	}

	/// <summary>
	///  Reads an enumerated value from the stream.
	/// </summary>
	@SuppressWarnings("unchecked")
	public <T extends Enumeration> T getEnumeration(String fieldName, Class<T> enumType)
	throws DecodingException	
	{         
		//	            #if SILVERLIGHT
		//	            Enum value = (Enum)Enum.ToObject(enumType, 0);
		//	            #else
//		Enum value = (Enum)Enum.GetValues(enumType).GetValue(0);
		T value = enumType.getEnumConstants()[0];
		//	            #endif

		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				int index = xml.lastIndexOf('_');

				if (index != -1)
				{
					int numericValue = Integer.parseInt(xml.substring(index + 1));
					Method m;
					try {
						m = enumType.getMethod("valueOf", int.class);
						value = (T) m.invoke(null, numericValue);
					} catch (SecurityException e) {
						throw new DecodingException(e);
					} catch (NoSuchMethodException e) {
						throw new DecodingException(e);
					} catch (IllegalArgumentException e) {
						throw new DecodingException(e);
					} catch (IllegalAccessException e) {
						throw new DecodingException(e);
					} catch (InvocationTargetException e) {
						throw new DecodingException(e);
					}
					
				}
				else
				{
					int numericValue = Integer.parseInt(xml);
					Method m;
					try {
						m = enumType.getMethod("valueOf", int.class);
						value = (T) m.invoke(null, numericValue);
					} catch (SecurityException e) {
						throw new DecodingException(e);
					} catch (NoSuchMethodException e) {
						throw new DecodingException(e);
					} catch (IllegalArgumentException e) {
						throw new DecodingException(e);
					} catch (IllegalAccessException e) {
						throw new DecodingException(e);
					} catch (InvocationTargetException e) {
						throw new DecodingException(e);
					}
					
//					value = ((Enum)enumType). //parse(enumType, xml, false);
				}
			}

			EndField(fieldName);
		}

		return value;
	}

	/// <summary>
	/// Reads an enumerated value array from the stream.
	/// </summary>
	@SuppressWarnings("unchecked")
	public <T extends Enumeration> T[] getEnumerationArray(String fieldName, Class<T> enumerationClass)
	throws DecodingException
	{
		if (enumerationClass == null) throw new IllegalArgumentException("enumerationClass");

		List<Enumeration> enums = new ArrayList<Enumeration>();

		if (BeginFieldSafe(fieldName, true))
		{                           
			String name = enumerationClass.toString();

			while (MoveToElement(name));
			{
				enums.add(getEnumeration(name, enumerationClass));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < enums.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			EndField(fieldName);
		}

		return (T[]) enums.toArray(new Enumeration[0]);
	}

	/// <summary>
	/// Reads an ExpandedNodeId from the stream.
	/// </summary>
	public ExpandedNodeId getExpandedNodeId(String fieldName) throws DecodingException
	{
		ExpandedNodeId value = ExpandedNodeId.NULL;

		if (BeginFieldSafe(fieldName, true))
		{                
			////pushNamespace(OPC_UA_XSD_NAMESPACE);
			value = ExpandedNodeId.parseExpandedNodeId(getString("Identifier"));
			//popNamespace();

			EndField(fieldName);
		}

		int namespaceIndex = value.getNamespaceIndex();
		int serverIndex = value.getServerIndex().intValue();
		boolean indexChanged = false;

		if (m_namespaceMappings != null && m_namespaceMappings.length > value.getNamespaceIndex())
		{
			//				value.SetNamespaceIndex(m_namespaceMappings[value.getNamespaceIndex()]);
			namespaceIndex = m_namespaceMappings[value.getNamespaceIndex()].intValue();
			indexChanged = true;
		}

		if (m_serverMappings != null && m_serverMappings.length > value.getServerIndex().intValue())
		{
			//				value.SetServerIndex(m_serverMappings[value.getNamespaceIndex()]);
			serverIndex = m_namespaceMappings[value.getNamespaceIndex()].intValue();
			indexChanged = true;
		}

		if(indexChanged)
			value = new ExpandedNodeId(UnsignedInteger.valueOf(serverIndex), namespaceIndex, value.getValue());

		return value;
	}

	/// <summary>
	/// Reads an ExpandedNodeId array from the stream.
	/// </summary>
	public ExpandedNodeId[] getExpandedNodeIdArray(String fieldName) throws DecodingException
	{
		List<ExpandedNodeId> values = new ArrayList<ExpandedNodeId>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			////pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("ExpandedNodeId"))
			{
				values.add(getExpandedNodeId("ExpandedNodeId"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new ExpandedNodeId[0]);
	}

	/// <summary>
	/// Reads an extension object from the stream.
	/// </summary>
	public ExtensionObject getExtensionObject(String fieldName) throws IllegalArgumentException, DecodingException
	{
		if (!BeginFieldSafe(fieldName, true))
		{
			return null;
		}

		//pushNamespace(OPC_UA_XSD_NAMESPACE);

		// read local type id.
		NodeId typeId = getNodeId("TypeId");

		// convert to absolute type id.
		ExpandedNodeId absoluteId = m_context.getNamespaceTable().toExpandedNodeId(typeId);//NodeId.ToExpandedNodeId(typeId, m_context.NamespaceUris);

		if (!NodeId.isNull(typeId) && ExpandedNodeId.isNull(absoluteId))
		{            
			logger.error(
					"Cannot de-serialized extension objects if the NamespaceUri is not in the NamespaceTable: Type = " + typeId 
			);
		}

		// read body.
		if (!BeginFieldSafe("Body", true))
		{
			// read end of extension object.
			EndField(fieldName);
			//popNamespace();

			try {
				return new ExtensionObject(m_context.getNamespaceTable().toNodeId(absoluteId), new XmlElement(""));
			} catch (ServiceResultException e) {
				throw new DecodingException(e);
			} // TODO is empty string ok?
		}

		// read the body.
		Object body = getExtensionObjectBody(absoluteId);

		// read end of body.
		EndField("Body");
		//popNamespace();

		// read end of extension object.
		EndField(fieldName);

		if(body instanceof XmlElement)
			try {
				return new ExtensionObject(m_context.getNamespaceTable().toNodeId(absoluteId), (XmlElement)body);
			} catch (ServiceResultException e) {
				throw new DecodingException(e);
			}
		else
			try {
				return new ExtensionObject(m_context.getNamespaceTable().toNodeId(absoluteId), (byte[])body);
			} catch (ServiceResultException e) {
				throw new DecodingException(e);
			}
	}

	/// <summary>
	/// Reads an array of extension objects from the stream.
	/// </summary>
	public ExtensionObject[] getExtensionObjectArray(String fieldName) throws DecodingException
	{
		List<ExtensionObject> values = new ArrayList<ExtensionObject>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("ExtensionObject"))
			{
				values.add(getExtensionObject("ExtensionObject"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new ExtensionObject[0]);
	}

	/// <summary>
	/// Reads the body extension object from the stream.
	/// </summary>
	public Object getExtensionObjectBody(ExpandedNodeId typeId) throws DecodingException
	{            
		moveToTag();

		// check for binary encoded body.
		if (m_reader.getLocalName() == "ByteString" && m_reader.getNamespaceURI() == OPC_UA_XSD_NAMESPACE)
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);
			byte[] bytes = getByteString("ByteString");       
			//popNamespace();

			return bytes;
		}
		return getXmlElement(EMPTY_STRING);
//		// check for empty body.
//		XmlDocument document = new XmlDocument();
//
//		if (m_reader.IsEmptyElement)
//		{        
//			document.InnerXml = getOuterXml();                        
//			return document.DocumentElement;
//		}

//		// lookup type.
//		IEncodeable encodeable = null;
//
////		Type systemType = m_context.Factory.GetSystemType(typeId);
//		Class<? extends IEncodeable> clazz;
//		try {
//			clazz = m_context.getEncodeableSerializer().getClass(m_context.getNamespaceTable().toNodeId(typeId));
//		} catch (ServiceResultException e) {
//			throw new DecodingException(e);
//		}
//
//		// decode known type.
//		if (clazz != null)
//		{                      
//			//pushNamespace(m_reader.getNamespaceURI());
//			encodeable = getEncodeable(m_reader.getLocalName(), clazz);
//			//popNamespace();
//
//			return encodeable;
//		}
//
//		// return undecoded xml body.                 
////		document.InnerXml = getOuterXml();                        
//		return getOuterXml();//document.DocumentElement;
	}


	/// <summary>
	/// Reads a float from the stream.
	/// </summary>
	public Float getFloat(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{

			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				float value = 0;

				if (xml.length() == 3)
				{
					if (xml == "NaN")
					{
						value = Float.NaN;
					}

					if (xml == "INF")
					{
						value = Float.POSITIVE_INFINITY;
					}
				}

				if (xml.length() == 4)
				{
					if (xml == "-INF")
					{
						value = Float.NEGATIVE_INFINITY;
					}
				}

				if (value == 0)
				{
					value = Float.parseFloat(xml);
				}

				EndField(fieldName);
				return value;
			}
		}

		return (float)0;
	}

	/// <summary>
	/// Reads a float array from the stream.
	/// </summary>
	public Float[] getFloatArray(String fieldName) throws DecodingException
	{
		List<Float> values = new ArrayList<Float>();

		if (BeginFieldSafe(fieldName, true))

		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Float"))
			{
				values.add(getFloat("Float"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Float[0]);
	}

	/// <summary>
	/// Reads a GUID from the stream.
	/// </summary>
	public UUID getGuid(String fieldName) throws DecodingException
	{
		//	            UUID value = 
		String guidString = null;
		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);
			guidString = getString("String");
			//	                value.GuidString = getString("String");
			//popNamespace();

			EndField(fieldName);
		}

		return UUID.fromString(guidString);
	}

	/// <summary>
	/// Reads a GUID array from the stream.
	/// </summary>
	public UUID[] getGuidArray(String fieldName) throws DecodingException
	{
		List<UUID> values = new ArrayList<UUID>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Guid"))
			{
				values.add(getGuid("Guid"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}
		

		return values.toArray(new UUID[0]);
	}

	/// <summary>
	/// Reads a short from the stream.
	/// </summary>
	public Short getInt16(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				short value = Short.parseShort(xml);
				EndField(fieldName);
				return value;
			}
		}

		return 0;
	}

	/// <summary>
	/// Reads a short array from the stream.
	/// </summary>
	public Short[] getInt16Array(String fieldName) throws DecodingException
	{
		List<Short> values = new ArrayList<Short>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Int16"))
			{
				values.add(getInt16("Int16"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Short[0]);
	}

	/// <summary>
	/// Reads an int from the stream.
	/// </summary>
	public Integer getInt32(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				int value = Integer.parseInt(xml);
				EndField(fieldName);
				return value;
			}
		}

		return 0;
	}

	/// <summary>
	/// Reads a int array from the stream.
	/// </summary>
	public Integer[] getInt32Array(String fieldName) throws DecodingException
	{
		List<Integer> values = new ArrayList<Integer>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Int32"))
			{
				values.add(getInt32("Int32"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Integer[0]);
	}

	/// <summary>
	/// Reads a int array from the stream.
	/// </summary>
	public int[] getInt32Array_(String fieldName) throws DecodingException
	{
		List<Integer> values = new ArrayList<Integer>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Int32"))
			{
				values.add(getInt32("Int32"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);

			// Create array
			int[] array = new int[values.size()];
			for(int i : values)
				array[i] = values.get(i);
			return array;
		}

		return new int[0];
	}

	/// <summary>
	/// Reads a long from the stream.
	/// </summary>
	public Long getInt64(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				long value = Long.parseLong(xml);
				EndField(fieldName);
				return value;
			}
		}

		return (long)0;
	}

	/// <summary>
	/// Reads a long array from the stream.
	/// </summary>
	public Long[] getInt64Array(String fieldName) throws DecodingException
	{
		List<Long> values = new ArrayList<Long>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Int64"))
			{
				values.add(getInt64("Int64"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Long[0]);
	}

	/// <summary>
	/// Reads an LocalizedText from the stream.
	/// </summary>
	public LocalizedText getLocalizedText(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			boolean isNil = false;
			String text = null;
			String locale = null;

			if (BeginFieldSafe("Locale", true))//, out isNil))
			{
				locale = getString(null);
				EndField("Locale");
			}
			else if (!isNil)
			{
				locale = EMPTY_STRING;
			}

			if (BeginFieldSafe("Text", true))//, out isNil))
			{
				text = getString(null);
				EndField("Text");
			}                
			else if (!isNil)
			{
				text = EMPTY_STRING;
			}

			LocalizedText value = new LocalizedText(text, locale);

			//popNamespace();

			EndField(fieldName);
			return value;
		}

		return LocalizedText.NULL;
	}

	/// <summary>
	/// Reads an LocalizedText array from the stream.
	/// </summary>
	public LocalizedText[] getLocalizedTextArray(String fieldName) throws DecodingException
	{
		List<LocalizedText> values = new ArrayList<LocalizedText>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("LocalizedText"))
			{
				values.add(getLocalizedText("LocalizedText"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new LocalizedText[0]);
	}

	@SuppressWarnings("unchecked")
	public <T extends IEncodeable> T getMessage()
	throws DecodingException	
	{
		NodeId	id 					= getNodeId(null);
		if (id==null) throw new DecodingException("Cannot decode "+id);
		Class<T> clazz = (Class<T>) m_context.getEncodeableSerializer().getClass(id);
		if (clazz==null) throw new DecodingException("Cannot decode "+id);
		return (T) m_context.getEncodeableSerializer().getEncodeable(clazz, this);
	}


	/// <summary>
	/// Reads an NodeId from the stream.
	/// </summary>
	public NodeId getNodeId(String fieldName) throws IllegalArgumentException, DecodingException
	{
		NodeId value = null;//;new NodeId();

		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);
			//	                value.IdentifierText = getString("Identifier");
			value = NodeId.parseNodeId(getString("Identifier"));
			//popNamespace();

			EndField(fieldName);
		}

		if (m_namespaceMappings != null && m_namespaceMappings.length > value.getNamespaceIndex())
		{
			value = NodeId.get(value.getIdType(), m_namespaceMappings[value.getNamespaceIndex()].intValue(), value.getValue());
			//				value = new NodeId(m_namespaceMappings[value.getNamespaceIndex()].intValue(), value.getValue());
			//				value.SetNamespaceIndex(m_namespaceMappings[value.getNamespaceIndex()]);
		}

		return value;
	}

	/// <summary>
	/// Reads an NodeId array from the stream.
	/// </summary>
	public NodeId[] getNodeIdArray(String fieldName) throws DecodingException
	{
		List<NodeId> values = new ArrayList<NodeId>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("NodeId"))
			{
				values.add(getNodeId("NodeId"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new NodeId[0]);
	}

	/// <summary>
	/// Reads an QualifiedName from the stream.
	/// </summary>
	public QualifiedName getQualifiedName(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			UnsignedShort namespaceIndex = UnsignedShort.ZERO;

			if (BeginFieldSafe("NamespaceIndex", true))
			{
				namespaceIndex = getUInt16(null);
				EndField("NamespaceIndex");
			}

			String name = null;

			if (BeginFieldSafe("Name", true))
			{
				name = getString(null);
				EndField("Name");
			}
			//				else if (!isNil)
			//				{
			//					name = String.Empty;
			//				}

			//popNamespace();
			EndField(fieldName);

			if (m_namespaceMappings != null && m_namespaceMappings.length > namespaceIndex.getValue())
			{
				namespaceIndex = m_namespaceMappings[namespaceIndex.getValue()];
			}

			return new QualifiedName(namespaceIndex, name);
		}

		return QualifiedName.NULL;
	}

	/// <summary>
	/// Reads an QualifiedName array from the stream.
	/// </summary>
	public QualifiedName[] getQualifiedNameArray(String fieldName) throws DecodingException
	{
		List<QualifiedName> values = new ArrayList<QualifiedName>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("QualifiedName"))
			{
				values.add(getQualifiedName("QualifiedName"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new QualifiedName[0]);
	}

	/// <summary>
	/// Reads a sbyte from the stream.
	/// </summary>
	public Byte getSByte(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				byte value = Byte.parseByte(xml);
				EndField(fieldName);
				return value;
			}
		}

		return 0;
	}

	/// <summary>
	/// Reads a sbyte array from the stream.
	/// </summary>
	public Byte[] getSByteArray(String fieldName) throws DecodingException
	{        
		List<Byte> values = new ArrayList<Byte>();

		if (BeginFieldSafe(fieldName, true))
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("SByte"))
			{
				values.add(getSByte("SByte"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Byte[0]);
	}

	public Object getScalarObject(String fieldName, int builtinTypeId)
	throws DecodingException	
	{
		switch (builtinTypeId) {
		case 1: return getBoolean(null);
		case 2: return getSByte(null);
		case 3: return getByte(null);
		case 4: return getInt16(null);
		case 5: return getUInt16(null);
		case 6: return getInt32(null);
		case 7: return getUInt32(null);
		case 8: return getInt64(null);
		case 9: return getUInt64(null);
		case 10: return getFloat(null);
		case 11: return getDouble(null);
		case 12: return getString(null);
		case 13: return getDateTime(null);
		case 14: return getGuid(null);
		case 15: return getByteString(null);
		case 16: return getXmlElement(null);
		case 17: return getNodeId(null);
		case 18: return getExpandedNodeId(null);
		case 19: return getStatusCode(null);
		case 20: return getQualifiedName(null);
		case 21: return getLocalizedText(null);
		case 22: return getExtensionObject(null);
		case 23: return getDataValue(null);
		case 24: return getVariant(null);		
		case 25: return getDiagnosticInfo(null);
		}
		throw new DecodingException("Cannot decode builtin type id "+builtinTypeId);
	}

	/// <summary>
	/// This method calls IsStartElement followed by Read to position you on the content of that element found in the input stream.
	/// </summary>
	public void getStartElement() throws DecodingException
	{
		if(m_reader.isStartElement())
			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			} 
	}

	/// <summary>
	/// Reads an StatusCode from the stream.
	/// </summary>
	public StatusCode getStatusCode(String fieldName) throws DecodingException
	{
		StatusCode value = StatusCode.getFromBits(0);

		if (BeginFieldSafe(fieldName, true))
		{                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);
			value = new StatusCode(getUInt32("Code"));
			//popNamespace();

			EndField(fieldName);
		}

		return value;
	}

	/// <summary>
	/// Reads an StatusCode array from the stream.
	/// </summary>
	public StatusCode[] getStatusCodeArray(String fieldName) throws DecodingException
	{
		List<StatusCode> values = new ArrayList<StatusCode>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("StatusCode"))
			{
				values.add(getStatusCode("StatusCode"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new StatusCode[0]);
	}

	/// <summary>
	/// Reads a String from the stream.
	/// </summary>
	public String getString(String fieldName) throws DecodingException
	{
			if (BeginFieldSafe(fieldName, true))
			{
				String value = getString();

				if (value != null)
				{
					value = value.trim();
				}

				EndField(fieldName);
				return value;
			}

		return null;
	}

	/// <summary>
	/// Reads a String array from the stream.
	/// </summary>
	public String[] getStringArray(String fieldName) throws DecodingException
	{
		List<String> values = new ArrayList<String>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("String"))
			{
				values.add(getString("String"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new String[0]);
	}

	@Override
	public Structure getStructure(String fieldName) 
	throws DecodingException 
	{
//		try {
			NodeId typeId = getNodeId(null);
//			int encodingByte = in.get(); // TODO support for encodingByte and binary
//			if (encodingByte==0) return null;
//			if (encodingByte==1) {						
//				Class<? extends IEncodeable> clazz = m_context.getEncodeableSerializer().getClass(typeId);
//				getInt32(null);			 // length
//				return (Structure) getEncodeable(fieldName, clazz);
//			}
//			if (encodingByte==2) {
				Class<? extends IEncodeable> clazz = m_context.getEncodeableSerializer().getClass(typeId);
				
				return (Structure) getEncodeable(fieldName, clazz);			
//			}
//			throw new DecodingException("Unexpected encoding byte ("+encodingByte+") in ExtensionObject");
//		} catch (IOException e) {
//			throw toDecodingException(e);
//		}
	}

	@Override
	public Structure[] getStructureArray(String fieldName) 
	throws DecodingException 
	{
		List<Structure> values = new ArrayList<Structure>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Structure"))
			{
				values.add(getStructure(null));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}
		
//			int len = in.getInt();
//			if (len==-1) return null;
//			assertArrayLength(len);
//			if (len>remaining()) throw new DecodingException(StatusCodes.Bad_EndOfStream, "Buffer underflow");
//			Structure[] result = new Structure[len];
//			for (int i=0; i<len; i++)
//				result[i] = getStructure(null);
//			return result;
		return values.toArray(new Structure[0]);
	}

	/// <summary>
	/// Reads a ushort from the stream.
	/// </summary>
	public UnsignedShort getUInt16(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				UnsignedShort value = UnsignedShort.parseUnsignedShort(xml);
				EndField(fieldName);
				return value;
			}
		}

		return UnsignedShort.ZERO;
	}

	/// <summary>
	/// Reads a ushort array from the stream.
	/// </summary>
	public UnsignedShort[] getUInt16Array(String fieldName) throws DecodingException
	{
		List<UnsignedShort> values = new ArrayList<UnsignedShort>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("UInt16"))
			{
				values.add(getUInt16("UInt16"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new UnsignedShort[0]);
	}

	/// <summary>
	/// Reads a UnsignedInteger from the stream.
	/// </summary>
	public UnsignedInteger getUInt32(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				UnsignedInteger value = UnsignedInteger.parseUnsignedInteger(xml);
				EndField(fieldName);
				return value;
			}
		}

		return UnsignedInteger.ZERO;
	}

	/// <summary>
	/// Reads a UnsignedInteger array from the stream.
	/// </summary>
	public UnsignedInteger[] getUInt32Array(String fieldName) throws DecodingException
	{
		List<UnsignedInteger> values = new ArrayList<UnsignedInteger>();


			if (BeginFieldSafe(fieldName, true))
			{                                
				//pushNamespace(OPC_UA_XSD_NAMESPACE);

				while (MoveToElement("UInt32"))
				{
					values.add(getUInt32("UInt32"));
				}

				// check the length.
				if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
				{
					throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
				}

				//popNamespace();

				EndField(fieldName);
			}


		return values.toArray(new UnsignedInteger[0]);
	}

	/// <summary>
	/// Reads a UnsignedLong from the stream.
	/// </summary>
	public UnsignedLong getUInt64(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			String xml = getString();

			if (!IsNullOrEmpty(xml))
			{
				UnsignedLong value = UnsignedLong.parseUnsignedLong(xml);
				EndField(fieldName);
				return value;
			}
		}

		return UnsignedLong.valueOf(0);
	}

	/// <summary>
	/// Reads a UnsignedLong array from the stream.
	/// </summary>
	public UnsignedLong[] getUInt64Array(String fieldName) throws DecodingException
	{
		List<UnsignedLong> values = new ArrayList<UnsignedLong>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("UInt64"))
			{
				values.add(getUInt64("UInt64"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new UnsignedLong[0]);
	}

	/// <summary>
	/// Reads an Variant from the stream.
	/// </summary>
	public Variant getVariant(String fieldName) throws DecodingException
	{
		Variant value = new Variant(null);

		if (BeginFieldSafe(fieldName, true))
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			if (BeginFieldSafe("Value", true))
			{
				//					TypeInfo typeInfo = null;
				Object variantContents = getVariantContents();//out typeInfo); // TODO
				value = new Variant(variantContents);//new Variant(variantContents, typeInfo);
				EndField("Value");
			}

			//popNamespace();

			if (!IsNullOrEmpty(fieldName))
			{
				EndField(fieldName);
			}
		}

		return value;
	}

	/// <summary>
	/// Reads an Variant array from the stream.
	/// </summary>
	public Variant[] getVariantArray(String fieldName) throws DecodingException
	{
		List<Variant> values = new ArrayList<Variant>();

		if (BeginFieldSafe(fieldName, true))
		{                                
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("Variant"))
			{
				values.add(getVariant("Variant"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new Variant[0]);
	}

	/// <summary>
	/// Reads the contents of an Variant object.
	/// </summary>
	/// <returns></returns>
	public Object getVariantContents() throws DecodingException//out TypeInfo typeInfo)
	{
		//		typeInfo = TypeInfo.Unknown;

		// skip whitespace.
		while (m_reader.getEventType() != XMLStreamConstants.START_ELEMENT)// XmlNodeType.Element) TODO should there also be END_ELEMENT?
		{
			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			}
		}

//		try
//		{
//			m_namespaces.push(OPC_UA_XSD_NAMESPACE);

			String typeName = m_reader.getLocalName();

			// process array types.
			if (typeName.startsWith("ListOf"))
			{
				String type = typeName.substring("ListOf".length());
				if (type.equals("Boolean")) { return getBooleanArray(typeName); }
				else if (type.equals("SByte")) { return getSByteArray(typeName); }
				else if (type.equals("Byte")) { return getByteArray(typeName); }
				else if (type.equals("Int16")) { return getInt16Array(typeName); }
				else if (type.equals("UInt16")) { return getUInt16Array(typeName); }
				else if (type.equals("Int32")) { return getInt32Array(typeName); }
				else if (type.equals("UInt32")) { return getUInt32Array(typeName); }
				else if (type.equals("Int64")) { return getInt64Array(typeName); }
				else if (type.equals("UInt64")) { return getUInt64Array(typeName); }
				else if (type.equals("Float")) { return getFloatArray(typeName); }
				else if (type.equals("Double")) { return getDoubleArray(typeName); }
				else if (type.equals("String")) { return getStringArray(typeName); }
				else if (type.equals("DateTime")) { return getDateTimeArray(typeName); }
				else if (type.equals("Guid")) { return getGuidArray(typeName); }
				else if (type.equals("ByteString")) { return getByteStringArray(typeName); }
				else if (type.equals("XmlElement")) { return getXmlElementArray(typeName); }
				else if (type.equals("NodeId")) { return getNodeIdArray(typeName); }
				else if (type.equals("ExpandedNodeId")) { return getExpandedNodeIdArray(typeName); }
				else if (type.equals("StatusCode")) { return getStatusCodeArray(typeName); }
				else if (type.equals("DiagnosticInfo")) { return getDiagnosticInfoArray(typeName); }
				else if (type.equals("QualifiedName")) { return getQualifiedNameArray(typeName); }
				else if (type.equals("LocalizedText")) { return getLocalizedTextArray(typeName); }
				else if (type.equals("ExtensionObject")) { return getExtensionObjectArray(typeName); }
				else if (type.equals("DataValue")) { return getDataValueArray(typeName); }
				else if (type.equals("Variant")) { return getVariantArray(typeName); }  
			}

			// process scalar types.
			else
			{
				if(typeName.equals("Null"))            
				{
					if (BeginFieldSafe(typeName, true))
					{
						EndField(typeName);
					}

					return null;
				}

				else if (typeName.equals("Boolean")) { return getBoolean(typeName); }
				else if (typeName.equals("SByte")) { return getSByte(typeName); }
				else if (typeName.equals("Byte")) { return getByte(typeName); }
				else if (typeName.equals("Int16")) { return getInt16(typeName); }
				else if (typeName.equals("UInt16")) { return getUInt16(typeName); }
				else if (typeName.equals("Int32")) { return getInt32(typeName); }
				else if (typeName.equals("UInt32")) { return getUInt32(typeName); }
				else if (typeName.equals("Int64")) { return getInt64(typeName); }
				else if (typeName.equals("UInt64")) { return getUInt64(typeName); }
				else if (typeName.equals("Float")) { return getFloat(typeName); }
				else if (typeName.equals("Double")) { return getDouble(typeName); }
				else if (typeName.equals("String")) { return getString(typeName); }
				else if (typeName.equals("DateTime")) { return getDateTime(typeName); }
				else if (typeName.equals("Guid")) { return getGuid(typeName); }
				else if (typeName.equals("ByteString")) { return getByteString(typeName); }
				else if (typeName.equals("XmlElement")) { return getXmlElement(typeName); }
				else if (typeName.equals("NodeId")) { return getNodeId(typeName); }
				else if (typeName.equals("ExpandedNodeId")) { return getExpandedNodeId(typeName); }
				else if (typeName.equals("StatusCode")) { return getStatusCode(typeName); }
				else if (typeName.equals("DiagnosticInfo")) { return getDiagnosticInfo(typeName); }                        
				else if (typeName.equals("QualifiedName")) { return getQualifiedName(typeName); }
				else if (typeName.equals("LocalizedText")) { return getLocalizedText(typeName); }
				else if (typeName.equals("ExtensionObject")) { return getExtensionObject(typeName); }
				else if (typeName.equals("DataValue")) { return getDataValue(typeName); }

				//				else if (typeName.equals("Matrix"))  TODO how should this be replaced?
				//				{
				//					Matrix matrix = getMatrix(typeName);
				//					typeInfo = matrix.TypeInfo;
				//					return matrix;     
				//				}
				//				}
			}

			throw new DecodingException(
					StatusCodes.Bad_DecodingError,
					"Element '" + 
					m_reader.getNamespaceURI() + 
					":" +
					m_reader.getLocalName() + 
			"' is not allowed in an Variant."); 
//		}
//		finally
//		{
//			m_namespaces.pop();
//		}
	}

	/// <summary>
	/// Reads an XmlElement from the stream.
	/// </summary>
	public XmlElement getXmlElement(String fieldName) throws DecodingException
	{
		if (BeginFieldSafe(fieldName, true))
		{
			return new XmlElement(getInnerXml(""));
		}

		return null;
	}

	private String getInnerXml(String fieldName) throws DecodingException {
	    String innerXml=""; // TODO refine and fix implementation
	    boolean isGetme=true;
	    int level = 0;
    	int eventType;
        try {
			do {
			    eventType = m_reader.getEventType();
			    switch (eventType) {
			        case XMLStreamConstants.START_ELEMENT:
			            if(m_reader.getLocalName().equals(fieldName)){
			                isGetme=true;
			            }
			            if(isGetme){
			                innerXml+="<"+m_reader.getLocalName()+">";
			            }
			            level++;
			            break;
			        case XMLStreamConstants.CHARACTERS:
			            if(isGetme){
			                innerXml+=m_reader.getText();
			            }
			            break;
			        case XMLStreamConstants.END_ELEMENT:
			            if (--level < 0)
			            	return innerXml;
			            if(m_reader.getLocalName().equals(fieldName)){
			                innerXml+="</"+m_reader.getLocalName()+">";
			                isGetme=false;
			            }
			            if(isGetme && !m_reader.getLocalName().equals(fieldName)){
			                innerXml+="</"+m_reader.getLocalName()+">";
			            }
			            break;
			        default:
			            break;
			    }
			    m_reader.next();
			} while (m_reader.hasNext());
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			}
	    return innerXml;
	}

	/// <summary>
	/// Reads an XmlElement array from the stream.
	/// </summary>
	public XmlElement[] getXmlElementArray(String fieldName) throws DecodingException
	{
		List<XmlElement> values = new ArrayList<XmlElement>();

		if (BeginFieldSafe(fieldName, true))
		{
			//pushNamespace(OPC_UA_XSD_NAMESPACE);

			while (MoveToElement("XmlElement"))
			{
				values.add(getXmlElement("XmlElement"));
			}

			// check the length.
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < values.size())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}

			//popNamespace();

			EndField(fieldName);
		}

		return values.toArray(new XmlElement[0]);
	}

	/// <summary>
	/// Initializes a String table from an XML stream.
	/// </summary>
	/// <param name="tableName">Name of the table.</param>
	/// <param name="elementName">Name of the element.</param>
	/// <param name="stringTable">The String table.</param>
	/// <returns>True if the table was found. False otherwise.</returns>
	public boolean LoadStringTable(String tableName, String elementName, List<String> stringTable) throws DecodingException
	{
		//pushNamespace(OPC_UA_XSD_NAMESPACE);

		try
		{
			if (!Peek(tableName))
			{
				return false;
			}

			getStartElement();

			while (Peek(elementName))
			{
				String namespaceUri = getString(elementName);
				stringTable.add(namespaceUri);
			}

			Skip(new QName(tableName, OPC_UA_XSD_NAMESPACE));
			return true;
		}
		finally
		{
			//popNamespace();
		}
	}

	/**
	 * Checks whether the current node is a content (non-white space text, CDATA, Element, EndElement, 
	 * EntityReference, or EndEntity) node. If the node is not a content node, the reader skips ahead 
	 * to the next content node or end of file. It skips over nodes of the following type: 
	 * ProcessingInstruction, DocumentType, Comment, Whitespace, or SignificantWhitespace.
	 */
	public void moveToContent() {
		while(m_reader.getEventType() != XMLStreamConstants.CDATA &&
				m_reader.getEventType() != XMLStreamConstants.START_ELEMENT &&
				m_reader.getEventType() != XMLStreamConstants.END_ELEMENT &&
				m_reader.getEventType() != XMLStreamConstants.ENTITY_REFERENCE &&
				m_reader.getEventType() != XMLStreamConstants.CHARACTERS &&
				m_reader.getEventType() != XMLStreamConstants.END_DOCUMENT)
			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				return;
			}
	}

	public void moveToEnd() {
		while(m_reader.getEventType() != XMLStreamConstants.END_ELEMENT &&
				m_reader.getEventType() != XMLStreamConstants.END_DOCUMENT)
			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				return;
			}
	}
	/// <summary>
	/// Returns true if the specified field is the next element to be extracted.
	/// </summary>
	public boolean Peek(String fieldName)
	{
		moveToContent();

		if (XMLStreamConstants.START_ELEMENT != m_reader.getEventType())
		{
			return false;
		}

		String localName = m_reader.getLocalName();
		if (!fieldName.equals(localName))
		{
			return false;
		}

//		if (m_namespaces.peek() != m_reader.getNamespaceURI())
//		{
//			return false;
//		}

		return true;
	}

	/// <summary>
	/// Returns the qualified name for the next element in the stream.
	/// </summary>
	public QName Peek(int nodeType)
	{
		moveToContent();

		if (/*nodeType != XMLStreamConstants.None &&*/ nodeType != m_reader.getEventType())
		{
			return null;
		}

		return new QName(m_reader.getLocalName(), m_reader.getNamespaceURI());
	}

//	/// <summary>
//	/// Pops a namespace from the namespace stack.
//	/// </summary>
//	public void popNamespace()
//	{
//		m_namespaces.pop();
//	}
//
//	/// <summary>
//	/// Pushes a namespace onto the namespace stack.
//	/// </summary>
//	public void pushNamespace(String namespaceUri)
//	{
//		m_namespaces.push(namespaceUri);
//	}        

	/// <summary>
	/// Initializes the tables used to map namespace and server uris during decoding.
	/// </summary>
	/// <param name="namespaceUris">The namespaces URIs referenced by the data being decoded.</param>
	/// <param name="serverUris">The server URIs referenced by the data being decoded.</param>
	public void SetMappingTables(NamespaceTable namespaceUris)//, List<String> serverUris) TODO add support for server uris
	{
		m_namespaceMappings = null;

		if (namespaceUris != null && m_context.getNamespaceTable().toArray() != null)
		{
			m_namespaceMappings = CreateMapping(namespaceUris.toArray(), false);
		}

		//		m_serverMappings = null;
		//
		//		if (serverUris != null && m_context.ServerUris != null)
		//		{
		//			m_serverMappings = CreateMapping(serverUris, false);
		//		}
	}

	/**
	 * Equivalent to XmlReader.Skip()
	 * @throws XMLStreamException 
	 */
	public void skip() throws XMLStreamException {
		int depth = 0;
		m_reader.next();
		if(m_reader.getEventType() != XMLStreamConstants.END_ELEMENT)
		{
			depth++;
			while(depth != 0)
			{
				m_reader.next();
				if(m_reader.getEventType() != XMLStreamConstants.START_ELEMENT)
					depth++;
				else if(m_reader.getEventType() != XMLStreamConstants.START_ELEMENT)
					depth--;
			}
		}
		// Reached same level, proceed to next element
		m_reader.next();
	}



	//			/// <summary>
	//			/// Reads an Matrix from the stream.
	//			/// </summary>
	//			private Matrix getMatrix(String fieldName)
	//			{
	//				Array elements = null;
	//				Int32Collection dimensions = null;
	//				TypeInfo typeInfo = null;
	//
	//				if (BeginFieldSafe(fieldName, true))
	//				{
	//					//pushNamespace(OPC_UA_XSD_NAMESPACE);
	//
	//					if (BeginFieldSafe("Elements", true))
	//					{   
	//						//	                    object contents = getVariantContents(out typeInfo); TODO
	//						elements = contents as Array;
	//						EndField("Elements");
	//					}
	//
	//					dimensions = getInt32Array("Dimensions");
	//
	//					//popNamespace();
	//
	//					EndField(fieldName);
	//				}
	//
	//				if (elements == null)
	//				{                
	//					throw new DecodingException(StatusCodes.BadDecodingError, "The Matrix contains invalid elements");
	//				}
	//
	//				if (dimensions != null && dimensions.Count > 0)
	//				{
	//					return new Matrix(elements, typeInfo.BuiltInType, dimensions.ToArray());
	//				}
	//
	//				return new Matrix(elements, typeInfo.BuiltInType);
	//			}

	/// <summary>
	/// Skips to the end of the specified element.
	/// </summary>
	/// <param name="qname">The qualified name of the element to skip.</param>
	public void Skip(QName qname) throws DecodingException
	{
		moveToContent();

		int depth = 1;

		while (depth > 0)
		{
			if (m_reader.getEventType() == XMLStreamConstants.END_ELEMENT)
			{
				if (m_reader.getLocalName().equals(qname.getLocalPart()) && m_reader.getNamespaceURI().equals(qname.getNamespaceURI()))
				{
					depth--;
				}
			}

			else if (m_reader.getEventType() == XMLStreamConstants.START_ELEMENT)//.Element)
				{
				if (m_reader.getLocalName().equals(qname.getLocalPart()) && m_reader.getNamespaceURI().equals(qname.getNamespaceURI()))
				{
					depth++;
				}
				}

			try {
				skip();
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			}
			moveToContent();
		}      
	}
	
	private boolean isStartElement(String localname)//, String namespace)
	{
		moveToContent();
		return Peek(localname);// m_reader.getEventType() == XMLStreamConstants.START_ELEMENT;
	}

	/// <summary>
	/// Reads the start of field.
	/// </summary>
	private boolean BeginField(String fieldName, boolean isOptional)/*, out boolean isNil)*/ throws NilException, DecodingException
	{
		//	            isNil = false;

		// allow caller to skip reading element tag if field name is not specified.
		if (IsNullOrEmpty(fieldName))
		{
			return true;
		}

		// move to the next node.
		moveToTag();

		// check if requested element is present.
		if (!isStartElement(fieldName))//, m_namespaces.peek()))
		{
			if (!isOptional)
			{
				throw new DecodingException(
						StatusCodes.Bad_DecodingError,
						String.format("Encountered element: '{1}:{0}' when expecting element: '{2}'.", m_reader.getLocalName(), m_reader.getNamespaceURI(), fieldName));//, m_namespaces.peek()));
			}

			throw new NilException();//isNil = true;
		}

		// check for empty or nil element.
		if (m_reader.getAttributeCount() != 0)//HasAttributes)
		{
			String nilValue = m_reader.getAttributeValue("nil", XML_SCHEMA_INSTANCE);

			if (!IsNullOrEmpty(nilValue))
			{
				if (Boolean.parseBoolean(nilValue))
				{
					throw new NilException();//isNil = true;
				}
			}
		}

		getStartElement();

		moveToContent();

		// check for an element with no children but not empty (due to
		// whitespace).
		if (m_reader.getEventType() == XMLStreamConstants.END_ELEMENT) {
			if (m_reader.getLocalName() == fieldName)// &&
														// m_reader.getNamespaceURI()
														// ==
														// m_namespaces.peek())
			{
				getEndElement();
				return false;
			}
		}

		// caller must read contents of element.
		return true;///*!isNil && */!isEmpty;
	}

	/**
	 * @throws DecodingException
	 */
	private void moveToTag() throws DecodingException {
		while(m_reader.getEventType() != XMLStreamConstants.START_ELEMENT &&
				m_reader.getEventType() != XMLStreamConstants.END_ELEMENT &&
				m_reader.getEventType() != XMLStreamConstants.ENTITY_REFERENCE &&
				m_reader.getEventType() != XMLStreamConstants.END_DOCUMENT)
			try {
				m_reader.nextTag();
			} catch (XMLStreamException e) {
				return;
			}
	}

	/// <summary>
	/// Reads the start of filed where the presences of the xsi:nil attribute is not significant.
	/// </summary>
	private boolean BeginFieldSafe(String fieldName, boolean isOptional) throws DecodingException
	{
		try {
			return BeginField(fieldName, isOptional);
		} catch (NilException e) {
			logger.warn("BeginField failed for fieldName=" + fieldName, e);
			return false;
		}
	}

	private UnsignedShort[] CreateMapping(String[] source,
			boolean updateTable) {
		if (source == null)
		{
			return null;
		}

		UnsignedShort[] mapping = new UnsignedShort[source.length];

		for (int ii = 0; ii < source.length; ii++)
		{
			String uri = source[ii];

			int index = m_context.getNamespaceTable().getIndex(uri);

			if (index < 0)
			{
				if (!updateTable)
				{
					mapping[ii] = UnsignedShort.MAX_VALUE;
					continue;
				}

				index = m_context.getNamespaceTable().add(-1, uri);
			}

			mapping[ii] = UnsignedShort.valueOf(index);
		}

		return mapping;
	}

	/// <summary>
	/// Reads the end of a field.
	/// </summary>
	private void EndField(String fieldName) throws DecodingException
	{
		if (!IsNullOrEmpty(fieldName))
		{
//			try {
//				m_reader.next();
//			} catch (XMLStreamException e) {
//				throw new DecodingException(
//						StatusCodes.Bad_DecodingError,
//						"Encountered element: '"+
//								localName+
//								":"+
//								m_reader.getNamespaceURI()+
//								"' when expecting end element: '"+
//								fieldName);
//			}
			moveToEnd();

			int eventType = m_reader.getEventType();
			String localName = m_reader.getLocalName();
			if (eventType != XMLStreamConstants.END_ELEMENT)
				throw new DecodingException(
						StatusCodes.Bad_DecodingError,
						"No end element found: '"+
						localName+
						":"+
						m_reader.getNamespaceURI() +
				"' eventType="+eventType);
				
				if (!localName.equals(fieldName))// || m_reader.getNamespaceURI() != m_namespaces.peek())
			{                    
				throw new DecodingException(
						StatusCodes.Bad_DecodingError,
						"Encountered end element: '"+
						localName+
						":"+
						m_reader.getNamespaceURI()+
						"' when expecting element: '"+
						fieldName+
//						":"+
//						m_namespaces.peek()+
				"'.");
			}

			getEndElement();
		}
	}

	/**
	 * Equivalent to XmlReader.ReadString()
	 * @return
	 * @throws DecodingException 
	 * @throws XMLStreamException 
	 */
	private String getContentAsString() throws DecodingException{
		//		return m_reader.getContentAsString();
		String text = m_reader.getText();
		try {
			m_reader.next();
		} catch (XMLStreamException e) {
			//throw new DecodingException(e);
		}
		return text;
	}
	/// <summary>
	/// Reads a String from the stream.
	/// </summary>
	private String getString() throws DecodingException
	{
		String value;
		value = getContentAsString();

		// check the length.
		if (value != null)
		{            
			if (m_context.getMaxArrayLength() > 0 && m_context.getMaxArrayLength() < value.length())
			{
				throw new DecodingException(StatusCodes.Bad_EncodingLimitsExceeded);
			}
		}

		return value;
	}
	/// <summary>
	/// Sets private members to default values.
	/// </summary>
	private void Initialize()
	{
		m_reader     = null;
//		m_namespaces = new Stack<String>();
	}
	private boolean IsNullOrEmpty(String xml) {
		if(xml == null)
			return true;
		if(xml.trim().length() == 0)
			return true;
		return false;
	}
	/// <summary>
	/// Moves to the next start element.
	/// </summary>
	private boolean MoveToElement(String elementName) throws DecodingException
	{
		while (!m_reader.isStartElement())
		{
			if (/*m_reader.getEventType() == XmlStreamConstants.None || TODO is this necessary? */m_reader.getEventType() == XMLStreamConstants.END_ELEMENT)
			{
				return false;
			}

			try {
				m_reader.next();
			} catch (XMLStreamException e) {
				throw new DecodingException(e);
			}
		}

		if (IsNullOrEmpty(elementName))
		{
			return true;
		}

		return (m_reader.getLocalName() == elementName);// && m_reader.getNamespaceURI() == m_namespaces.peek());
	}

	@Override
	public void pushNamespace(String namespaceUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popNamespace() {
		// TODO Auto-generated method stub
		
	}

	public EncoderContext getEncoderContext() {
		return m_context;
	}
	
	public void setEncoderContext(EncoderContext ctx) {
		this.m_context = ctx;
	}

}
