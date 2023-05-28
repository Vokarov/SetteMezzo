package enums;




public enum Semi {
    spade   (1),
    coppe   (2),
    bastoni (3),
    denari  (4);

    private int valore;

    public int getValore() {
        return valore;
    }


    Semi(int valore) {
        this.valore = valore;
    }

    public static String getSeme(int valore){
        for (Semi seme : Semi.values()) {
            if(seme.getValore()== valore){
                return seme.name();
            }
        }
        return null;
    }


}
