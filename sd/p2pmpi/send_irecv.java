import p2pmpi.mpi.Comm;
import p2pmpi.mpi.MPI;
import p2pmpi.mpi.Request;
import p2pmpi.mpi.Status;

public class send_irecv {
	
	final static int SIZE = 4032 ;             // Need to be : 504x8xY ...         

  final static int LOCAL_SIZE =  288 ;              // 14 PEs
//	final static int LOCAL_SIZE =  336 ;              // 12 PEs
//	final static int LOCAL_SIZE = 448 ;               //  9 PEs
//	final static int LOCAL_SIZE = 504 ;               //  8 PEs
//	final static int LOCAL_SIZE = 576 ;               //  7 PEs
//	final static int LOCAL_SIZE = 672 ;               //  6 PEs
//	final static int LOCAL_SIZE = 1008 ;               //  4 PEs
//	final static int LOCAL_SIZE = 1344 ;              //  3 PEs
//	final static int LOCAL_SIZE = 2016 ;              //  2 PEs
//	final static int LOCAL_SIZE = 4032 ;             //  1 PE
	
	// On double la taille de la matrice locale A
	static double A[][] = new double[2*LOCAL_SIZE][SIZE];               /* Matrixes : C = A.B           */
	static double TB[][] = new double[LOCAL_SIZE][SIZE];
	static double C[][] = new double[SIZE][LOCAL_SIZE];	

	// Offsets des zones de lecture et ecriture
 	static int OI = 0;
 	static int NI = 1;
	
	static int Me = -1;                                     /* Processor rank               */
	static int NbPE = -1;                                   /* Number of processors 		   */
	
	static int PrintedElt_i = 0;                            /* Coordinates of the element   */
	static int PrintedElt_j = 0;                            /* to priL_SIZE * SIZE) % NbPEnt.                    */
	static int PrinterPE = -1;  
	
	static void ProcessorInit()
	{
		Me = MPI.COMM_WORLD.Rank();		// MON NUMERO DE PROCESSUS
		NbPE = MPI.COMM_WORLD.Size();	// NB DE PROCESSUS LANCES
	
		
	/* Intializations of variables to "print the central element of the C matrix".   */
	/* Coordinates of processor owning the central element, and Coordinates of the   */
	/* central element (of the global C matrix) on its processor.                    */
	 PrinterPE = NbPE/2;
	 if (NbPE % 2 != 0) {
	   PrintedElt_i = SIZE/2;
	   PrintedElt_j = LOCAL_SIZE/2;
	 } else {
	   PrintedElt_i = SIZE/2;
	   PrintedElt_j = 0;
	 }

	/* Check if the configuration of the computations and of the run seem coherent.  */
	 if (Me < 0 || NbPE <= 0 || Me >= NbPE) {
	   System.err.println("Fatal Erorr: Bad intialization of the Me and NbPE variables: Me = "+ Me +" NbPE = "+ NbPE);
	   System.exit(0);
	 }
	 if (SIZE/LOCAL_SIZE != NbPE) {
	   System.err.println("PE"+Me+" : Fatal Erorr: "+ NbPE +" process(es) detected and "+SIZE/LOCAL_SIZE+" process(es) required (SIZE/LOCAL_SIZE)!");
	   System.exit(0);
	 }
	}
	
	
	static void LocalMatrixInit()
	{
	 int OffsetA_i, OffsetTB_j;               /* Offset of the local matrix elements */
	 int i, j;                                /* Local matrix indexes                */

	/* Offset of line and column numbers of the element on the processor.            */
	 OffsetA_i = LOCAL_SIZE*Me;
	 OffsetTB_j = LOCAL_SIZE*Me;

	/* Initialization of the local matrix elements                                   */
	 for (i = 0; i < LOCAL_SIZE; i++)
	    for (j = 0; j < SIZE; j++)
	       A[i][j] = (double) ((i+OffsetA_i)*SIZE + j);

	 for (i = 0; i < SIZE; i++)
	    for (j = 0; j < LOCAL_SIZE; j++)
	       TB[j][i] = (double) (i*SIZE + j + OffsetTB_j);

	 for (i = 0; i < SIZE; i++)
	    for (j = 0; j < LOCAL_SIZE; j++)
	       C[i][j] = 0.0;
	}
	

	
  static void PrintResultsAndPerf(double megaflops, double d1, double d2, double d3) {
    if (Me == PrinterPE) {
      System.out.printf(
          "PE%d: Product of two square matrixes of %dx%d doubles:\n", Me, SIZE,
          SIZE);
      System.out
          .printf("\tPE%d: C[%d][%d] = %.2f\n", Me, PrintedElt_i, LOCAL_SIZE
              * Me + PrintedElt_j, (float) C[PrintedElt_i][PrintedElt_j]);
      System.out.printf("\tPE%d: Elapsed time of the loop = %.2f(s)\n", Me,
          (float) d1);
      System.out.printf("\tPE%d: Total Megaflops = %.2f\n", Me,
          (float) megaflops);

      System.out.printf(
          "\n\tPE%d: Total elapsed time of the application = %.2f(s)\n", Me,
          (float) d2);
      System.out.printf(
          "\n\tPE%d: Temps pris pour la decouverte des pairs = %.2f(s)\n", Me,
          (float) d3);
    }
  }
	
