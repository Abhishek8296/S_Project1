package com.auto.base;

import org.testng.Reporter;

public class Page {

	public static void log(String msg, String logLevel) {

		  if(msg.endsWith(".")) {

		   msg=msg.substring(0, msg.length()-1) + "|" ;

		  } else {

		   msg=msg + "|";

		  }

		  System.out.println( IValues.SPACES_THREE + "|" + msg );


		  if(logLevel==ILogLevel.PAGE){

		   Reporter.log("<br/><b><font color='DarkRed'>" + msg + "</font></b>");

		  } else if(logLevel==ILogLevel.TEST){

		   Reporter.log("<br/>" + IValues.HTML_SPACE + "<font color='Green'>" + msg + "</font>");

		  } else if(logLevel==ILogLevel.METHOD){ // add 3 space indentation

		   Reporter.log("<br/>" + IValues.HTML_SPACE_THREE + "<font color='Green'>" + msg + "</font>" );

		  } else if(logLevel == ILogLevel.QUESTION){

		   Reporter.log("<br/>" + IValues.HTML_SPACE + "<i><font color='Yellow'>" + msg + "</font></i>");

		  } else if(logLevel == ILogLevel.TO_DO_STEPS){

		   Reporter.log("<br/>" + IValues.HTML_SPACE + "TODO: <i><font color='Megenta'>" + msg + "</font></i>");

		  } else if(logLevel == ILogLevel.ASSERTS){

		   Reporter.log("<br/><b>" + IValues.HTML_SPACE + "CHECKPOINT: <font color='Green'>" + msg + "</font></b>");

		  } else if(logLevel == ILogLevel.TESTCASE){

		   Reporter.log("<br/> <b>TESTCASE:<font color='Green'>" + msg + "</font></b>");

		  } else if(logLevel == ILogLevel.PRE_CONDITION){

		   Reporter.log("<br/> PRECONDITION:<b><font color='Megenta'>[" + msg + "]</font></b>");

		  } else if(logLevel == ILogLevel.BUG){

		   Reporter.log("<br/><font color='Red' style='background-color: yellow;'><b> BUG: [" + msg + "]</font> </b>");

		  } else if(logLevel == ILogLevel.MANUAL_TESTING_NOTE){

		   Reporter.log("<br/><font color='Black' style='background-color: yellow;'><b> Note For Manual Testers: </b>[" + msg + "]</font>");

		  } else if(logLevel == ILogLevel.BUG_GIT_HUB_LINK){

		   msg = msg.replace("|", IValues.SPACE ) ;
		   Reporter.log("<br/><font color='Blue' style='background-color: yellow;'><b> ISSUE_Link:  "
		     + "<a href='"+ msg + "'>"
		       + "[" + msg + "]"
		         + " </a></font> </b>");
		   //throw new Error("Force-fail-manually. Check Bug description and Issue link in log");
		  } else if(logLevel == ILogLevel.ERROR ){

		   Reporter.log("<br/> <b> <font color='Red' style='background-color: white;'> [" + msg + "]</font> </b>");

		  } else if(logLevel == ILogLevel.WARNING ){

		   Reporter.log("<br/> <b> <font color='Yellow' style='background-color: gray;'> [" + msg + "]</font> </b>");

		  } else if(logLevel == ILogLevel.FAILURE ){

		   Reporter.log("<br/> <b> <font color='Red' size='3' > [" + msg + "]</font> </b>");

		  }

		 }

	public String getValue(String _key){
		 return TestCore.config.getProperty(_key);
	}
}
