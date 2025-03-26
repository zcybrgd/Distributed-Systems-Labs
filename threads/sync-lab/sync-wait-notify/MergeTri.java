package threads.sync_lab.sync_wait_notify;
public class MergeTri extends Thread {
  private int[] t;
  private int debut, fin;
  private MergeTri parent;
  private int nbNotify=0;
  public MergeTri(int[] t){
   this(null,t,0,t.length-1);
  }
  private MergeTri(MergeTri parent,int[] t,int debut, int fin){
   this.parent= parent;
   this.t = t;
   this.debut = debut;
   this.fin = fin;
   start();
  }
  //cette fonction est pour sunchronizer, elle notifie le parent thread quand both children ont finit de s executer
  public synchronized void notifier() {
   nbNotify++;
   if (nbNotify == 2) {  //when both child threads are done
            notify();
        }
  }
  @Override
  public void run(){
   if(fin-debut<2){
    if(t[debut]>t[fin]){echanger(debut,fin);}
   }
   else{ // on commence la recursivite
    int milieu = (debut+fin)/2;
    MergeTri tri1 = new MergeTri(this,t,debut,milieu);
    MergeTri tri2 = new MergeTri(this,t,milieu+1,fin);
    synchronized(this){
     try{
      while(nbNotify<2){
      wait();
      }
     }
     catch(InterruptedException e){
      e.printStackTrace();
     }
    }
    //merge the sorted halves
    triFusion(debut,fin);
   }
   if(parent!=null){
    synchronized (parent) {
                parent.notifier();
            }
   }
  }
  private void echanger(int i,int j){
   int valeur = t[i];
   t[i] = t[j];
   t[j] = valeur;
  }
  private void triFusion(int debut, int fin){
   int[] tFusion = new int[fin-debut+1];
   int milieu = (debut+fin)/2;
   int i1=debut;
   int i2 = milieu +1;
   int iFusion = 0;
   while(i1<= milieu && i2<=fin){
    if(t[i1]<t[i2]){
     tFusion[iFusion++] = t[i1++];
    } else {
     tFusion[iFusion++] = t[i2++];
    }
   }
        // Copy any remaining elements from the left half
        while (i1 <= milieu) {
            tFusion[iFusion++] = t[i1++];
        }
        // Copy any remaining elements from the right half
        while (i2 <= fin) {
            tFusion[iFusion++] = t[i2++];
        }
        //copy the sorted elements back to the original array
        for (int i = 0; i < tFusion.length; i++) {
            t[debut + i] = tFusion[i];
        }
  }
  public static void main(String args[]){
   int[] t = {5,8,3,2,7,10,1};
   MergeTri trieur = new MergeTri(t);
   try {
    trieur.join();
   } catch(InterruptedException e){
    e.printStackTrace();
   }
   for(int value : t){
    System.out.print(value +  ";");
   }
   System.out.println();
  }
}