	static void ComputationAndCirculation()
	{	 		int i;
    Request request = null;
    Status status = null;
		// Mode bloquant
	  
		for(i = 0 ; i < NbPE - 1 ; i++)
	  {
			MPI.COMM_WORLD.Send(A, OI*LOCAL_SIZE, LOCAL_SIZE, MPI.OBJECT,(Me + 1 + NbPE) % NbPE, 0);
			request = MPI.COMM_WORLD.Irecv(A, NI*LOCAL_SIZE,LOCAL_SIZE, MPI.OBJECT,(Me - 1 + NbPE) % NbPE, 0);
			SeqLocalProduct(i);
			status = request.Wait();
			OI = NI;
			NI = 1-NI;
	  }
		/*Dernier calcul hors de la boucle*/	  SeqLocalProduct(i);
	}


	static void SeqLocalProduct(int step)
	{
		double temp = 0.0;
 		int Offset_A = ((Me+step)*LOCAL_SIZE)%SIZE;
 		for (int i = 0; i < LOCAL_SIZE; i++) {
     for (int j = 0; j < LOCAL_SIZE; j++) {
       temp = 0.0;
       for (int k = 0; k < SIZE; k+=8) {
         temp += A[OI*LOCAL_SIZE+i][k] * TB[j][k] +
                 A[OI*LOCAL_SIZE+i][k+1] * TB[j][k+1] +
                 A[OI*LOCAL_SIZE+i][k+2] * TB[j][k+2] +
                 A[OI*LOCAL_SIZE+i][k+3] * TB[j][k+3] +
                 A[OI*LOCAL_SIZE+i][k+4] * TB[j][k+4] +
                 A[OI*LOCAL_SIZE+i][k+5] * TB[j][k+5] +
                 A[OI*LOCAL_SIZE+i][k+6] * TB[j][k+6] +
                 A[OI*LOCAL_SIZE+i][k+7] * TB[j][k+7] ;
       }
       C[i+Offset_A][j] += temp;
      }
 	  }
  }
	
	
	public static void main(String[] args){
		double td1, tf1; 
    double td2, tf2; 
    double td3, tf3;
    double d1, d2, d3;
    double megaflops;

		// td3 et tf3 pour calculer le temps de découverte des pairs    
    td3 = MPI.Wtime();
    MPI.Init(args); 
    tf3 = MPI.Wtime();

    td2 = MPI.Wtime();
    ProcessorInit();

    LocalMatrixInit();

    if (Me == PrinterPE) {
      System.out.printf("Parallel computation starts\n");
    }
    MPI.COMM_WORLD.Barrier();
    td1 = MPI.Wtime();

    ComputationAndCirculation(); 

    MPI.COMM_WORLD.Barrier();
    tf1 = MPI.Wtime();
    tf2 = MPI.Wtime();

    d1 = (tf1 - td1) * Math.pow(10, -3);
    d2 = (tf2 - td2) * Math.pow(10, -3);
		// durée de la recherche des pairs
    d3 = (tf3 - td3) * Math.pow(10, -3);
    megaflops = (2.0 * Math.pow(SIZE, 3)) / d1 * 1E-6;

    PrintResultsAndPerf(megaflops, d1, d2, d3); 

    MPI.COMM_WORLD.Barrier();
    MPI.Finalize(); 
	}



}
