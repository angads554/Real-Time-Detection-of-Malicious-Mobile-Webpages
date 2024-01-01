package com;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.table.TableColumn;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
public class ViewFeatures extends JFrame{
	DefaultTableModel dtm;
	JScrollPane jsp,jsp1;
	JTable table;
	JPanel p1;
	JTextArea area;
public ViewFeatures(){
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(){
			return false;
		}
	};
	table = new JTable(dtm);
	table.getTableHeader().setFont(new Font("Courier New",Font.BOLD,15));
	table.setFont(new Font("Courier New",Font.BOLD,14));
	table.setRowHeight(30);
	jsp = new JScrollPane(table);
	getContentPane().add(jsp,BorderLayout.CENTER);

	
}
}