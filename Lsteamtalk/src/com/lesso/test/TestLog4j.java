package com.lesso.test;

import org.apache.log4j.Logger;

public class TestLog4j {
	public void log4j(){

		  Logger log=Logger.getLogger(TestLog4j.class);

		  System.out.println("test log4j");

		  log.info("IIIIIIIIIIIIIIIIII");  
          log.warn("WWWWWWWWWWWWWWWWWWWWWW");  
          log.error("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");  

		 }


		 public static void main(String[] args) {

		  TestLog4j tl=new TestLog4j();

		  tl.log4j(); 

		 }
}
