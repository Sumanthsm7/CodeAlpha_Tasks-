import java.util.*;

public class StockTradingPlatformConsole {
    static class Stock {
        String symbol;
        double price;
        Stock(String symbol, double price) { this.symbol = symbol; this.price = price; }
    }

    static class PortfolioItem {
        String symbol;
        int quantity;
        PortfolioItem(String symbol, int quantity) { this.symbol = symbol; this.quantity = quantity; }
    }

    private static ArrayList<Stock> market = new ArrayList<>();
    private static ArrayList<PortfolioItem> portfolio = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initMarket();
        int choice;
        do {
            System.out.println("\n=== STOCK TRADING PLATFORM ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getInt();

            switch (choice) {
                case 1 -> showMarket();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> showPortfolio();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void initMarket() {
        market.add(new Stock("AAPL", 190.25));
        market.add(new Stock("TSLA", 245.60));
        market.add(new Stock("GOOG", 130.40));
        market.add(new Stock("AMZN", 155.10));
    }

    static void showMarket() {
        System.out.println("\nSymbol\tPrice");
        for (Stock s : market)
            System.out.printf("%s\t%.2f%n", s.symbol, s.price);
    }

    static void buyStock() {
        System.out.print("Enter stock symbol: ");
        String sym = sc.nextLine().toUpperCase();
        Stock s = findStock(sym);
        if (s == null) { System.out.println("Not found."); return; }
        System.out.print("Enter quantity: ");
        int qty = getInt();
        if (qty <= 0) { System.out.println("Invalid quantity."); return; }

        portfolio.add(new PortfolioItem(sym, qty));
        System.out.printf("Bought %d shares of %s at %.2f%n", qty, sym, s.price);
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String sym = sc.nextLine().toUpperCase();
        PortfolioItem item = portfolio.stream().filter(p -> p.symbol.equals(sym)).findFirst().orElse(null);
        if (item == null) { System.out.println("Not in portfolio."); return; }
        System.out.print("Enter quantity to sell: ");
        int qty = getInt();
        if (qty >= item.quantity) {
            portfolio.remove(item);
            System.out.println("Sold all shares of " + sym);
        } else {
            item.quantity -= qty;
            System.out.println("Sold " + qty + " shares of " + sym);
        }
    }

    static void showPortfolio() {
        if (portfolio.isEmpty()) { System.out.println("Portfolio empty."); return; }
        System.out.println("\nSymbol\tQuantity");
        for (PortfolioItem p : portfolio)
            System.out.printf("%s\t%d%n", p.symbol, p.quantity);
    }

    static Stock findStock(String sym) {
        for (Stock s : market)
            if (s.symbol.equals(sym)) return s;
        return null;
    }

    static int getInt() {
        while (true) try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { System.out.print("Enter number: "); }
    }
}