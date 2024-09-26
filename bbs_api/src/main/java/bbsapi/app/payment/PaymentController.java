package bbsapi.app.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bbsapi.domain.model.ChargeReponse;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

	@Autowired
    StripeClient stripeClient;


    @RequestMapping(value="/charge", method=RequestMethod.POST, produces = "application/json")
    public ChargeReponse chargeCard(@RequestParam String token, @RequestParam Integer amount) throws Exception{

    	ChargeReponse chargeReponse = new ChargeReponse(stripeClient.chargeCreditCard(token, amount), amount);
        return chargeReponse;
    }
    
   
}