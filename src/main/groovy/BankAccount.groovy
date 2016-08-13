/**
 * Created by liuyufei on 8/12/16.
 */
class BankAccount {

    private balance

    BankAccount(openingBalance) {
        balance = openingBalance
    }

    void deposit(amount) {
        balance += amount
    }

    def void withdraw(amount) {
        if(amount>balance){
            throw new InsufficientFundsException()
        }
        balance -= amount
    }

    def void accrueInterest(){
        def service = new InterestRateService()
        def rate = service.getInterestRate()

        def accuredInterest = balance*rate

        deposit(accuredInterest)

    }
}
