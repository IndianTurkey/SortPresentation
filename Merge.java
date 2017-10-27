package sort;


//以下排序算法来自普林斯顿红色算法书
public class Merge {
	private static int[] aux;
	
	//自顶向下
	public synchronized void sortTD(int[] a){
		aux=new int[a.length];
		try {
         	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		sort(a,0,a.length-1);
	}
	
	//自底向上
	public synchronized void sortBU(int[] a){
		int N = a.length;
		try {
         	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		aux=new int[N];
		for(int sz=1;sz<N;sz=sz+sz)
			for(int lo=0;lo<N-sz;lo+=sz+sz)
				merge(a, lo, lo+sz-1, lo+sz+sz-1<N-1?lo+sz+sz-1:N-1);
	}
	
	public synchronized void sort(int[] a,int lo,int hi){
		if(hi<=lo){
			return;
		}
		int mid=lo+(hi-lo)/2;
		sort(a,lo,mid);
		sort(a,mid+1,hi);
		merge(a, lo, mid, hi);
		MyPanel.draw(a, lo, hi, 1);
	}
	
	public synchronized void merge(int[] a,int lo,int mid,int hi){
		MyPanel.draw(a, lo, hi, 2);
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
		MyPanel.draw(a, lo, hi, 1);
		 try {
         	this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	//同步该排序，让其一步一步进行
	public synchronized void noti(){
    	this.notify();
    }
}
