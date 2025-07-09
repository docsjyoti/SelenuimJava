package drivers.strategies;

public class DriverStrategyImplementer {

    public static DriverStrategy chooseStrategy(String strategy){
        switch (strategy) {
            case "Chrome":
                return new Chrome();
            default:
                return null;
        }
    }
}
