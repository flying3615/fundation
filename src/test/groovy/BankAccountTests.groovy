import groovy.mock.interceptor.MockFor
import groovy.mock.interceptor.StubFor

/**
 * Created by liuyufei on 8/12/16.
 */
class BankAccountTests extends GroovyTestCase{


    private BankAccount account

    def void setUp(){
        account = new BankAccount(10)
        println 'in setup'
    }

    def void tearDown(){
        account = null
        println 'in tearDown'
    }


    def void testCanDepositMoney(){
        account.deposit(5)
        assert 15 == account.balance
    }

    def void testCanWithdrawMoney(){
        account.withdraw(5)
        assert 5 == account.balance
    }


    def void testCanNotWithdrawMoreMoneyThanBalance(){
        shouldFail(InsufficientFundsException){
            account.withdraw(15)
        }
    }

    def void testCanAccureInterest(){

        //create stub for InterestRateService value
        //stub doesn't check getInterestRate invocation
//        def service = new StubFor(InterestRateService)

        //we create mock for InterestRateService
        //better than stub, mock will check getInterestRate's invocation
        def service = new MockFor(InterestRateService)
        service.demand.getInterestRate{
            return 0.10
        }
        service.use {
            account.accrueInterest()
            assert 11 == account.balance
        }

    }
}
