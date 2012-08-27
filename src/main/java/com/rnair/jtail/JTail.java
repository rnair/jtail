package com.rnair.jtail;

import com.rnair.jtail.core.SimpleTailingController;
import com.rnair.jtail.core.TailingController;

public class JTail {
	
    public static void main( String[] args )
    {
    	try{
    	
    	TailingController c = new SimpleTailingController();
    	c.initialize(null);
    	c.start();
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    
}
