package sort;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conf extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public static Integer num;
	JRadioButton mButton;
	JRadioButton qButton;
	/**
	 * Create the frame.
	 */
	public Conf() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 231, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("随机产生");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("个数");
		
		JButton btnNewButton = new JButton("初始化");
		
		//****************************初始化按钮鼠标监听*********************************
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public synchronized void mouseClicked(MouseEvent e) {
				num=Integer.parseInt(textField.getText());
				Present.data=new int[num];
				for (int i = 0; i < Present.data.length; i++) {
					Present.data[i]=(int)(Math.random()*200);
				}
				dispose();
				for (int j = 0; j < Present.data.length; j++) {
					System.out.println(Present.data[j]);
				}
                Present.contentPane=new MyPanel(Present.data,-1,999);
                Present.frame.setContentPane(Present.contentPane);
                Present.contentPane.updateUI();
                Present.contentPane.setVisible(true);
                if(mButton.isSelected()){
                	Present.mThread.start();
                }else if(qButton.isSelected()){
                	Present.qThread.start();
                }
			}
		});
		//*****************************************************************
		
		qButton = new JRadioButton("快速排序");
		qButton.setSelected(true);
		qButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (qButton.isSelected()) {
					mButton.setSelected(false);
				}
			}
		});
		
		mButton = new JRadioButton("归并排序");
		mButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mButton.isSelected()) {
					qButton.setSelected(false);
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(qButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(mButton))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(btnNewButton)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(mButton)
						.addComponent(qButton))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(29))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
