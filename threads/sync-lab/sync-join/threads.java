package threads.sync_lab.sync_join;

public class threads extends Thread {

  private int[] t;
  private int debut, fin;

  public threads(int[] t){
   this(t,0,t.length-1);
  }

  private threads(int[] t,int debut, int fin){
   this.t = t;
   this.debut = debut;
   this.fin = fin;
  }
  @Override
  public void run(){
   if(fin-debut<2){
    if(t[debut]>t[fin]){echanger(debut,fin);}
   }
   else{ // on commence la recursivite
    int milieu = (debut+fin)/2;
    threads tri1 = new threads(t,debut,milieu);
    threads tri2 = new threads(t,milieu+1,fin);
    //on les lance les 2
    tri1.start();
    tri2.start();
    try {
    //here we wait for both threads to finish execution
    tri1.join();
    tri2.join();
    } catch(InterruptedException e) {
     e.printStackTrace();
    }
    //merge the sorted halves
    triFusion(debut,fin);
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
   threads trieur = new threads(t);
   trieur.start();
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
