package ru.pochtabank.model;

public class ClientRegistry {
    //Признак предприятия
    private String tsg;
    //Вид услуги
    private String vu;
    //Сумма начисления
    private float sumn;
    //Пенни
    private float peni;
    //Сумма задолженности
    private float sumd;
    //Месяц оплаты
    private String mesOpl;
    //Код счета
    private String predpr;
    //код ЖЭУ
    private String geu;
    //Лицевой счет
    private String kod;
    //Литера
    private char kodls;
    //Адрес
    private String adr;
    //Контрольное число
    private String kc;
    //Код платежа
    private String rsoCgar;

    public ClientRegistry() {
    }

    public ClientRegistry(String kod, String mesOpl, float sumn, float peni, float sumd) {
        this.sumn = sumn;
        this.peni = peni;
        this.sumd = sumd;
        this.mesOpl = mesOpl;
        this.kod = kod;
    }

    public ClientRegistry(String tsg, String vu, float sumn, float peni, float sumd, String mesOpl,
                          String predpr, String geu, String kod, char kodls, String adr, String kc, String rsoCgar) {
        this.tsg = tsg;
        this.vu = vu;
        this.sumn = sumn;
        this.peni = peni;
        this.sumd = sumd;
        this.mesOpl = mesOpl;
        this.predpr = predpr;
        this.geu = geu;
        this.kod = kod;
        this.kodls = kodls;
        this.adr = adr;
        this.kc = kc;
        this.rsoCgar = rsoCgar;
    }

    public String getTsg() {
        return tsg;
    }

    public void setTsg(String tsg) {
        this.tsg = tsg;
    }

    public String getVu() {
        return vu;
    }

    public void setVu(String vu) {
        this.vu = vu;
    }

    public float getSumn() {
        return sumn;
    }

    public void setSumn(float sumn) {
        this.sumn = sumn;
    }

    public float getPeni() {
        return peni;
    }

    public void setPeni(float peni) {
        this.peni = peni;
    }

    public float getSumd() {
        return sumd;
    }

    public void setSumd(float sumd) {
        this.sumd = sumd;
    }

    public String getMesOpl() {
        return mesOpl;
    }

    public void setMesOpl(String mesOpl) {
        this.mesOpl = mesOpl;
    }

    public String getPredpr() {
        return predpr;
    }

    public void setPredpr(String predpr) {
        this.predpr = predpr;
    }

    public String getGeu() {
        return geu;
    }

    public void setGeu(String geu) {
        this.geu = geu;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public char getKodls() {
        return kodls;
    }

    public void setKodls(char kodls) {
        this.kodls = kodls;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getKc() {
        return kc;
    }

    public void setKc(String kc) {
        this.kc = kc;
    }

    public String getRsoCgar() {
        return rsoCgar;
    }

    public void setRsoCgar(String rsoCgar) {
        this.rsoCgar = rsoCgar;
    }

}