public class Quick{
    private void drow(int[] a,int i,int j,int lo,int hi,int mode){
        Present.contentPane=new MyPanel(a,i,j,lo,hi,mode);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
    }
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
            	this.drow(a,i,j,lo,hi,2);
                try {
    				this.wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	if(i==hi) break;
            }
            while(v<a[--j]){
            	this.drow(a,i,j,lo,hi,2);
                try {
                	this.wait();
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            	if(j==lo) break;
            }
            if(i>=j) break;
            this.drow(a,i,j,lo,hi,1);
            try {
            	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            exch(a,i,j);
            this.drow(a,i,j,lo,hi,1);
            try {
            	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        this.drow(a,lo,j,lo,hi,1);
        try {
        	this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        exch(a,lo,j);
        this.drow(a,lo,j,lo,hi,1);
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
        Present.contentPane=new MyPanel(a,-1,999);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
    }
    public synchronized void noti(){
    	this.notify();
    }

}
