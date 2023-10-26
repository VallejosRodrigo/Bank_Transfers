import java.util.concurrent.locks.*;


public class Bank_center
{
    public Bank_center(){

        accountBank = new double[100]; //100 bank accounts

        for(int i = 0; i<100 ;i++){  //Add 4000 to each account
            accountBank[i] = 4000;
        }

        enoughMoney = closingBank.newCondition(); //evaluate the balance

    }

    public void transactions(int from, int to, double amount) throws InterruptedException{

        closingBank.lock(); //blocks the entire method so that only one thread of execution enters at a time

        try
        {
            while(accountBank[from] < amount)
            {
                enoughMoney.await(); //Puts the thread on hold
                System.out.println(accountBank[from]);
            }
            System.out.println(Thread.currentThread()); //print thread

            accountBank[from] -= amount; //rest money source account
            System.out.printf("Amount:%10.2f   From-> [%d] To-> [%d]", amount, from, to);
            accountBank[to] += amount; //add money destination account
            System.out.println("   Money Source Account: "+accountBank[from]+"  and Money Destination Account: "+accountBank[to]);
            System.out.printf(" Total Money:  %10.2f%n", getTotalMoney());

            enoughMoney.signalAll(); //wakes up all waiting threads

        }finally {
            closingBank.unlock(); //Releases the method allowing waiting threads to enter
        }
    }

    public double getTotalMoney(){
        double acc = 0;

        for (double i: accountBank){
            acc += i;
        }
        return acc;
    }


    private final double[] accountBank;

    private Lock closingBank = new ReentrantLock();

    private double amount;

    private Condition enoughMoney;


}
