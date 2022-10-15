package gsm;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Market{
	static int clicks;
	static JFrame frame;
	static JLabel jL;
	public static void main(String[] args) throws IOException, InterruptedException{
		int lines = 0, count = 0; float tw = 22;
		String path = System.getProperty("user.home") + File.separator + "Documents";
		path += File.separator + "StockManagerFiles";
		new File(path).mkdirs();
		File file = new File(path + File.separator + "firstRun.txt");
		boolean firstRun = false;
		if(!file.exists()){
		    firstRun = true;
		    file.createNewFile();
		}
		
		File stockList = new File(path + File.separator + "stocklist.txt");
		
		if(firstRun) {
			stockList.createNewFile();
		    FileWriter fWriter = new FileWriter(stockList);
		    fWriter.write("GUPTA:500" + System.lineSeparator()
		    		+ "THAT:500" + System.lineSeparator()
		    		+ "CUAN:500" + System.lineSeparator()
		    		+ "TIRED:500" + System.lineSeparator()
		    		+ "SHIWEI:500" + System.lineSeparator()
		    		+ "HUANG:500" + System.lineSeparator());
		    fWriter.close();
		    System.out.println("First run.");
		} else {
		    System.out.println("Not the first run.");
		}
		
		Scanner scanner = new Scanner(stockList);
		while (scanner.hasNextLine()) {
		    lines++;
		    scanner.nextLine();
		}
		scanner.close();
		Stock[] stock = new Stock[lines];
		
		Scanner scan = new Scanner(stockList);
		while(scan.hasNextLine()) {
			String[] array = scan.nextLine().split(":", 2);
			stock[count] = new Stock(array[0], Double.parseDouble(array[1]));
			count++;
		}
		scan.close();
		
		frame = new JFrame("The Generic Stock Viewer"); //start of code
		JPanel panel = new JPanel();
		LocalDateTime date = LocalDateTime.now().minusYears(30).minusWeeks(10).minusDays(2);;
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		JLabel daJ = new JLabel(date.format(myFormatObj) + "");
		daJ.setFont(daJ.getFont().deriveFont((float) tw - 5));
		JLabel[] l = new JLabel[lines];
		jL = new JLabel("The Generic Stock Viewer");
		EmptyBorder border = new EmptyBorder(5, 20, 100, 20);
	    jL.setBorder(border);
		jL.setFont(jL.getFont().deriveFont((float) tw + 5));
		jL.setHorizontalAlignment(JLabel.CENTER);
		for(int i=0;i<l.length;i++) {
			l[i] = new JLabel(stock[i].toString());
			l[i].setFont(l[i].getFont().deriveFont(tw));
			l[i].setAlignmentX(JLabel.CENTER_ALIGNMENT);
			if(stock[i].equalsN("THAT")) {
				l[i].setToolTipText("Yes.");
			} else if(stock[i].equalsN("GUPTA")) {
				l[i].setToolTipText("Specializes in political subterfuge.");
			} else if(stock[i].equalsN("CUAN")) {
				l[i].setToolTipText("The best quality basketballs.");
			} else if(stock[i].equalsN("TIRED")) {
				l[i].setToolTipText("The antithesis to tiredness is our coffee!");
			} else if(stock[i].equalsN("HUANG")) {
				l[i].setToolTipText("Helping relationships grow!");
			} else if(stock[i].equalsN("SHIWEI")) {
				l[i].setToolTipText("We help you get better.");
			} else {
				System.out.println(stock[i].getSymbol());
			}
			panel.add(l[i]);
		}
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setLayout(new BorderLayout());
		frame.add(jL,BorderLayout.PAGE_START);
		frame.add(panel,BorderLayout.CENTER);
		frame.add(daJ,BorderLayout.PAGE_END);
		URL iconURL = Market.class.getResource("R.png");
		// iconURL is null when not found
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true) {
			Thread.sleep((long) myRandom(1000, 2000)); //speed
			setGetShare(stock);
			ArrayList<FileWriter> fw = new ArrayList<FileWriter>();
			int count2 = 0;
			for(int j=0;j<l.length;j++) {
				l[j].setText(stock[j].toString());
				if(count2==0) {
					count2++;
					fw.add(new FileWriter(stockList));
				} else {
					fw.add(new FileWriter(stockList,true));
				}
				fw.get(j).write(stock[j].toStringWithout$Space()+"\n");
				fw.get(j).close();
			}
			date = LocalDateTime.now().minusYears(30).minusWeeks(10).minusDays(2);
			daJ.setText(date.format(myFormatObj) + "");
		}
	}
	static double myRandom(double min, double max) {
		Random r = new Random();
		return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
	}
	static void setGetShare(Stock[] s) {
		for(int i=0;i<s.length;i++) {
			if(s[i].getSharePriceN()<=10) {
				s[i].setSharePrice(s[i].getSharePriceN()+myRandom(-18,20));
			} else {
				s[i].setSharePrice(s[i].getSharePriceN()+myRandom(-20,20));
			}
		}
	}
}