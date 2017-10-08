public class Merge {
	private static int[] aux;
	public synchronized void sort(int[] a){
		aux=new int[a.length];
		try {
         	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		sort(a,0,a.length-1);
	}
	private void drow(int[] a,int lo,int hi,int mode){
        Present.contentPane=new MyPanel(a,lo,hi);
        Present.frame.setContentPane(Present.contentPane);
        Present.contentPane.updateUI();
    }
	public synchronized void sort(int[] a,int lo,int hi){
		if(hi<=lo){
			return;
		}
		int mid=lo+(hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a, lo, mid, hi);
		drow(a, lo, hi, 1);
	}
	public synchronized void merge(int[] a,int lo,int mid,int hi){
		drow(a, lo, hi, 2);
		 try {
        	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++)
			aux[k]=a[k];
		for(int k=lo;k<=hi;k++)
			if(i>mid)               a[k]=aux[j++];
			else if(j>hi)           a[k]=aux[i++];
			else if(aux[j]<aux[i])  a[k]=aux[j++];
			else                    a[k]=aux[i++];
		drow(a, lo, hi, 1);
		 try {
         	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	public synchronized void noti(){
    	this.notify();
    }
}
