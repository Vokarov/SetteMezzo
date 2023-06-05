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

    public static String getSemeName(int valore){
        for (Semi seme : Semi.values()) {
            if(seme.getValore()== valore){
                return seme.name();
            }
        }
        return null;
    }
    public static Semi getSeme(int valore){
        for (Semi seme : Semi.values()) {
            if(seme.getValore()== valore){
                return seme;
            }
        }
        return null;
    }



}
