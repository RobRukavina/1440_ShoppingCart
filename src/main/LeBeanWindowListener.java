package main;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.JOptionPane;

public class LeBeanWindowListener implements WindowListener{
	    @Override
	    public void windowOpened(WindowEvent e) {
	        // Code to execute when the window is opened
	    }

	    @Override
	    public void windowClosing(WindowEvent e) {
	    	StringBuilder sb = new StringBuilder();
	    	double total = 0.00;

	    	// Find all receipts
	    	File f = new File("src/records");
	    	File[] matchingFiles = f.listFiles(new FilenameFilter() {
	    	    public boolean accept(File dir, String name) {
	    	        return name.substring(0, 7).startsWith("receipt") && name.endsWith("txt");
	    	    }
	    	});
	    	
	    	if(matchingFiles.length > 0) {
	    		// Show daily report
		    	for(File fi : matchingFiles) {
		    		try(Scanner scnr = new Scanner(new File(fi.toString()))){
		    			while(scnr.hasNext()) {
		    				String line = scnr.nextLine();
		    				
		    				if(line.startsWith("Total")) {
		    					String[] fields = line.split(",");
		    					total += Double.parseDouble(fields[1]);
		    					
		    				} else {
		    					sb.append(line + "\n");	    					
		    				} 
		    			}
		    		} catch(Exception ex) {
		    			System.out.println(ex.getMessage());
		    			ex.printStackTrace();
		    		}
		    	}
	
		    	sb.append(String.format("%s: %.2f","Total", total));
		    	
		    	// Print Report to records folder
		    	LocalDateTime now = LocalDateTime.now();
		
		        // Get dateTime for fileName
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String fdt = now.format(formatter).replaceAll(":", "_").replace(" ", ",");
	        
	        	try(PrintWriter pw = new PrintWriter(new File("src/records/DailyReport_" + fdt + ".csv"))){
		    		pw.print(sb);
		    	} catch(Exception ex) {
		    		System.out.println(ex.getMessage());
		    		ex.printStackTrace();
		    	}
		    	
		    	JOptionPane.showMessageDialog(null, sb, "Daily Report", JOptionPane.INFORMATION_MESSAGE);
		    	System.out.println("Showing Report");	
	    	
		    	// Remove all old receipts
		    	try {
		    		for(File file : matchingFiles) {
		    			file.delete();
		    		}
	
			        System.out.println("Cleanup complete, application closing");
		    	
		    	} catch(Exception ex) {
		    		System.out.println(ex.getMessage());
		    		ex.printStackTrace();
		    	}
	    	}
	    }

	    @Override
	    public void windowClosed(WindowEvent e) {
	        // Code to execute after the window is closed
	    }

	    @Override
	    public void windowIconified(WindowEvent e) {
	        // Code to execute when the window is minimized
	    }

	    @Override
	    public void windowDeiconified(WindowEvent e) {
	        // Code to execute when the window is restored from minimized state
	    }

	    @Override
	    public void windowActivated(WindowEvent e) {
	        // Code to execute when the window becomes active
	    }

	    @Override
	    public void windowDeactivated(WindowEvent e) {
	        // Code to execute when the window becomes inactive
	    }
	}
