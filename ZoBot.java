package zobot;
 
 
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
 
import org.jibble.pircbot.*;
 
public class ZoBot extends PircBot
{
    
    public ZoBot() {
        this.setName("Zo-bot");
    }
    
    Hashtable<String,Long> nutzer = new Hashtable();
    Hashtable<String,String> nicks = new Hashtable();
    public void onMessage(String channel, String sender,String login, String hostname, String message)
    {
    
     //sender für .seen speichern
     if(nutzer.containsKey(sender))
     {
    	 nutzer.remove(sender);
     }
     nutzer.put(sender, new java.util.Date().getTime());
    
     //////////////////////////////////
     //// 		  >TIME			   ////
     //////////////////////////////////
     if (message.equalsIgnoreCase(">time"))
     {
	     Calendar cal = Calendar.getInstance();
	     String time = cal.get(Calendar.DAY_OF_MONTH) + "."
	     + cal.get(Calendar.MONTH) + " "
	     + cal.get(Calendar.HOUR_OF_DAY) + ":"
	     + cal.get(Calendar.MINUTE) + "Uhr";
	     sendMessage(channel, sender + ": Es ist der " + time);
     }
     //////////////////////////////////
     //// 		 >SEEN 			   ////
     //////////////////////////////////
     else if(message.startsWith(">seen"))
     {
	     if(message.length() < 7)
	     {
	    	 sendMessage(channel, sender + ": Wen soll ich denn suchen?");
	     }else
	     {
		     String gesucht = message.substring(6);
		    
		     if(nutzer.containsKey(gesucht))
		     {
			     Calendar cal = Calendar.getInstance();
			     cal.setTime(new Date(nutzer.get(gesucht)));
			     String time = cal.get(Calendar.DAY_OF_MONTH) + "."
						+ cal.get(Calendar.MONTH) + " "
						+ cal.get(Calendar.HOUR_OF_DAY) + ":"
						+ cal.get(Calendar.MINUTE) + "Uhr";
		    
			     sendMessage(channel, sender + ": " + gesucht + " habe ich zulezt gesehen:");
			     sendMessage(channel, sender + ": " + time);
		     }else
		     {
		    	 sendMessage(channel, sender + ": Den kenn ich nicht...");
		     }
	     }
     }
     //////////////////////////////////
     //// 		>ORIGIN			   ////
     //////////////////////////////////
     else if(message.startsWith(">origin"))
     {
	     if(message.length() < 9)
	     {
	    	 sendMessage(channel, sender + ": Wen soll ich denn suchen?");
	     }
	     else
	     {
		     String gesucht = message.substring(8);
		     if(nicks.containsKey(gesucht))
		     {
		    	 sendMessage(channel, sender + ": " + nicks.get(gesucht) + " -> " + gesucht);
		     }
		     else
		     {
		    	 sendMessage(channel, sender + ": kein Nickchange aufgezeichnet.");
		     }
		 }
	 }
}
    
    public void onNickChange(String oldNick, String login, String hostname, String newNick)
    {
	     // Nickchanges speichern für >origin
	     String nicknames = "";
	     if(nicks.containsKey(oldNick))
	     {
		     nicknames = nicks.get(oldNick);
		     nicks.remove(oldNick);
	     }
			 
		if(nicknames.equals(""))
		{
			nicknames = oldNick;
		}
		else
		{
			nicknames += " -> " + oldNick;
		}
		 
		if(nicknames.split(" -> ").length > 8)
		{
			nicknames = nicknames.substring(nicknames.indexOf(" -> ")+2);
		}
		 
		nicks.put(newNick, nicknames);		 
    } 
    
}