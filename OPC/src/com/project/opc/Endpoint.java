package com.project.opc;

public class Endpoint {
    private String url;
    private String protocol;
    private String Smode;
    private String Spolicy;
    private String Slevel;
    
    public Endpoint(){
        super();
    }
    
    public Endpoint(String url, String protocol, String Smode,String Spolicy,String Slevel) {
        super();
        this.url = url;
        this.protocol = protocol;
        this.Smode = Smode;
        this.Spolicy = Spolicy;
        this.Slevel = Slevel;
    }

    @Override
    public String toString() {	
        return this.url + ". " + " Security Level: "+ this.Slevel;
    }
    
    public String getUrl(){
    	return this.url;
    }
    public String getProtocol(){
    	return this.protocol;
    }
    public String getSmode(){
    	return this.Smode;
    }
    public String getSpolicy(){
    	return this.Spolicy;
    }
    public String getSlevel(){
    	return this.Slevel;
    }
    
    public void setUrl(String url){
    	this.url=url;
    }
    public void setSmode(String Smode){
    	this.Smode=Smode;
    }
    public void setSpolicy(String Spolicy){
    	this.Spolicy=Spolicy;
    }
    public void setSlevel(String Slevel){
    	this.Slevel=Slevel;
    }
    
}
