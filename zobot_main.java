package zobot;

import org.jibble.pircbot.*;
 
public class zobot_main {
    
    public static void main(String[] args) throws Exception {
        
        // Now start our bot up.
        ZoBot bot = new ZoBot();
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("de.quakenet.org");
 
        // Join the #pircbot channel.
        bot.joinChannel("#testchannel");
        
    }
}