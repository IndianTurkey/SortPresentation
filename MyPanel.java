package sort;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	int[] data;
	int arg1=999,arg2=999,scan=999;
	int part=999,mode=8,lo=-1,hi=999;
	
	//归并实例化
	public MyPanel(int[] a,int l,int h) {
		data=a.clone();
		lo=l;hi=h;
	}
	
	
	//快排实例化
	public MyPanel(int[] a,int x,int y,int l,int h,int m) {
		data=a.clone();
		arg1=x;
		arg2=y;
		part=l;
		mode=m;
		lo=l;
		hi=h;
	}
	
	//重写panel的绘制方法，让其根据数组绘制出条状图
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < data.length; i++) {
			if(i<lo||i>hi){
				g.setColor(Color.GRAY);
			}
			else if((i==arg1||i==arg2)&&mode==1){
				g.setColor(Color.RED); 
			}else if((i==arg1||i==arg2)&&mode==2){
				g.setColor(Color.ORANGE);
			}else if(i==part){
				g.setColor(Color.BLUE);
			}else{
				g.setColor(Color.BLACK);
			}
			g.fillRect(30+i*20, 30, 8, data[i]*2);
		}
		g.setColor(Color.RED);
		g.fillRect(180,500,8,8);
		g.drawString("需要交换的项",200,510);
		g.setColor(Color.ORANGE);
		g.fillRect(30,500,8,8);
		g.drawString("正在扫描的项",50,510);
		g.setColor(Color.BLUE);
		g.fillRect(30,520,8,8);
		g.drawString("划分项",50,530);
		g.setColor(Color.GRAY);
		g.fillRect(180,520,8,8);
		g.drawString("目前未激活的项",200,530);
		g.setColor(Color.BLACK);
		g.fillRect(320,500,8,8);
		g.drawString("待处理项",340,510);
	}
	
	
	//归并绘制
	public static void draw(int[] a,int lo,int hi,int mode){
        Present.contentPane=new MyPanel(a,lo,hi);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
    }
	
	
	//快排绘制
	public static void draw(int[] a,int i,int j,int lo,int hi,int mode){
        Present.contentPane=new MyPanel(a,i,j,lo,hi,mode);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
    }
	
}
