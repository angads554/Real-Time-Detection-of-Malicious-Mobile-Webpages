package com;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import java.io.File;
import org.jfree.ui.RefineryUtilities;
public class KayoMaliciousDetection extends JFrame{	
	JPanel p1,p2;
	JLabel l1;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	Font f1,f2;
	DefaultTableModel dtm;
	JScrollPane jsp;
	JTable table;
	JFileChooser chooser;
	File file;
	static int malicious,benign;
public KayoMaliciousDetection(){
	setTitle("Kayo Mobile Malicious Web Pages Detection");
	f1 = new Font("Courier New",Font.BOLD+Font.ITALIC,18);
	p1 = new JPanel();
    l1 = new JLabel("<HTML><BODY><CENTER>Detecting Mobile Malicious Webpages in Real Time</CENTER></BODY></HTML>".toUpperCase());
	l1.setFont(this.f1);
    l1.setForeground(new Color(125,254,120));
    p1.add(l1);
    p1.setBackground(new Color(100,30,40));

    f2 = new Font("Courier New",Font.BOLD,14);

	chooser = new JFileChooser(new File("."));
	chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY);
	
	p2 = new JPanel();
	p2.setLayout(null);
	b1 = new JButton("Upload Mobile Webpages");
	b1.setFont(f2);
	b1.setBounds(100,50,300,50);
	p2.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			clearTable();
			int option = chooser.showOpenDialog(KayoMaliciousDetection.this);
			if(option == chooser.APPROVE_OPTION){
				file = chooser.getSelectedFile();
				JOptionPane.showMessageDialog(KayoMaliciousDetection.this,"Dataset Loaded");
			}
		}
	});
	
	b2 = new JButton("Process Pages For Kayo Features");
	b2.setFont(f2);
	b2.setBounds(450,50,300,50);
	p2.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			clearTable();
			File list[] = file.listFiles();
			for(int i=0;i<list.length;i++){
				Object row[] = {(i+1),list[i].getName(),(list[i].length()/1000)+" KB"};
				dtm.addRow(row);
			}
		}
	});

	b3 = new JButton("Javascript Features Set");
	b3.setFont(f2);
	b3.setBounds(100,120,300,50);
	p2.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ExtractFeatures.readPage(file);
			ViewFeatures vf = new ViewFeatures();
			vf.setTitle("View Javascript Features Set");
			vf.dtm.addColumn("Page Name");
			vf.dtm.addColumn("Keyword Count");
			vf.dtm.addColumn("External Count");
			vf.dtm.addColumn("Noscript Count");
			for(int i=0;i<ExtractFeatures.javascript.size();i++){
				JavascriptFeatures jf = ExtractFeatures.javascript.get(i);
				Object row[] = {jf.getPage(),jf.getKeywordCount(),jf.getExternalCount(),jf.getNoscriptCount()};
				vf.dtm.addRow(row);
			}
			vf.setVisible(true);
			vf.setSize(600,400);
		}
	});

	b4 = new JButton("HTML Features Set");
	b4.setFont(f2);
	b4.setBounds(450,120,300,50);
	p2.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ViewFeatures vf = new ViewFeatures();
			vf.setTitle("View HTML Features Set");
			vf.dtm.addColumn("Page Name");
			vf.dtm.addColumn("Redirection Count");
			for(int i=0;i<ExtractFeatures.html.size();i++){
				HTMLFeatures hf = ExtractFeatures.html.get(i);
				Object row[] = {hf.getPage(),hf.getRedirection()};
				vf.dtm.addRow(row);
			}
			vf.setVisible(true);
			vf.setSize(600,400);
		}
	});

	b5 = new JButton("URL Features Set");
	b5.setFont(f2);
	b5.setBounds(100,190,300,50);
	p2.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ViewFeatures vf = new ViewFeatures();
			vf.setTitle("View URL Features Set");
			vf.dtm.addColumn("Page Name");
			vf.dtm.addColumn("Subdomain Count");
			for(int i=0;i<ExtractFeatures.url.size();i++){
				URLFeatures uf = ExtractFeatures.url.get(i);
				Object row[] = {uf.getPage(),uf.getSubdomain()};
				vf.dtm.addRow(row);
			}
			vf.setVisible(true);
			vf.setSize(600,400);
		}
	});

	b6 = new JButton("Mobile Features Set");
	b6.setFont(f2);
	b6.setBounds(450,190,300,50);
	p2.add(b6);
	b6.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ViewFeatures vf = new ViewFeatures();
			vf.setTitle("View Mobile Features Set");
			vf.dtm.addColumn("Page Name");
			vf.dtm.addColumn("SMS Count");
			vf.dtm.addColumn("MMS Count");
			vf.dtm.addColumn("Telephone Count");
			for(int i=0;i<ExtractFeatures.mobile.size();i++){
				MobileFeatures mf = ExtractFeatures.mobile.get(i);
				Object row[] = {mf.getPage(),mf.getSmsCount(),mf.getMmsCount(),mf.getTelephoneCount()};
				vf.dtm.addRow(row);
			}
			vf.setVisible(true);
			vf.setSize(600,400);
		}
	});

	b7 = new JButton("View Detection Classification");
	b7.setFont(f2);
	b7.setBounds(100,260,300,50);
	p2.add(b7);
	b7.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			malicious = 0;
			benign = 0;
			Classification.classify();
			ViewFeatures vf = new ViewFeatures();
			vf.setTitle("View Detection Classification");
			vf.dtm.addColumn("Page Name");
			vf.dtm.addColumn("Detection");
			for(int i=0;i<Classification.malicious.size();i++){
				Object row[] = {Classification.malicious.get(i),"Malicious Page"};
				vf.dtm.addRow(row);
				malicious = malicious + 1;
			}
			File list[] = file.listFiles();
			for(int i=0;i<list.length;i++){
				if(!Classification.malicious.contains(list[i].getName())){
					Object row[] = {list[i].getName(),"Benign Page"};
					vf.dtm.addRow(row);
					benign = benign + 1;
				}
			}
			vf.setVisible(true);
			vf.setSize(600,600);
		}
	});

	b8 = new JButton("Detection Chart");
	b8.setFont(f2);
	b8.setBounds(450,260,300,50);
	p2.add(b8);
	b8.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Chart chart1 = new Chart("Detection Chart");
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	dtm = new DefaultTableModel(){
		public boolean isCellEditable(){
			return false;
		}
	};
	dtm.addColumn("Page No");
	dtm.addColumn("Webpage Name");
	dtm.addColumn("Webpage Size");
	table = new JTable(dtm);
	table.getTableHeader().setFont(new Font("Courier New",Font.BOLD,15));
	table.setFont(new Font("Courier New",Font.BOLD,14));
	table.setRowHeight(30);
	jsp = new JScrollPane(table);
	jsp.setBounds(10,330,900,300);
	p2.add(jsp);

	getContentPane().add(p1, "North");
    getContentPane().add(p2, "Center");
}
public void clearTable(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	KayoMaliciousDetection kayo = new KayoMaliciousDetection();
	kayo.setVisible(true);
	kayo.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
}