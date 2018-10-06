package ru.pochtabank.model;

public class BankRegistry {
    //лицевой счет
    private String personalAccount;
    //период оплаты
    private String paymentPeriod;
    //сумма начислений
    private float chargesAmount;

    public BankRegistry(ClientRegistry clientRegistry) {
        this.personalAccount = clientRegistry.getKod();
        this.paymentPeriod = clientRegistry.getMesOpl();
        this.chargesAmount = clientRegistry.getSumn() + clientRegistry.getPeni() + clientRegistry.getSumd();
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        if (!paymentPeriod.matches("^[0-9]*${1,30}"))
            throw new IllegalArgumentException("Personal account have to be from 1 till 30 length");
        this.personalAccount = personalAccount;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        if (!paymentPeriod.matches("^[0-9]{4}$"))
            throw new IllegalArgumentException("Bad payment period format. Must be MMYY");
        this.paymentPeriod = paymentPeriod;
    }

    public float getChargesAmount() {
        return chargesAmount;
    }

    public void setChargesAmount(float chargesAmount) {
        this.chargesAmount = chargesAmount;
    }
}
