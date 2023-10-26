//TODO: This program is to exercise the threads. In this case, 100 bank accounts are created which carry
// out transactions with each other infinitely, maintaining a total amount of: 400,000.

public class Main {

    public static void main(String[] args) {

    Bank_center bank_center = new Bank_center();
    for(int i=0; i<100; i++){
        TransferExecution transferExecution = new TransferExecution(bank_center,i,4000);
        Thread thread = new Thread(transferExecution);
        thread.start();

    }


    }
}
