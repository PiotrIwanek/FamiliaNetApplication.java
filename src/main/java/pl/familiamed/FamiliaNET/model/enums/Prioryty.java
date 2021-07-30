package pl.familiamed.FamiliaNET.model.enums;

public enum Prioryty {

    LOW , MEDIUM , HIGH;


    public static  Prioryty of (String string ){
        switch (string){
            case "low":
                return LOW;
            case "medium":
                return MEDIUM;
            case "high":
                return HIGH;
            default:
                return LOW;
        }
    }
}
