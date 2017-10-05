package sort;

public class Quick{
    private void exch(int[] a,int p1,int p2){
       int temp=a[p1];
       a[p1]=a[p2];
       a[p2]=temp;
       return;
    }
    private synchronized int partition(int[] a,int lo,int hi){
        int i=lo,j=hi+1;
        int v=a[lo];
        while(true){
            while(a[++i]<v){
            	//Present.contentPane.setVisible(false);
                Present.contentPane=new MyPanel(a,i,j,lo,hi,2);
                Present.frame.setContentPane(Present.contentPane);
                Present.contentPane.updateUI();
                //Present.contentPane.setVisible(true);
                try {
    				this.wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	if(i==hi) break;
            }
            while(v<a[--j]){
            	//Present.contentPane.setVisible(false);
                Present.contentPane=new MyPanel(a,i,j,lo,hi,2);
                Present.frame.setContentPane(Present.contentPane);
                Present.contentPane.updateUI();
                //Present.contentPane.setVisible(true);
                try {
                	this.wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	if(j==lo) break;
            }
            if(i>=j) break;
            //Present.contentPane.setVisible(false);
            Present.contentPane=new MyPanel(a,i,j,lo,hi,1);
            Present.frame.setContentPane(Present.contentPane);
            Present.contentPane.updateUI();
            //Present.contentPane.setVisible(true);
            try {
            	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            exch(a,i,j);
            //Present.contentPane.setVisible(false);
            Present.contentPane=new MyPanel(a,i,j,lo,hi,1);
            Present.frame.setContentPane(Present.contentPane);
            Present.contentPane.updateUI();
            //Present.contentPane.setVisible(true);
            try {
            	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        //Present.contentPane.setVisible(false);
        Present.contentPane=new MyPanel(a,lo,j,lo,hi,1);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
        //Present.contentPane.setVisible(true);
        try {
        	this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        exch(a,lo,j);
        //Present.contentPane.setVisible(false);
        Present.contentPane=new MyPanel(a,lo,j,lo,hi,1);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
        //Present.contentPane.setVisible(true);
        try {
        	this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return j;
    }
    private void sort(int[] a,int lo,int hi){
        if(hi<=lo) return;
        int j=partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }
    public void sort(int[] a){
        sort(a,0,a.length-1);
        //Present.contentPane.setVisible(false);
        Present.contentPane=new MyPanel(a,-1,999);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
        //Present.contentPane.setVisible(true);
    }
    public synchronized void noti(){
    	this.notify();
    }

}
