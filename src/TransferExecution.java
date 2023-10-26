public class TransferExecution implements Runnable
{
    public TransferExecution(Bank_center b, int from, double max){
        bank_center = b;
        sourceAccount = from;
        maximumAmount = max;


    }
    @Override
    public void run()
    {
        try
        {
            while(true){ //infinite loop

                int toAccount = (int)(Math.random()*100); //random account number
                double almost = maximumAmount*Math.random(); //random figure $$
                bank_center.transactions(sourceAccount, toAccount, almost);

                Thread.sleep(100);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();}
    }

    private Bank_center bank_center;
    private int sourceAccount;
    private double maximumAmount;

}
