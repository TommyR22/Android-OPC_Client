This is the OPC UA Java Stack Distribution.

The stack requires Java SE 6 (JDK 1.6) or later.

  * Libraries

Necessary libraries for all applications are

Apache Log4J: log4j*.jar

http://logging.apache.org/log4j/1.2/

Security libraries

Bouncy Castle: bc*.jar
Spongy Castle: sc*.jar

http://www.bouncycastle.org/
http://rtyley.github.io/spongycastle/

HTTPS libraries

Apache HTTP Core: http*.jar
Apache Commons logging: commons-logging-*.jar

http://hc.apache.org/
http://commons.apache.org/proper/commons-logging/

 * Licenses

Library licenses are in the license-directory. 

All Apache libraries use the Apache License 2.0. 
Bouncy Castle and Spongy Castle use the Bouncy Castle License. 

  * Security

Security libraries are used as they are available. If no extra libraries are available, 
the Sun JCE implementation will be used.  Testing has so far based on the Bouncy Castle 
library and it is therefore recommended in normal applications.

For SunJCE 256 bit support, you will have to install the JCE Unlimited Strength 
Jurisdiction Policy Files, from Oracle (for Java 6 or 7, respectively):

http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html
http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html

Android includes a limited version of Bouncy Castle and the standard Bouncy Castle cannot 
be installed there. It also does not include the Sun classes. However, the Spongy Castle
libraries will provide the same functionality as Bouncy Castle in Android, so these 
libraries should be used in Android, unless the application can do without security altogether.

  * Known issues

- TLS 1.2 policy required by OPC UA does not work (required ciphers not supported by JSSE) 
- HTTPS testing is not finished yet with the other stacks
- Basic256Sha256 security profile testing is not finished yet with the other stacks
- .NET Client requires that the server has a certificate signed by a trusted CA, if HTTPS is used.
  See the readme_certificates.txt for more 

Issues are logged into OPC Foundation Mantis at
 
http://www.opcfoundation.org/mantis
