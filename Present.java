import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Present extends JFrame {

	public static Present frame;
	public static MyPanel contentPane; 
	private static Quick quick;
	private static Merge merge;
	public static int[] data;
	public static Thread qThread,mThread;
	/**
	 * Launch the application.
	 */
	public static synchronized void main(String[] args) {

		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					frame = new Present();
					frame.setVisible(true);
					qThread =new Thread(){
						public void run() {
							quick.sort(data);
						}
					};
					mThread =new Thread(){
						public void run() {
							merge.sort(data);
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
		quick=new Quick();
		merge=new Merge();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(qThread.isAlive()){
					quick.noti();
				}else if(mThread.isAlive()){
					merge.noti();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
	}

}
