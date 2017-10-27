package sort;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.alee.laf.WebLookAndFeel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Present extends JFrame {

	public static Present frame;                     //主窗体
	public static MyPanel contentPane;               //绘图窗体
	private static Quick quick;                      //快排
	private static Merge merge;						 //归并
	public static int[] data;                        //排序数组
	public static Thread qThread,mThread,m2Thread;            //排序线程
	/**
	 * Launch the application.
	 */
	public static synchronized void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.globalControlFont  = new FontUIResource("宋体",0, 12);
					WebLookAndFeel.globalMenuFont = new FontUIResource("宋体",0, 12);
					WebLookAndFeel.install();
					
					frame = new Present();
					frame.setVisible(true);
					
					qThread =new Thread(){
						public void run() {
							quick.sort(data);
						}
					};
					mThread =new Thread(){
						public void run() {
							merge.sortTD(data);
						}
					};
					m2Thread =new Thread(){
						public void run() {
							merge.sortBU(data);
						}
					};
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Present() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		
		quick=new Quick();
		merge=new Merge();
		
		//************************菜单**********************
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnConfig = new JMenu("配置");
		menuBar.add(mnConfig);
		JMenuItem mntmInitializeArray = new JMenuItem("初始化数组");
		mntmInitializeArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conf con = new Conf();
				con.setVisible(true);
			}
		});
		mnConfig.add(mntmInitializeArray);
		//*************************************************
		
		//************************鼠标监听**********************
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(qThread.isAlive()){
					quick.noti();
				}else if(mThread.isAlive()){
					merge.noti();
				}else if(m2Thread.isAlive()){
					merge.noti();
				}
			}
		});
		//*************************************************
		
		//************************键盘监听**********************
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()=='n'){
					if(qThread.isAlive()){
						quick.noti();
					}else if(mThread.isAlive()){
						merge.noti();
					}else if(m2Thread.isAlive()){
						merge.noti();
					}
				}
			}
		});
		//*************************************************
	}
}